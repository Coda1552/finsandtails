package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.BandedRedbackShrimpEntity;
import teamdraco.finsandstails.common.entities.WherbleEntity;

public class BandedRedbackShrimpModel extends AnimatedGeoModel<BandedRedbackShrimpEntity> {

    @Override
    public ResourceLocation getModelLocation(BandedRedbackShrimpEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/banded_redback_shrimp.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BandedRedbackShrimpEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/banded_redback_shrimp.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BandedRedbackShrimpEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/banded_redback_shrimp.animations.json");
    }

    @Override
    public void setLivingAnimations(BandedRedbackShrimpEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

        if (!entity.isInWater()) {
            body.setRotationZ(1.5708f);
        }
        else {
            body.setRotationX(extraData.netHeadYaw * (float)Math.PI / 180F);
            body.setRotationY(extraData.headPitch * (float)Math.PI / 180F);
        }
    }
}
