package teamdraco.fins.common.entities;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;
import teamdraco.fins.init.FinsEntities;
import teamdraco.fins.init.FinsItems;
import teamdraco.fins.init.FinsSounds;

import java.util.Random;

public class WherbleEntity extends AnimalEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(WherbleEntity.class, DataSerializers.INT);

    public WherbleEntity(EntityType<? extends AnimalEntity> p_i48568_1_, World p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(Items.BEETROOT), false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, FoxEntity.class, 8.0F, 1.0D, 1.15D));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, WolfEntity.class, 8.0F, 1.0D, 1.15D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 8).add(Attributes.MOVEMENT_SPEED, 0.2);
    }

    public static boolean checkWherbleSpawnRules(EntityType<? extends WherbleEntity> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_) {
        return p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.GRASS_BLOCK) && p_223316_1_.getRawBrightness(p_223316_3_, 0) > 8 && p_223316_1_.getRandom().nextFloat() > 0.9F;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
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
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        if (isBaby() && heldItem.getItem() == Items.FLOWER_POT && this.isAlive()) {
            this.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            heldItem.shrink(1);
            ItemStack bucket = new ItemStack(FinsItems.BABY_WHERBLE_POT.get());
            if (this.hasCustomName()) {
                bucket.setHoverName(this.getCustomName());
            }
            if (!this.level.isClientSide) {
                bucket.getOrCreateTag().putInt("Age", getAge());
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) player, bucket);
            }
            if (heldItem.isEmpty()) {
                player.setItemInHand(hand, bucket);
            } else if (!player.inventory.add(bucket)) {
                player.drop(bucket, false);
            }

            this.remove();
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }
        return super.mobInteract(player, hand);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (dataTag == null) {
            setVariant(random.nextInt(4));
        }
        if (spawnDataIn == null) {
            spawnDataIn = new AgeableData(1);
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.BEETROOT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FinsSounds.WHERBLE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FinsSounds.WHERBLE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FinsSounds.WHERBLE_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        WherbleEntity wherble = FinsEntities.WHERBLE.get().create(p_241840_1_);
        wherble.setVariant(random.nextInt(4));
        return wherble;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.WHERBLE_SPAWN_EGG.get());
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isBaby() ? 0.2F : 0.4F;
    }
}
