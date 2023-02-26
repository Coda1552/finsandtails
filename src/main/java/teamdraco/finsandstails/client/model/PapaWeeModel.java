package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.PapaWeeEntity;

public class PapaWeeModel extends AnimatedGeoModel<PapaWeeEntity> {

    @Override
    public ResourceLocation getModelResource(PapaWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/papa_wee.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PapaWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/papa_wee.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PapaWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/papa_wee.animations.json");
    }
}
