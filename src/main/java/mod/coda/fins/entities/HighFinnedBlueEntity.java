package mod.coda.fins.entities;

import mod.coda.fins.init.FinsItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class HighFinnedBlueEntity extends AbstractGroupFishEntity {
    public HighFinnedBlueEntity(EntityType<? extends HighFinnedBlueEntity> type, World world) {
        super(type, world);
    }

    @Override
    public int getMaxGroupSize() {
        return 12;
    }

    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(FinsItems.HIGH_FINNED_BLUE_BUCKET.get());
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
        return new ItemStack(FinsItems.HIGH_FINNED_BLUE_SPAWN_EGG.get());
    }
}
