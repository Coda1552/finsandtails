package teamdraco.fins.common.entities;

import teamdraco.fins.init.FinsItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class NightLightSquidEntity extends AbstractGroupFishEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(NightLightSquidEntity.class, DataSerializers.INT);
    public float prevSquidPitch;
    public float squidRotation;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;

    public NightLightSquidEntity(EntityType<? extends NightLightSquidEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveControl = new NightLightSquidEntity.MoveHelperController(this);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new NightLightSquidEntity.SwimGoal(this));
    }

    public void playerTouch(PlayerEntity entityIn) {
        if (entityIn instanceof ServerPlayerEntity) {
            entityIn.addEffect(new EffectInstance(Effects.NIGHT_VISION, 200, 0));
        }

    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.5F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SQUID_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.SQUID_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SQUID_DEATH;
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    protected boolean isMovementNoisy() {
        return false;
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(FinsItems.NIGHT_LIGHT_SQUID_BUCKET.get());
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (dataTag == null) {
            setVariant(random.nextInt(4));
        } else {
            if (dataTag.contains("Variant", 3)){
                this.setVariant(dataTag.getInt("Variant"));
            }
        }
        return spawnDataIn;
    }

    protected void saveToBucketTag(ItemStack bucket) {
        CompoundNBT compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("Variant", this.getVariant());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    private void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        setVariant(compound.getInt("Variant"));
    }

    public boolean hurt(DamageSource source, float amount) {
        if (super.hurt(source, amount) && this.getLastHurtByMob() != null) {
            this.squirtInk();
            return true;
        } else {
            return false;
        }
    }

    private Vector3d rotateVector(Vector3d p_207400_1_) {
        Vector3d vector3d = p_207400_1_.xRot(this.prevSquidPitch * ((float)Math.PI / 180F));
        return vector3d.yRot(-this.yBodyRotO * ((float)Math.PI / 180F));
    }

    private void squirtInk() {
        this.playSound(SoundEvents.SQUID_SQUIRT, this.getSoundVolume(), this.getVoicePitch());
        Vector3d vector3d = this.rotateVector(new Vector3d(0.0D, -1.0D, 0.0D)).add(this.getX(), this.getY(), this.getZ());

        for(int i = 0; i < 30; ++i) {
            Vector3d vector3d1 = this.rotateVector(new Vector3d((double)this.random.nextFloat() * 0.6D - 0.3D, -1.0D, (double)this.random.nextFloat() * 0.6D - 0.3D));
            Vector3d vector3d2 = vector3d1.scale(0.3D + (double)(this.random.nextFloat() * 2.0F));
            ((ServerWorld)this.level).sendParticles(ParticleTypes.SQUID_INK, vector3d.x, vector3d.y + 0.5D, vector3d.z, 0, vector3d2.x, vector3d2.y, vector3d2.z, (double)0.1F);
        }

    }

    public void travel(Vector3d travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.01F, travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }

    }

    public static boolean checkSquidSpawnRules(EntityType<NightLightSquidEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random p_223365_4_) {
        return pos.getY() < world.getSeaLevel() && world.getBlockState(pos.above()).is(Blocks.WATER);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 19) {
            this.squidRotation = 0.0F;
        } else {
            super.handleEntityEvent(id);
        }

    }

    public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn) {
        this.randomMotionVecX = randomMotionVecXIn;
        this.randomMotionVecY = randomMotionVecYIn;
        this.randomMotionVecZ = randomMotionVecZIn;
    }

    public boolean hasMovementVector() {
        return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
    }

    class FleeGoal extends Goal {
        private int tickCounter;

        private FleeGoal() {
        }

        public boolean canUse() {
            LivingEntity livingentity = NightLightSquidEntity.this.getLastHurtByMob();
            if (NightLightSquidEntity.this.isInWater() && livingentity != null) {
                return NightLightSquidEntity.this.distanceToSqr(livingentity) < 100.0D;
            } else {
                return false;
            }
        }

        public void start() {
            this.tickCounter = 0;
        }

        public void tick() {
            ++this.tickCounter;
            LivingEntity livingentity = NightLightSquidEntity.this.getLastHurtByMob();
            if (livingentity != null) {
                Vector3d vector3d = new Vector3d(NightLightSquidEntity.this.getX() - livingentity.getX(), NightLightSquidEntity.this.getY() - livingentity.getY(), NightLightSquidEntity.this.getZ() - livingentity.getZ());
                BlockState blockstate = NightLightSquidEntity.this.level.getBlockState(new BlockPos(NightLightSquidEntity.this.getX() + vector3d.x, NightLightSquidEntity.this.getY() + vector3d.y, NightLightSquidEntity.this.getZ() + vector3d.z));
                FluidState fluidstate = NightLightSquidEntity.this.level.getFluidState(new BlockPos(NightLightSquidEntity.this.getX() + vector3d.x, NightLightSquidEntity.this.getY() + vector3d.y, NightLightSquidEntity.this.getZ() + vector3d.z));
                if (fluidstate.is(FluidTags.WATER) || blockstate.isAir()) {
                    double d0 = vector3d.length();
                    if (d0 > 0.0D) {
                        vector3d.normalize();
                        float f = 3.0F;
                        if (d0 > 5.0D) {
                            f = (float)((double)f - (d0 - 5.0D) / 5.0D);
                        }

                        if (f > 0.0F) {
                            vector3d = vector3d.scale((double)f);
                        }
                    }

                    if (blockstate.isAir()) {
                        vector3d = vector3d.subtract(0.0D, vector3d.y, 0.0D);
                    }

                    NightLightSquidEntity.this.setMovementVector((float)vector3d.x / 20.0F, (float)vector3d.y / 20.0F, (float)vector3d.z / 20.0F);
                }

                if (this.tickCounter % 10 == 5) {
                    NightLightSquidEntity.this.level.addParticle(ParticleTypes.BUBBLE, NightLightSquidEntity.this.getX(), NightLightSquidEntity.this.getY(), NightLightSquidEntity.this.getZ(), 0.0D, 0.0D, 0.0D);
                }

            }
        }
    }

    static class MoveHelperController extends MovementController {
        private final NightLightSquidEntity fish;

        MoveHelperController(NightLightSquidEntity fish) {
            super(fish);
            this.fish = fish;
        }

        public void tick() {
            if (this.fish.isEyeInFluid(FluidTags.WATER)) {
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MovementController.Action.MOVE_TO && !this.fish.getNavigation().isDone()) {
                float f = (float)(this.speedModifier * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.fish.setSpeed(MathHelper.lerp(0.125F, this.fish.getSpeed(), f));
                double d0 = this.wantedX - this.fish.getX();
                double d1 = this.wantedY - this.fish.getY();
                double d2 = this.wantedZ - this.fish.getZ();
                if (d1 != 0.0D) {
                    double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, (double)this.fish.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
                }

                if (d0 != 0.0D || d2 != 0.0D) {
                    float f1 = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.fish.yRot = this.rotlerp(this.fish.yRot, f1, 90.0F);
                    this.fish.yBodyRot = this.fish.yRot;
                }

            } else {
                this.fish.setSpeed(0.0F);
            }
        }
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.NIGHT_LIGHT_SQUID_SPAWN_EGG.get());
    }

    static class SwimGoal extends RandomSwimmingGoal {
        private final NightLightSquidEntity fish;

        public SwimGoal(NightLightSquidEntity fish) {
            super(fish, 1.0D, 1);
            this.fish = fish;
        }

        public boolean canUse() {
            return super.canUse();
        }
    }
}
