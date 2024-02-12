package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.HighFinnedBlueEntity;

public class HighFinnedBlueModel extends GeoModel<HighFinnedBlueEntity> {

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
    public void setCustomAnimations(HighFinnedBlueEntity entity, long uniqueID, AnimationState<HighFinnedBlueEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotZ(1.5708f);
        }
    }
}
