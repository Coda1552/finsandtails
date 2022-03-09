package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.BluWeeEntity;
import teamdraco.finsandstails.common.entities.SiderolWhiskeredSnailEntity;
import teamdraco.finsandstails.common.entities.WherbleEntity;

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

    @Override
    public void setLivingAnimations(SiderolWhiskeredSnailEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");

        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
            body.setPositionY(-1F);
        }
    }
}
