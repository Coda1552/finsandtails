package teamdraco.finsandstails.common.entities.ai;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SpearMeleeAttackGoal extends MeleeAttackGoal {
    protected final AbstractSchoolingFish fish;


    public SpearMeleeAttackGoal(AbstractSchoolingFish mob, double speedMod, boolean followingTargetEvenIfNotSeen) {
        super(mob, speedMod, followingTargetEvenIfNotSeen);
        this.fish = mob;
    }

    @Override
    public boolean canUse() {
        return !fish.isFollower() && super.canUse();
    }

    @Override
    public void tick() {
        super.tick();

        if (fish.getTarget() == null) {
            stop();
        }
        else {
            Vec3 pos = fish.getTarget().position();

            // todo - account for negative fish/target pos
            double x = Math.min(0.25D, pos.x - fish.position().x);
            double y = Math.min(0.25D, pos.y - fish.position().y);
            double z = Math.min(0.25D, pos.z - fish.position().z);
            fish.setDeltaMovement(x, y, z);
            fish.lookAt(fish.getTarget(), 90.0F, 90.0F);
        }
    }

    @Override
    public void start() {
        super.start();
    }
}
