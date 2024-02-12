package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.SiderolWhiskeredSnailEntity;

public class SiderolWhiskeredSnailModel extends GeoModel<SiderolWhiskeredSnailEntity> {

    @Override
    public ResourceLocation getModelResource(SiderolWhiskeredSnailEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/siderol_whiskered_snail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SiderolWhiskeredSnailEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/siderol_whiskered_snail.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SiderolWhiskeredSnailEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/siderol_whiskered_snail.animations.json");
    }

    @Override
    public void setCustomAnimations(SiderolWhiskeredSnailEntity entity, long uniqueID, AnimationState<SiderolWhiskeredSnailEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("body");

        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
            body.setPosY(-1F);
        }
    }
}
