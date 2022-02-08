package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.MudhorseEntity;

public class MudhorseModel extends AnimatedGeoModel<MudhorseEntity> {

    @Override
    public ResourceLocation getModelLocation(MudhorseEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/mudhorse.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MudhorseEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/mudhorse.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MudhorseEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/mudhorse.animation.json");
    }
}
