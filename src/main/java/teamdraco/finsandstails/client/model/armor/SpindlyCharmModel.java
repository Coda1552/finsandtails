package teamdraco.finsandstails.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.SpindlyCharmItem;

public class SpindlyCharmModel extends AnimatedGeoModel<SpindlyCharmItem> {

    @Override
    public ResourceLocation getModelResource(SpindlyCharmItem object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/armor/spindly_charm.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpindlyCharmItem object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/armor/spindly_" + object.getTypeName() + "_charm.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpindlyCharmItem object) {
        return null;
    }
}
