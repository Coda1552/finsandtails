package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.GopjetEntity;

public class GopjetModel extends AnimatedGeoModel<GopjetEntity> {
    private static final ResourceLocation GOPJET = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/gopjet/gopjet.png");
    private static final ResourceLocation BOOSTING = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/gopjet/boosting.png");

    @Override
    public ResourceLocation getModelLocation(GopjetEntity gopjet) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/gopjet.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GopjetEntity gopjet) {
        return gopjet.isBoosting() ? BOOSTING : GOPJET;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GopjetEntity gopjet) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/gopjet.animation.json");
    }
}
