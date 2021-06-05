package teamdraco.fins.common.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.fins.init.FinsItems;
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
import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.util.EntityPredicates.NO_CREATIVE_OR_SPECTATOR;
import static net.minecraft.util.EntityPredicates.NO_SPECTATORS;

public class GopjetEntity extends AbstractFishEntity {
    private static final EntityPredicate SWIM_WITH_PLAYER_TARGETING = (new EntityPredicate()).range(10.0D).allowSameTeam().allowInvulnerable().allowUnseeable();
    private static final DataParameter<Boolean> IS_BOOSTING = EntityDataManager.defineId(GopjetEntity.class, DataSerializers.BOOLEAN);
    private static final int BOOST_TIMER = 400;
    private int boostTimer = BOOST_TIMER;

    public GopjetEntity(EntityType<? extends GopjetEntity> type, World world) {
        super(type, world);
        this.moveControl = new GopjetEntity.MoveHelperController(this);
        this.lookControl = new DolphinLookController(this, 10);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, TealArrowfishEntity.class, 6, 1.0D, 1.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, NO_SPECTATORS::test));
        this.goalSelector.addGoal(2, new GopjetEntity.SwimGoal(this));
        this.goalSelector.addGoal(2, new GopjetEntity.SwimWithPlayerGoal(this, 4.0D));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10).add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_BOOSTING, false);
    }

    @Override
    public void tick() {
        List<PlayerEntity> list = level.getEntitiesOfClass(PlayerEntity.class, this.getBoundingBox().inflate(3.0D), NO_CREATIVE_OR_SPECTATOR);

        super.tick();
        if (boostTimer > 0) {
            --boostTimer;
        }
        if (boostTimer == 0 || !list.isEmpty()/* && !this.fromBucket()*/) {
            boostTimer = BOOST_TIMER;
            setDeltaMovement(calculateViewVector(xRot, yRot).multiply(2.0d, 0.0d, 2.0d));
        }
        if (boostTimer <= 350) {
            setBoosting(false);
        }
        level.broadcastEntityEvent(this, (byte)38);
        if (isBoosting()) {
            level.broadcastEntityEvent(this, (byte)39);
        }
    }

    public void setBoosting(boolean isBoosting) {
        this.getEntityData().set(IS_BOOSTING, isBoosting);
    }

    public boolean isBoosting() {
        return this.getEntityData().get(IS_BOOSTING);
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(FinsItems.GOPJET_BUCKET.get());
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

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 38) {
            this.swimmingParticles(ParticleTypes.BUBBLE);
        }
        if (id == 39) {
            this.boostingParticles(ParticleTypes.BUBBLE);
        } else {
            super.handleEntityEvent(id);
        }

    }

    @OnlyIn(Dist.CLIENT)
    private void swimmingParticles(IParticleData p_208401_1_) {
        double d0 = this.random.nextGaussian() * 0.056D;
        double d1 = this.random.nextGaussian() * 0.034D;
        double d2 = this.random.nextGaussian() * 0.025D;
        this.level.addParticle(p_208401_1_, this.getX(), this.getRandomY(), this.getZ(), d0, d1, d2);
    }

    @OnlyIn(Dist.CLIENT)
    private void boostingParticles(IParticleData p_208401_1_) {
        for (int i = 0; i < 4; i++) {
            double d0 = this.random.nextGaussian() * 0.056D;
            double d1 = this.random.nextGaussian() * 0.034D;
            double d2 = this.random.nextGaussian() * 0.025D;
            this.level.addParticle(p_208401_1_, this.getX(), this.getRandomY(), this.getZ(), d0, d1, d2);
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

        public boolean canUse() {
            return super.canUse();
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
                this.gopjet.setDeltaMovement(this.gopjet.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MovementController.Action.MOVE_TO && !this.gopjet.getNavigation().isDone()) {
                double d0 = this.wantedX - this.gopjet.getX();
                double d1 = this.wantedY - this.gopjet.getY();
                double d2 = this.wantedZ - this.gopjet.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double)2.5000003E-7F) {
                    this.mob.setZza(0.0F);
                } else {
                    float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.gopjet.yRot = this.rotlerp(this.gopjet.yRot, f, 10.0F);
                    this.gopjet.yBodyRot = this.gopjet.yRot;
                    this.gopjet.yHeadRot = this.gopjet.yRot;
                    float f1 = (float)(this.speedModifier * this.gopjet.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.gopjet.isInWater()) {
                        this.gopjet.setSpeed(f1 * 0.02F);
                        float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * (double)(180F / (float)Math.PI)));
                        f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
                        this.gopjet.xRot = this.rotlerp(this.gopjet.xRot, f2, 5.0F);
                        float f3 = MathHelper.cos(this.gopjet.xRot * ((float)Math.PI / 180F));
                        float f4 = MathHelper.sin(this.gopjet.xRot * ((float)Math.PI / 180F));
                        this.gopjet.zza = f3 * f1;
                        this.gopjet.yya = -f4 * f1;
                    } else {
                        this.gopjet.setSpeed(f1 * 0.1F);
                    }

                }
            } else {
                this.gopjet.setSpeed(0.0F);
                this.gopjet.setXxa(0.0F);
                this.gopjet.setYya(0.0F);
                this.gopjet.setZza(0.0F);
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
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            this.targetPlayer = this.gopjet.level.getNearestPlayer(GopjetEntity.SWIM_WITH_PLAYER_TARGETING, this.gopjet);
            if (this.targetPlayer == null) {
                return false;
            } else {
                return this.targetPlayer.isSwimming() && this.gopjet.getTarget() != this.targetPlayer;
            }
        }

        public boolean canContinueToUse() {
            return this.targetPlayer != null && this.targetPlayer.isSwimming() && this.gopjet.distanceToSqr(this.targetPlayer) < 256.0D;
        }

        public void stop() {
            this.targetPlayer = null;
            this.gopjet.getNavigation().stop();
        }

        public void tick() {
            this.gopjet.getLookControl().setLookAt(this.targetPlayer, (float)(this.gopjet.getMaxHeadYRot() + 20), (float)this.gopjet.getMaxHeadXRot());
            if (this.gopjet.distanceToSqr(this.targetPlayer) < 6.25D) {
                this.gopjet.getNavigation().stop();
            } else {
                this.gopjet.getNavigation().moveTo(this.targetPlayer, this.speed);
            }
        }
    }
}
