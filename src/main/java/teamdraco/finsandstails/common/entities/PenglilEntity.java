package teamdraco.finsandstails.common.entities;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import teamdraco.finsandstails.common.entities.util.GroundAndSwimmerNavigator;
import teamdraco.finsandstails.registry.FTItems;
import teamdraco.finsandstails.registry.FinsSounds;

import javax.annotation.Nullable;
import java.util.Random;

public class PenglilEntity extends TameableEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(PenglilEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> IS_LYING = EntityDataManager.defineId(PenglilEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> RELAX_STATE_ONE = EntityDataManager.defineId(PenglilEntity.class, DataSerializers.BOOLEAN);

    public PenglilEntity(EntityType<? extends PenglilEntity> type, World world) {
        super(type, world);
        this.moveControl = new PenglilEntity.MoveHelperController(this);
        this.maxUpStep = 1;
    }

    @Override
    protected PathNavigator createNavigation(World worldIn) {
        return new GroundAndSwimmerNavigator(this, level);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    public static boolean canPenglilSpawn(EntityType<? extends TameableEntity> penglil, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getBlockState(pos.below()).getBlock() == Blocks.SAND && worldIn.getRawBrightness(pos, 0) > 8;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SitGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PapaWeeEntity.class, 8.0F, 1.6D, 1.4D));
        this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1.0D, 10) {
            @Override
            public boolean canUse() {
                return !this.mob.isInWater() && super.canUse();
            }
        });
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 1) {
            @Override
            public boolean canUse() {
                return super.canUse() && isInWater();
            }
        });
        this.goalSelector.addGoal(3, new PenglilEntity.MorningGiftGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PeaWeeEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, BluWeeEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, BandedRedbackShrimpEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, HighFinnedBlueEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, WeeWeeEntity.class, true));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10).add(Attributes.ATTACK_DAMAGE, 1).add(Attributes.MOVEMENT_SPEED, 0.15);
    }

    public void setTame(boolean tamed) {
        super.setTame(tamed);
        if (tamed) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
            this.setHealth(20.0F);
        } else {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
        }
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        Item item = heldItem.getItem();
        ItemStack itemstack1 = new ItemStack(FTItems.PENGLIL_BUCKET.get());
        ActionResultType actionresulttype = super.mobInteract(player, hand);

        if (heldItem.getItem() == Items.BUCKET && this.isAlive() && !this.isOrderedToSit()) {
            playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            heldItem.shrink(1);
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

        float maxHealth = this.getMaxHealth();
        float health = this.getHealth();
        if (heldItem.getItem() == FTItems.BLU_WEE.get() && health < maxHealth) {
            if (!player.isCreative()) {
                heldItem.shrink(1);
            }
            heal(2);
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level.addParticle(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            return ActionResultType.SUCCESS;
        }

        if (item == FTItems.HIGH_FINNED_BLUE.get() && !this.isTame()) {
            if (!player.abilities.instabuild) {
                heldItem.shrink(1);
            }
            if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                this.tame(player);
                this.navigation.stop();
                this.setOrderedToSit(true);
                this.setTarget(null);
                this.level.broadcastEntityEvent(this, (byte) 7);
            }
            else {
                this.level.broadcastEntityEvent(this, (byte) 6);
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isOwnedBy(player) && item != FTItems.HIGH_FINNED_BLUE.get()){
            setOrderedToSit(!isInSittingPose());
            this.jumping = false;
            this.navigation.stop();
            return ActionResultType.SUCCESS;
        }

        return actionresulttype;
    }

    @Override
    public int getAmbientSoundInterval() {
        return 480;
    }

    private void setBucketData(ItemStack bucket) {
        CompoundNBT compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("Variant", this.getVariant());
        if (isTame()) {
            compoundnbt.putUUID("Owner", getOwnerUUID());
        }
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public void travel(Vector3d travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(IS_LYING, false);
        this.entityData.define(RELAX_STATE_ONE, false);
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

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if (dataTag == null) {
            setVariant(random.nextInt(8));
        } else {
            if (dataTag.contains("Variant", 3)){
                this.setVariant(dataTag.getInt("Variant"));
            }
            if (dataTag.hasUUID("Owner")) {
                this.setOwnerUUID(dataTag.getUUID("Owner"));
                this.setTame(true);
            }
        }
        return spawnDataIn;
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    protected SoundEvent getAmbientSound() {
        return FinsSounds.PENGLIL_AMBIENT.get();
    }

    protected SoundEvent getDeathSound() {
        return FinsSounds.PENGLIL_DEATH.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FinsSounds.PENGLIL_HURT.get();
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FTItems.PENGLIL_SPAWN_EGG.get());
    }

    public boolean isLying() {
        return this.entityData.get(IS_LYING);
    }

    public void setLying(boolean p_213419_1_) {
        this.entityData.set(IS_LYING, p_213419_1_);
    }

    public void setRelaxStateOne(boolean p_213415_1_) {
        this.entityData.set(RELAX_STATE_ONE, p_213415_1_);
    }

    public boolean isRelaxStateOne() {
        return this.entityData.get(RELAX_STATE_ONE);
    }

    static class MorningGiftGoal extends Goal {
        private final PenglilEntity penglil;
        private PlayerEntity owner;
        private BlockPos bedPos;
        private int tickCounter;

        public MorningGiftGoal(PenglilEntity catIn) {
            this.penglil = catIn;
        }

        public boolean canUse() {
            if (!this.penglil.isTame()) {
                return false;
            } else if (this.penglil.isOrderedToSit()) {
                return false;
            } else {
                LivingEntity livingentity = this.penglil.getOwner();
                if (livingentity instanceof PlayerEntity) {
                    this.owner = (PlayerEntity)livingentity;
                    if (!livingentity.isSleeping()) {
                        return false;
                    }

                    if (this.penglil.distanceToSqr(this.owner) > 100.0D) {
                        return false;
                    }

                    BlockPos blockpos = this.owner.blockPosition();
                    BlockState blockstate = this.penglil.level.getBlockState(blockpos);
                    if (blockstate.getBlock().is(BlockTags.BEDS)) {
                        this.bedPos = blockstate.getOptionalValue(BedBlock.FACING).map((p_234186_1_) -> {
                            return blockpos.relative(p_234186_1_.getOpposite());
                        }).orElseGet(() -> {
                            return new BlockPos(blockpos);
                        });
                        return !this.spaceIsOccupied();
                    }
                }

                return false;
            }
        }

        private boolean spaceIsOccupied() {
            for(PenglilEntity penglilentity : this.penglil.level.getEntitiesOfClass(PenglilEntity.class, (new AxisAlignedBB(this.bedPos)).inflate(2.0D))) {
                if (penglilentity != this.penglil && (penglilentity.isLying() || penglilentity.isRelaxStateOne())) {
                    return true;
                }
            }

            return false;
        }

        public boolean canContinueToUse() {
            return this.penglil.isTame() && !this.penglil.isOrderedToSit() && this.owner != null && this.owner.isSleeping() && this.bedPos != null && !this.spaceIsOccupied();
        }

        public void start() {
            if (this.bedPos != null) {
                this.penglil.setInSittingPose(false);
                this.penglil.getNavigation().moveTo(this.bedPos.getX(), this.bedPos.getY(), this.bedPos.getZ(), 1.1F);
            }

        }

        public void stop() {
            this.penglil.setLying(false);
            float f = this.penglil.level.getTimeOfDay(1.0F);
            if (this.owner.getSleepTimer() >= 100 && (double)f > 0.77D && (double)f < 0.8D && (double)this.penglil.level.getRandom().nextFloat() < 0.5D) {
                this.giveMorningGift();
            }

            this.tickCounter = 0;
            this.penglil.setRelaxStateOne(false);
            this.penglil.getNavigation().stop();
        }

        private void giveMorningGift() {
            Random random = this.penglil.getRandom();
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
            blockpos$mutable.set(this.penglil.blockPosition());
            this.penglil.randomTeleport((double)(blockpos$mutable.getX() + random.nextInt(11) - 5), (double)(blockpos$mutable.getY() + random.nextInt(5) - 2), (double)(blockpos$mutable.getZ() + random.nextInt(11) - 5), false);
            blockpos$mutable.set(this.penglil.blockPosition());
            LootTable loottable = this.penglil.level.getServer().getLootTables().get(LootTables.FISHING);
            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)this.penglil.level)).withParameter(LootParameters.ORIGIN, this.penglil.position()).withParameter(LootParameters.THIS_ENTITY, this.penglil).withRandom(random);

            for(ItemStack itemstack : loottable.getRandomItems(lootcontext$builder.create(LootParameterSets.GIFT))) {
                this.penglil.level.addFreshEntity(new ItemEntity(this.penglil.level, (double)blockpos$mutable.getX() - (double)MathHelper.sin(this.penglil.yBodyRot * ((float)Math.PI / 180F)), (double)blockpos$mutable.getY(), (double)blockpos$mutable.getZ() + (double)MathHelper.cos(this.penglil.yBodyRot * ((float)Math.PI / 180F)), itemstack));
            }

        }

        public void tick() {
            if (this.owner != null && this.bedPos != null) {
                this.penglil.setInSittingPose(false);
                this.penglil.getNavigation().moveTo(this.bedPos.getX(), this.bedPos.getY(), this.bedPos.getZ(), 1.1F);
                if (this.penglil.distanceToSqr(this.owner) < 2.5D) {
                    ++this.tickCounter;
                    if (this.tickCounter > 16) {
                        this.penglil.setLying(true);
                        this.penglil.setRelaxStateOne(false);
                    } else {
                        this.penglil.lookAt(this.owner, 45.0F, 45.0F);
                        this.penglil.setRelaxStateOne(true);
                    }
                } else {
                    this.penglil.setLying(false);
                }
            }

        }
    }

    static class MoveHelperController extends MovementController {
        private final PenglilEntity penglil;

        MoveHelperController(PenglilEntity penglil) {
            super(penglil);
            this.penglil = penglil;
        }

        private void updateSpeed() {
            if (this.penglil.isInWater()) {
                this.penglil.setDeltaMovement(this.penglil.getDeltaMovement().add(0.0D, 0.005D, 0.0D));

                if (this.penglil.isBaby()) {
                    this.penglil.setSpeed(Math.max(this.penglil.getSpeed() / 3.0F, 0.06F));
                }
            }
            else if (this.penglil.onGround) {
                this.penglil.setSpeed(Math.max(this.penglil.getSpeed(), 0.06F));
            }
        }

        public void tick() {
            this.updateSpeed();
            if (this.operation == MovementController.Action.MOVE_TO && !this.penglil.getNavigation().isDone()) {
                double d0 = this.wantedX - this.penglil.getX();
                double d1 = this.wantedY - this.penglil.getY();
                double d2 = this.wantedZ - this.penglil.getZ();
                double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.penglil.yRot = this.rotlerp(this.penglil.yRot, f, 90.0F);
                this.penglil.yBodyRot = this.penglil.yRot;
                float f1 = (float)(this.speedModifier * this.penglil.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.penglil.setSpeed(MathHelper.lerp(0.125F, this.penglil.getSpeed(), f1));
                this.penglil.setDeltaMovement(this.penglil.getDeltaMovement().add(0.0D, (double)this.penglil.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.penglil.setSpeed(0.0F);
            }
        }
    }
}