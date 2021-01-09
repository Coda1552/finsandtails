package mod.coda.fins.entity;

import mod.coda.fins.init.FinsItems;
import mod.coda.fins.init.FinsSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RedBullCrabEntity extends AbstractFishEntity {

    public RedBullCrabEntity(EntityType<? extends RedBullCrabEntity> type, World world) {
        super(type, world);
        this.moveController = new RedBullCrabEntity.MoveHelperController(this);
        this.stepHeight = 0.7f;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, RedBullCrabEntity.class, false));
    }

    protected PathNavigator createNavigator(World world) {
        return new GroundPathNavigator(this, world);
    }

    static class MoveHelperController extends MovementController {
        private final RedBullCrabEntity crab;

        MoveHelperController(RedBullCrabEntity crab) {
            super(crab);
            this.crab = crab;
        }

        public void tick() {
            if (this.crab.areEyesInFluid(FluidTags.WATER)) {
                this.crab.setMotion(this.crab.getMotion().add(0.0D, 0.0D, 0.0D));
            }

            if (this.action == Action.MOVE_TO && !this.crab.getNavigator().noPath()) {
                double d0 = this.posX - this.crab.getPosX();
                double d1 = this.posY - this.crab.getPosY();
                double d2 = this.posZ - this.crab.getPosZ();
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.crab.rotationYaw = this.limitAngle(this.crab.rotationYaw, f, 90.0F);
                this.crab.renderYawOffset = this.crab.rotationYaw;
                float f1 = (float) (this.speed * this.crab.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.crab.setAIMoveSpeed(MathHelper.lerp(0.125F, this.crab.getAIMoveSpeed(), f1));
                this.crab.setMotion(this.crab.getMotion().add(0.0D, (double) this.crab.getAIMoveSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.crab.setAIMoveSpeed(0.0F);
            }
        }
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getTrueSource();

            if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof AbstractArrowEntity)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));
        if (flag) {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    protected ItemStack getFishBucket() {
        return new ItemStack(FinsItems.RED_BULL_CRAB_BUCKET.get());
    }

    public static AttributeModifierMap.MutableAttribute func_234176_m_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 6).createMutableAttribute(Attributes.ATTACK_DAMAGE, 1);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_TROPICAL_FISH_HURT;
    }

    protected SoundEvent getDeathSound() {
        return FinsSounds.CRAB_DEATH.get();
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_FLOP;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.RED_BULL_CRAB_SPAWN_EGG.get());
    }
}