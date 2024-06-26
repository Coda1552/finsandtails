package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.SwampMuckerEntity;

public class SwampMuckerModel extends DefaultedEntityGeoModel<SwampMuckerEntity> {

    public SwampMuckerModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "swamp_mucker"));
    }
}
