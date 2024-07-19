package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.GopjetEntity;

public class GopjetModel extends DefaultedEntityGeoModel<GopjetEntity> {
    private static final ResourceLocation GOPJET = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/gopjet/gopjet.png");
    private static final ResourceLocation BOOSTING = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/gopjet/boosting.png");

    public GopjetModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "gopjet"));
    }

    @Override
    public ResourceLocation getTextureResource(GopjetEntity gopjet) {
        return gopjet.isBoosting() ? BOOSTING : GOPJET;
    }
}
