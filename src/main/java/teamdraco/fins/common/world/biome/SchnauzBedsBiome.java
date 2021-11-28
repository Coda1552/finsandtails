package teamdraco.fins.common.world.biome;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import teamdraco.fins.init.FinsEntities;

public class SchnauzBedsBiome extends FinsBiome {
    static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, "fins:schnauz_beds", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.SAND.defaultBlockState(), Blocks.SANDSTONE.defaultBlockState(), Blocks.SAND.defaultBlockState())));
    static final Biome.Climate CLIMATE = new Biome.Climate(Biome.RainType.RAIN, 0.8F, Biome.TemperatureModifier.NONE, 0.4F);

    static final MobSpawnInfo.Builder SPAWN_SETTINGS = new MobSpawnInfo.Builder();

    static final BiomeGenerationSettings.Builder GENERATION_SETTINGS = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SURFACE_BUILDER);

    public SchnauzBedsBiome() {
        super(CLIMATE, Biome.Category.OCEAN, -1.2F, .15F, (new BiomeAmbience.Builder()).waterColor(4566523).waterFogColor(2587774).fogColor(12638463).skyColor(7842047).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build(), GENERATION_SETTINGS.build(), SPAWN_SETTINGS.build());
    }

    static {
        GENERATION_SETTINGS.addStructureStart(StructureFeatures.RUINED_PORTAL_OCEAN);

        DefaultBiomeFeatures.addDefaultOres(GENERATION_SETTINGS);

        SPAWN_SETTINGS.addSpawn(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(FinsEntities.BANDED_REDBACK_SHRIMP.get(), 1000, 3, 3));
    }
}