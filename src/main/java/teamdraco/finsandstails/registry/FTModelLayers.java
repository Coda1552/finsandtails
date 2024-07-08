package teamdraco.finsandstails.registry;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;

public class FTModelLayers {

    public static final ModelLayerLocation BANDED_REDBACK_SHRIMP = main("banded_redback_shrimp");
    public static final ModelLayerLocation GOLDEN_RIVER_RAY = main("golden_river_ray");

    private static ModelLayerLocation register(String id, String name) {
        return new ModelLayerLocation(new ResourceLocation(FinsAndTails.MOD_ID, id), name);
    }
    private static ModelLayerLocation main(String id) {
        return register(id, "main");
    }
}
