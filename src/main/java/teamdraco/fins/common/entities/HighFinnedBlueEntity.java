package teamdraco.fins.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import teamdraco.fins.init.FinsItems;

public class HighFinnedBlueEntity extends AbstractGroupFishEntity {

    public HighFinnedBlueEntity(EntityType<? extends HighFinnedBlueEntity> type, World world) {
        super(type, world);
    }

    @Override
    public int getMaxSchoolSize() {
        return 12;
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(FinsItems.HIGH_FINNED_BLUE_BUCKET.get());
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
        return new ItemStack(FinsItems.HIGH_FINNED_BLUE_SPAWN_EGG.get());
    }
}
