package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.PhantomNudibranchEntity;

public class PhantomNudibranchModel extends AnimatedGeoModel<PhantomNudibranchEntity> {

    @Override
    public ResourceLocation getModelLocation(PhantomNudibranchEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/phantom_nudibranch.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PhantomNudibranchEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/phantom_nudibranch.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PhantomNudibranchEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/phantom_nudibranch.animations.json");
    }
}
