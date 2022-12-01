package teamdraco.finsandstails.common.entities.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import teamdraco.finsandstails.common.entities.CrownedHorateeEntity;

import java.util.EnumSet;

public class SitEvenWaterWhenOrderedToGoal extends Goal {
	private final CrownedHorateeEntity mob;

	public SitEvenWaterWhenOrderedToGoal(CrownedHorateeEntity p_25898_) {
		this.mob = p_25898_;
		this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	public boolean canContinueToUse() {
		return this.mob.getCommandType() == CrownedHorateeEntity.CommandType.SIT;
	}

	public boolean canUse() {
		if (!this.mob.isTame()) {
			return false;
		} else if (!this.mob.isOnGround()) {
			return false;
		} else {
			LivingEntity livingentity = this.mob.getOwner();
			if (livingentity == null) {
				return true;
			} else {
				return this.mob.distanceToSqr(livingentity) < 144.0D && livingentity.getLastHurtByMob() != null ? false : this.mob.getCommandType() == CrownedHorateeEntity.CommandType.SIT;
			}
		}
	}

	public void start() {
		this.mob.getNavigation().stop();
		this.mob.setCommandType(CrownedHorateeEntity.CommandType.SIT);
	}

	public void stop() {
		this.mob.setCommandType(CrownedHorateeEntity.CommandType.FOLLOW);
	}
}