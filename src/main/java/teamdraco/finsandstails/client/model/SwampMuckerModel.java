package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.SwampMuckerEntity;

public class SwampMuckerModel extends GeoModel<SwampMuckerEntity> {

    @Override
    public ResourceLocation getModelResource(SwampMuckerEntity swampMucker) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/swamp_mucker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SwampMuckerEntity swampMucker) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/swamp_mucker.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SwampMuckerEntity swampMucker) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/swamp_mucker.animations.json");
    }
}
