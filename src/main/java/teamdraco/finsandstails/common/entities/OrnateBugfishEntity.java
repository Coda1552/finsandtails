package teamdraco.finsandstails.common.entities;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamdraco.finsandstails.common.entities.ai.control.FTSmoothSwimmingMoveControl;
import teamdraco.finsandstails.registry.FTItems;

import java.util.function.Predicate;

public class OrnateBugfishEntity extends AbstractSchoolingFish implements GeoEntity {
    public static final Predicate<LivingEntity> IS_PREY = (entity) -> entity.isAlive() && (
            entity instanceof TropicalFish
                    || entity instanceof Cod
                    || entity instanceof Salmon
                    || entity instanceof HighFinnedBlueEntity
                    || entity instanceof FlatbackSuckerEntity
                    || entity instanceof BluWeeEntity
                    || entity instanceof PeaWeeEntity
                    || entity instanceof PhantomNudibranchEntity
                    || entity instanceof TealArrowfishEntity
                    || entity instanceof BandedRedbackShrimpEntity
                    || entity instanceof SwampMuckerEntity
                    || entity instanceof WeeWeeEntity
                    || entity instanceof VibraWeeEntity);
    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    public OrnateBugfishEntity(EntityType<? extends OrnateBugfishEntity> type, Level world) {
        super(type, world);
        this.moveControl = new FTSmoothSwimmingMoveControl(this, 85, 10, 0.1F, 0.5F, true);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PapaWeeEntity.class, 8.0F, 1.6D, 1.4D));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, AbstractFish.class, 10, true, false, IS_PREY));
    }

    @Override
    public void travel(Vec3 vec3) {
        if (isInWater() && isEffectiveAi() && getTarget() != null) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
        }
        super.travel(vec3);
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6).add(Attributes.ATTACK_DAMAGE, 1);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(FTItems.ORNATE_BUGFISH_BUCKET.get());
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
        return new ItemStack(FTItems.ORNATE_BUGFISH_SPAWN_EGG.get());
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
            event.setAnimation(RawAnimation.begin().thenLoop("animation.ornate_bugfish.swim"));
        }
        else {
            event.setAnimation(RawAnimation.begin().thenLoop("animation.ornate_bugfish.idle"));
        }
        return PlayState.CONTINUE;
    }
}
