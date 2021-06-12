package teamdraco.fins.common.entities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.fish.PufferfishEntity;
import teamdraco.fins.init.FinsItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import teamdraco.fins.init.FinsSounds;

import java.util.List;

public class FlatbackSuckerEntity extends AbstractFishEntity {
    public FlatbackSuckerEntity(EntityType<? extends FlatbackSuckerEntity> type, World world) {
        super(type, world);
        this.moveControl = new FlatbackSuckerEntity.MoveHelperController(this);
    }

    protected PathNavigator createNavigation(World world) {
        return new GroundPathNavigator(this, world);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 6);
    }

    @Override
    public void tick() {
        super.tick();
        List<LivingEntity> list = this.level.getEntitiesOfClass(FlatbackSuckerEntity.class, this.getBoundingBox().inflate(2.0D));
        if (this.isAlive() && list.size() >= 3 && random.nextFloat() > 0.99F) {
            this.playSound(FinsSounds.FLATBACK_SUCKER_CLICK.get(), 0.4F, 1.0F);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
        this.goalSelector.addGoal(4, new FlatbackSuckerEntity.SwimGoal(this));
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(FinsItems.FLATBACK_SUCKER_BUCKET.get());
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
        return new ItemStack(FinsItems.FLATBACK_SUCKER_SPAWN_EGG.get());
    }

    static class MoveHelperController extends MovementController {
        private final FlatbackSuckerEntity fish;

        MoveHelperController(FlatbackSuckerEntity fish) {
            super(fish);
            this.fish = fish;
        }

        public void tick() {
            if (this.fish.isEyeInFluid(FluidTags.WATER)) {
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.0D, 0.0D));
            }

            if (this.operation == MovementController.Action.MOVE_TO && !this.fish.getNavigation().isDone()) {
                double d0 = this.wantedX - this.fish.getX();
                double d1 = this.wantedY - this.fish.getY();
                double d2 = this.wantedZ - this.fish.getZ();
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.fish.yRot = this.rotlerp(this.fish.yRot, f, 90.0F);
                this.fish.yBodyRot = this.fish.yRot;
                float f1 = (float)(this.speedModifier * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.fish.setSpeed(MathHelper.lerp(0.125F, this.fish.getSpeed(), f1));
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, (double)this.fish.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.fish.setSpeed(0.0F);
            }
        }
    }

    static class SwimGoal extends RandomSwimmingGoal {
        private final FlatbackSuckerEntity fish;

        public SwimGoal(FlatbackSuckerEntity fish) {
            super(fish, 1.0D, 40);
            this.fish = fish;
        }

        public boolean canUse() {
            return super.canUse();
        }
    }
}
