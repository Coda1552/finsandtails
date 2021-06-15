package teamdraco.fins.common.entities;

import teamdraco.fins.common.entities.util.goals.WeeHurtByEntityGoal;
import teamdraco.fins.init.FinsEntities;
import teamdraco.fins.init.FinsItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class WeeWeeEntity extends AbstractFishEntity {
    public WeeWeeEntity(EntityType<? extends WeeWeeEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, TealArrowfishEntity.class, 6, 1.0D, 1.5D));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, SquidEntity.class, 6, 1.0D, 1.5D));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, DolphinEntity.class, 6, 1.0D, 1.5D));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, OrnateBugfishEntity.class, 6, 1.0D, 1.5D));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, PenglilEntity.class, 6, 1.0D, 1.5D));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
        this.goalSelector.addGoal(2, new WeeHurtByEntityGoal(this));
        this.goalSelector.addGoal(3, new WeeWeeEntity.SwimGoal(this));
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(FinsItems.WEE_WEE_BUCKET.get());
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
        return new ItemStack(FinsItems.WEE_WEE_SPAWN_EGG.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (random.nextInt(2500) == 0 && shouldSpawnPapaWee()) {
            PapaWeeEntity papaWee = FinsEntities.PAPA_WEE.get().create(level);
            papaWee.setPos(this.getX(), this.getY(), this.getZ());

            level.addFreshEntity(papaWee);
        }
    }

    private boolean shouldSpawnPapaWee() {
        List<WeeWeeEntity> weeList = this.level.getEntitiesOfClass(WeeWeeEntity.class, this.getBoundingBox().inflate(8.0D));
        List<PapaWeeEntity> papaWeeList = this.level.getEntitiesOfClass(PapaWeeEntity.class, this.getBoundingBox().inflate(16.0D));
        if (weeList.size() >= 10 && papaWeeList.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    static class SwimGoal extends RandomSwimmingGoal {
        private final WeeWeeEntity fish;

        public SwimGoal(WeeWeeEntity fish) {
            super(fish, 1.0D, 40);
            this.fish = fish;
        }

        public boolean canUse() {
            return super.canUse();
        }
    }
}
