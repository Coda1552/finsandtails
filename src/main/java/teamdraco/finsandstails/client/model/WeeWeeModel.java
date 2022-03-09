package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.TealArrowfishEntity;
import teamdraco.finsandstails.common.entities.WeeWeeEntity;

public class WeeWeeModel extends AnimatedGeoModel<WeeWeeEntity> {

    @Override
    public ResourceLocation getModelLocation(WeeWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/wee_wee.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WeeWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wee_wee.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WeeWeeEntity wee) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee.animation.json");
    }

    @Override
    public void setLivingAnimations(WeeWeeEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotationZ(1.5708f);
        }
    }
}
