package teamdraco.fins.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.FollowSchoolLeaderGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import teamdraco.fins.common.entities.util.goals.WeeHurtByEntityGoal;
import teamdraco.fins.init.FinsEntities;
import teamdraco.fins.init.FinsItems;

import javax.annotation.Nullable;
import java.util.List;

public class VibraWeeEntity extends AbstractGroupFishEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(VibraWeeEntity.class, DataSerializers.INT);

    public VibraWeeEntity(EntityType<? extends VibraWeeEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, TealArrowfishEntity.class, 6, 1.0D, 1.5D));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, OrnateBugfishEntity.class, 6, 1.0D, 1.5D));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new WeeHurtByEntityGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 40));
        this.goalSelector.addGoal(5, new FollowSchoolLeaderGoal(this));
    }

    @Override
    public int getMaxSchoolSize() {
        return 9;
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (dataTag == null) {
            setVariant(random.nextInt(15));
        } else {
            if (dataTag.contains("Variant", 3)){
                this.setVariant(dataTag.getInt("Variant"));
            }
        }
        return spawnDataIn;
    }

    protected void saveToBucketTag(ItemStack bucket) {
        CompoundNBT compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("Variant", this.getVariant());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    private void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        setVariant(compound.getInt("Variant"));
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(FinsItems.VIBRA_WEE_BUCKET.get());
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
        return new ItemStack(FinsItems.VIBRA_WEE_SPAWN_EGG.get());
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
        List<VibraWeeEntity> weeList = this.level.getEntitiesOfClass(VibraWeeEntity.class, this.getBoundingBox().inflate(8.0D));
        List<PapaWeeEntity> papaWeeList = this.level.getEntitiesOfClass(PapaWeeEntity.class, this.getBoundingBox().inflate(16.0D));
        if (weeList.size() >= 10 && papaWeeList.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }
}
