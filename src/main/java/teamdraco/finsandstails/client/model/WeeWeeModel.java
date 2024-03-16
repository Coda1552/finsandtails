package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.WeeWeeEntity;

public class WeeWeeModel extends GeoModel<WeeWeeEntity> {

    @Override
    public ResourceLocation getModelResource(WeeWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/wee_wee.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeeWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wee_wee.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeeWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee_wee.animations.json");
    }

    @Override
    public void setCustomAnimations(WeeWeeEntity entity, long uniqueID, AnimationState<WeeWeeEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("root");

        if (!entity.isInWater()) {
            body.setRotZ(1.5708f);
        }
    }
}
