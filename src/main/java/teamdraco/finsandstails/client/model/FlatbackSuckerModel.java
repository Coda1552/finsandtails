package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.FlatbackSuckerEntity;

public class FlatbackSuckerModel extends DefaultedEntityGeoModel<FlatbackSuckerEntity> {

    public FlatbackSuckerModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "flatback_sucker"));
    }
}
