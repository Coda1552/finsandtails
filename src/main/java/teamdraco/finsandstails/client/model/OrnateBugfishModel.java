package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.OrnateBugfishEntity;

public class OrnateBugfishModel extends GeoModel<OrnateBugfishEntity> {

    @Override
    public ResourceLocation getModelResource(OrnateBugfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/ornate_bugfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OrnateBugfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/ornate_bugfish.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OrnateBugfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/ornate_bugfish.animations.json");
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
