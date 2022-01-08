package teamdraco.finsandstails.common.entities;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import teamdraco.finsandstails.registry.FTItems;

public class HighFinnedBlueEntity extends AbstractSchoolingFish {

    public HighFinnedBlueEntity(EntityType<? extends HighFinnedBlueEntity> type, Level world) {
        super(type, world);
    }

    @Override
    public int getMaxSchoolSize() {
        return 12;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(FTItems.HIGH_FINNED_BLUE_BUCKET.get());
    }

    public SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    public SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    public SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.COD_HURT;
    }

    public SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(FTItems.HIGH_FINNED_BLUE_SPAWN_EGG.get());
    }
}
