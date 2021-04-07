package teamdraco.fins.common.entities;

import teamdraco.fins.init.FinsItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BandedRedbackShrimpEntity extends AbstractGroupFishEntity {
    public BandedRedbackShrimpEntity(EntityType<? extends BandedRedbackShrimpEntity> type, World world) {
        super(type, world);
    }

    @Override
    public int getMaxGroupSize() {
        return 3;
    }

    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(FinsItems.BANDED_REDBACK_SHRIMP_BUCKET.get());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.BANDED_REDBACK_SHRIMP_SPAWN_EGG.get());
    }
}
