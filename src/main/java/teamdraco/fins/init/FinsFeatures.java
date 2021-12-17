package teamdraco.fins.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.world.tree.LaminaTreeFeature;

public class FinsFeatures {
    public static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, FinsAndTails.MOD_ID);

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> LAMINA_TREE_FEATURE = REGISTER.register("lamina_tree", LaminaTreeFeature::new);

    public static class Configured {
        public static final ConfiguredFeature<?, ?> LAMINA_TREE = FinsFeatures.LAMINA_TREE_FEATURE.get()
                .configured(FinsFeatures.Configs.LAMINA_TREE_CONFIG)
                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1)));

        private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> cf) {
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(FinsAndTails.MOD_ID, name), cf);
        }

        public static void registerConfiguredFeatures() {
            register("lamina_tree", LAMINA_TREE);

        }
    }

    public static class Configs {
        public static final BaseTreeFeatureConfig LAMINA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(FinsBlocks.LAMINA_STALK.get().defaultBlockState()),
                new SimpleBlockStateProvider(FinsBlocks.LAMINA_PADS.get().defaultBlockState()),
                new BlobFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), 0),
                new StraightTrunkPlacer(0, 0, 0),
                new TwoLayerFeature(0, 0, 0)))
                .ignoreVines()
                .heightmap(Heightmap.Type.MOTION_BLOCKING)
                .build();
    }
}
