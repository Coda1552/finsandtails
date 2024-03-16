package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.RedBullCrabEntity;

public class RedBullCrabModel extends GeoModel<RedBullCrabEntity> {

    @Override
    public ResourceLocation getModelResource(RedBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/bull_crab.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RedBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/red_bull_crab.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RedBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/bull_crab.animations.json");
    }
}
