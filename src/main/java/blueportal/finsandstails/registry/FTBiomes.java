package blueportal.finsandstails.registry;

import blueportal.finsandstails.FinsAndTails;
import blueportal.finsandstails.util.FeatureOrderUtil;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FTBiomes {
  public static final ResourceKey<Biome> PENGLIL_PARADISE = createKey("penglil_paradise");

  public static void bootstrap(BootstapContext<Biome> context) {
    HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
    HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);

    context.register(PENGLIL_PARADISE, penglilParadise(features, carvers));
  }

  public static ResourceKey<Biome> createKey(String name) {
    return ResourceKey.create(Registries.BIOME, new ResourceLocation(FinsAndTails.MOD_ID, name));
  }

  private static Biome penglilParadise(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
    BiomeGenerationSettings.Builder biomeGenBuilder = new BiomeGenerationSettings.Builder(features, carvers);
    globalOverworldGeneration(biomeGenBuilder);
    BiomeDefaultFeatures.addDefaultOres(biomeGenBuilder);
    BiomeDefaultFeatures.addDefaultSoftDisks(biomeGenBuilder);
    BiomeDefaultFeatures.addMossyStoneBlock(biomeGenBuilder);

    MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
    BiomeDefaultFeatures.warmOceanSpawns(mobSpawnBuilder, 0, 0);
    mobSpawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.TURTLE, 12, 4, 4))
            .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(FTEntities.PENGLIL.get(), 12, 3, 6));
    BiomeDefaultFeatures.commonSpawns(mobSpawnBuilder);

    return biome(true, 0.6F, 1.2F, biomeGenBuilder, mobSpawnBuilder)
            .specialEffects(new BiomeSpecialEffects.Builder()
                    .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_UNDER_WATER))
                    .waterColor(4445678)
                    .waterFogColor(270131)
                    .fogColor(12638463)
                    .grassColorOverride(15785814)
                    .skyColor(15918728/*calculateSkyColor(1.2F)*/)
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                    .build()
            )
            .build();
  }

  private static Biome.BiomeBuilder biome(boolean hasPrecipitation, float downfall, float temperature, BiomeGenerationSettings.Builder biomeGenBuilder, MobSpawnSettings.Builder mobSpawnBuilder) {
    return new Biome.BiomeBuilder().hasPrecipitation(hasPrecipitation).downfall(downfall).temperature(temperature).generationSettings(biomeGenBuilder.build()).mobSpawnSettings(mobSpawnBuilder.build());
  }

  protected static int calculateSkyColor(float temperature) {
    float i = temperature / 3.0F;
    i = Mth.clamp(i, -1.0F, 1.0F);
    return Mth.hsvToRgb(0.62222224F - i * 0.05F, 0.5F + i * 0.1F, 1.0F);
  }

  private static void globalOverworldGeneration(BiomeGenerationSettings.Builder biomeGenBuilder) {
    BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeGenBuilder);
    BiomeDefaultFeatures.addDefaultCrystalFormations(biomeGenBuilder);
    BiomeDefaultFeatures.addDefaultMonsterRoom(biomeGenBuilder);
    BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeGenBuilder);
    BiomeDefaultFeatures.addDefaultSprings(biomeGenBuilder);
    BiomeDefaultFeatures.addSurfaceFreezing(biomeGenBuilder);
  }
}