package teamdraco.fins.common.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import teamdraco.fins.init.FinsItems;

import javax.annotation.Nullable;

public class GoldenRiverRayEntity extends AbstractGroupFishEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(GoldenRiverRayEntity.class, DataSerializers.INT);

    public GoldenRiverRayEntity(EntityType<? extends GoldenRiverRayEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.85D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PlayerEntity.class, 6, 1.0D, 1.85D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 1.0D, 1));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, RiverPebbleSnailEntity.class, false));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 6).add(Attributes.ATTACK_DAMAGE, 1);
    }

    public void playerTouch(PlayerEntity entityIn) {
        if (entityIn instanceof ServerPlayerEntity && entityIn.hurt(DamageSource.mobAttack(this), 1)) {
            ((ServerPlayerEntity)entityIn).connection.send(new SChangeGameStatePacket(SChangeGameStatePacket.PUFFER_FISH_STING, 0.0F));
            entityIn.addEffect(new EffectInstance(Effects.POISON, 120, 0));
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity attacker = source.getDirectEntity();
        if (attacker instanceof LivingEntity) {
            ((LivingEntity) attacker).addEffect(new EffectInstance(Effects.POISON, 200, 0));
            attacker.hurt(DamageSource.mobAttack(this), 1);
        }
        return super.hurt(source, amount);
    }

    @Override
    protected void saveToBucketTag(ItemStack bucket) {
        CompoundNBT compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("Variant", this.getVariant());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
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

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @javax.annotation.Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if (dataTag == null) {
            setVariant(random.nextInt(3));
        } else {
            if (dataTag.contains("Variant", 3)){
                this.setVariant(dataTag.getInt("Variant"));
            }
        }
        return spawnDataIn;
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(FinsItems.GOLDEN_RIVER_RAY_BUCKET.get());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.GOLDEN_RIVER_RAY_SPAWN_EGG.get());
    }

    public boolean doHurtTarget(Entity entityIn) {
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));
        if (flag) {
            this.doEnchantDamageEffects(this, entityIn);
        }

        return flag;
    }
}
