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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;
import teamdraco.fins.init.FinsEntities;
import teamdraco.fins.init.FinsItems;
import teamdraco.fins.init.FinsSounds;

public class WherbleEntity extends AnimalEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(WherbleEntity.class, DataSerializers.INT);

    public WherbleEntity(EntityType<? extends AnimalEntity> p_i48568_1_, World p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new MateGoal(this, 1.0D));
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
            if (isFood(heldItem) && canFindSnow() && !this.level.isClientSide && this.getAge() == 0 && this.canFallInLove()) {
                setInLove(player);
                this.usePlayerItem(player, heldItem);
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
        return spawnDataIn;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.BEETROOT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FinsSounds.MUDHORSE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FinsSounds.MUDHORSE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FinsSounds.MUDHORSE_DEATH.get();
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

    private boolean canFindSnow() {
        BlockPos blockpos = this.blockPosition();
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 8; ++j) {
                for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
                    for(int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l) {
                        blockpos$mutable.setWithOffset(blockpos, k, i, l);
                        if (this.level.getBlockState(blockpos$mutable).is(Blocks.SNOW) || this.level.getBlockState(blockpos$mutable).is(Blocks.SNOW_BLOCK)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    class MateGoal extends BreedGoal {
        private final WherbleEntity wherble;

        public MateGoal(WherbleEntity p_i229957_2_, double p_i229957_3_) {
            super(p_i229957_2_, p_i229957_3_);
            this.wherble = p_i229957_2_;
        }

        public boolean canUse() {
            if (super.canUse()) {
                if (!canFindSnow()) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }


    }
}
