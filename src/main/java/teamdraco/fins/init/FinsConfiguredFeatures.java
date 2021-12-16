package teamdraco.fins.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.world.tree.LaminaTreeFeature;

public class FinsConfiguredFeatures {
    public static ConfiguredFeature<?, ?> CONFIGURED_LAMINA_TREE;

    public static void registerConfiguredStructures() {
        CONFIGURED_LAMINA_TREE = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(FinsAndTails.MOD_ID, "configured_lamina_tree"),
                FinsFeatures.LAMINA_TREE.get()
                        .configured(new ProbabilityConfig(0.5F))
                        .count(32)
                        .decorated(Features.Placements.TOP_SOLID_HEIGHTMAP).squared());
    }
}