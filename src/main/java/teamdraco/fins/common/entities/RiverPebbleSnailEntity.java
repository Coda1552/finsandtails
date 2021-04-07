package teamdraco.fins.common.entities;

import teamdraco.fins.init.FinsEntities;
import teamdraco.fins.init.FinsItems;
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
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class RiverPebbleSnailEntity extends AnimalEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(RiverPebbleSnailEntity.class, DataSerializers.VARINT);

    public RiverPebbleSnailEntity(EntityType<? extends RiverPebbleSnailEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveController = new RiverPebbleSnailEntity.MoveHelperController(this);
        this.stepHeight = 1f;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreedGoal(this, 1.0f));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(Items.BROWN_MUSHROOM), false));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    protected void onGrowingAdult() {
        super.onGrowingAdult();
        if (!this.isChild() && this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            this.entityDropItem(FinsItems.RIVER_PEBBLE_SNAIL_SHELL.get(), 1);
        }
    }

    public static AttributeModifierMap.MutableAttribute func_234176_m_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 4).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15);
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_COD_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COD_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_ENDERMITE_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
        RiverPebbleSnailEntity snail = FinsEntities.RIVER_PEBBLE_SNAIL.get().create(world);
        if (ageable instanceof RiverPebbleSnailEntity) {
            snail.setVariant(rand.nextInt(5));
        }
        return snail;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.25F;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.RIVER_PEBBLE_SNAIL_SPAWN_EGG.get());
    }

    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.BROWN_MUSHROOM;
    }

    @Override
    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getHeldItem(hand);

        if (heldItem.getItem() == Items.FLOWER_POT && this.isAlive() && !this.isChild()) {
            playSound(SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            heldItem.shrink(1);
            ItemStack itemstack1 = new ItemStack(FinsItems.RIVER_PEBBLE_SNAIL_POT.get());
            this.setBucketData(itemstack1);
            if (!this.world.isRemote) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) player, itemstack1);
            }
            if (heldItem.isEmpty()) {
                player.setHeldItem(hand, itemstack1);
            } else if (!player.inventory.addItemStackToInventory(itemstack1)) {
                player.dropItem(itemstack1, false);
            }
            this.remove();
            return ActionResultType.SUCCESS;
        }
        return super.func_230254_b_(player, hand);
    }

    private void setBucketData(ItemStack bucket) {
        CompoundNBT compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("Variant", this.getVariant());
        if (this.hasCustomName()) {
            bucket.setDisplayName(this.getCustomName());
        }
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @javax.annotation.Nullable ILivingEntityData spawnDataIn, @javax.annotation.Nullable CompoundNBT dataTag) {
        if (dataTag == null) {
            setVariant(rand.nextInt(5));
        } else {
            if (dataTag.contains("Variant", 3)) {
                this.setVariant(dataTag.getInt("Variant"));
            }
        }
        return spawnDataIn;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(VARIANT, 0);
    }

    public int getVariant() {
        return this.dataManager.get(VARIANT);
    }

    private void setVariant(int variant) {
        this.dataManager.set(VARIANT, variant);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Variant", getVariant());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        setVariant(compound.getInt("Variant"));
    }

    protected PathNavigator createNavigator(World world) {
        return new GroundPathNavigator(this, world);
    }

    static class MoveHelperController extends MovementController {
        private final RiverPebbleSnailEntity snail;

        MoveHelperController(RiverPebbleSnailEntity snail) {
            super(snail);
            this.snail = snail;
        }

        public void tick() {
            if (this.snail.areEyesInFluid(FluidTags.WATER)) {
                this.snail.setMotion(this.snail.getMotion().add(0.0D, 0.0D, 0.0D));
            }

            if (this.action == Action.MOVE_TO && !this.snail.getNavigator().noPath()) {
                double d0 = this.posX - this.snail.getPosX();
                double d1 = this.posY - this.snail.getPosY();
                double d2 = this.posZ - this.snail.getPosZ();
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.snail.rotationYaw = this.limitAngle(this.snail.rotationYaw, f, 90.0F);
                this.snail.renderYawOffset = this.snail.rotationYaw;
                float f1 = (float) (this.speed * this.snail.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.snail.setAIMoveSpeed(MathHelper.lerp(0.125F, this.snail.getAIMoveSpeed(), f1));
                this.snail.setMotion(this.snail.getMotion().add(0.0D, (double) this.snail.getAIMoveSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.snail.setAIMoveSpeed(0.0F);
            }
        }
    }
}