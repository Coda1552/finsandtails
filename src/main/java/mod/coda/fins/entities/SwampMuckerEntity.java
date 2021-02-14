package mod.coda.fins.entities;

import mod.coda.fins.entities.ai.SwampMuckerJumpGoal;
import mod.coda.fins.init.FinsItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SwampMuckerEntity  extends AbstractGroupFishEntity {
    public SwampMuckerEntity(EntityType<? extends SwampMuckerEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new SwampMuckerJumpGoal(this, 2));
        this.goalSelector.addGoal(1, new SwimGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute func_234176_m_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 6).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5);
    }

    @Override
    public int getMaxGroupSize() {
        return 5;
    }

    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(FinsItems.SWAMP_MUCKER_BUCKET.get());
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

    static class SwimGoal extends RandomSwimmingGoal {
        private final SwampMuckerEntity fish;

        public SwimGoal(SwampMuckerEntity fish) {
            super(fish, 1.0D, 40);
            this.fish = fish;
        }

        public boolean shouldExecute() {
            return this.fish.func_212800_dy() && super.shouldExecute();
        }
    }


    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.SWAMP_MUCKER_SPAWN_EGG.get());
    }
}
