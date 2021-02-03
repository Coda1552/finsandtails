package mod.coda.fins.entity;

import mod.coda.fins.init.FinsEntities;
import mod.coda.fins.init.FinsItems;
import mod.coda.fins.init.FinsSounds;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class MudhorseEntity extends AnimalEntity {
    private LivingEntity commander;
    private int commanderSetTime;
    private int attackTimer;

    public MudhorseEntity(EntityType<? extends MudhorseEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(FinsItems.SWAMP_MUCKER.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new CommanderHurt(this));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public static AttributeModifierMap.MutableAttribute func_234176_m_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 30).createMutableAttribute(Attributes.ATTACK_DAMAGE, 2).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2);
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getTrueSource();

            if (entity != null && !(entity instanceof AbstractArrowEntity)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        this.attackTimer = 10;
        this.world.setEntityState(this, (byte) 4);
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
        if (flag) {
            entityIn.setMotion(entityIn.getMotion().add(0.0D, (double) 0.3F, 0.0D));
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == FinsItems.SWAMP_MUCKER.get();
    }

    protected SoundEvent getAmbientSound() {
        return FinsSounds.MUDHORSE_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FinsSounds.MUDHORSE_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return FinsSounds.MUDHORSE_DEATH.get();
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_HORSE_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    public void livingTick() {
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
        if (commanderSetTime > 0) {
            --commanderSetTime;
        } else {
            commander = null;
        }
        super.livingTick();
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 4) {
            this.attackTimer = 10;
            this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
        return FinsEntities.MUDHORSE.get().create(world);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? 1.0F : 2.15F;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.MUDHORSE_SPAWN_EGG.get());
    }

    public LivingEntity getCommander() {
        return commander;
    }

    public void setCommander(LivingEntity commander) {
        this.commander = commander;
        commanderSetTime = 200;
    }

    private static class CommanderHurt extends TargetGoal {
        private final MudhorseEntity mudHorse;
        private LivingEntity attacker;
        private int timestamp;

        public CommanderHurt(MudhorseEntity mudHorse) {
            super(mudHorse, false);
            this.mudHorse = mudHorse;
            this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
        }

        public boolean shouldExecute() {
            LivingEntity livingentity = this.mudHorse.getCommander();
            if (livingentity != null) {
                this.attacker = livingentity.getLastAttackedEntity();
                int i = livingentity.getLastAttackedEntityTime();
                if (i != this.timestamp && this.isSuitableTarget(this.attacker, EntityPredicate.DEFAULT)) {
                    if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
                        if (target instanceof WolfEntity) {
                            WolfEntity wolfentity = (WolfEntity) target;
                            return !wolfentity.isTamed() || wolfentity.getOwner() != livingentity;
                        } else if (target instanceof PlayerEntity && livingentity instanceof PlayerEntity && !((PlayerEntity) livingentity).canAttackPlayer((PlayerEntity) target)) {
                            return false;
                        } else if (target instanceof AbstractHorseEntity && ((AbstractHorseEntity) target).isTame()) {
                            return false;
                        } else {
                            return !(target instanceof TameableEntity) || !((TameableEntity) target).isTamed();
                        }
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }

        public void startExecuting() {
            this.goalOwner.setAttackTarget(this.attacker);
            LivingEntity livingentity = this.mudHorse.getCommander();
            if (livingentity != null) {
                this.timestamp = livingentity.getLastAttackedEntityTime();
            }

            super.startExecuting();
        }
    }
}
