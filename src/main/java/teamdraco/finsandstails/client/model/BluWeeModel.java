package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.finsandtails.geckolib3.core.processor.IBone;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.BandedRedbackShrimpEntity;
import teamdraco.finsandstails.common.entities.BluWeeEntity;

public class BluWeeModel extends AnimatedGeoModel<BluWeeEntity> {

    @Override
    public ResourceLocation getModelLocation(BluWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/wee.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BluWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/blu_wee.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BluWeeEntity wee) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee.animation.json");
    }

    @Override
    public void setLivingAnimations(BluWeeEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotationZ(1.5708f);
        }
    }
}
