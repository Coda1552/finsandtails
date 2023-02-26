package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.HighFinnedBlueEntity;

public class HighFinnedBlueModel extends AnimatedGeoModel<HighFinnedBlueEntity> {

    @Override
    public ResourceLocation getModelResource(HighFinnedBlueEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/high_finned_blue.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HighFinnedBlueEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/high_finned_blue.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HighFinnedBlueEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/high_finned_blue.animations.json");
    }

    @Override
    public void setCustomAnimations(HighFinnedBlueEntity entity, int uniqueID, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotationZ(1.5708f);
        }
    }
}
