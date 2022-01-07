package teamdraco.finsandstails.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
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
import teamdraco.finsandstails.common.entities.util.goals.WeeHurtByEntityGoal;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;

import java.util.List;

public class WeeWeeEntity extends AbstractFishEntity {

    public WeeWeeEntity(EntityType<? extends WeeWeeEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, LivingEntity.class, 6, 1.0D, 1.5D));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
        this.goalSelector.addGoal(2, new WeeHurtByEntityGoal(this));
        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 1.0D, 40));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D);
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(FTItems.WEE_WEE_BUCKET.get());
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
        return new ItemStack(FTItems.WEE_WEE_SPAWN_EGG.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (random.nextInt(2500) == 0 && shouldSpawnPapaWee()) {
            PapaWeeEntity papaWee = FTEntities.PAPA_WEE.get().create(level);
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
}
