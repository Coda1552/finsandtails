package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.RubberBellyGliderEntity;

public class RubberBellyGliderModel extends AnimatedGeoModel<RubberBellyGliderEntity> {
    private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/rubber_belly_glider/normal.png");
    private static final ResourceLocation TEXTURE_PUFFED = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/rubber_belly_glider/puffed.png");

    @Override
    public ResourceLocation getModelLocation(RubberBellyGliderEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/rubber_belly_glider.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RubberBellyGliderEntity wee) {
        return wee.isPuffed() ? TEXTURE_PUFFED : TEXTURE_NORMAL;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RubberBellyGliderEntity wee) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee.animation.json");
    }
}
