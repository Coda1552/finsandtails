package teamdraco.fins.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import teamdraco.fins.init.FinsItems;

public class BandedRedbackShrimpEntity extends AbstractGroupFishEntity {

    public BandedRedbackShrimpEntity(EntityType<? extends BandedRedbackShrimpEntity> type, World world) {
        super(type, world);
        this.moveControl = new MoveHelperController(this);
    }

    @Override
    public int getMaxSchoolSize() {
        return 3;
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(FinsItems.BANDED_REDBACK_SHRIMP_BUCKET.get());
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
        return new ItemStack(FinsItems.BANDED_REDBACK_SHRIMP_SPAWN_EGG.get());
    }

    static class MoveHelperController extends MovementController {
        private final BandedRedbackShrimpEntity shrimp;

        public MoveHelperController(BandedRedbackShrimpEntity p_i48945_1_) {
            super(p_i48945_1_);
            this.shrimp = p_i48945_1_;
        }

        public void tick() {
            if (this.shrimp.isInWater()) {
                this.shrimp.setDeltaMovement(this.shrimp.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == Action.MOVE_TO && !this.shrimp.getNavigation().isDone()) {
                double d0 = this.wantedX - this.shrimp.getX();
                double d1 = this.wantedY - this.shrimp.getY();
                double d2 = this.wantedZ - this.shrimp.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double)2.5000003E-7F) {
                    this.mob.setZza(0.0F);
                } else {
                    float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.shrimp.yRot = this.rotlerp(this.shrimp.yRot, f, 10.0F);
                    this.shrimp.yBodyRot = this.shrimp.yRot;
                    this.shrimp.yHeadRot = this.shrimp.yRot;
                    float f1 = (float)(this.speedModifier * this.shrimp.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.shrimp.isInWater()) {
                        this.shrimp.setSpeed(f1 * 0.02F);
                        float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * (double)(180F / (float)Math.PI)));
                        f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
                        this.shrimp.xRot = this.rotlerp(this.shrimp.xRot, f2, 5.0F);
                        float f3 = MathHelper.cos(this.shrimp.xRot * ((float)Math.PI / 180F));
                        float f4 = MathHelper.sin(this.shrimp.xRot * ((float)Math.PI / 180F));
                        this.shrimp.zza = f3 * f1;
                        this.shrimp.yya = -f4 * f1;
                    } else {
                        this.shrimp.setSpeed(f1 * 0.1F);
                    }

                }
            } else {
                this.shrimp.setSpeed(0.0F);
                this.shrimp.setXxa(0.0F);
                this.shrimp.setYya(0.0F);
                this.shrimp.setZza(0.0F);
            }
        }
    }
}
