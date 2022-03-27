package teamdraco.finsandstails.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.charms.SpindlyEmeraldCharm;

public class SpindlyEmeraldModel extends AnimatedGeoModel<SpindlyEmeraldCharm> {

    @Override
    public ResourceLocation getModelLocation(SpindlyEmeraldCharm object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/armor/spindly_charm.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpindlyEmeraldCharm object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/armor/spindly_emerald_charm.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpindlyEmeraldCharm object) {
        return null;
    }
}
