package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.SiderolWhiskeredSnailEntity;

public class SiderolWhiskeredSnailModel extends AnimatedGeoModel<SiderolWhiskeredSnailEntity> {

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
    public void setCustomAnimations(SiderolWhiskeredSnailEntity entity, int uniqueID, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");

        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
            body.setPositionY(-1F);
        }
    }
}
