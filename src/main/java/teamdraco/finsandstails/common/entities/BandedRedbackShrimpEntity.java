package teamdraco.finsandstails.common.entities;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamdraco.finsandstails.registry.FTItems;

public class BandedRedbackShrimpEntity extends AbstractSchoolingFish implements GeoEntity {
    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    public BandedRedbackShrimpEntity(EntityType<? extends BandedRedbackShrimpEntity> type, Level world) {
        super(type, world);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.1F, 0.5F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 30);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, OrnateBugfishEntity.class, 6.0F, 1.5D, 2.0D));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, RubberBellyGliderEntity.class, 6.0F, 1.5D, 2.0D));
    }

    @Override
    public int getMaxSchoolSize() {
        return 3;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(FTItems.BANDED_REDBACK_SHRIMP_BUCKET.get());
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
        return new ItemStack(FTItems.BANDED_REDBACK_SHRIMP_SPAWN_EGG.get());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 5, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if (event.isMoving()) {
            event.setAnimation(RawAnimation.begin().thenLoop("animation.shrimp.swim"));
        }
        else {
            event.setAnimation(RawAnimation.begin().thenLoop("animation.shrimp.idle"));
        }
        return PlayState.CONTINUE;
    }

}