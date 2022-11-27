package teamdraco.finsandstails.common.entities;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import teamdraco.finsandstails.common.entities.ai.CrownedHorateeNavigator;
import teamdraco.finsandstails.common.entities.ai.SwimWithoutGroundGoal;
import teamdraco.finsandstails.common.entities.ai.WalkWithGroundGoal;
import teamdraco.finsandstails.common.entities.ai.control.SmoothWalkGroundAndSwimMoveControl;
import teamdraco.finsandstails.registry.FTEntities;

import java.util.EnumSet;
import java.util.Random;

public class CrownedHorateeEntity extends Animal implements IAnimatable, IAnimationTickable, IHydrate {
	private static final EntityDataAccessor<Boolean> HAS_BABY = SynchedEntityData.defineId(CrownedHorateeEntity.class, EntityDataSerializers.BOOLEAN);

	private final AnimationFactory factory = new AnimationFactory(this);
	private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.SEA_PICKLE);

	public CrownedHorateeEntity(EntityType<? extends CrownedHorateeEntity> p_27523_, Level p_27524_) {
		super(p_27523_, p_27524_);
		this.moveControl = new SmoothWalkGroundAndSwimMoveControl(this, 85, 10, 0.5F, 1.0F, false);
		this.lookControl = new SmoothSwimmingLookControl(this, 10);
		this.setCanPickUpLoot(true);
		this.maxUpStep = 1.0F;
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
	}

	public MobType getMobType() {
		return MobType.WATER;
	}

	public boolean checkSpawnObstruction(LevelReader p_30348_) {
		return p_30348_.isUnobstructed(this);
	}

	public int getAmbientSoundInterval() {
		return 120;
	}

	protected int getExperienceReward(Player p_30353_) {
		return 1 + this.level.random.nextInt(3);
	}

	public void baseTick() {
		int i = this.getAirSupply();
		super.baseTick();
		this.handleAirSupply(i);
	}

	public boolean isPushedByFluid() {
		return false;
	}

	public boolean canBeLeashed(Player p_30346_) {
		return false;
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.15D, true));
		this.goalSelector.addGoal(1, new CrownedBreedGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new LayBabyGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 0.85F, FOOD_ITEMS, false));
		this.goalSelector.addGoal(4, new GoToWaterGoal(this, 1.0F));
		this.goalSelector.addGoal(5, new SwimWithoutGroundGoal(this));
		this.goalSelector.addGoal(6, new WalkWithGroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_BABY, false);
	}

	public boolean hasBaby() {
		return this.entityData.get(HAS_BABY);
	}

	public void setHasBaby(boolean p_30235_) {
		this.entityData.set(HAS_BABY, p_30235_);
	}

	@Override
	public boolean isFood(ItemStack p_27600_) {
		return FOOD_ITEMS.test(p_27600_);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag p_30176_) {
		super.addAdditionalSaveData(p_30176_);
		p_30176_.putBoolean("HasBaby", this.hasBaby());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag p_30162_) {
		super.readAdditionalSaveData(p_30162_);
		this.setHasBaby(p_30162_.getBoolean("HasBaby"));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 14.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	public float getWalkTargetValue(BlockPos p_30159_, LevelReader p_30160_) {
		return CrownedHorateeEntity.onSandOrGravel(p_30160_, p_30159_) ? 10.0F + p_30160_.getBrightness(p_30159_) - 0.5F : p_30160_.getBrightness(p_30159_) - 0.5F;
	}

	public static boolean checkCrownedSpawnRules(EntityType<? extends CrownedHorateeEntity> p_30179_, LevelAccessor p_30180_, MobSpawnType p_30181_, BlockPos p_30182_, Random p_30183_) {
		return p_30182_.getY() < p_30180_.getSeaLevel() + 4 && CrownedHorateeEntity.onSandOrGravel(p_30180_, p_30182_) && isBrightEnoughToSpawn(p_30180_, p_30182_);
	}

	public static boolean onSandOrGravel(BlockGetter p_57763_, BlockPos p_57764_) {
		return isSandOrGravel(p_57763_, p_57764_.below());
	}

	public static boolean isSandOrGravel(BlockGetter p_57801_, BlockPos p_57802_) {
		return p_57801_.getBlockState(p_57802_).is(BlockTags.SAND) || p_57801_.getBlockState(p_57802_).is(Tags.Blocks.GRAVEL);
	}


	public void travel(Vec3 p_27490_) {
		if (this.isEffectiveAi() && this.isInWater() && !this.isOnGround()) {
			this.moveRelative(0.05F, p_27490_);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if (this.getTarget() == null) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.006D, 0.0D));
			}
		} else {
			super.travel(p_27490_);
		}

	}

	@Override
	protected PathNavigation createNavigation(Level worldIn) {
		return new CrownedHorateeNavigator(this, level);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	protected void handleAirSupply(int p_149194_) {
		if (this.isAlive() && !this.isInWaterRainOrBubble() && !this.isBaby()) {
			this.setAirSupply(p_149194_ - 1);
			if (this.getAirSupply() == -20) {
				this.setAirSupply(0);
				this.hurt(DamageSource.DRY_OUT, 2.0F);
			}
		} else {
			this.setAirSupply(this.getMaxAirSupply());
		}
	}

	public void rehydrate() {
		int i = this.getAirSupply() + 1200;
		this.setAirSupply(Math.min(i, this.getMaxAirSupply()));
	}

	public int getMaxAirSupply() {
		return 6000;
	}


	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return factory;
	}

	@Override
	public boolean removeWhenFarAway(double p_21542_) {
		return false;
	}

	private boolean isMoving() {
		return this.getDeltaMovement().horizontalDistanceSqr() > 0D;
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (this.isMoving()) {
			if (this.isInWater()) {
				if (this.isOnGround()) {
					event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.walk", true));
				} else {
					event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.swim", true));
				}
			} else {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.crawl", true));
			}
		} else {
			if (this.isInWater()) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.idle_water", true));
			} else {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.idle_land", true));
			}
		}
		return PlayState.CONTINUE;
	}

	@Override
	public int tickTimer() {
		return tickCount;
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return FTEntities.CROWNED_HORATTE.get().create(p_146743_);
	}

	public boolean canFallInLove() {
		return super.canFallInLove() && !this.hasBaby();
	}

	static class CrownedBreedGoal extends BreedGoal {
		private final CrownedHorateeEntity crownedHorateeEntity;

		CrownedBreedGoal(CrownedHorateeEntity p_30244_, double p_30245_) {
			super(p_30244_, p_30245_);
			this.crownedHorateeEntity = p_30244_;
		}

		public boolean canUse() {
			return super.canUse() && !this.crownedHorateeEntity.hasBaby();
		}

		protected void breed() {
			ServerPlayer serverplayer = this.animal.getLoveCause();
			if (serverplayer == null && this.partner.getLoveCause() != null) {
				serverplayer = this.partner.getLoveCause();
			}

			if (serverplayer != null) {
				serverplayer.awardStat(Stats.ANIMALS_BRED);
				CriteriaTriggers.BRED_ANIMALS.trigger(serverplayer, this.animal, this.partner, (AgeableMob) null);
			}

			this.crownedHorateeEntity.setHasBaby(true);
			this.crownedHorateeEntity.setAge(6000);
			this.partner.setAge(6000);
			this.animal.resetLove();
			this.partner.resetLove();
		}
	}

	static class LayBabyGoal extends MoveToBlockGoal {
		private final CrownedHorateeEntity crownedHorateeEntity;

		LayBabyGoal(CrownedHorateeEntity p_30276_, double p_30277_) {
			super(p_30276_, p_30277_, 16);
			this.crownedHorateeEntity = p_30276_;
		}

		public boolean canUse() {
			return this.crownedHorateeEntity.hasBaby() ? super.canUse() : false;
		}

		public boolean canContinueToUse() {
			return super.canContinueToUse() && this.crownedHorateeEntity.hasBaby();
		}

		public void tick() {
			super.tick();
			if (!this.crownedHorateeEntity.isInWater() && this.isReachedTarget()) {
				this.crownedHorateeEntity.setHasBaby(false);
				if (this.crownedHorateeEntity.level instanceof ServerLevel serverLevel) {
					this.crownedHorateeEntity.spawnChildFromBreeding(serverLevel, this.crownedHorateeEntity);
				}
			}
		}

		protected boolean isValidTarget(LevelReader p_30280_, BlockPos p_30281_) {
			return !p_30280_.isEmptyBlock(p_30281_.above()) && p_30280_.isEmptyBlock(p_30281_) ? false : CrownedHorateeEntity.onSandOrGravel(p_30280_, p_30281_);
		}
	}

	static class GoToWaterGoal extends Goal {
		private final CrownedHorateeEntity mob;
		private double wantedX;
		private double wantedY;
		private double wantedZ;
		private final double speedModifier;
		private final Level level;

		public GoToWaterGoal(CrownedHorateeEntity p_32425_, double p_32426_) {
			this.mob = p_32425_;
			this.speedModifier = p_32426_;
			this.level = p_32425_.level;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean canUse() {
			if (this.mob.getAirSupply() > 1200) {
				return false;
			} else if (this.mob.isInWater()) {
				return false;
			} else {
				Vec3 vec3 = this.getWaterPos();
				if (vec3 == null) {
					return false;
				} else {
					this.wantedX = vec3.x;
					this.wantedY = vec3.y;
					this.wantedZ = vec3.z;
					return true;
				}
			}
		}

		public boolean canContinueToUse() {
			return !this.mob.getNavigation().isDone();
		}

		public void start() {
			this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
		}

		@javax.annotation.Nullable
		private Vec3 getWaterPos() {
			Random random = this.mob.getRandom();
			BlockPos blockpos = this.mob.blockPosition();

			for (int i = 0; i < 10; ++i) {
				BlockPos blockpos1 = blockpos.offset(random.nextInt(20) - 10, 2 - random.nextInt(8), random.nextInt(20) - 10);
				if (this.level.getBlockState(blockpos1).is(Blocks.WATER)) {
					return Vec3.atBottomCenterOf(blockpos1);
				}
			}

			return null;
		}
	}
}
