package teamdraco.finsandstails.common.entities;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;

public class RiverPebbleSnailEntity extends AnimalEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(RiverPebbleSnailEntity.class, DataSerializers.INT);

    public RiverPebbleSnailEntity(EntityType<? extends RiverPebbleSnailEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveControl = new RiverPebbleSnailEntity.MoveHelperController(this);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(Items.BROWN_MUSHROOM), false));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    protected void ageBoundaryReached() {
        super.ageBoundaryReached();
        if (!this.isBaby() && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.spawnAtLocation(FTItems.RIVER_PEBBLE_SNAIL_SHELL.get(), 1);
        }
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 4).add(Attributes.MOVEMENT_SPEED, 0.15);
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.9F;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENDERMITE_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity ageable) {
        RiverPebbleSnailEntity snail = FTEntities.RIVER_PEBBLE_SNAIL.get().create(world);
        if (ageable instanceof RiverPebbleSnailEntity) {
            snail.setVariant(random.nextInt(5));
        }
        return snail;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.25F;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FTItems.RIVER_PEBBLE_SNAIL_SPAWN_EGG.get());
    }

    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.BROWN_MUSHROOM;
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getItemInHand(hand);

        if (heldItem.getItem() == Items.FLOWER_POT && this.isAlive() && !this.isBaby()) {
            playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            heldItem.shrink(1);
            ItemStack itemstack1 = new ItemStack(FTItems.RIVER_PEBBLE_SNAIL_POT.get());
            this.setBucketData(itemstack1);
            if (!this.level.isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) player, itemstack1);
            }
            if (heldItem.isEmpty()) {
                player.setItemInHand(hand, itemstack1);
            } else if (!player.inventory.add(itemstack1)) {
                player.drop(itemstack1, false);
            }
            this.remove();
            return ActionResultType.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    private void setBucketData(ItemStack bucket) {
        CompoundNBT compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("Variant", this.getVariant());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @javax.annotation.Nullable ILivingEntityData spawnDataIn, @javax.annotation.Nullable CompoundNBT dataTag) {
        if (dataTag == null) {
            setVariant(random.nextInt(5));
        } else {
            if (dataTag.contains("Variant", 3)) {
                this.setVariant(dataTag.getInt("Variant"));
            }
        }
        return spawnDataIn;
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

    protected PathNavigator createNavigation(World world) {
        return new GroundPathNavigator(this, world);
    }

    static class MoveHelperController extends MovementController {
        private final RiverPebbleSnailEntity snail;

        MoveHelperController(RiverPebbleSnailEntity snail) {
            super(snail);
            this.snail = snail;
        }

        public void tick() {
            if (this.snail.isEyeInFluid(FluidTags.WATER)) {
                this.snail.setDeltaMovement(this.snail.getDeltaMovement().add(0.0D, 0.0D, 0.0D));
            }

            if (this.operation == Action.MOVE_TO && !this.snail.getNavigation().isDone()) {
                double d0 = this.wantedX - this.snail.getX();
                double d1 = this.wantedY - this.snail.getY();
                double d2 = this.wantedZ - this.snail.getZ();
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.snail.yRot = this.rotlerp(this.snail.yRot, f, 90.0F);
                this.snail.yBodyRot = this.snail.yRot;
                float f1 = (float) (this.speedModifier * this.snail.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.snail.setSpeed(MathHelper.lerp(0.125F, this.snail.getSpeed(), f1));
                this.snail.setDeltaMovement(this.snail.getDeltaMovement().add(0.0D, (double) this.snail.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.snail.setSpeed(0.0F);
            }
        }
    }
}