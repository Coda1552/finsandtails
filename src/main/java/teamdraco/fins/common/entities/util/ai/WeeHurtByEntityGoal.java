package teamdraco.fins.common.entities.util.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import teamdraco.fins.common.entities.PapaWeeEntity;

import java.util.List;

public class WeeHurtByEntityGoal extends Goal {
    private final AbstractFishEntity wee;

    public WeeHurtByEntityGoal(AbstractFishEntity owner) {
        this.wee = owner;
    }

    @Override
    public boolean canUse() {
        return wee.getLastHurtByMob() != null;
    }

    @Override
    public void start() {
        List<PapaWeeEntity> list = wee.level.getEntitiesOfClass(PapaWeeEntity.class, wee.getBoundingBox().inflate(7.5D));
        LivingEntity attacker = wee.getLastHurtByMob();

        if (!list.isEmpty()) {
            for (PapaWeeEntity papaWee : list) {
                papaWee.setTarget(attacker);
            }
        }

        super.start();
    }
}
