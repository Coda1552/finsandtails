package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.FlatbackLeafSnailEntity;

public class FlatbackLeafSnailModel extends AnimatedGeoModel<FlatbackLeafSnailEntity> {

    @Override
    public ResourceLocation getModelResource(FlatbackLeafSnailEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/flatback_leaf_snail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FlatbackLeafSnailEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/flatback_leaf_snail.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FlatbackLeafSnailEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/flatback_leaf_snail.animations.json");
    }

    @Override
    public void setCustomAnimations(FlatbackLeafSnailEntity entity, int uniqueID, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");

        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
            body.setPositionY(-1.5F);
        }
    }
}
