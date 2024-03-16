package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.item.TealArrowfishArrowEntity;

public class TealArrowfishArrowModel extends GeoModel<TealArrowfishArrowEntity> {

    @Override
    public ResourceLocation getModelResource(TealArrowfishArrowEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/projectile/teal_arrowfish_arrow.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TealArrowfishArrowEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/teal_arrowfish.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TealArrowfishArrowEntity wee) {
        return null;
    }
}
