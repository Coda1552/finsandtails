package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.SwampMuckerEntity;

public class SwampMuckerModel extends AnimatedGeoModel<SwampMuckerEntity> {

    @Override
    public ResourceLocation getModelLocation(SwampMuckerEntity swampMucker) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/swamp_mucker.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SwampMuckerEntity swampMucker) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/swamp_mucker.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SwampMuckerEntity swampMucker) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee.animation.json");
    }
}
