package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.PeaWeeEntity;

public class PeaWeeModel extends GeoModel<PeaWeeEntity> {

    @Override
    public ResourceLocation getModelResource(PeaWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/wee.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PeaWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/pea_wee.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PeaWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee.animations.json");
    }

    @Override
    public void setCustomAnimations(PeaWeeEntity entity, long uniqueID, AnimationState<PeaWeeEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotZ(1.5708f);

        }
    }
}