package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.item.TealArrowfishArrowEntity;

public class TealArrowfishArrowModel extends DefaultedEntityGeoModel<TealArrowfishArrowEntity> {

    public TealArrowfishArrowModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "teal_arrowfish"));
    }

    @Override
    public ResourceLocation getAnimationResource(TealArrowfishArrowEntity wee) {
        return null;
    }
}
