package teamdraco.fins.common.entities;

import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.fins.init.FinsItems;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.EnumSet;

public class GopjetEntity extends AbstractFishEntity {
    private static final EntityPredicate field_213810_bA = (new EntityPredicate()).setDistance(10.0D).allowFriendlyFire().allowInvulnerable().setLineOfSiteRequired();
    private static final DataParameter<Boolean> IS_BOOSTING = EntityDataManager.createKey(GopjetEntity.class, DataSerializers.BOOLEAN);
    private static final int BOOST_TIMER = 400;
    private int boostTimer = BOOST_TIMER;

    public GopjetEntity(EntityType<? extends GopjetEntity> type, World world) {
        super(type, world);
        this.moveController = new GopjetEntity.MoveHelperController(this);
        this.lookController = new DolphinLookController(this, 10);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, TealArrowfishEntity.class, 6, 1.0D, 1.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NOT_SPECTATING::test));
        this.goalSelector.addGoal(2, new GopjetEntity.SwimGoal(this));
        this.goalSelector.addGoal(2, new GopjetEntity.SwimWithPlayerGoal(this, 4.0D));
    }

    public static AttributeModifierMap.MutableAttribute func_234176_m_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(IS_BOOSTING, false);
    }

    @Override
    public void tick() {
        super.tick();
        if (boostTimer > 0) {
            --boostTimer;
        }
        if (boostTimer == 0) {
            boostTimer = BOOST_TIMER;
            setMotion(getVectorForRotation(rotationPitch, rotationYaw).mul(2.0d, 0.0d, 2.0d));
        }
        if (boostTimer <= 350) {
            setBoosting(false);
        }
        world.setEntityState(this, (byte)38);
        if (isBoosting()) {
            world.setEntityState(this, (byte)39);
        }
    }

    public void setBoosting(boolean isBoosting) {
        this.getDataManager().set(IS_BOOSTING, isBoosting);
    }

    public boolean isBoosting() {
        return this.getDataManager().get(IS_BOOSTING);
    }

    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(FinsItems.GOPJET_BUCKET.get());
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

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 38) {
            this.swimmingParticles(ParticleTypes.BUBBLE);
        }
        if (id == 39) {
            this.boostingParticles(ParticleTypes.BUBBLE);
        } else {
            super.handleStatusUpdate(id);
        }

    }

    @OnlyIn(Dist.CLIENT)
    private void swimmingParticles(IParticleData p_208401_1_) {
        double d0 = this.rand.nextGaussian() * 0.056D;
        double d1 = this.rand.nextGaussian() * 0.034D;
        double d2 = this.rand.nextGaussian() * 0.025D;
        this.world.addParticle(p_208401_1_, this.getPosX(), this.getPosYRandom(), this.getPosZ(), d0, d1, d2);
    }

    @OnlyIn(Dist.CLIENT)
    private void boostingParticles(IParticleData p_208401_1_) {
        for (int i = 0; i < 4; i++) {
            double d0 = this.rand.nextGaussian() * 0.056D;
            double d1 = this.rand.nextGaussian() * 0.034D;
            double d2 = this.rand.nextGaussian() * 0.025D;
            this.world.addParticle(p_208401_1_, this.getPosX(), this.getPosYRandom(), this.getPosZ(), d0, d1, d2);
        }
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.GOPJET_SPAWN_EGG.get());
    }

    static class SwimGoal extends RandomSwimmingGoal {
        private final GopjetEntity fish;

        public SwimGoal(GopjetEntity fish) {
            super(fish, 1.5D, 40);
            this.fish = fish;
        }

        public boolean shouldExecute() {
            return super.shouldExecute();
        }
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
                    float f1 = (float)(this.speed * this.gopjet.getAttributeValue(Attributes.MOVEMENT_SPEED));
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

    static class SwimWithPlayerGoal extends Goal {
        private final GopjetEntity gopjet;
        private final double speed;
        private PlayerEntity targetPlayer;

        SwimWithPlayerGoal(GopjetEntity gopjetIn, double speedIn) {
            this.gopjet = gopjetIn;
            this.speed = speedIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean shouldExecute() {
            this.targetPlayer = this.gopjet.world.getClosestPlayer(GopjetEntity.field_213810_bA, this.gopjet);
            if (this.targetPlayer == null) {
                return false;
            } else {
                return this.targetPlayer.isSwimming() && this.gopjet.getAttackTarget() != this.targetPlayer;
            }
        }

        public boolean shouldContinueExecuting() {
            return this.targetPlayer != null && this.targetPlayer.isSwimming() && this.gopjet.getDistanceSq(this.targetPlayer) < 256.0D;
        }

        public void resetTask() {
            this.targetPlayer = null;
            this.gopjet.getNavigator().clearPath();
        }

        public void tick() {
            this.gopjet.getLookController().setLookPositionWithEntity(this.targetPlayer, (float)(this.gopjet.getHorizontalFaceSpeed() + 20), (float)this.gopjet.getVerticalFaceSpeed());
            if (this.gopjet.getDistanceSq(this.targetPlayer) < 6.25D) {
                this.gopjet.getNavigator().clearPath();
            } else {
                this.gopjet.getNavigator().tryMoveToEntityLiving(this.targetPlayer, this.speed);
            }
        }
    }
}
