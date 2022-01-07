package teamdraco.finsandstails.common.entities.util.goals;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import teamdraco.finsandstails.common.entities.BluWeeEntity;
import teamdraco.finsandstails.common.entities.PeaWeeEntity;
import teamdraco.finsandstails.common.entities.VibraWeeEntity;
import teamdraco.finsandstails.common.entities.WeeWeeEntity;

public class PapaWeeAttractionGoal extends Goal {
    private final MobEntity entity;

    public PapaWeeAttractionGoal(MobEntity entity) {
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
        for (MobEntity mobEntity : entity.level.getEntitiesOfClass(MobEntity.class, entity.getBoundingBox().inflate(12.0D), e -> e != entity && e.getVehicle() == null)) {
            if (mobEntity instanceof WeeWeeEntity || mobEntity instanceof VibraWeeEntity || mobEntity instanceof PeaWeeEntity || mobEntity instanceof BluWeeEntity) {
                mobEntity.getNavigation().moveTo(entity, mobEntity.getSpeed() * 1.25D);
            }
        }
    }
}