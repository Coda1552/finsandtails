package teamdraco.finsandstails.common.entities;

import coda.dracoshoard.common.entities.ai.FancySwimmingMoveHelperController;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.HitResult;
import teamdraco.finsandstails.registry.FTItems;

import java.util.function.Predicate;

public class OrnateBugfishEntity extends AbstractSchoolingFish {
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

    public OrnateBugfishEntity(EntityType<? extends OrnateBugfishEntity> type, Level world) {
        super(type, world);
        this.moveControl = new FancySwimmingMoveHelperController(this);
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
    public int getMaxSchoolSize() {
        return 6;
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
}
