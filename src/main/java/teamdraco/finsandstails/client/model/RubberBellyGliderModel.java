package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.RubberBellyGliderEntity;

public class RubberBellyGliderModel extends AnimatedGeoModel<RubberBellyGliderEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/rubber_belly_glider.png");

    @Override
    public ResourceLocation getModelLocation(RubberBellyGliderEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/rubber_belly_glider.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RubberBellyGliderEntity wee) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RubberBellyGliderEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/rubber_belly_glider.animations.json");
    }

    @Override
    public void setLivingAnimations(RubberBellyGliderEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

        if (entity.isInWater()) {
            body.setRotationX(extraData.headPitch * ((float)Math.PI / 180F));
            body.setRotationY(extraData.netHeadYaw * ((float)Math.PI / 180F));
        }

        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
            body.setPositionY(-1F);
        }
        else {
            body.setPositionY(-0.2F);
        }
    }
}
