package teamdraco.finsandstails.common.entities;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;
import software.bernie.finsandtails.geckolib3.core.IAnimatable;
import software.bernie.finsandtails.geckolib3.core.IAnimationTickable;
import software.bernie.finsandtails.geckolib3.core.PlayState;
import software.bernie.finsandtails.geckolib3.core.controller.AnimationController;
import software.bernie.finsandtails.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.finsandtails.geckolib3.core.manager.AnimationData;
import software.bernie.finsandtails.geckolib3.core.manager.AnimationFactory;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;
import teamdraco.finsandstails.registry.FtSounds;

import java.util.Random;

public class WherbleEntity extends Animal implements IAnimatable, IAnimationTickable {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(WherbleEntity.class, EntityDataSerializers.INT);
    private final AnimationFactory factory = new AnimationFactory(this);

    public WherbleEntity(EntityType<? extends Animal> p_i48568_1_, Level p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(Items.BEETROOT), false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Fox.class, 8.0F, 1.0D, 1.15D));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Wolf.class, 8.0F, 1.0D, 1.15D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8).add(Attributes.MOVEMENT_SPEED, 0.2);
    }

    public static boolean checkWherbleSpawnRules(EntityType<? extends WherbleEntity> p_223316_0_, LevelAccessor p_223316_1_, MobSpawnType p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_) {
        return p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.GRASS_BLOCK) && p_223316_1_.getRawBrightness(p_223316_3_, 0) > 8 && p_223316_1_.getRandom().nextFloat() > 0.9F;
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return source != DamageSource.FREEZE && super.hurt(source, amount);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setVariant(compound.getInt("Variant"));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        if (isBaby() && heldItem.getItem() == Items.FLOWER_POT && this.isAlive()) {
            this.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            heldItem.shrink(1);
            ItemStack bucket = new ItemStack(FTItems.BABY_WHERBLE_POT.get());
            if (this.hasCustomName()) {
                bucket.setHoverName(this.getCustomName());
            }
            if (!this.level.isClientSide) {
                bucket.getOrCreateTag().putInt("Age", getAge());
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bucket);
            }
            if (heldItem.isEmpty()) {
                player.setItemInHand(hand, bucket);
            } else if (!player.getInventory().add(bucket)) {
                player.drop(bucket, false);
            }

            this.discard();
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
        return super.mobInteract(player, hand);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (dataTag == null) {
            setVariant(random.nextInt(4));
        }
        if (spawnDataIn == null) {
            spawnDataIn = new AgeableMobGroupData(1);
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.BEETROOT;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return FtSounds.WHERBLE_AMBIENT.get();
    }

    @Override
    public SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FtSounds.WHERBLE_HURT.get();
    }

    @Override
    public SoundEvent getDeathSound() {
        return FtSounds.WHERBLE_DEATH.get();
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        WherbleEntity wherble = FTEntities.WHERBLE.get().create(p_241840_1_);
        wherble.setVariant(random.nextInt(4));
        return wherble;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(FTItems.WHERBLE_SPAWN_EGG.get());
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return this.isBaby() ? 0.2F : 0.4F;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }
}
