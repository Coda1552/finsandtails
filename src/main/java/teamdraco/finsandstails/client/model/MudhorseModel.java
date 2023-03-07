package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.MudhorseEntity;

public class MudhorseModel extends AnimatedGeoModel<MudhorseEntity> {

    @Override
    public ResourceLocation getModelResource(MudhorseEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/mudhorse.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MudhorseEntity wee) {
        return wee.commanderSetTime > 0 ? new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/mudhorse/entranced.png") : new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/mudhorse/mudhorse.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MudhorseEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/mudhorse.animations.json");
    }

    @Override
    public void setCustomAnimations(MudhorseEntity entity, int uniqueID, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");

        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
        }
    }
}