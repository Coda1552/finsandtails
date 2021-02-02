package mod.coda.fins.entity;

import mod.coda.fins.init.FinsItems;
import mod.coda.fins.init.FinsSounds;
import mod.coda.fins.pathfinding.GroundAndSwimmerNavigator;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.CatEntity;
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
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
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

import javax.annotation.Nullable;
import java.util.Random;

public class PenglilEntity extends TameableEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(PenglilEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> field_213428_bG = EntityDataManager.createKey(PenglilEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> field_213429_bH = EntityDataManager.createKey(PenglilEntity.class, DataSerializers.BOOLEAN);
    private Vector3d target;

    public PenglilEntity(EntityType<? extends PenglilEntity> type, World world) {
        super(type, world);
        this.moveController = new PenglilEntity.MoveHelperController(this);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.stepHeight = 1;
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        return new GroundAndSwimmerNavigator(this, world);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

    public static boolean canPenglilSpawn(EntityType<? extends TameableEntity> penglil, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getBlockState(pos.down()).getBlock() == Blocks.SAND && worldIn.getLightSubtracted(pos, 0) > 8;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SitGoal(this));
        this.goalSelector.addGoal(1, new PenglilEntity.MorningGiftGoal(this));
        this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 2.0D, 100) {
            @Override
            public boolean shouldExecute() {
                return super.shouldExecute() && isInWater();
            }
        });
        this.goalSelector.addGoal(2, new PanicGoal(this, 0.5D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 2.0D, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PeaWeeEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, BluWeeEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, BandedRedbackShrimpEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, HighFinnedBlueEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, WeeWeeEntity.class, true));
    }

    public static AttributeModifierMap.MutableAttribute func_234176_m_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10).createMutableAttribute(Attributes.ATTACK_DAMAGE, 1).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15);
    }

    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
            this.setHealth(20.0F);
        } else {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
        }
    }

    @Override
    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        Item item = heldItem.getItem();
        ActionResultType actionresulttype = super.func_230254_b_(player, hand);

        if (heldItem.getItem() == Items.BUCKET && this.isAlive()) {
            playSound(SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            heldItem.shrink(1);
            ItemStack itemstack1 = new ItemStack(FinsItems.PENGLIL_BUCKET.get());
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

        float maxHealth = this.getMaxHealth();
        float health = this.getHealth();
        if (heldItem.getItem() == FinsItems.BLU_WEE.get() && health < maxHealth) {
            if (!player.isCreative()) {
                heldItem.shrink(1);
            }
            heal(2);
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.addParticle(ParticleTypes.HEART, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.5D, this.getPosZRandom(1.0D), d0, d1, d2);
            return ActionResultType.SUCCESS;
        }

        if (item == FinsItems.HIGH_FINNED_BLUE.get() && !this.isTamed()) {
            if (!player.abilities.isCreativeMode) {
                heldItem.shrink(1);
            }
            if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                this.setTamedBy(player);
                this.navigator.clearPath();
                this.func_233687_w_(true);
                this.setAttackTarget((LivingEntity) null);
                this.world.setEntityState(this, (byte) 7);
            }
            else {
                this.world.setEntityState(this, (byte) 6);
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isOwner(player) && item != FinsItems.HIGH_FINNED_BLUE.get()){
            func_233687_w_(!isEntitySleeping());
            this.isJumping = false;
            this.navigator.clearPath();
            return ActionResultType.SUCCESS;
        }

        return actionresulttype;
    }

    private void setBucketData(ItemStack bucket) {
        CompoundNBT compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
        if (isTamed()) {
            compoundnbt.putUniqueId("Owner", getOwnerId());
        }
        if (this.hasCustomName()) {
            bucket.setDisplayName(this.getCustomName());
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (target == null && rand.nextInt(120) == 0) {
            target = RandomPositionGenerator.findRandomTarget(this, 10, 7);
            if (target != null) {
                getNavigator().tryMoveToXYZ(target.getX(), target.getY(), target.getZ(), 0.15);
            }
        }

        if (target != null) {
            if (getDistanceSq(target) <= 4) {
                target = null;
            } else if (getNavigator().noPath()) {
                getNavigator().tryMoveToXYZ(target.getX(), target.getY(), target.getZ(), 0.15);
            }
        }
    }

    @Override
    public void travel(Vector3d p_213352_1_) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(0.01F, p_213352_1_);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.9D));
            if (this.getAttackTarget() == null) {
                this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(p_213352_1_);
        }
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(VARIANT, 0);
        this.dataManager.register(field_213428_bG, false);
        this.dataManager.register(field_213429_bH, false);

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

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if (dataTag == null) {
            setVariant(rand.nextInt(8));
        } else {
            if (dataTag.contains("BucketVariantTag", 3)){
                this.setVariant(dataTag.getInt("BucketVariantTag"));
            }
            if (dataTag.hasUniqueId("Owner")) {
                this.setOwnerId(dataTag.getUniqueId("Owner"));
                this.setTamed(true);
            }
        }
        return spawnDataIn;
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
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

    protected SoundEvent getFlopSound() {
        return null;
    }

    private static class MoveHelperController extends MovementController {
        private final PenglilEntity penglil;

        MoveHelperController(PenglilEntity penglil) {
            super(penglil);
            this.penglil = penglil;
        }

        public void tick() {
            this.penglil.setMotion(this.penglil.getMotion().add(0.0D, 0.005D, 0.0D));
            if (this.action == Action.MOVE_TO && !this.penglil.getNavigator().noPath()) {
                double d0 = this.posX - this.penglil.getPosX();
                double d1 = this.posY - this.penglil.getPosY();
                double d2 = this.posZ - this.penglil.getPosZ();
                double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.penglil.rotationYaw = this.limitAngle(this.penglil.rotationYaw, f, 90.0F);
                this.penglil.renderYawOffset = this.penglil.rotationYaw;
                float f1 = (float) (this.speed * 0.1f);
                this.penglil.setAIMoveSpeed(MathHelper.lerp(0.125F, this.penglil.getAIMoveSpeed(), f1));
                this.penglil.setMotion(this.penglil.getMotion().add(MathHelper.clamp(d0, speed / -10, speed / 10), (double) this.penglil.getAIMoveSpeed() * d1 * 0.1D, MathHelper.clamp(d2, speed / -10, speed / 10)));
            } else {
                this.penglil.setAIMoveSpeed(0.0F);
            }
        }
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.PENGLIL_SPAWN_EGG.get());
    }

    public boolean func_213416_eg() {
        return this.dataManager.get(field_213428_bG);
    }

    public void func_213419_u(boolean p_213419_1_) {
        this.dataManager.set(field_213428_bG, p_213419_1_);
    }

    public void func_213415_v(boolean p_213415_1_) {
        this.dataManager.set(field_213429_bH, p_213415_1_);
    }

    public boolean func_213409_eh() {
        return this.dataManager.get(field_213429_bH);
    }

    static class MorningGiftGoal extends Goal {
        private final PenglilEntity penglil;
        private PlayerEntity owner;
        private BlockPos bedPos;
        private int tickCounter;

        public MorningGiftGoal(PenglilEntity catIn) {
            this.penglil = catIn;
        }

        public boolean shouldExecute() {
            if (!this.penglil.isTamed()) {
                return false;
            } else if (this.penglil.isSitting()) {
                return false;
            } else {
                LivingEntity livingentity = this.penglil.getOwner();
                if (livingentity instanceof PlayerEntity) {
                    this.owner = (PlayerEntity)livingentity;
                    if (!livingentity.isSleeping()) {
                        return false;
                    }

                    if (this.penglil.getDistanceSq(this.owner) > 100.0D) {
                        return false;
                    }

                    BlockPos blockpos = this.owner.getPosition();
                    BlockState blockstate = this.penglil.world.getBlockState(blockpos);
                    if (blockstate.getBlock().isIn(BlockTags.BEDS)) {
                        this.bedPos = blockstate.func_235903_d_(BedBlock.HORIZONTAL_FACING).map((p_234186_1_) -> {
                            return blockpos.offset(p_234186_1_.getOpposite());
                        }).orElseGet(() -> {
                            return new BlockPos(blockpos);
                        });
                        return !this.func_220805_g();
                    }
                }

                return false;
            }
        }

        private boolean func_220805_g() {
            for(PenglilEntity penglilentity : this.penglil.world.getEntitiesWithinAABB(PenglilEntity.class, (new AxisAlignedBB(this.bedPos)).grow(2.0D))) {
                if (penglilentity != this.penglil && (penglilentity.func_213416_eg() || penglilentity.func_213409_eh())) {
                    return true;
                }
            }

            return false;
        }

        public boolean shouldContinueExecuting() {
            return this.penglil.isTamed() && !this.penglil.isSitting() && this.owner != null && this.owner.isSleeping() && this.bedPos != null && !this.func_220805_g();
        }

        public void startExecuting() {
            if (this.bedPos != null) {
                this.penglil.setSleeping(false);
                this.penglil.getNavigator().tryMoveToXYZ((double)this.bedPos.getX(), (double)this.bedPos.getY(), (double)this.bedPos.getZ(), (double)1.1F);
            }

        }

        public void resetTask() {
            this.penglil.func_213419_u(false);
            float f = this.penglil.world.func_242415_f(1.0F);
            if (this.owner.getSleepTimer() >= 100 && (double)f > 0.77D && (double)f < 0.8D && (double)this.penglil.world.getRandom().nextFloat() < 0.5D) {
                this.func_220804_h();
            }

            this.tickCounter = 0;
            this.penglil.func_213415_v(false);
            this.penglil.getNavigator().clearPath();
        }

        private void func_220804_h() {
            Random random = this.penglil.getRNG();
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
            blockpos$mutable.setPos(this.penglil.getPosition());
            this.penglil.attemptTeleport((double)(blockpos$mutable.getX() + random.nextInt(11) - 5), (double)(blockpos$mutable.getY() + random.nextInt(5) - 2), (double)(blockpos$mutable.getZ() + random.nextInt(11) - 5), false);
            blockpos$mutable.setPos(this.penglil.getPosition());
            LootTable loottable = this.penglil.world.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING);
            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)this.penglil.world)).withParameter(LootParameters.field_237457_g_, this.penglil.getPositionVec()).withParameter(LootParameters.THIS_ENTITY, this.penglil).withRandom(random);

            for(ItemStack itemstack : loottable.generate(lootcontext$builder.build(LootParameterSets.GIFT))) {
                this.penglil.world.addEntity(new ItemEntity(this.penglil.world, (double)blockpos$mutable.getX() - (double)MathHelper.sin(this.penglil.renderYawOffset * ((float)Math.PI / 180F)), (double)blockpos$mutable.getY(), (double)blockpos$mutable.getZ() + (double)MathHelper.cos(this.penglil.renderYawOffset * ((float)Math.PI / 180F)), itemstack));
            }

        }

        public void tick() {
            if (this.owner != null && this.bedPos != null) {
                this.penglil.setSleeping(false);
                this.penglil.getNavigator().tryMoveToXYZ((double)this.bedPos.getX(), (double)this.bedPos.getY(), (double)this.bedPos.getZ(), (double)1.1F);
                if (this.penglil.getDistanceSq(this.owner) < 2.5D) {
                    ++this.tickCounter;
                    if (this.tickCounter > 16) {
                        this.penglil.func_213419_u(true);
                        this.penglil.func_213415_v(false);
                    } else {
                        this.penglil.faceEntity(this.owner, 45.0F, 45.0F);
                        this.penglil.func_213415_v(true);
                    }
                } else {
                    this.penglil.func_213419_u(false);
                }
            }

        }
    }
}