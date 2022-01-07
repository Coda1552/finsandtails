package teamdraco.finsandstails.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import teamdraco.finsandstails.FinsAndTails;

public class FinsConfiguredStructures {
    public static StructureFeature<?, ?> CONFIGURED_SAILORS_SHIP = FinsStructures.SAILORS_SHIP.get().configured(IFeatureConfig.NONE);

    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(FinsAndTails.MOD_ID, "configured_sailors_ship"), CONFIGURED_SAILORS_SHIP);

        FlatGenerationSettings.STRUCTURE_FEATURES.put(FinsStructures.SAILORS_SHIP.get(), CONFIGURED_SAILORS_SHIP);
    }
}
