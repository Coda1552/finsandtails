package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.BluWeeEntity;

public class BluWeeModel extends AnimatedGeoModel<BluWeeEntity> {

    @Override
    public ResourceLocation getModelLocation(BluWeeEntity bluWeeEntity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/wee.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BluWeeEntity bluWeeEntity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/blu_wee.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BluWeeEntity bluWeeEntity) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee.animation.json");
    }
}
