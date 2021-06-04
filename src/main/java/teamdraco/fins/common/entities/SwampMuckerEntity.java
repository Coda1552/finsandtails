package teamdraco.fins.common.entities;

import teamdraco.fins.common.entities.util.ai.SwampMuckerJumpGoal;
import teamdraco.fins.init.FinsItems;
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

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 6).add(Attributes.MOVEMENT_SPEED, 0.5);
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(FinsItems.SWAMP_MUCKER_BUCKET.get());
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

    static class SwimGoal extends RandomSwimmingGoal {
        private final SwampMuckerEntity fish;

        public SwimGoal(SwampMuckerEntity fish) {
            super(fish, 1.0D, 40);
            this.fish = fish;
        }

        public boolean canUse() {
            return this.fish.canRandomSwim() && super.canUse();
        }
    }


    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.SWAMP_MUCKER_SPAWN_EGG.get());
    }
}
