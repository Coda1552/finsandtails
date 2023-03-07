package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.BluWeeEntity;

public class BluWeeModel extends AnimatedGeoModel<BluWeeEntity> {

    @Override
    public ResourceLocation getModelResource(BluWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/wee.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BluWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/blu_wee.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BluWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee.animations.json");
    }

    @Override
    public void setCustomAnimations(BluWeeEntity entity, int uniqueID, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotationZ(1.5708f);
        }
    }
}
