package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.GopjetEntity;

public class GopjetModel extends GeoModel<GopjetEntity> {
    private static final ResourceLocation GOPJET = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/gopjet/gopjet.png");
    private static final ResourceLocation BOOSTING = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/gopjet/boosting.png");

    @Override
    public ResourceLocation getModelResource(GopjetEntity gopjet) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/gopjet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GopjetEntity gopjet) {
        return gopjet.isBoosting() ? BOOSTING : GOPJET;
    }

    @Override
    public ResourceLocation getAnimationResource(GopjetEntity gopjet) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/gopjet.animations.json");
    }
}
