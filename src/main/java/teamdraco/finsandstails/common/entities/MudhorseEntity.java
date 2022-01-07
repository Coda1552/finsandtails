package teamdraco.finsandstails.common.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import teamdraco.finsandstails.common.entities.util.goals.MudhorseForageGoal;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;
import teamdraco.finsandstails.registry.FtSounds;

import java.util.EnumSet;

public class MudhorseEntity extends AnimalEntity implements IRangedAttackMob {
    private LivingEntity commander;
    private int commanderSetTime;
    private int attackTimer;
    public int eatAnimationTick;
    private MudhorseForageGoal eatBlockGoal;

    public MudhorseEntity(EntityType<? extends MudhorseEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.eatBlockGoal = new MudhorseForageGoal(this);
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 20, 10.0F));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.25D, Ingredient.of(FTItems.SWAMP_MUCKER.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, this.eatBlockGoal);
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new CommanderHurt(this));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 30).add(Attributes.ATTACK_DAMAGE, 2).add(Attributes.MOVEMENT_SPEED, 0.2);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getEntity();

            if (entity != null && !(entity instanceof AbstractArrowEntity)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.hurt(source, amount);
        }
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        this.attackTimer = 10;
        this.level.broadcastEntityEvent(this, (byte) 4);
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
        if (flag) {
            entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(0.0D, (double) 0.3F, 0.0D));
            this.doEnchantDamageEffects(this, entityIn);
        }

        return flag;
    }

    public void performRangedAttack(LivingEntity p_82196_1_, float p_82196_2_) {
        SnowballEntity snowballentity = new SnowballEntity(this.level, this);
        double d0 = p_82196_1_.getEyeY() - (double)1.1F;
        double d1 = p_82196_1_.getX() - this.getX();
        double d2 = d0 - snowballentity.getY();
        double d3 = p_82196_1_.getZ() - this.getZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        snowballentity.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.EGG_THROW, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(snowballentity);
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadEatAngleScale(float p_70890_1_) {
        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
            float f = ((float)(this.eatAnimationTick - 4) - p_70890_1_) / 32.0F;
            return ((float)Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
        } else {
            return this.eatAnimationTick > 0 ? ((float)Math.PI / 5F) : this.xRot * ((float)Math.PI / 180F);
        }
    }

    @Override
    protected void customServerAiStep() {
        this.eatAnimationTick = this.eatBlockGoal.getEatAnimationTick();
        super.customServerAiStep();
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == FTItems.SWAMP_MUCKER.get();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FtSounds.MUDHORSE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FtSounds.MUDHORSE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FtSounds.MUDHORSE_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.HORSE_STEP, 0.15F, 1.0F);
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public void aiStep() {
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
        if (commanderSetTime > 0) {
            --commanderSetTime;
        } else {
            commander = null;
        }

        if (this.level.isClientSide) {
            this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        }
        super.aiStep();
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            this.attackTimer = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        }
        if (id == 10) {
            this.eatAnimationTick = 40;
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity ageable) {
        return FTEntities.MUDHORSE.get().create(world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isBaby() ? 0.7F : 1.4F;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FTItems.MUDHORSE_SPAWN_EGG.get());
    }

    public LivingEntity getCommander() {
        return commander;
    }

    public void setCommander(LivingEntity commander) {
        this.commander = commander;
        commanderSetTime = 600;
    }

    private static class CommanderHurt extends TargetGoal {
        private final MudhorseEntity mudhorse;
        private LivingEntity attacker;
        private int timestamp;

        public CommanderHurt(MudhorseEntity mudhorse) {
            super(mudhorse, false);
            this.mudhorse = mudhorse;
            this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        }

        public boolean canUse() {
            LivingEntity livingentity = this.mudhorse.getCommander();
            if (livingentity != null) {
                this.attacker = livingentity.getLastHurtMob();
                int i = livingentity.getLastHurtMobTimestamp();
                if (i != this.timestamp && this.canAttack(this.attacker, EntityPredicate.DEFAULT)) {
                    if (!(targetMob instanceof CreeperEntity) && !(targetMob instanceof GhastEntity)) {
                        if (targetMob instanceof WolfEntity) {
                            WolfEntity wolfentity = (WolfEntity) targetMob;
                            return !wolfentity.isTame() || wolfentity.getOwner() != livingentity;
                        } else if (targetMob instanceof PlayerEntity && livingentity instanceof PlayerEntity && !((PlayerEntity) livingentity).canHarmPlayer((PlayerEntity) targetMob)) {
                            return false;
                        } else if (targetMob instanceof AbstractHorseEntity && ((AbstractHorseEntity) targetMob).isTamed()) {
                            return false;
                        } else {
                            return !(targetMob instanceof TameableEntity) || !((TameableEntity) targetMob).isTame();
                        }
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }

        public void start() {
            this.mob.setTarget(this.attacker);
            LivingEntity livingentity = this.mudhorse.getCommander();
            if (livingentity != null) {
                this.timestamp = livingentity.getLastHurtMobTimestamp();
            }

            super.start();
        }
    }
}
