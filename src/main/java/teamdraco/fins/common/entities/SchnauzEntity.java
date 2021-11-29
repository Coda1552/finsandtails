package teamdraco.fins.common.entities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;
import teamdraco.fins.common.entities.util.GroundAndSwimmerNavigator;
import teamdraco.fins.init.FinsEntities;
import teamdraco.fins.init.FinsItems;
import teamdraco.fins.init.FinsSounds;

import java.util.Random;

public class SchnauzEntity extends AnimalEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(SchnauzEntity.class, DataSerializers.INT);

    public SchnauzEntity(EntityType<? extends SchnauzEntity> type, World world) {
        super(type, world);
        this.moveControl = new SchnauzEntity.MoveHelperController(this);
        this.lookControl = new DolphinLookController(this, 10);
        this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
    }

    public static AttributeModifierMap.MutableAttribute registerSchnauzAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 25).add(Attributes.MOVEMENT_SPEED, 0.15);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FindWaterGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, OrnateBugfishEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
        this.goalSelector.addGoal(1, new BreedGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 1) {
            @Override
            public boolean canUse() {
                return super.canUse() && isInWater();
            }
        });
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1.0D, 15) {
            @Override
            public boolean canUse() {
                return !this.mob.isInWater() && super.canUse();
            }
        });
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
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

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (dataTag == null) {
            setVariant(random.nextInt(3));
        }
        if (spawnDataIn == null) {
            spawnDataIn = new AgeableData(1);
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.WATER;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        setAirSupply(300);
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean canBeLeashed(PlayerEntity player) {
        return true;
    }

    @Override
    protected PathNavigator createNavigation(World worldIn) {
        return new GroundAndSwimmerNavigator(this, level);
    }

    @Override
    public void travel(Vector3d travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.6D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }
    }

    @Override
    public int getAmbientSoundInterval() {
        return 500;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FinsItems.SCHNAUZ_SPAWN_EGG.get());
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        SchnauzEntity schnauz = FinsEntities.SCHNAUZ.get().create(p_241840_1_);
        schnauz.setVariant(random.nextInt(3));
        return schnauz;
    }

    public static boolean checkSchnauzSpawnRules(EntityType<SchnauzEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random p_223365_4_) {
        return pos.getY() < world.getSeaLevel() && world.getBlockState(pos.above()).is(Blocks.WATER);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FinsSounds.MUDHORSE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FinsSounds.MUDHORSE_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FinsSounds.MUDHORSE_HURT.get();
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return isBaby() ? 0.05f : 0.15f;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.SEAGRASS;
    }

    static class MoveHelperController extends MovementController {
        private final SchnauzEntity schnauz;

        MoveHelperController(SchnauzEntity schnauz) {
            super(schnauz);
            this.schnauz = schnauz;
        }

        private void updateSpeed() {
            if (this.schnauz.isInWater()) {
                this.schnauz.setDeltaMovement(this.schnauz.getDeltaMovement().add(0.0D, 0.005D, 0.0D));

                if (this.schnauz.isBaby()) {
                    this.schnauz.setSpeed(Math.max(this.schnauz.getSpeed() / 3.0F, 0.06F));
                }
            }
            else if (this.schnauz.onGround) {
                this.schnauz.setSpeed(Math.max(this.schnauz.getSpeed(), 0.06F));
            }
        }

        @Override
        public void tick() {
            this.updateSpeed();
            if (this.operation == Action.MOVE_TO && !this.schnauz.getNavigation().isDone()) {
                double d0 = this.wantedX - this.schnauz.getX();
                double d1 = this.wantedY - this.schnauz.getY();
                double d2 = this.wantedZ - this.schnauz.getZ();
                double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.schnauz.yRot = this.rotlerp(this.schnauz.yRot, f, 90.0F);
                this.schnauz.yBodyRot = this.schnauz.yRot;
                float f1 = (float)(this.speedModifier * this.schnauz.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.schnauz.setSpeed(MathHelper.lerp(0.125F, this.schnauz.getSpeed(), f1));
                this.schnauz.setDeltaMovement(this.schnauz.getDeltaMovement().add(0.0D, (double)this.schnauz.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.schnauz.setSpeed(0.0F);
            }
        }
    }
}