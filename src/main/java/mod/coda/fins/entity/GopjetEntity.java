package mod.coda.fins.entity;

import mod.coda.fins.entity.ai.GopjetLookController;
import mod.coda.fins.init.FinsItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GopjetEntity extends AbstractGroupFishEntity {
    private int boostTimer = 0;

    public GopjetEntity(EntityType<? extends GopjetEntity> type, World world) {
        super(type, world);
        this.moveController = new GopjetEntity.MoveHelperController(this);
        this.lookController = new GopjetLookController(this, 10);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, TealArrowfishEntity.class, 6, 1.0D, 1.5D));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NOT_SPECTATING::test));
    }

    public static AttributeModifierMap.MutableAttribute func_234176_m_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    static class MoveHelperController extends MovementController {
        private final GopjetEntity gopjet;

        public MoveHelperController(GopjetEntity gopjetEntity) {
            super(gopjetEntity);
            this.gopjet = gopjetEntity;
        }

        public void tick() {
            if (this.gopjet.isInWater()) {
                this.gopjet.setMotion(this.gopjet.getMotion().add(0.0D, 0.005D, 0.0D));
            }

            if (this.action == MovementController.Action.MOVE_TO && !this.gopjet.getNavigator().noPath()) {
                double d0 = this.posX - this.gopjet.getPosX();
                double d1 = this.posY - this.gopjet.getPosY();
                double d2 = this.posZ - this.gopjet.getPosZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double)2.5000003E-7F) {
                    this.mob.setMoveForward(0.0F);
                } else {
                    float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.gopjet.rotationYaw = this.limitAngle(this.gopjet.rotationYaw, f, 10.0F);
                    this.gopjet.renderYawOffset = this.gopjet.rotationYaw;
                    this.gopjet.rotationYawHead = this.gopjet.rotationYaw;
                    float f1 = (float)(this.speed * this.gopjet.getAttributeValue(Attributes.ATTACK_DAMAGE));
                    if (this.gopjet.isInWater()) {
                        this.gopjet.setAIMoveSpeed(f1 * 0.02F);
                        float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * (double)(180F / (float)Math.PI)));
                        f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
                        this.gopjet.rotationPitch = this.limitAngle(this.gopjet.rotationPitch, f2, 5.0F);
                        float f3 = MathHelper.cos(this.gopjet.rotationPitch * ((float)Math.PI / 180F));
                        float f4 = MathHelper.sin(this.gopjet.rotationPitch * ((float)Math.PI / 180F));
                        this.gopjet.moveForward = f3 * f1;
                        this.gopjet.moveVertical = -f4 * f1;
                    } else {
                        this.gopjet.setAIMoveSpeed(f1 * 0.1F);
                    }

                }
            } else {
                this.gopjet.setAIMoveSpeed(0.0F);
                this.gopjet.setMoveStrafing(0.0F);
                this.gopjet.setMoveVertical(0.0F);
                this.gopjet.setMoveForward(0.0F);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void func_208401_a(IParticleData p_208401_1_) {
        for(int i = 0; i < 7; ++i) {
            double d0 = this.rand.nextGaussian() * 0.01D;
            double d1 = this.rand.nextGaussian() * 0.01D;
            double d2 = this.rand.nextGaussian() * 0.01D;
            this.world.addParticle(p_208401_1_, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.2D, this.getPosZRandom(1.0D), d0, d1, d2);
        }
    }

    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(FinsItems.BLU_WEE_BUCKET.get());
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
}
