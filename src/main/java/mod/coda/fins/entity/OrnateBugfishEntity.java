package mod.coda.fins.entity;

import mod.coda.fins.init.FinsItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.fish.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class OrnateBugfishEntity extends AbstractGroupFishEntity {
    public OrnateBugfishEntity(EntityType<? extends OrnateBugfishEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 3.0D, true));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NOT_SPECTATING::test));
        this.goalSelector.addGoal(2, new OrnateBugfishEntity.SwimGoal(this));
        this.goalSelector.addGoal(5, new FollowSchoolLeaderGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, TropicalFishEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, CodEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SalmonEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, HighFinnedBlueEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, FlatbackSuckerEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SwampMuckerEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, BluWeeEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PeaWeeEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PhantomNudibranchEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, TealArrowfishEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, BandedRedbackShrimpEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, RubberBellyGliderEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, VibraWeeEntity   .class, false));
    }

    @Override
    public int getMaxGroupSize() {
        return 6;
    }

    static class SwimGoal extends RandomSwimmingGoal {
        private final OrnateBugfishEntity fish;

        public SwimGoal(OrnateBugfishEntity fish) {
            super(fish, 1.0D, 40);
            this.fish = fish;
        }

        public boolean shouldExecute() {
            return super.shouldExecute();
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

    public static AttributeModifierMap.MutableAttribute func_234176_m_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 8).createMutableAttribute(Attributes.ATTACK_DAMAGE, 1);
    }

    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(FinsItems.ORNATE_BUGFISH_BUCKET.get());
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
        return new ItemStack(FinsItems.ORNATE_BUGFISH_SPAWN_EGG.get());
    }
}
