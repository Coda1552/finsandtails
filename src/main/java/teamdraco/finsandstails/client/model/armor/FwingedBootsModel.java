package teamdraco.finsandstails.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.FwingedBootsItem;

public class FwingedBootsModel extends AnimatedGeoModel<FwingedBootsItem> {

    @Override
    public ResourceLocation getModelResource(FwingedBootsItem object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/armor/fwinged_boots.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FwingedBootsItem object) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/armor/fwinged_boots.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FwingedBootsItem object) {
        return null;
    }
}
