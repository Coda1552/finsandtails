package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.WanderingSailorEntity;

public class WanderingSailorModel extends GeoModel<WanderingSailorEntity> {

    @Override
    public ResourceLocation getModelResource(WanderingSailorEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/wandering_sailor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WanderingSailorEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wandering_sailor/wandering_sailor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WanderingSailorEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wandering_sailor.animations.json");
    }
}
