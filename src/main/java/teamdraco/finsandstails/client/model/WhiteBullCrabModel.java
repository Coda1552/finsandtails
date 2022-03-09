package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.WhiteBullCrabEntity;

public class WhiteBullCrabModel extends AnimatedGeoModel<WhiteBullCrabEntity> {

    @Override
    public ResourceLocation getModelLocation(WhiteBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/bull_crab.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WhiteBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/white_bull_crab.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WhiteBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/bull_crab.animations.json");
    }
}
