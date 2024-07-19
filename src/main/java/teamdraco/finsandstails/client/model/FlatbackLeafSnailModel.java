package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.FlatbackLeafSnailEntity;

public class FlatbackLeafSnailModel extends DefaultedEntityGeoModel<FlatbackLeafSnailEntity> {

    public FlatbackLeafSnailModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "flatback_leaf_snail"));
    }
}
