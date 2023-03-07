package teamdraco.finsandstails.common.entities;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;
import teamdraco.finsandstails.common.entities.ai.WeeHurtByEntityGoal;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;

import java.util.List;

public class WeeWeeEntity extends AbstractSchoolingFish implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public WeeWeeEntity(EntityType<? extends WeeWeeEntity> type, Level world) {
        super(type, world);
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, LivingEntity.class, 6, 1.0D, 1.5D));
        this.goalSelector.addGoal(1, new WeeHurtByEntityGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(FTItems.WEE_WEE_BUCKET.get());
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
        return new ItemStack(FTItems.WEE_WEE_SPAWN_EGG.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (random.nextInt(2500) == 0 && shouldSpawnPapaWee()) {
            PapaWeeEntity papaWee = FTEntities.PAPA_WEE.get().create(level);
            papaWee.setPos(this.getX(), this.getY(), this.getZ());

            level.addFreshEntity(papaWee);
        }
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }

    private boolean shouldSpawnPapaWee() {
        List<WeeWeeEntity> weeList = this.level.getEntitiesOfClass(WeeWeeEntity.class, this.getBoundingBox().inflate(8.0D));
        List<PapaWeeEntity> papaWeeList = this.level.getEntitiesOfClass(PapaWeeEntity.class, this.getBoundingBox().inflate(16.0D));
        if (weeList.size() >= 10 && papaWeeList.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.weewee.swim", ILoopType.EDefaultLoopTypes.LOOP));
        }
        else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.weewee.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
