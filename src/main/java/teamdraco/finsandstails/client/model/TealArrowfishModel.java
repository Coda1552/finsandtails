package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.TealArrowfishEntity;

public class TealArrowfishModel extends AnimatedGeoModel<TealArrowfishEntity> {

    @Override
    public ResourceLocation getModelLocation(TealArrowfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/teal_arrowfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TealArrowfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/teal_arrowfish.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TealArrowfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/teal_arrowfish.animations.json");
    }

    @Override
    public void setLivingAnimations(TealArrowfishEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotationZ(1.5708f);
        }
    }
}
