package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.PhantomNudibranchEntity;

public class PhantomNudibranchModel extends DefaultedEntityGeoModel<PhantomNudibranchEntity> {

    public PhantomNudibranchModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "phantom_nudibranch"));
    }
}
