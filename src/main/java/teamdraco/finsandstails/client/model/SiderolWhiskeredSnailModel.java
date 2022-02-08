package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.BluWeeEntity;
import teamdraco.finsandstails.common.entities.SiderolWhiskeredSnailEntity;

public class SiderolWhiskeredSnailModel extends AnimatedGeoModel<SiderolWhiskeredSnailEntity> {

    @Override
    public ResourceLocation getModelLocation(SiderolWhiskeredSnailEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/siderol_whiskered_snail.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SiderolWhiskeredSnailEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/siderol_whiskered_snail.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SiderolWhiskeredSnailEntity wee) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee.animation.json");
    }
}
