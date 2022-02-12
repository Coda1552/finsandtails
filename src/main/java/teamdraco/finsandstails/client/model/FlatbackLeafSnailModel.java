package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.finsandtails.geckolib3.core.processor.IBone;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.FlatbackLeafSnailEntity;
import teamdraco.finsandstails.common.entities.RubberBellyGliderEntity;

public class FlatbackLeafSnailModel extends AnimatedGeoModel<FlatbackLeafSnailEntity> {

    @Override
    public ResourceLocation getModelLocation(FlatbackLeafSnailEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/flatback_leaf_snail.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FlatbackLeafSnailEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/flatback_leaf_snail.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FlatbackLeafSnailEntity entity) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/flatback_leaf_snail.animation.json");
    }

    @Override
    public void setLivingAnimations(FlatbackLeafSnailEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");

        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
            body.setPositionY(-2.75F);
        }
    }
}
