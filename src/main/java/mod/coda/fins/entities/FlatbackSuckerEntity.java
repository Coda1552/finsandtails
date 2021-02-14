package mod.coda.fins.entities;

import mod.coda.fins.init.FinsItems;
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

public class FlatbackSuckerEntity extends AbstractFishEntity {
    public FlatbackSuckerEntity(EntityType<? extends FlatbackSuckerEntity> type, World world) {
        super(type, world);
        this.moveController = new FlatbackSuckerEntity.MoveHelperController(this);
    }

    protected PathNavigator createNavigator(World world) {
        return new GroundPathNavigator(this, world);
    }

    static class MoveHelperController extends MovementController {
        private final FlatbackSuckerEntity fish;

        MoveHelperController(FlatbackSuckerEntity fish) {
            super(fish);
            this.fish = fish;
        }

        public void tick() {
            if (this.fish.areEyesInFluid(FluidTags.WATER)) {
                this.fish.setMotion(this.fish.getMotion().add(0.0D, 0.0D, 0.0D));
            }

            if (this.action == MovementController.Action.MOVE_TO && !this.fish.getNavigator().noPath()) {
                double d0 = this.posX - this.fish.getPosX();
                double d1 = this.posY - this.fish.getPosY();
                double d2 = this.posZ - this.fish.getPosZ();
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.fish.rotationYaw = this.limitAngle(this.fish.rotationYaw, f, 90.0F);
                this.fish.renderYawOffset = this.fish.rotationYaw;
                float f1 = (float)(this.speed * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.fish.setAIMoveSpeed(MathHelper.lerp(0.125F, this.fish.getAIMoveSpeed(), f1));
                this.fish.setMotion(this.fish.getMotion().add(0.0D, (double)this.fish.getAIMoveSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.fish.setAIMoveSpeed(0.0F);
            }
        }
    }

    public static AttributeModifierMap.MutableAttribute func_234176_m_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 6);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NOT_SPECTATING::test));
        this.goalSelector.addGoal(4, new FlatbackSuckerEntity.SwimGoal(this));
    }

    static class SwimGoal extends RandomSwimmingGoal {
        private final FlatbackSuckerEntity fish;

        public SwimGoal(FlatbackSuckerEntity fish) {
            super(fish, 1.0D, 40);
            this.fish = fish;
        }

        public boolean shouldExecute() {
            return super.shouldExecute();
        }
    }

    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(FinsItems.FLATBACK_SUCKER_BUCKET.get());
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
        return new ItemStack(FinsItems.FLATBACK_SUCKER_SPAWN_EGG.get());
    }
}
