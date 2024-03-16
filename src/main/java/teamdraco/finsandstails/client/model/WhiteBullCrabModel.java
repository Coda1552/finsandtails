package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.WhiteBullCrabEntity;

public class WhiteBullCrabModel extends GeoModel<WhiteBullCrabEntity> {

    @Override
    public ResourceLocation getModelResource(WhiteBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/bull_crab.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WhiteBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/white_bull_crab.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WhiteBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/bull_crab.animations.json");
    }
}
