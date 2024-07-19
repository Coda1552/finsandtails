package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.PapaWeeEntity;

public class PapaWeeModel extends DefaultedEntityGeoModel<PapaWeeEntity> {

    public PapaWeeModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "papa_wee"));
    }
}