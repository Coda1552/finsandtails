package mod.coda.fins.entity;

import mod.coda.fins.init.FinsEntities;
import mod.coda.fins.init.FinsSounds;
import mod.coda.fins.pathfinding.GroundAndSwimmerNavigator;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.function.Predicate;

public class RubberBellyGliderEntity extends AnimalEntity {
    private static final DataParameter<Boolean> PUFFED = EntityDataManager.createKey(RubberBellyGliderEntity.class, DataSerializers.BOOLEAN);
    private int puffTimer;

    private static final Predicate<Entity> ENEMY_MATCHER = (entity) -> {
        if (!(entity instanceof LivingEntity)) {
            return false;
        } else {
            if (entity instanceof PlayerEntity) {
                return !((PlayerEntity) entity).isCreative() && !entity.isSpectator();
            }

            return entity instanceof OrnateBugfishEntity || isEntityPrey(entity);
        }
    };

    public RubberBellyGliderEntity(EntityType<? extends RubberBellyGliderEntity> type, World world) {
        super(type, world);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.moveController = new RubberBellyGliderEntity.MoveHelperController(this);
    }

    private static boolean isEntityPrey(Entity entity) {
        return entity instanceof SquidEntity || entity instanceof BandedRedbackShrimpEntity;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.WATER;
    }

    public boolean isNotColliding(IWorldReader worldIn) {
        return worldIn.checkNoEntityCollision(this);
    }

    public void baseTick() {
        super.baseTick();
        setAir(300);
    }

    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public boolean canBeLeashedTo(PlayerEntity player) {
        return !this.getLeashed() && !isInWater();
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(PUFFED, false);
    }

    public boolean isPuffed() {
        return this.dataManager.get(PUFFED);
    }

    public void setPuffed(boolean p_203714_1_) {
        this.dataManager.set(PUFFED, p_203714_1_);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Puffed", this.isPuffed());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setPuffed(compound.getBoolean("Puffed"));
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        if (PUFFED.equals(key)) {
            this.recalculateSize();
        }

        super.notifyDataManagerChange(key);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FindWaterGoal(this));
        this.goalSelector.addGoal(1, new RubberBellyGliderEntity.PuffGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 1.0D, 40));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new GliderJumpGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NOT_SPECTATING::test));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, OrnateBugfishEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NOT_SPECTATING::test));

        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, MobEntity.class, 10, true, false, RubberBellyGliderEntity::isEntityPrey));
        this.targetSelector.addGoal(0, new MeleeAttackGoal(this, 0.3, false));
    }

    public static AttributeModifierMap.MutableAttribute registerRBGAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.ATTACK_DAMAGE, 1).createMutableAttribute(Attributes.MAX_HEALTH, 25).createMutableAttribute(Attributes.MOVEMENT_SPEED, 1.6);
    }

    protected PathNavigator createNavigator(World worldIn) {
        return new GroundAndSwimmerNavigator(this, worldIn);
    }

    public void travel(Vector3d p_213352_1_) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(0.01F, p_213352_1_);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.9D));
            if (this.getAttackTarget() == null) {
                this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(p_213352_1_);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (isPuffed() && ++puffTimer >= 200) {
            playSound(SoundEvents.ENTITY_PUFFER_FISH_BLOW_OUT, getSoundVolume(), getSoundPitch());
            setPuffed(false);
            puffTimer = 0;
        }
    }

    /*@Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.RUBBER_BELLY_GLIDER_SPAWN_EGG.get());
    }*/

    @Override
    public AgeableEntity func_241840_a(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        RubberBellyGliderEntity entity = new RubberBellyGliderEntity(FinsEntities.RUBBER_BELLY_GLIDER.get(), this.world);
        entity.onInitialSpawn(serverWorld, this.world.getDifficultyForLocation(entity.getPosition()), SpawnReason.BREEDING, null, null);
        return entity;
    }

    public void onCollideWithPlayer(PlayerEntity entityIn) {
        if (ENEMY_MATCHER.test(entityIn) && this.isPuffed() && entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 3))) {
            entityIn.addPotionEffect(new EffectInstance(Effects.POISON, 60, 0));
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        Entity immediateSource = source.getImmediateSource();
        boolean attacked = super.attackEntityFrom(source, amount);
        if (immediateSource != null && isPuffed() && ENEMY_MATCHER.test(immediateSource)) {
            if (attacked) {
                immediateSource.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 2));
                return true;
            } else {
                return false;
            }
        }
        return attacked;
    }

    protected SoundEvent getAmbientSound() {
        return FinsSounds.RUBBER_BELLY_GLIDER_AMBIENT.get();
    }

    protected SoundEvent getDeathSound() {
        return FinsSounds.RUBBER_BELLY_GLIDER_DEATH.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FinsSounds.RUBBER_BELLY_GLIDER_AMBIENT.get();
    }

    protected float getSoundVolume() {
        return 0.4f;
    }

    static class PuffGoal extends Goal {
        private final RubberBellyGliderEntity glider;

        public PuffGoal(RubberBellyGliderEntity glider) {
            this.glider = glider;
        }

        public boolean shouldExecute() {
            return !glider.isPuffed() && !glider.world.getEntitiesInAABBexcluding(glider, this.glider.getBoundingBox().grow(2.0D), RubberBellyGliderEntity.ENEMY_MATCHER).isEmpty();
        }

        public void startExecuting() {
            glider.playSound(SoundEvents.ENTITY_PUFFER_FISH_BLOW_UP, glider.getSoundVolume(), glider.getSoundPitch());
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

        public boolean shouldExecute() {
            if (this.glider.getRNG().nextInt(50) != 0) {
                return false;
            } else {
                Direction direction = this.glider.getAdjustedHorizontalFacing();
                int i = direction.getXOffset();
                int j = direction.getZOffset();
                BlockPos blockpos = this.glider.getPosition();

                for(int k : JUMP_DISTANCES) {
                    if (!this.canJumpTo(blockpos, i, j, k) || !this.isAirAbove(blockpos, i, j, k)) {
                        return false;
                    }
                }

                return true;
            }
        }

        private boolean canJumpTo(BlockPos pos, int dx, int dz, int scale) {
            BlockPos blockpos = pos.add(dx * scale, 0, dz * scale);
            return this.glider.world.getFluidState(blockpos).isTagged(FluidTags.WATER) && !this.glider.world.getBlockState(blockpos).getMaterial().blocksMovement();
        }

        private boolean isAirAbove(BlockPos pos, int dx, int dz, int scale) {
            return this.glider.world.getBlockState(pos.add(dx * scale, 1, dz * scale)).isAir() && this.glider.world.getBlockState(pos.add(dx * scale, 2, dz * scale)).isAir();
        }

        public boolean shouldContinueExecuting() {
            double d0 = this.glider.getMotion().y;
            return (!(d0 * d0 < (double)0.03F) || this.glider.rotationPitch == 0.0F || !(Math.abs(this.glider.rotationPitch) < 10.0F) || !this.glider.isInWater()) && !this.glider.isOnGround();
        }

        public boolean isPreemptible() {
            return false;
        }

        public void startExecuting() {
            Direction direction = this.glider.getAdjustedHorizontalFacing();
            this.glider.setMotion(this.glider.getMotion().add((double)direction.getXOffset() * 0.6D, 0.7D, (double)direction.getZOffset() * 0.6D));
            this.glider.getNavigator().clearPath();
        }

        public void resetTask() {
            this.glider.rotationPitch = 0.0F;
        }

        public void tick() {
            boolean flag = this.inWater;
            if (!flag) {
                FluidState fluidstate = this.glider.world.getFluidState(this.glider.getPosition());
                this.inWater = fluidstate.isTagged(FluidTags.WATER);
            }

            if (this.inWater && !flag) {
                this.glider.playSound(SoundEvents.ENTITY_DOLPHIN_JUMP, 1.0F, 1.0F);
            }

            Vector3d vector3d = this.glider.getMotion();
            if (vector3d.y * vector3d.y < (double)0.03F && this.glider.rotationPitch != 0.0F) {
                this.glider.rotationPitch = MathHelper.rotLerp(this.glider.rotationPitch, 0.0F, 0.2F);
            } else {
                double d0 = Math.sqrt(Entity.horizontalMag(vector3d));
                double d1 = Math.signum(-vector3d.y) * Math.acos(d0 / vector3d.length()) * (double)(180F / (float)Math.PI);
                this.glider.rotationPitch = (float)d1;
            }
        }
    }

    static class MoveHelperController extends MovementController {
        private final RubberBellyGliderEntity glider;

        MoveHelperController(RubberBellyGliderEntity glider) {
            super(glider);
            this.glider = glider;
        }

        public void tick() {
            if (this.glider.areEyesInFluid(FluidTags.WATER)) {
                this.glider.setMotion(this.glider.getMotion().add(0.0D, 0.005D, 0.0D));
            }

            if (this.action == MovementController.Action.MOVE_TO && !this.glider.getNavigator().noPath()) {
                double d0 = this.posX - this.glider.getPosX();
                double d1 = this.posY - this.glider.getPosY();
                double d2 = this.posZ - this.glider.getPosZ();
                double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.glider.rotationYaw = this.limitAngle(this.glider.rotationYaw, f, 90.0F);
                this.glider.renderYawOffset = this.glider.rotationYaw;
                float f1 = (float)(this.speed * this.glider.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                this.glider.setAIMoveSpeed(MathHelper.lerp(0.125F, this.glider.getAIMoveSpeed(), f1));
                this.glider.setMotion(this.glider.getMotion().add(0.0D, (double)this.glider.getAIMoveSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.glider.setAIMoveSpeed(0.0F);
            }
        }
    }
}
