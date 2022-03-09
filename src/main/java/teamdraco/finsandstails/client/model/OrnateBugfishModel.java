package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.OrnateBugfishEntity;

public class OrnateBugfishModel extends AnimatedGeoModel<OrnateBugfishEntity> {

    @Override
    public ResourceLocation getModelLocation(OrnateBugfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/ornate_bugfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(OrnateBugfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/ornate_bugfish.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(OrnateBugfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/ornate_bugfish.animation.json");
    }

    @Override
    public void setLivingAnimations(OrnateBugfishEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotationZ(1.5708f);

        }
    }
}
