package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.FlatbackSuckerEntity;

public class FlatbackSuckerModel extends GeoModel<FlatbackSuckerEntity> {

    @Override
    public ResourceLocation getModelResource(FlatbackSuckerEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/flatback_sucker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FlatbackSuckerEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/flatback_sucker.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FlatbackSuckerEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/flatback_sucker.animations.json");
    }
}
