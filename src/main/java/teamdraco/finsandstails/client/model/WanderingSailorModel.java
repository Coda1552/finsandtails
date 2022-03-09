package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.WanderingSailorEntity;

public class WanderingSailorModel extends AnimatedGeoModel<WanderingSailorEntity> {

    @Override
    public ResourceLocation getModelLocation(WanderingSailorEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/wandering_sailor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WanderingSailorEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wandering_sailor/wandering_sailor.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WanderingSailorEntity entity) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wandering_sailor.animation.json");
    }
}
