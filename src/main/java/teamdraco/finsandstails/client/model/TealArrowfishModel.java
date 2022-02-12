package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.TealArrowfishEntity;

public class TealArrowfishModel extends AnimatedGeoModel<TealArrowfishEntity> {

    @Override
    public ResourceLocation getModelLocation(TealArrowfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/teal_arrowfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TealArrowfishEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/teal_arrowfish.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TealArrowfishEntity wee) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee.animation.json");
    }
}
