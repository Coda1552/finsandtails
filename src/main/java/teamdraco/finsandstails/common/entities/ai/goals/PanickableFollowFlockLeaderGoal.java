package teamdraco.finsandstails.common.entities.ai.goals;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.level.entity.EntityTypeTest;
import teamdraco.finsandstails.common.entities.ai.IPanickableSchooling;

import java.util.List;

public class PanickableFollowFlockLeaderGoal extends FollowFlockLeaderGoal {
    private final AbstractSchoolingFish mob;

    public PanickableFollowFlockLeaderGoal(AbstractSchoolingFish mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return mob instanceof IPanickableSchooling && super.canUse();
    }

    @Override
    public void tick() {
        if (mob instanceof IPanickableSchooling panickable) {
            List<Class<? extends Entity>> list = panickable.toAvoid();

            for (Class<? extends Entity> entity : list) {
                List<? extends Entity> livingEntityList = mob.level().getEntities(EntityTypeTest.forClass(entity), mob.getBoundingBox().inflate(12.0D), e -> !e.isSpectator());

                if (livingEntityList.isEmpty()) {
                    super.tick();
                }
                else {
                    if (mob.leader == null) {
                        super.tick();
                    }
                    else if (mob.leader.hasFollowers()) {
                        mob.leader.removeFollower(); // todo - fix crash. removeFollower() results in crash from "this.leader is null"
                    }
                    if (panickable.avoidGoal() != null) {
                        panickable.avoidGoal().start();
                    }
                }
            }
        }

    }
}
