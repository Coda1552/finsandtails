package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.FlatbackSuckerEntity;

public class FlatbackSuckerModel extends AnimatedGeoModel<FlatbackSuckerEntity> {

    @Override
    public ResourceLocation getModelLocation(FlatbackSuckerEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/flatback_sucker.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FlatbackSuckerEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/flatback_sucker.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FlatbackSuckerEntity entity) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/flatback_sucker.animation.json");
    }
}
