package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.MudhorseEntity;

public class MudhorseModel extends GeoModel<MudhorseEntity> {

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

}