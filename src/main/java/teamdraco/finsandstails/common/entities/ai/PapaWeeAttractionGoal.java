package teamdraco.finsandstails.common.entities.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import teamdraco.finsandstails.common.entities.BluWeeEntity;
import teamdraco.finsandstails.common.entities.PeaWeeEntity;
import teamdraco.finsandstails.common.entities.VibraWeeEntity;
import teamdraco.finsandstails.common.entities.WeeWeeEntity;

public class PapaWeeAttractionGoal extends Goal {
    private final Mob entity;

    public PapaWeeAttractionGoal(Mob entity) {
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return entity.tickCount % 60 == 0 && entity.getPassengers().isEmpty();
    }

    @Override
    public boolean canContinueToUse() {
        return entity.tickCount % 80 != 0;
    }

    @Override
    public void start() {
        super.start();
        for (Mob mob : entity.level().getEntitiesOfClass(Mob.class, entity.getBoundingBox().inflate(12.0D), e -> e != entity && e.getVehicle() == null)) {
            if (mob instanceof WeeWeeEntity || mob instanceof VibraWeeEntity || mob instanceof PeaWeeEntity || mob instanceof BluWeeEntity) {
                mob.getNavigation().moveTo(entity, mob.getSpeed() * 1.25D);
            }
        }
    }
}