package teamdraco.finsandstails.common.entities;

import coda.dracoshoard.common.entities.ai.GroundAndSwimmerNavigator;
import coda.dracoshoard.common.entities.goals.WaterJumpGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;
import teamdraco.finsandstails.registry.FtSounds;

import java.util.function.Predicate;

public class RubberBellyGliderEntity extends Animal {
    private static final EntityDataAccessor<Boolean> PUFFED = SynchedEntityData.defineId(RubberBellyGliderEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDimensions PUFFED_SIZE = EntityDimensions.scalable(0.7f, 0.5f);
    private int puffTimer;
    private static final Predicate<Entity> ENEMY_MATCHER = (entity) -> {
        if (entity instanceof Player) {
            return !((Player) entity).isCreative() && !entity.isSpectator();
        } else {
            return entity instanceof OrnateBugfishEntity || isEntityPrey(entity);
        }
    };

    public RubberBellyGliderEntity(EntityType<? extends RubberBellyGliderEntity> type, Level world) {
        super(type, world);
        this.moveControl = new MoveHelperController(this);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.maxUpStep = 1.0f;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new RubberBellyGliderEntity.PuffGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, OrnateBugfishEntity.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(1, new BreedGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 1) {
            @Override
            public boolean canUse() {
                return super.canUse() && isInWater();
            }
        });
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D, 15) {
            @Override
            public boolean canUse() {
                return !this.mob.isInWater() && super.canUse();
            }
        });
        this.goalSelector.addGoal(3, new WaterJumpGoal(this, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, SpindlyGemCrabEntity.class, 90, true, false, null));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, RubberBellyGliderEntity::isEntityPrey));
    }

    public static AttributeSupplier.Builder registerRBGAttributes() {
        return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 1).add(Attributes.MAX_HEALTH, 25).add(Attributes.MOVEMENT_SPEED, 0.12);
    }

    private static boolean isEntityPrey(Entity entity) {
        return entity instanceof Squid || entity instanceof BandedRedbackShrimpEntity || entity instanceof WhiteBullCrabEntity|| entity instanceof RedBullCrabEntity;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public MobType getMobType() {
        return MobType.WATER;
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
    public boolean canBeLeashed(Player player) {
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
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Puffed", this.isPuffed());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setPuffed(compound.getBoolean("Puffed"));
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (PUFFED.equals(key)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(key);
    }

    @Override
    protected PathNavigation createNavigation(Level worldIn) {
        return new GroundAndSwimmerNavigator(this, level);
    }

    @Override
    public void travel(Vec3 travelVector) {
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
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(FTItems.RUBBER_BELLY_GLIDER_SPAWN_EGG.get());
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageableEntity) {
        return FTEntities.RUBBER_BELLY_GLIDER.get().create(level);
    }

    @Override
    public void playerTouch(Player entityIn) {
        if (entityIn instanceof ServerPlayer && isPuffed()) {
            entityIn.hurt(DamageSource.mobAttack(this), 2);
            entityIn.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
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
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return isBaby() ? 0.05f : 0.15f;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
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

    static class MoveHelperController extends MoveControl {
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
            if (this.operation == MoveControl.Operation.MOVE_TO && !this.glider.getNavigation().isDone()) {
                double d0 = this.wantedX - this.glider.getX();
                double d1 = this.wantedY - this.glider.getY();
                double d2 = this.wantedZ - this.glider.getZ();
                double d3 = Mth.sqrt((float) (d0 * d0 + d1 * d1 + d2 * d2));
                d1 = d1 / d3;
                float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.glider.yRot = this.rotlerp(this.glider.yRot, f, 90.0F);
                this.glider.yBodyRot = this.glider.yRot;
                float f1 = (float)(this.speedModifier * this.glider.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.glider.setSpeed(Mth.lerp(0.125F, this.glider.getSpeed(), f1));
                this.glider.setDeltaMovement(this.glider.getDeltaMovement().add(0.0D, (double)this.glider.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.glider.setSpeed(0.0F);
            }
        }
    }
}