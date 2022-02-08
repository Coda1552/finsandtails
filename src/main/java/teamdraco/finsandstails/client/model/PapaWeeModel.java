package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.PapaWeeEntity;

public class PapaWeeModel extends AnimatedGeoModel<PapaWeeEntity> {

    @Override
    public ResourceLocation getModelLocation(PapaWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/papa_wee.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PapaWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/papa_wee.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PapaWeeEntity wee) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee.animation.json");
    }
}
