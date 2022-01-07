package teamdraco.finsandstails.common.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import teamdraco.finsandstails.common.entities.util.GroundAndSwimmerNavigator;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;
import teamdraco.finsandstails.registry.FtSounds;

import java.util.function.Predicate;

public class RubberBellyGliderEntity extends AnimalEntity {
    private static final DataParameter<Boolean> PUFFED = EntityDataManager.defineId(RubberBellyGliderEntity.class, DataSerializers.BOOLEAN);
    private static final EntitySize PUFFED_SIZE = EntitySize.scalable(0.7f, 0.5f);
    private int puffTimer;
    private static final Predicate<Entity> ENEMY_MATCHER = (entity) -> {
        if (entity instanceof PlayerEntity) {
            return !((PlayerEntity) entity).isCreative() && !entity.isSpectator();
        } else {
            return entity instanceof OrnateBugfishEntity || isEntityPrey(entity);
        }
    };

    public RubberBellyGliderEntity(EntityType<? extends RubberBellyGliderEntity> type, World world) {
        super(type, world);
        this.moveControl = new RubberBellyGliderEntity.MoveHelperController(this);
        this.lookControl = new DolphinLookController(this, 10);
        this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
        this.maxUpStep = 1.0f;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new RubberBellyGliderEntity.PuffGoal(this));
        this.goalSelector.addGoal(0, new FindWaterGoal(this));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, OrnateBugfishEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
        this.goalSelector.addGoal(1, new BreedGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 1) {
            @Override
            public boolean canUse() {
                return super.canUse() && isInWater();
            }
        });
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1.0D, 15) {
            @Override
            public boolean canUse() {
                return !this.mob.isInWater() && super.canUse();
            }
        });
        this.goalSelector.addGoal(3, new GliderJumpGoal(this));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, SpindlyGemCrabEntity.class, 90, true, false, null));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MobEntity.class, 10, true, false, RubberBellyGliderEntity::isEntityPrey));
    }

    public static AttributeModifierMap.MutableAttribute registerRBGAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 1).add(Attributes.MAX_HEALTH, 25).add(Attributes.MOVEMENT_SPEED, 0.12);
    }

    private static boolean isEntityPrey(Entity entity) {
        return entity instanceof SquidEntity || entity instanceof BandedRedbackShrimpEntity || entity instanceof WhiteBullCrabEntity|| entity instanceof RedBullCrabEntity;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.WATER;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        setAirSupply(300);
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean canBeLeashed(PlayerEntity player) {
        return true;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PUFFED, false);
    }

    public boolean isPuffed() {
        return this.entityData.get(PUFFED);
    }

    public void setPuffed(boolean p_203714_1_) {
        this.entityData.set(PUFFED, p_203714_1_);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Puffed", this.isPuffed());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setPuffed(compound.getBoolean("Puffed"));
    }

    @Override
    public void onSyncedDataUpdated(DataParameter<?> key) {
        if (PUFFED.equals(key)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(key);
    }

    @Override
    protected PathNavigator createNavigation(World worldIn) {
        return new GroundAndSwimmerNavigator(this, level);
    }

    @Override
    public void travel(Vector3d travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }
    }

    @Override
    public int getAmbientSoundInterval() {
        return 500;
    }

    @Override
    public void tick() {
        super.tick();
        if (isPuffed() && ++puffTimer >= 100) {
            playSound(SoundEvents.PUFFER_FISH_BLOW_OUT, getSoundVolume(), getVoicePitch());
            setPuffed(false);
            puffTimer = 0;
        }
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FTItems.RUBBER_BELLY_GLIDER_SPAWN_EGG.get());
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        return FTEntities.RUBBER_BELLY_GLIDER.get().create(level);
    }

    @Override
    public void playerTouch(PlayerEntity entityIn) {
        if (entityIn instanceof ServerPlayerEntity && isPuffed()) {
            entityIn.hurt(DamageSource.mobAttack(this), 2);
            entityIn.addEffect(new EffectInstance(Effects.POISON, 200, 0));
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FtSounds.RUBBER_BELLY_GLIDER_AMBIENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FtSounds.RUBBER_BELLY_GLIDER_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FtSounds.RUBBER_BELLY_GLIDER_HURT.get();
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return isBaby() ? 0.05f : 0.15f;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    public EntitySize getDimensions(Pose poseIn) {
        return isPuffed() ? PUFFED_SIZE : super.getDimensions(poseIn);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == FTItems.AMBER_SPINDLY_GEM_CRAB.get() || stack.getItem() == FTItems.RUBY_SPINDLY_GEM_CRAB.get() || stack.getItem() == FTItems.EMERALD_SPINDLY_GEM_CRAB.get() || stack.getItem() == FTItems.SAPPHIRE_SPINDLY_GEM_CRAB.get() || stack.getItem() == FTItems.PEARL_SPINDLY_GEM_CRAB.get();
    }

    static class PuffGoal extends Goal {
        private final RubberBellyGliderEntity glider;

        public PuffGoal(RubberBellyGliderEntity glider) {
            this.glider = glider;
        }

        @Override
        public boolean canUse() {
            return glider.isInWater() && !glider.isPuffed() && !glider.level.getEntities(glider, this.glider.getBoundingBox().inflate(2.5D), RubberBellyGliderEntity.ENEMY_MATCHER).isEmpty();
        }

        @Override
        public void start() {
            glider.playSound(SoundEvents.PUFFER_FISH_BLOW_UP, glider.getSoundVolume(), glider.getVoicePitch());
            glider.setPuffed(true);
        }
    }

    public static class GliderJumpGoal extends JumpGoal {
        private static final int[] JUMP_DISTANCES = new int[]{0, 1, 4, 5, 6, 7};
        private final RubberBellyGliderEntity glider;
        private boolean inWater;

        public GliderJumpGoal(RubberBellyGliderEntity glider) {
            this.glider = glider;
        }

        @Override
        public boolean canUse() {
            if (this.glider.getRandom().nextInt(50) != 0) {
                return false;
            } else {
                Direction direction = this.glider.getMotionDirection();
                int i = direction.getStepX();
                int j = direction.getStepZ();
                BlockPos blockpos = this.glider.blockPosition();

                for(int k : JUMP_DISTANCES) {
                    if (!this.canJumpTo(blockpos, i, j, k) || !this.isAirAbove(blockpos, i, j, k)) {
                        return false;
                    }
                }

                return true;
            }
        }

        private boolean canJumpTo(BlockPos pos, int dx, int dz, int scale) {
            BlockPos blockpos = pos.offset(dx * scale, 0, dz * scale);
            return this.glider.level.getFluidState(blockpos).is(FluidTags.WATER) && !this.glider.level.getBlockState(blockpos).getMaterial().blocksMotion();
        }

        private boolean isAirAbove(BlockPos pos, int dx, int dz, int scale) {
            return this.glider.level.getBlockState(pos.offset(dx * scale, 1, dz * scale)).isAir() && this.glider.level.getBlockState(pos.offset(dx * scale, 2, dz * scale)).isAir();
        }

        @Override
        public boolean canContinueToUse() {
            double d0 = this.glider.getDeltaMovement().y;
            return (!(d0 * d0 < (double)0.03F) || this.glider.xRot == 0.0F || !(Math.abs(this.glider.xRot) < 10.0F) || !this.glider.isInWater()) && !this.glider.isOnGround();
        }

        @Override
        public boolean isInterruptable() {
            return false;
        }

        @Override
        public void start() {
            Direction direction = this.glider.getMotionDirection();
            this.glider.setDeltaMovement(this.glider.getDeltaMovement().add((double)direction.getStepX() * 0.6D, 0.7D, (double)direction.getStepZ() * 0.6D));
            this.glider.getNavigation().stop();
        }

        @Override
        public void stop() {
            this.glider.xRot = 0.0F;
        }

        @Override
        public void tick() {
            boolean flag = this.inWater;
            if (!flag) {
                FluidState fluidstate = this.glider.level.getFluidState(this.glider.blockPosition());
                this.inWater = fluidstate.is(FluidTags.WATER);
            }

            if (this.inWater && !flag) {
                this.glider.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
            }

            Vector3d vector3d = this.glider.getDeltaMovement();
            if (vector3d.y * vector3d.y < (double)0.03F && this.glider.xRot != 0.0F) {
                this.glider.xRot = MathHelper.rotlerp(this.glider.xRot, 0.0F, 0.2F);
            } else {
                double d0 = Math.sqrt(Entity.getHorizontalDistanceSqr(vector3d));
                double d1 = Math.signum(-vector3d.y) * Math.acos(d0 / vector3d.length()) * (double)(180F / (float)Math.PI);
                this.glider.xRot = (float)d1;
            }
        }
    }

    static class MoveHelperController extends MovementController {
        private final RubberBellyGliderEntity glider;

        MoveHelperController(RubberBellyGliderEntity glider) {
            super(glider);
            this.glider = glider;
        }

        private void updateSpeed() {
            if (this.glider.isInWater()) {
                this.glider.setDeltaMovement(this.glider.getDeltaMovement().add(0.0D, 0.005D, 0.0D));

                if (this.glider.isBaby()) {
                    this.glider.setSpeed(Math.max(this.glider.getSpeed() / 3.0F, 0.06F));
                }
            }
            else if (this.glider.onGround) {
                this.glider.setSpeed(Math.max(this.glider.getSpeed(), 0.06F));
            }
        }

        public void tick() {
            this.updateSpeed();
            if (this.operation == MovementController.Action.MOVE_TO && !this.glider.getNavigation().isDone()) {
                double d0 = this.wantedX - this.glider.getX();
                double d1 = this.wantedY - this.glider.getY();
                double d2 = this.wantedZ - this.glider.getZ();
                double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.glider.yRot = this.rotlerp(this.glider.yRot, f, 90.0F);
                this.glider.yBodyRot = this.glider.yRot;
                float f1 = (float)(this.speedModifier * this.glider.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.glider.setSpeed(MathHelper.lerp(0.125F, this.glider.getSpeed(), f1));
                this.glider.setDeltaMovement(this.glider.getDeltaMovement().add(0.0D, (double)this.glider.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.glider.setSpeed(0.0F);
            }
        }
    }
}