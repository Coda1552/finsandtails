package teamdraco.fins.common.entities;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.fins.init.FinsItems;
import teamdraco.fins.init.FinsSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

public class SpindlyGemCrabEntity extends AbstractFishEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(SpindlyGemCrabEntity.class, DataSerializers.VARINT);

    public SpindlyGemCrabEntity(EntityType<? extends SpindlyGemCrabEntity> type, World world) {
        super(type, world);
        this.moveController = new SpindlyGemCrabEntity.MoveHelperController(this);
        this.stepHeight = 0.7f;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TemptGoal(this, 1.0D, false, Ingredient.fromTag(Tags.Items.GEMS)));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
    }

    protected PathNavigator createNavigator(World world) {
        return new GroundPathNavigator(this, world);
    }

    protected ItemStack getFishBucket() {
        return new ItemStack(FinsItems.SPINDLY_GEM_CRAB_BUCKET.get());
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (dataTag == null) {
            setVariant(rand.nextInt(5));
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

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 38) {
            this.shineParticles(ParticleTypes.END_ROD);
        }
        else {
            super.handleStatusUpdate(id);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void shineParticles(IParticleData p_208401_1_) {
        if (rand.nextFloat() > 0.975D) {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.addParticle(p_208401_1_, this.getPosX(), this.getPosYRandom(), this.getPosZ(), d0, d1, d2);
        }
    }

    public static AttributeModifierMap.MutableAttribute func_234176_m_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 8.0D);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_TROPICAL_FISH_HURT;
    }

    protected SoundEvent getDeathSound() {
        return FinsSounds.CRAB_DEATH.get();
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_FLOP;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.SPINDLY_GEM_CRAB_SPAWN_EGG.get());
    }

    @Override
    public void tick() {
        super.tick();
        world.setEntityState(this, (byte)38);
    }

    static class MoveHelperController extends MovementController {
        private final SpindlyGemCrabEntity crab;

        MoveHelperController(SpindlyGemCrabEntity crab) {
            super(crab);
            this.crab = crab;
        }

        public void tick() {
            if (this.crab.areEyesInFluid(FluidTags.WATER)) {
                this.crab.setMotion(this.crab.getMotion().add(0.0D, 0.0D, 0.0D));
            }

            if (this.action == MovementController.Action.MOVE_TO && !this.crab.getNavigator().noPath()) {
                double d0 = this.posX - this.crab.getPosX();
                double d1 = this.posY - this.crab.getPosY();
                double d2 = this.posZ - this.crab.getPosZ();
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.crab.rotationYaw = this.limitAngle(this.crab.rotationYaw, f, 90.0F);
                this.crab.renderYawOffset = this.crab.rotationYaw;
                float f1 = (float) (this.speed * this.crab.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.crab.setAIMoveSpeed(MathHelper.lerp(0.125F, this.crab.getAIMoveSpeed(), f1));
                this.crab.setMotion(this.crab.getMotion().add(0.0D, (double) this.crab.getAIMoveSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.crab.setAIMoveSpeed(0.0F);
            }
        }
    }
}