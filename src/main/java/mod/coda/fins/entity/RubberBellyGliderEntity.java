/*
package mod.coda.fins.entity;

import mod.coda.fins.init.FinsEntities;
import mod.coda.fins.init.FinsSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class RubberBellyGliderEntity extends AnimalEntity {
    private static final DataParameter<Integer> PUFF_STATE = EntityDataManager.createKey(RubberBellyGliderEntity.class, DataSerializers.VARINT);
    private int puffTimer;
    private int deflateTimer;
    private static final Predicate<LivingEntity> ENEMY_MATCHER = (entity) -> {
        if (entity == null) {
            return false;
        }
        else if (!(entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative()) && !entity.isSpectator()) {
            return true;
        }
        else if (entity instanceof OrnateBugfishEntity) {
            return true;
        }
        else {
            return false;
        }
    };

    public RubberBellyGliderEntity(EntityType<? extends RubberBellyGliderEntity> type, World world) {
        super(type, world);
        this.moveController = new RubberBellyGliderEntity.MoveHelperController(this);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.WATER;
    }

    protected void updateAir(int p_209207_1_) {
        if (this.isAlive() && !this.isInWaterOrBubbleColumn()) {
            this.setAir(p_209207_1_ - 1);
            if (this.getAir() == -20) {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.DROWN, 2.0F);
            }
        } else {
            this.setAir(300);
        }
    }

    public void baseTick() {
        int i = this.getAir();
        super.baseTick();
        this.updateAir(i);
    }

    public boolean isPushedByWater() {
        return false;
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(PUFF_STATE, 0);
    }

    public int getPuffState() {
        return this.dataManager.get(PUFF_STATE);
    }

    public void setPuffState(int p_203714_1_) {
        this.dataManager.set(PUFF_STATE, p_203714_1_);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("PuffState", this.getPuffState());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setPuffState(compound.getInt("PuffState"));
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        if (PUFF_STATE.equals(key)) {
            this.recalculateSize();
        }

        super.notifyDataManagerChange(key);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FindWaterGoal(this));
        this.goalSelector.addGoal(1, new RubberBellyGliderEntity.PuffGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(3, new RubberBellyGliderEntity.SwimGoal(this));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)1.6F);
    }

    protected PathNavigator createNavigator(World worldIn) {
        return new SwimmerPathNavigator(this, worldIn);
    }

    public void travel(Vec3d p_213352_1_) {
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

    public void tick() {
        if (!this.world.isRemote && this.isAlive() && this.isServerWorld()) {
            if (this.puffTimer > 0) {
                if (this.getPuffState() == 0) {
                    this.playSound(SoundEvents.ENTITY_PUFFER_FISH_BLOW_UP, this.getSoundVolume(), this.getSoundPitch());
                    this.setPuffState(1);
                }

                ++this.puffTimer;
            } else if (this.getPuffState() != 0) {
                if (this.deflateTimer > 80 && this.getPuffState() == 1) {
                    this.playSound(SoundEvents.ENTITY_PUFFER_FISH_BLOW_OUT, this.getSoundVolume(), this.getSoundPitch());
                    this.setPuffState(0);
                }

                ++this.deflateTimer;
            }
        }

        super.tick();
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageableEntity) {
        RubberBellyGliderEntity entity = new RubberBellyGliderEntity(FinsEntities.RUBBER_BELLY_GLIDER, this.world);
        entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)), SpawnReason.BREEDING, (ILivingEntityData) null, (CompoundNBT) null);
        return entity;
    }

    static class SwimGoal extends RandomSwimmingGoal {
        private final RubberBellyGliderEntity glider;

        public SwimGoal(RubberBellyGliderEntity glider) {
            super(glider, 1.0D, 40);
            this.glider = glider;
        }

        public boolean shouldExecute() {
            return super.shouldExecute();
        }
    }

    public void onCollideWithPlayer(PlayerEntity entityIn) {
        int i = this.getPuffState();
        if (entityIn instanceof ServerPlayerEntity && i > 0 && entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 5)) {
            entityIn.addPotionEffect(new EffectInstance(Effects.POISON, 60 * i, 0));
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
                double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.glider.rotationYaw = this.limitAngle(this.glider.rotationYaw, f, 90.0F);
                this.glider.renderYawOffset = this.glider.rotationYaw;
                float f1 = (float)(this.speed * this.glider.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
                this.glider.setAIMoveSpeed(MathHelper.lerp(0.125F, this.glider.getAIMoveSpeed(), f1));
                this.glider.setMotion(this.glider.getMotion().add(0.0D, (double)this.glider.getAIMoveSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.glider.setAIMoveSpeed(0.0F);
            }
        }
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
            List<LivingEntity> list = this.glider.world.getEntitiesWithinAABB(LivingEntity.class, this.glider.getBoundingBox().grow(2.0D), RubberBellyGliderEntity.ENEMY_MATCHER);
            return !list.isEmpty();
        }

        public void startExecuting() {
            this.glider.puffTimer = 1;
            this.glider.deflateTimer = 0;
        }

        public void resetTask() {
            this.glider.puffTimer = 0;
        }

        public boolean shouldContinueExecuting() {
            List<LivingEntity> list = this.glider.world.getEntitiesWithinAABB(LivingEntity.class, this.glider.getBoundingBox().grow(2.0D), RubberBellyGliderEntity.ENEMY_MATCHER);
            return !list.isEmpty();
        }
    }
}*/
