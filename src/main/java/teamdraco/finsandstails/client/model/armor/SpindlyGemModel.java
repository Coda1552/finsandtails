package teamdraco.finsandstails.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.SpindlyGemCharmItem;

public class SpindlyGemModel extends AnimatedGeoModel<SpindlyGemCharmItem> {

    @Override
    public ResourceLocation getModelResource(SpindlyGemCharmItem object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/armor/spindly_charm.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpindlyGemCharmItem object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/armor/gem_crab_amulet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpindlyGemCharmItem object) {
        return null;
    }
}
