package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.BandedRedbackShrimpEntity;

public class BandedRedbackShrimpModel extends AnimatedGeoModel<BandedRedbackShrimpEntity> {

    @Override
    public ResourceLocation getModelLocation(BandedRedbackShrimpEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/banded_redback_shrimp.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BandedRedbackShrimpEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/banded_redback_shrimp.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BandedRedbackShrimpEntity entity) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/banded_redback_shrimp.animation.json");
    }
}
