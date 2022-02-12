package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.VibraWeeEntity;

public class VibraWeeModel extends AnimatedGeoModel<VibraWeeEntity> {

    @Override
    public ResourceLocation getModelLocation(VibraWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/vibra_wee.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(VibraWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/blu_vibra_wee.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(VibraWeeEntity wee) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/vibra_wee.animation.json");
    }
}
