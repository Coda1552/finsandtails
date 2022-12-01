package teamdraco.finsandstails.common.entities.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import teamdraco.finsandstails.common.entities.CrownedHorateeEntity;

import java.util.EnumSet;

public class ShareTheBubbleGoal extends Goal {
	private final CrownedHorateeEntity tamable;
	private LivingEntity owner;
	private final LevelReader level;
	private final double speedModifier;
	private int timeToRecalcPath;
	private float oldWaterCost;

	private final PathNavigation navigation;

	public ShareTheBubbleGoal(CrownedHorateeEntity p_25294_, double p_25295_) {
		this.tamable = p_25294_;
		this.level = p_25294_.level;
		this.speedModifier = p_25295_;
		this.navigation = p_25294_.getNavigation();
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		LivingEntity livingentity = this.tamable.getOwner();
		if (livingentity == null) {
			return false;
		} else if (livingentity.isSpectator()) {
			return false;
		} else if (this.tamable.isOrderedToSit()) {
			return false;
		} else {
			this.owner = livingentity;
			return this.owner.getAirSupply() <= 0;
		}
	}

	public boolean canContinueToUse() {
		if (this.tamable.isOrderedToSit()) {
			return false;
		} else {
			return this.owner.getAirSupply() <= this.owner.getMaxAirSupply();
		}
	}

	public void start() {
		this.timeToRecalcPath = 0;
		this.oldWaterCost = this.tamable.getPathfindingMalus(BlockPathTypes.WATER);
		this.tamable.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
	}

	public void stop() {
		this.owner = null;
		this.navigation.stop();
		this.tamable.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
		this.tamable.setBubbleCharge(false);
	}

	public void tick() {
		this.tamable.getLookControl().setLookAt(this.owner, 10.0F, (float) this.tamable.getMaxHeadXRot());
		if (--this.timeToRecalcPath <= 0) {
			this.timeToRecalcPath = this.adjustedTickDelay(10);
			if (!this.tamable.isLeashed() && !this.tamable.isPassenger()) {
				this.navigation.moveTo(this.owner, this.speedModifier);
			}

			if (this.tamable.distanceToSqr(this.owner) <= 16.0D) {
				this.owner.setAirSupply(this.owner.getAirSupply() + 20);
				this.tamable.setBubbleCharge(true);
			} else {
				this.tamable.setBubbleCharge(false);
			}
		}
	}
}