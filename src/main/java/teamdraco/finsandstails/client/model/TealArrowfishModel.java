package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.TealArrowfishEntity;

public class TealArrowfishModel extends GeoModel<TealArrowfishEntity> {

    @Override
    public ResourceLocation getModelResource(TealArrowfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/teal_arrowfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TealArrowfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/teal_arrowfish.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TealArrowfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/teal_arrowfish.animations.json");
    }

    @Override
    public void setCustomAnimations(TealArrowfishEntity entity, long uniqueID, AnimationState<TealArrowfishEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotZ(1.5708f);
        }
    }
}
