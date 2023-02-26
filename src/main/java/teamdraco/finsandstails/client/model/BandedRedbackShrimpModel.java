package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.BandedRedbackShrimpEntity;

public class BandedRedbackShrimpModel extends AnimatedGeoModel<BandedRedbackShrimpEntity> {

    @Override
    public ResourceLocation getModelResource(BandedRedbackShrimpEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/banded_redback_shrimp.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BandedRedbackShrimpEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/banded_redback_shrimp.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BandedRedbackShrimpEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/banded_redback_shrimp.animations.json");
    }

    @Override
    public void setCustomAnimations(BandedRedbackShrimpEntity animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone body = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);

        if (!animatable.isInWater()) {
            body.setRotationZ(1.5708f);
        } else {
            body.setRotationX(extraData.headPitch * (float) Math.PI / 180F);
            body.setRotationY(extraData.netHeadYaw * (float) Math.PI / 180F);
        }
    }
}
