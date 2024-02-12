package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.RubberBellyGliderEntity;

public class RubberBellyGliderModel extends GeoModel<RubberBellyGliderEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/rubber_belly_glider.png");

    @Override
    public ResourceLocation getModelResource(RubberBellyGliderEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/rubber_belly_glider.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RubberBellyGliderEntity wee) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(RubberBellyGliderEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/rubber_belly_glider.animations.json");
    }

    @Override
    public void setCustomAnimations(RubberBellyGliderEntity entity, long uniqueID, AnimationState<RubberBellyGliderEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("body");

        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (entity.isInWater()) {
            body.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
            body.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        }

        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
            body.setPosY(-1F);
        }
        else {
            body.setPosY(-0.2F);
        }
    }
}
