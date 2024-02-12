package teamdraco.finsandstails.common.entities;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.AbstractFish;
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
import teamdraco.finsandstails.common.entities.ai.WeeHurtByEntityGoal;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;

import java.util.List;

public class PeaWeeEntity extends AbstractFish implements GeoEntity {
    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    public PeaWeeEntity(EntityType<? extends PeaWeeEntity> type, Level world) {
        super(type, world);
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, TealArrowfishEntity.class, 6, 1.0f, 1.5f));
        this.goalSelector.addGoal(1, new WeeHurtByEntityGoal(this));
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(FTItems.PEA_WEE_BUCKET.get());
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
        return new ItemStack(FTItems.PEA_WEE_SPAWN_EGG.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (random.nextInt(2500) == 0 && shouldSpawnPapaWee()) {
            PapaWeeEntity papaWee = FTEntities.PAPA_WEE.get().create(level());
            papaWee.setPos(this.getX(), this.getY(), this.getZ());

            level().addFreshEntity(papaWee);
        }
    }

    private boolean shouldSpawnPapaWee() {
        List<PeaWeeEntity> weeList = this.level().getEntitiesOfClass(PeaWeeEntity.class, this.getBoundingBox().inflate(8.0D));
        List<PapaWeeEntity> papaWeeList = this.level().getEntitiesOfClass(PapaWeeEntity.class, this.getBoundingBox().inflate(16.0D));
        if (weeList.size() >= 10 && papaWeeList.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
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
            event.setAnimation(RawAnimation.begin().thenLoop("animation.wee.swim"));
        }
        else {
            event.setAnimation(RawAnimation.begin().thenLoop("animation.wee.idle"));
        }
        return PlayState.CONTINUE;
    }
}

