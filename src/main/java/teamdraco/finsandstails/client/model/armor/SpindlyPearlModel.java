package teamdraco.finsandstails.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.charms.SpindlyAmberCharm;
import teamdraco.finsandstails.common.items.charms.SpindlyPearlCharm;

public class SpindlyPearlModel extends AnimatedGeoModel<SpindlyPearlCharm> {

    @Override
    public ResourceLocation getModelLocation(SpindlyPearlCharm object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/armor/spindly_charm.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpindlyPearlCharm object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/armor/spindly_pearl_charm.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpindlyPearlCharm object) {
        return null;
    }
}
