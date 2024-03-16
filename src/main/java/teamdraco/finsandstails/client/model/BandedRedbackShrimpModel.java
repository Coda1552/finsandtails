package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.BandedRedbackShrimpEntity;

public class BandedRedbackShrimpModel extends GeoModel<BandedRedbackShrimpEntity> {

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
    public void setCustomAnimations(BandedRedbackShrimpEntity animatable, long instanceId, AnimationState<BandedRedbackShrimpEntity> animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        CoreGeoBone body = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = animationEvent.getData(DataTickets.ENTITY_MODEL_DATA);

        if (!animatable.isInWater()) {
            body.setRotZ(1.5708f);
        } else {
            body.setRotX(extraData.headPitch() * (float) Math.PI / 180F);
            body.setRotY(extraData.netHeadYaw() * (float) Math.PI / 180F);
        }
    }
}
