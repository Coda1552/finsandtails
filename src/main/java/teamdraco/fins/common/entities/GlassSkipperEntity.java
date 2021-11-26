package teamdraco.fins.common.entities;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.fins.init.FinsEntities;
import teamdraco.fins.init.FinsItems;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;

public class GlassSkipperEntity extends AnimalEntity implements IFlyingAnimal {
    private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(GlassSkipperEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> DATA_REMAINING_ANGER_TIME = EntityDataManager.defineId(GlassSkipperEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(GlassSkipperEntity.class, DataSerializers.INT);
    private int numCropsGrownSincePollination;
    private int remainingCooldownBeforeLocatingNewFlower = 0;
    @Nullable
    private BlockPos savedFlowerPos = null;
    @Nullable
    private GlassSkipperEntity.PollinateGoal pollinateGoal;
    private int underWaterTicks;

    public GlassSkipperEntity(EntityType<? extends GlassSkipperEntity> p_i225714_1_, World p_i225714_2_) {
        super(p_i225714_1_, p_i225714_2_);
        this.moveControl = new FlyingMovementController(this, 20, true);
        this.setPathfindingMalus(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
        this.setPathfindingMalus(PathNodeType.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(PathNodeType.COCOA, -1.0F);
        this.setPathfindingMalus(PathNodeType.FENCE, -1.0F);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte) 0);
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        this.entityData.define(VARIANT, 0);
    }

    public float getWalkTargetValue(BlockPos p_205022_1_, IWorldReader p_205022_2_) {
        return p_205022_2_.getBlockState(p_205022_1_).isAir() ? 10.0F : 0.0F;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(ItemTags.FLOWERS), false));
        this.pollinateGoal = new GlassSkipperEntity.PollinateGoal();
        this.goalSelector.addGoal(4, this.pollinateGoal);
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(6, new FindFlowerGoal());
        this.goalSelector.addGoal(7, new GlassSkipperEntity.FindPollinationTargetGoal());
        this.goalSelector.addGoal(8, new GlassSkipperEntity.WanderGoal());
        this.goalSelector.addGoal(9, new SwimGoal(this));
    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);

        if (this.hasSavedFlowerPos()) {
            p_213281_1_.put("FlowerPos", NBTUtil.writeBlockPos(this.getSavedFlowerPos()));
        }

        p_213281_1_.putBoolean("HasNectar", this.hasNectar());
        p_213281_1_.putBoolean("HasStung", this.hasStung());
        p_213281_1_.putInt("CropsGrownSincePollination", this.numCropsGrownSincePollination);
        p_213281_1_.putInt("Variant", getVariant());
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        this.savedFlowerPos = null;
        if (p_70037_1_.contains("FlowerPos")) {
            this.savedFlowerPos = NBTUtil.readBlockPos(p_70037_1_.getCompound("FlowerPos"));
        }

        setVariant(p_70037_1_.getInt("Variant"));
        super.readAdditionalSaveData(p_70037_1_);
        this.setHasNectar(p_70037_1_.getBoolean("HasNectar"));
        this.numCropsGrownSincePollination = p_70037_1_.getInt("CropsGrownSincePollination");
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.GLASS_SKIPPER_SPAWN_EGG.get());
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @javax.annotation.Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (spawnDataIn == null) {
            if (random.nextInt(500) == 0) {
                this.setVariant(4);
            }
            else {
                setVariant(random.nextInt(4));
            }
        } else {
            if (dataTag != null && dataTag.contains("Variant", 3)){
                this.setVariant(dataTag.getInt("Variant"));
            }
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    private void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public void tick() {
        super.tick();
        if (this.hasNectar() && this.getCropsGrownSincePollination() < 10 && this.random.nextFloat() < 0.05F) {
            for (int i = 0; i < this.random.nextInt(2) + 1; ++i) {
                this.spawnFluidParticle(this.level, this.getX() - (double) 0.3F, this.getX() + (double) 0.3F, this.getZ() - (double) 0.3F, this.getZ() + (double) 0.3F, this.getY(0.5D), ParticleTypes.FALLING_NECTAR);
            }
        }
    }

    private void spawnFluidParticle(World p_226397_1_, double p_226397_2_, double p_226397_4_, double p_226397_6_, double p_226397_8_, double p_226397_10_, IParticleData p_226397_12_) {
        p_226397_1_.addParticle(p_226397_12_, MathHelper.lerp(p_226397_1_.random.nextDouble(), p_226397_2_, p_226397_4_), p_226397_10_, MathHelper.lerp(p_226397_1_.random.nextDouble(), p_226397_6_, p_226397_8_), 0.0D, 0.0D, 0.0D);
    }

    private void pathfindRandomlyTowards(BlockPos p_226433_1_) {
        Vector3d vector3d = Vector3d.atBottomCenterOf(p_226433_1_);
        int i = 0;
        BlockPos blockpos = this.blockPosition();
        int j = (int) vector3d.y - blockpos.getY();
        if (j > 2) {
            i = 4;
        } else if (j < -2) {
            i = -4;
        }

        int k = 6;
        int l = 8;
        int i1 = blockpos.distManhattan(p_226433_1_);
        if (i1 < 15) {
            k = i1 / 2;
            l = i1 / 2;
        }

        Vector3d vector3d1 = RandomPositionGenerator.getAirPosTowards(this, k, l, i, vector3d, (double) ((float) Math.PI / 10F));
        if (vector3d1 != null) {
            this.navigation.setMaxVisitedNodesMultiplier(0.5F);
            this.navigation.moveTo(vector3d1.x, vector3d1.y, vector3d1.z, 1.0D);
        }
    }

    @Nullable
    public BlockPos getSavedFlowerPos() {
        return this.savedFlowerPos;
    }

    public boolean hasSavedFlowerPos() {
        return this.savedFlowerPos != null;
    }

    protected void customServerAiStep() {
        if (this.isInWaterOrBubble()) {
            ++this.underWaterTicks;
        } else {
            this.underWaterTicks = 0;
        }

        if (this.underWaterTicks > 20) {
            this.hurt(DamageSource.DROWN, 1.0F);
        }

    }

    private int getCropsGrownSincePollination() {
        return this.numCropsGrownSincePollination;
    }

    private void incrementNumCropsGrownSincePollination() {
        ++this.numCropsGrownSincePollination;
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {

            if (this.remainingCooldownBeforeLocatingNewFlower > 0) {
                --this.remainingCooldownBeforeLocatingNewFlower;
            }
        }
    }

    public boolean hasNectar() {
        return this.getFlag(8);
    }

    private void setHasNectar(boolean p_226447_1_) {
        this.setFlag(8, p_226447_1_);
    }

    public boolean hasStung() {
        return this.getFlag(4);
    }

    private boolean isTooFarAway(BlockPos p_226437_1_) {
        return !this.closerThan(p_226437_1_, 32);
    }

    private void setFlag(int p_226404_1_, boolean p_226404_2_) {
        if (p_226404_2_) {
            this.entityData.set(DATA_FLAGS_ID, (byte) (this.entityData.get(DATA_FLAGS_ID) | p_226404_1_));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte) (this.entityData.get(DATA_FLAGS_ID) & ~p_226404_1_));
        }

    }

    private boolean getFlag(int p_226456_1_) {
        return (this.entityData.get(DATA_FLAGS_ID) & p_226456_1_) != 0;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FLYING_SPEED, 0.95F).add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    protected PathNavigator createNavigation(World p_175447_1_) {
        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, p_175447_1_) {
            public boolean isStableDestination(BlockPos p_188555_1_) {
                return !this.level.getBlockState(p_188555_1_.below()).isAir();
            }

            public void tick() {
                if (!GlassSkipperEntity.this.pollinateGoal.isPollinating()) {
                    super.tick();
                }
            }
        };
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanFloat(false);
        flyingpathnavigator.setCanPassDoors(true);
        return flyingpathnavigator;
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return p_70877_1_.getItem().is(ItemTags.FLOWERS);
    }

    private boolean isFlowerValid(BlockPos p_226439_1_) {
        return this.level.isLoaded(p_226439_1_) && this.level.getBlockState(p_226439_1_).getBlock().is(BlockTags.FLOWERS);
    }

    protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_) {
    }

    protected SoundEvent getAmbientSound() {
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.BEE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.BEE_DEATH;
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    public GlassSkipperEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        GlassSkipperEntity entity = FinsEntities.GLASS_SKIPPER.get().create(p_241840_1_);
        entity.setVariant(random.nextInt(5));
        return entity;
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return this.isBaby() ? p_213348_2_.height * 0.5F : p_213348_2_.height * 0.5F;
    }

    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
        return false;
    }

    protected void checkFallDamage(double p_184231_1_, boolean p_184231_3_, BlockState p_184231_4_, BlockPos p_184231_5_) {
    }

    protected boolean makeFlySound() {
        return true;
    }

    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.isInvulnerableTo(p_70097_1_)) {
            return false;
        } else {
            Entity entity = p_70097_1_.getEntity();
            if (!this.level.isClientSide) {
                this.pollinateGoal.stopPollinating();
            }

            return super.hurt(p_70097_1_, p_70097_2_);
        }
    }

    public CreatureAttribute getMobType() {
        return CreatureAttribute.ARTHROPOD;
    }

    protected void jumpInLiquid(ITag<Fluid> p_180466_1_) {
        this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.01D, 0.0D));
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3d getLeashOffset() {
        return new Vector3d(0.0D, (0.5F * this.getEyeHeight()), (this.getBbWidth() * 0.2F));
    }

    private boolean closerThan(BlockPos p_226401_1_, int p_226401_2_) {
        return p_226401_1_.closerThan(this.blockPosition(), p_226401_2_);
    }

    public class FindFlowerGoal extends Goal {
        private int travellingTicks = GlassSkipperEntity.this.level.random.nextInt(10);

        FindFlowerGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return GlassSkipperEntity.this.savedFlowerPos != null && !GlassSkipperEntity.this.hasRestriction() && this.wantsToGoToKnownFlower() && GlassSkipperEntity.this.isFlowerValid(GlassSkipperEntity.this.savedFlowerPos) && !GlassSkipperEntity.this.closerThan(GlassSkipperEntity.this.savedFlowerPos, 2);
        }

        public void start() {
            this.travellingTicks = 0;
            super.start();
        }

        public void stop() {
            this.travellingTicks = 0;
            GlassSkipperEntity.this.navigation.stop();
            GlassSkipperEntity.this.navigation.resetMaxVisitedNodesMultiplier();
        }

        public void tick() {
            if (GlassSkipperEntity.this.savedFlowerPos != null) {
                ++this.travellingTicks;
                if (this.travellingTicks > 600) {
                    GlassSkipperEntity.this.savedFlowerPos = null;
                } else if (!GlassSkipperEntity.this.navigation.isInProgress()) {
                    if (GlassSkipperEntity.this.isTooFarAway(GlassSkipperEntity.this.savedFlowerPos)) {
                        GlassSkipperEntity.this.savedFlowerPos = null;
                    } else {
                        GlassSkipperEntity.this.pathfindRandomlyTowards(GlassSkipperEntity.this.savedFlowerPos);
                    }
                }
            }
        }

        private boolean wantsToGoToKnownFlower() {
            return true;
        }
    }

    class FindPollinationTargetGoal extends Goal {
        private FindPollinationTargetGoal() {
        }

        public boolean canUse() {
            if (GlassSkipperEntity.this.getCropsGrownSincePollination() >= 10) {
                return false;
            } else if (GlassSkipperEntity.this.random.nextFloat() < 0.3F) {
                return false;
            } else {
                return GlassSkipperEntity.this.hasNectar();
            }
        }

        @Override
        public boolean canContinueToUse() {
            return this.canUse();
        }

        public void tick() {
            if (GlassSkipperEntity.this.random.nextInt(30) == 0) {
                for (int i = 1; i <= 2; ++i) {
                    BlockPos blockpos = GlassSkipperEntity.this.blockPosition().below(i);
                    BlockState blockstate = GlassSkipperEntity.this.level.getBlockState(blockpos);
                    Block block = blockstate.getBlock();
                    boolean flag = false;
                    IntegerProperty integerproperty = null;
                    if (block.is(BlockTags.BEE_GROWABLES)) {
                        if (block instanceof CropsBlock) {
                            CropsBlock cropsblock = (CropsBlock) block;
                            if (!cropsblock.isMaxAge(blockstate)) {
                                flag = true;
                                integerproperty = cropsblock.getAgeProperty();
                            }
                        } else if (block instanceof StemBlock) {
                            int j = blockstate.getValue(StemBlock.AGE);
                            if (j < 7) {
                                flag = true;
                                integerproperty = StemBlock.AGE;
                            }
                        } else if (block == Blocks.SWEET_BERRY_BUSH) {
                            int k = blockstate.getValue(SweetBerryBushBlock.AGE);
                            if (k < 3) {
                                flag = true;
                                integerproperty = SweetBerryBushBlock.AGE;
                            }
                        }

                        if (flag) {
                            GlassSkipperEntity.this.level.levelEvent(2005, blockpos, 0);
                            GlassSkipperEntity.this.level.setBlockAndUpdate(blockpos, blockstate.setValue(integerproperty, Integer.valueOf(blockstate.getValue(integerproperty) + 1)));
                            GlassSkipperEntity.this.incrementNumCropsGrownSincePollination();
                        }
                    }
                }

            }
        }
    }

    class PollinateGoal extends Goal {
        private final Predicate<BlockState> VALID_POLLINATION_BLOCKS = (p_226499_0_) -> {
            if (p_226499_0_.is(BlockTags.TALL_FLOWERS)) {
                if (p_226499_0_.is(Blocks.SUNFLOWER)) {
                    return p_226499_0_.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER;
                } else {
                    return true;
                }
            } else {
                return p_226499_0_.is(BlockTags.SMALL_FLOWERS);
            }
        };
        private int successfulPollinatingTicks = 0;
        private int lastSoundPlayedTick = 0;
        private boolean pollinating;
        private Vector3d hoverPos;
        private int pollinatingTicks = 0;

        PollinateGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            if (GlassSkipperEntity.this.remainingCooldownBeforeLocatingNewFlower > 0) {
                return false;
            } else if (GlassSkipperEntity.this.hasNectar()) {
                return false;
            } else if (GlassSkipperEntity.this.level.isRaining()) {
                return false;
            } else if (GlassSkipperEntity.this.random.nextFloat() < 0.7F) {
                return false;
            } else {
                Optional<BlockPos> optional = this.findNearbyFlower();
                if (optional.isPresent()) {
                    GlassSkipperEntity.this.savedFlowerPos = optional.get();
                    GlassSkipperEntity.this.navigation.moveTo((double) GlassSkipperEntity.this.savedFlowerPos.getX() + 0.5D, (double) GlassSkipperEntity.this.savedFlowerPos.getY() + 0.5D, (double) GlassSkipperEntity.this.savedFlowerPos.getZ() + 0.5D, (double) 1.2F);
                    return true;
                } else {
                    return false;
                }
            }
        }

        public boolean canContinueToUse() {
            if (!this.pollinating) {
                return false;
            } else if (!GlassSkipperEntity.this.hasSavedFlowerPos()) {
                return false;
            } else if (GlassSkipperEntity.this.level.isRaining()) {
                return false;
            } else if (this.hasPollinatedLongEnough()) {
                return GlassSkipperEntity.this.random.nextFloat() < 0.2F;
            } else if (GlassSkipperEntity.this.tickCount % 20 == 0 && !GlassSkipperEntity.this.isFlowerValid(GlassSkipperEntity.this.savedFlowerPos)) {
                GlassSkipperEntity.this.savedFlowerPos = null;
                return false;
            } else {
                return true;
            }
        }

        private boolean hasPollinatedLongEnough() {
            return this.successfulPollinatingTicks > 400;
        }

        private boolean isPollinating() {
            return this.pollinating;
        }

        private void stopPollinating() {
            this.pollinating = false;
        }

        public void start() {
            this.successfulPollinatingTicks = 0;
            this.pollinatingTicks = 0;
            this.lastSoundPlayedTick = 0;
            this.pollinating = true;
        }

        public void stop() {
            if (this.hasPollinatedLongEnough()) {
                GlassSkipperEntity.this.setHasNectar(true);
            }

            this.pollinating = false;
            GlassSkipperEntity.this.navigation.stop();
            GlassSkipperEntity.this.remainingCooldownBeforeLocatingNewFlower = 200;
        }

        public void tick() {
            ++this.pollinatingTicks;
            if (this.pollinatingTicks > 600) {
                GlassSkipperEntity.this.savedFlowerPos = null;
            } else {
                Vector3d vector3d = Vector3d.atBottomCenterOf(GlassSkipperEntity.this.savedFlowerPos).add(0.0D, (double) 0.6F, 0.0D);
                if (vector3d.distanceTo(GlassSkipperEntity.this.position()) > 1.0D) {
                    this.hoverPos = vector3d;
                    this.setWantedPos();
                } else {
                    if (this.hoverPos == null) {
                        this.hoverPos = vector3d;
                    }

                    boolean flag = GlassSkipperEntity.this.position().distanceTo(this.hoverPos) <= 0.1D;
                    boolean flag1 = true;
                    if (!flag && this.pollinatingTicks > 600) {
                        GlassSkipperEntity.this.savedFlowerPos = null;
                    } else {
                        if (flag) {
                            boolean flag2 = GlassSkipperEntity.this.random.nextInt(25) == 0;
                            if (flag2) {
                                this.hoverPos = new Vector3d(vector3d.x() + (double) this.getOffset(), vector3d.y(), vector3d.z() + (double) this.getOffset());
                                GlassSkipperEntity.this.navigation.stop();
                            } else {
                                flag1 = false;
                            }

                            GlassSkipperEntity.this.getLookControl().setLookAt(vector3d.x(), vector3d.y(), vector3d.z());
                        }

                        if (flag1) {
                            this.setWantedPos();
                        }

                        ++this.successfulPollinatingTicks;
                        if (GlassSkipperEntity.this.random.nextFloat() < 0.05F && this.successfulPollinatingTicks > this.lastSoundPlayedTick + 60) {
                            this.lastSoundPlayedTick = this.successfulPollinatingTicks;
                            GlassSkipperEntity.this.playSound(SoundEvents.BEE_POLLINATE, 1.0F, 1.0F);
                        }

                    }
                }
            }
        }

        private void setWantedPos() {
            GlassSkipperEntity.this.getMoveControl().setWantedPosition(this.hoverPos.x(), this.hoverPos.y(), this.hoverPos.z(), (double) 0.35F);
        }

        private float getOffset() {
            return (GlassSkipperEntity.this.random.nextFloat() * 2.0F - 1.0F) * 0.33333334F;
        }

        private Optional<BlockPos> findNearbyFlower() {
            return this.findNearestBlock(this.VALID_POLLINATION_BLOCKS, 5.0D);
        }

        private Optional<BlockPos> findNearestBlock(Predicate<BlockState> p_226500_1_, double p_226500_2_) {
            BlockPos blockpos = GlassSkipperEntity.this.blockPosition();
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for (int i = 0; (double) i <= p_226500_2_; i = i > 0 ? -i : 1 - i) {
                for (int j = 0; (double) j < p_226500_2_; ++j) {
                    for (int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
                        for (int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l) {
                            blockpos$mutable.setWithOffset(blockpos, k, i - 1, l);
                            if (blockpos.closerThan(blockpos$mutable, p_226500_2_) && p_226500_1_.test(GlassSkipperEntity.this.level.getBlockState(blockpos$mutable))) {
                                return Optional.of(blockpos$mutable);
                            }
                        }
                    }
                }
            }

            return Optional.empty();
        }
    }

    class WanderGoal extends Goal {
        WanderGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return GlassSkipperEntity.this.navigation.isDone() && GlassSkipperEntity.this.random.nextInt(10) == 0;
        }

        public boolean canContinueToUse() {
            return GlassSkipperEntity.this.navigation.isInProgress();
        }

        public void start() {
            Vector3d vector3d = this.findPos();
            if (vector3d != null) {
                GlassSkipperEntity.this.navigation.moveTo(GlassSkipperEntity.this.navigation.createPath(new BlockPos(vector3d), 1), 1.0D);
            }

        }

        @Nullable
        private Vector3d findPos() {
            Vector3d vector3d;
            vector3d = GlassSkipperEntity.this.getViewVector(0.0F);

            Vector3d vector3d2 = RandomPositionGenerator.getAboveLandPos(GlassSkipperEntity.this, 8, 7, vector3d, ((float)Math.PI / 2F), 2, 1);
            return vector3d2 != null ? vector3d2 : RandomPositionGenerator.getAirPos(GlassSkipperEntity.this, 8, 4, -2, vector3d, (double)((float)Math.PI / 2F));
        }
    }
}