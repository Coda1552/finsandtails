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
    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(NightLightSquidEntity.class, DataSerializers.VARINT);
    public float prevSquidPitch;
    public float squidRotation;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;

    public NightLightSquidEntity(EntityType<? extends NightLightSquidEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveController = new NightLightSquidEntity.MoveHelperController(this);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new NightLightSquidEntity.SwimGoal(this));
    }

    public void onCollideWithPlayer(PlayerEntity entityIn) {
        if (entityIn instanceof ServerPlayerEntity) {
            entityIn.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 200, 0));
        }

    }

    public static AttributeModifierMap.MutableAttribute func_234227_m_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10.0D);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.5F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SQUID_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SQUID_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SQUID_DEATH;
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(FinsItems.NIGHT_LIGHT_SQUID_BUCKET.get());
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (dataTag == null) {
            setVariant(rand.nextInt(4));
        } else {
            if (dataTag.contains("Variant", 3)){
                this.setVariant(dataTag.getInt("Variant"));
            }
        }
        return spawnDataIn;
    }

    protected void setBucketData(ItemStack bucket) {
        CompoundNBT compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("Variant", this.getVariant());
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(VARIANT, 0);
    }

    public int getVariant() {
        return this.dataManager.get(VARIANT);
    }

    private void setVariant(int variant) {
        this.dataManager.set(VARIANT, variant);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Variant", getVariant());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        setVariant(compound.getInt("Variant"));
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (super.attackEntityFrom(source, amount) && this.getRevengeTarget() != null) {
            this.squirtInk();
            return true;
        } else {
            return false;
        }
    }

    private Vector3d func_207400_b(Vector3d p_207400_1_) {
        Vector3d vector3d = p_207400_1_.rotatePitch(this.prevSquidPitch * ((float)Math.PI / 180F));
        return vector3d.rotateYaw(-this.prevRenderYawOffset * ((float)Math.PI / 180F));
    }

    private void squirtInk() {
        this.playSound(SoundEvents.ENTITY_SQUID_SQUIRT, this.getSoundVolume(), this.getSoundPitch());
        Vector3d vector3d = this.func_207400_b(new Vector3d(0.0D, -1.0D, 0.0D)).add(this.getPosX(), this.getPosY(), this.getPosZ());

        for(int i = 0; i < 30; ++i) {
            Vector3d vector3d1 = this.func_207400_b(new Vector3d((double)this.rand.nextFloat() * 0.6D - 0.3D, -1.0D, (double)this.rand.nextFloat() * 0.6D - 0.3D));
            Vector3d vector3d2 = vector3d1.scale(0.3D + (double)(this.rand.nextFloat() * 2.0F));
            ((ServerWorld)this.world).spawnParticle(ParticleTypes.SQUID_INK, vector3d.x, vector3d.y + 0.5D, vector3d.z, 0, vector3d2.x, vector3d2.y, vector3d2.z, (double)0.1F);
        }

    }

    public void travel(Vector3d travelVector) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(0.01F, travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.9D));
            if (this.getAttackTarget() == null) {
                this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }

    }

    public static boolean func_223365_b(EntityType<NightLightSquidEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random p_223365_4_) {
        return pos.getY() < world.getSeaLevel() && world.getBlockState(pos.up()).isIn(Blocks.WATER);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 19) {
            this.squidRotation = 0.0F;
        } else {
            super.handleStatusUpdate(id);
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

        public boolean shouldExecute() {
            LivingEntity livingentity = NightLightSquidEntity.this.getRevengeTarget();
            if (NightLightSquidEntity.this.isInWater() && livingentity != null) {
                return NightLightSquidEntity.this.getDistanceSq(livingentity) < 100.0D;
            } else {
                return false;
            }
        }

        public void startExecuting() {
            this.tickCounter = 0;
        }

        public void tick() {
            ++this.tickCounter;
            LivingEntity livingentity = NightLightSquidEntity.this.getRevengeTarget();
            if (livingentity != null) {
                Vector3d vector3d = new Vector3d(NightLightSquidEntity.this.getPosX() - livingentity.getPosX(), NightLightSquidEntity.this.getPosY() - livingentity.getPosY(), NightLightSquidEntity.this.getPosZ() - livingentity.getPosZ());
                BlockState blockstate = NightLightSquidEntity.this.world.getBlockState(new BlockPos(NightLightSquidEntity.this.getPosX() + vector3d.x, NightLightSquidEntity.this.getPosY() + vector3d.y, NightLightSquidEntity.this.getPosZ() + vector3d.z));
                FluidState fluidstate = NightLightSquidEntity.this.world.getFluidState(new BlockPos(NightLightSquidEntity.this.getPosX() + vector3d.x, NightLightSquidEntity.this.getPosY() + vector3d.y, NightLightSquidEntity.this.getPosZ() + vector3d.z));
                if (fluidstate.isTagged(FluidTags.WATER) || blockstate.isAir()) {
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
                    NightLightSquidEntity.this.world.addParticle(ParticleTypes.BUBBLE, NightLightSquidEntity.this.getPosX(), NightLightSquidEntity.this.getPosY(), NightLightSquidEntity.this.getPosZ(), 0.0D, 0.0D, 0.0D);
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
            if (this.fish.areEyesInFluid(FluidTags.WATER)) {
                this.fish.setMotion(this.fish.getMotion().add(0.0D, 0.005D, 0.0D));
            }

            if (this.action == MovementController.Action.MOVE_TO && !this.fish.getNavigator().noPath()) {
                float f = (float)(this.speed * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.fish.setAIMoveSpeed(MathHelper.lerp(0.125F, this.fish.getAIMoveSpeed(), f));
                double d0 = this.posX - this.fish.getPosX();
                double d1 = this.posY - this.fish.getPosY();
                double d2 = this.posZ - this.fish.getPosZ();
                if (d1 != 0.0D) {
                    double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    this.fish.setMotion(this.fish.getMotion().add(0.0D, (double)this.fish.getAIMoveSpeed() * (d1 / d3) * 0.1D, 0.0D));
                }

                if (d0 != 0.0D || d2 != 0.0D) {
                    float f1 = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.fish.rotationYaw = this.limitAngle(this.fish.rotationYaw, f1, 90.0F);
                    this.fish.renderYawOffset = this.fish.rotationYaw;
                }

            } else {
                this.fish.setAIMoveSpeed(0.0F);
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

        public boolean shouldExecute() {
            return super.shouldExecute();
        }
    }
}
