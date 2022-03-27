package teamdraco.finsandstails.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.charms.SpindlyAmberCharm;
import teamdraco.finsandstails.common.items.charms.SpindlyGemCharm;

public class SpindlyAmberModel extends AnimatedGeoModel<SpindlyAmberCharm> {

    @Override
    public ResourceLocation getModelLocation(SpindlyAmberCharm object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/armor/spindly_charm.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpindlyAmberCharm object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/armor/spindly_amber_charm.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpindlyAmberCharm object) {
        return null;
    }
}
