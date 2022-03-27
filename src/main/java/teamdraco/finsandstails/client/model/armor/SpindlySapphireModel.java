package teamdraco.finsandstails.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.charms.SpindlySapphireCharm;

public class SpindlySapphireModel extends AnimatedGeoModel<SpindlySapphireCharm> {

    @Override
    public ResourceLocation getModelLocation(SpindlySapphireCharm object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/armor/spindly_charm.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpindlySapphireCharm object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/armor/spindly_sapphire_charm.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpindlySapphireCharm object) {
        return null;
    }
}
