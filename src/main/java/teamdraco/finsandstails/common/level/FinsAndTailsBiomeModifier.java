package teamdraco.finsandstails.common.level;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.finsandstails.FTConfig;
import teamdraco.finsandstails.registry.FTBiomeModifiers;
import teamdraco.finsandstails.registry.FTEntities;

import java.util.Optional;

public class FinsAndTailsBiomeModifier implements BiomeModifier {
    public static final FinsAndTailsBiomeModifier INSTANCE = new FinsAndTailsBiomeModifier();


    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && biome.containsTag(BiomeTags.IS_OVERWORLD) && !biome.is(Biomes.DEEP_DARK) && !biome.is(Tags.Biomes.IS_VOID)) {
            if (biome.is(Tags.Biomes.IS_SWAMP)) {
                builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.SWAMP_MUCKER.get(), FTConfig.Common.INSTANCE.swampMuckerSpawnWeight.get(), 2, 4));
                builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.FLATBACK_SUCKER.get(), FTConfig.Common.INSTANCE.flatbackSuckerSpawnWeight.get(), 1, 1));
                builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.MUDHORSE.get(), FTConfig.Common.INSTANCE.mudhorseSpawnWeight.get(), 2, 3));
            }

            if (biome.is(BiomeTags.IS_JUNGLE)) {
                builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.PEA_WEE.get(), FTConfig.Common.INSTANCE.peaWeeSpawnWeight.get(), 1, 3));
                builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.VIBRA_WEE.get(), FTConfig.Common.INSTANCE.vibraWeeSpawnWeight.get(), 2, 5));
            }

            if (biome.is(BiomeTags.IS_HILL)) {
                builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.SIDEROL_WHISKERED_SNAIL.get(), FTConfig.Common.INSTANCE.siderolWhiskeredSnailSpawnWeight.get(), 1, 2));
            }

            if (biome.is(BiomeTags.IS_BEACH)) {
                builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.PENGLIL.get(), FTConfig.Common.INSTANCE.penglilSpawnWeight.get(), 3, 5));
                builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.RUBBER_BELLY_GLIDER.get(), FTConfig.Common.INSTANCE.rubberBellyGliderSpawnWeight.get(), 1, 2));
            }

            if (biome.is(BiomeTags.IS_FOREST)) {
                builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.FLATBACK_LEAF_SNAIL.get(), FTConfig.Common.INSTANCE.flatbackLeafSnailSpawnWeight.get(), 1, 2));
            }

            if (biome.is(Tags.Biomes.IS_COLD_OVERWORLD)) {
                builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.WHERBLE.get(), FTConfig.Common.INSTANCE.wherbleSpawnWeight.get(), 2, 6));
            }

            Optional<ResourceKey<Biome>> optionalResourceKey = ForgeRegistries.BIOMES.getResourceKey(biome.get());
            if (optionalResourceKey.isPresent()) {
                String name = optionalResourceKey.get().toString();
                if (name.equals("cold_ocean") || name.equals("deep_cold_ocean")) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.BLU_WEE.get(), FTConfig.Common.INSTANCE.bluWeeSpawnWeight.get(), 4, 8));
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.TEAL_ARROWFISH.get(), FTConfig.Common.INSTANCE.tealArrowfishSpawnWeight.get(), 1, 1));
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.PHANTOM_NUDIBRANCH.get(), FTConfig.Common.INSTANCE.phantomNudibranchSpawnWeight.get(), 1, 1));
                }

                if (name.equals("warm_ocean") || name.equals("deep_warm_ocean")) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.BANDED_REDBACK_SHRIMP.get(), FTConfig.Common.INSTANCE.bandedRedbackShrimpSpawnWeight.get(), 3, 3));
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.ORNATE_BUGFISH.get(), FTConfig.Common.INSTANCE.ornateBugfishSpawnWeight.get(), 5, 5));
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.SPINDLY_GEM_CRAB.get(), FTConfig.Common.INSTANCE.spindlyGemCrabSpawnWeight.get(), 1, 3));
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.RUBBER_BELLY_GLIDER.get(), FTConfig.Common.INSTANCE.rubberBellyGliderSpawnWeight.get(), 1, 2));
                }

                if (name.equals("ocean") || name.equals("deep_ocean")) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.HIGH_FINNED_BLUE.get(), FTConfig.Common.INSTANCE.highFinnedBlueSpawnWeight.get(), 6, 12));
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.GOPJET.get(), FTConfig.Common.INSTANCE.gopjetSpawnWeight.get(), 2, 3));
                }

                if (name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean")) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.RED_BULL_CRAB.get(), FTConfig.Common.INSTANCE.redBullCrabSpawnWeight.get(), 1, 1));
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.WHITE_BULL_CRAB.get(), FTConfig.Common.INSTANCE.whiteBullCrabSpawnWeight.get(), 2, 4));
                }

                if (name.equals("frozen_ocean") || name.equals("deep_frozen_ocean")) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.NIGHT_LIGHT_SQUID.get(), FTConfig.Common.INSTANCE.nightLightSquidSpawnWeight.get(), 1, 2));
                }

                if (name.equals("river")) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.WEE_WEE.get(), FTConfig.Common.INSTANCE.weeWeeSpawnWeight.get(), 2, 6));
                    builder.getMobSpawnSettings().getSpawner(MobCategory.AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.RIVER_PEBBLE_SNAIL.get(), FTConfig.Common.INSTANCE.riverPebbleSnailSpawnWeight.get(), 1, 1));
                    builder.getMobSpawnSettings().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.GOLDEN_RIVER_RAY.get(), FTConfig.Common.INSTANCE.goldenRiverRaySpawnWeight.get(), 1, 1));
                }
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return FTBiomeModifiers.FINS_AND_TAILS_MODIFIER_TYPE.get();
    }
}