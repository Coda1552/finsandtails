package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.BluWeeEntity;

public class BluWeeModel extends GeoModel<BluWeeEntity> {

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
    public void setCustomAnimations(BluWeeEntity entity, long uniqueID, AnimationState<BluWeeEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotZ(1.5708f);
        }
    }
}
