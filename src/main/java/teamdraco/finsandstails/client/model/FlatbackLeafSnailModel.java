package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.FlatbackLeafSnailEntity;

public class FlatbackLeafSnailModel extends GeoModel<FlatbackLeafSnailEntity> {

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

}
