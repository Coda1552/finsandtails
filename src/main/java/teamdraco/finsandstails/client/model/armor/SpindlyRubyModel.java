package teamdraco.finsandstails.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.charms.SpindlyRubyCharm;

public class SpindlyRubyModel extends AnimatedGeoModel<SpindlyRubyCharm> {

    @Override
    public ResourceLocation getModelLocation(SpindlyRubyCharm object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/armor/spindly_charm.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpindlyRubyCharm object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/armor/spindly_ruby_charm.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpindlyRubyCharm object) {
        return null;
    }
}
