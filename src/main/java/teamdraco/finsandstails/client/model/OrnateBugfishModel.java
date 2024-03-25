package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.OrnateBugfishEntity;

public class OrnateBugfishModel extends DefaultedEntityGeoModel<OrnateBugfishEntity> {

    public OrnateBugfishModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "ornate_bugfish"));
    }


    @Override
    public void setCustomAnimations(OrnateBugfishEntity entity, long uniqueID, AnimationState<OrnateBugfishEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotZ(1.5708f);

        }
    }
}
