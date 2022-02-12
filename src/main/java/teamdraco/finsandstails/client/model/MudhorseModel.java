package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.finsandtails.geckolib3.core.processor.IBone;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.MudhorseEntity;
import teamdraco.finsandstails.common.entities.RiverPebbleSnailEntity;

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
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/mudhorse.animations.json");
    }

    @Override
    public void setLivingAnimations(MudhorseEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");

        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
            body.setPositionY(-2.75F);
        }
    }
}