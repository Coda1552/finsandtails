package teamdraco.finsandstails.common.entities;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamdraco.finsandstails.common.entities.ai.IPanickableSchooling;
import teamdraco.finsandstails.common.entities.ai.goals.PanickableFollowFlockLeaderGoal;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;

import java.util.List;

public class HighFinnedBlueEntity extends AbstractSchoolingFish implements GeoEntity, IPanickableSchooling {
    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    public AvoidEntityGoal<?> avoidGoal;

    public HighFinnedBlueEntity(EntityType<? extends HighFinnedBlueEntity> type, Level world) {
        super(type, world);
    }

    protected void registerGoals() {
        avoidGoal = new AvoidEntityGoal<>(this, Player.class, 2.0F, 2.4D, 1.8D);

        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(1, new PanickableFollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(2, avoidGoal());
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 40));
    }

    @Override
    public int getMaxSchoolSize() {
        return 12;
    }


    @Override
    public AvoidEntityGoal<?> avoidGoal() {
        return avoidGoal;
    }

    @Override
    public List<Class<? extends Entity>> toAvoid() {
        return List.of(Player.class, PenglilEntity.class);
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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoEntity>(this, "controller", 5, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }

    private <E extends GeoEntity> PlayState predicate(AnimationState<E> event) {
        if (event.isMoving()) {
            event.setAnimation(RawAnimation.begin().thenLoop("animations.high_finned_blue.swim"));
        }
        else {
            event.setAnimation(RawAnimation.begin().thenLoop("animations.high_finned_blue.idle"));
        }
        return PlayState.CONTINUE;
    }

}
