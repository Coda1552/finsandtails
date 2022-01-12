package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.PeaWeeEntity;

public class PeaWeeModel extends AnimatedGeoModel<PeaWeeEntity> {

    @Override
    public ResourceLocation getModelLocation(PeaWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/wee.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PeaWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/pea_wee.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PeaWeeEntity wee) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee.animation.json");
    }
}
