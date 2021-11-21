package teamdraco.fins.common.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.fish.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import teamdraco.fins.init.FinsItems;

import java.util.function.Predicate;

public class OrnateBugfishEntity extends AbstractGroupFishEntity {
    public static final Predicate<LivingEntity> IS_PREY = (entity) -> entity.isAlive() && (
            entity instanceof TropicalFishEntity
            || entity instanceof CodEntity
            || entity instanceof SalmonEntity
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

    public OrnateBugfishEntity(EntityType<? extends OrnateBugfishEntity> type, World world) {
        super(type, world);
        this.moveControl = new MoveHelperController(this);
        this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FollowSchoolLeaderGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PapaWeeEntity.class, 8.0F, 1.6D, 1.4D));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 40));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, AbstractFishEntity.class, 10, true, false, IS_PREY));
    }

    @Override
    public int getMaxSchoolSize() {
        return 6;
    }

    public void travel(Vector3d p_213352_1_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), p_213352_1_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(p_213352_1_);
        }

    }

    public boolean doHurtTarget(Entity entityIn) {
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));
        if (flag) {
            this.doEnchantDamageEffects(this, entityIn);
        }

        return flag;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 6).add(Attributes.ATTACK_DAMAGE, 1);
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(FinsItems.ORNATE_BUGFISH_BUCKET.get());
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
        return new ItemStack(FinsItems.ORNATE_BUGFISH_SPAWN_EGG.get());
    }

    static class MoveHelperController extends MovementController {
        private final OrnateBugfishEntity fish;

        public MoveHelperController(OrnateBugfishEntity p_i48945_1_) {
            super(p_i48945_1_);
            this.fish = p_i48945_1_;
        }

        public void tick() {
            if (this.fish.isInWater()) {
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MovementController.Action.MOVE_TO && !this.fish.getNavigation().isDone()) {
                double d0 = this.wantedX - this.fish.getX();
                double d1 = this.wantedY - this.fish.getY();
                double d2 = this.wantedZ - this.fish.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double)2.5000003E-7F) {
                    this.mob.setZza(0.0F);
                } else {
                    float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.fish.yRot = this.rotlerp(this.fish.yRot, f, 10.0F);
                    this.fish.yBodyRot = this.fish.yRot;
                    this.fish.yHeadRot = this.fish.yRot;
                    float f1 = (float)(this.speedModifier * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.fish.isInWater()) {
                        this.fish.setSpeed(f1 * 0.02F);
                        float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * (double)(180F / (float)Math.PI)));
                        f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
                        this.fish.xRot = this.rotlerp(this.fish.xRot, f2, 5.0F);
                        float f3 = MathHelper.cos(this.fish.xRot * ((float)Math.PI / 180F));
                        float f4 = MathHelper.sin(this.fish.xRot * ((float)Math.PI / 180F));
                        this.fish.zza = f3 * f1;
                        this.fish.yya = -f4 * f1;
                    } else {
                        this.fish.setSpeed(f1 * 0.1F);
                    }

                }
            } else {
                this.fish.setSpeed(0.0F);
                this.fish.setXxa(0.0F);
                this.fish.setYya(0.0F);
                this.fish.setZza(0.0F);
            }
        }
    }
}
