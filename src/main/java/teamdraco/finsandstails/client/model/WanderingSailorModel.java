package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.WanderingSailorEntity;

public class WanderingSailorModel extends DefaultedEntityGeoModel<WanderingSailorEntity> {

    public WanderingSailorModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "wandering_sailor"));
    }

    @Override
    public ResourceLocation getTextureResource(WanderingSailorEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wandering_sailor/wandering_sailor.png");
    }
}
