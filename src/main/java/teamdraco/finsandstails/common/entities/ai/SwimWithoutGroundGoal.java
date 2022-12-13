package teamdraco.finsandstails.common.entities.ai;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;

public class SwimWithoutGroundGoal extends RandomSwimmingGoal {
	private final PathfinderMob fish;

	public SwimWithoutGroundGoal(PathfinderMob p_27505_) {
		super(p_27505_, 1.0D, 10);
		this.fish = p_27505_;
	}

	public boolean canUse() {
		return !this.fish.isOnGround() && this.fish.isInWater() && super.canUse();
	}
}
