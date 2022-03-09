package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.RedBullCrabEntity;

public class RedBullCrabModel extends AnimatedGeoModel<RedBullCrabEntity> {

    @Override
    public ResourceLocation getModelLocation(RedBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/bull_crab.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RedBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/red_bull_crab.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RedBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/bull_crab.animations.json");
    }
}
