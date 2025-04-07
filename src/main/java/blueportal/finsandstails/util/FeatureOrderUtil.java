package blueportal.finsandstails.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FeatureOrderUtil {

  /**
   * List of how features in the VEGETAL_DECORATION step are ordered across all vanilla biomes aggregated into one list
   * Note: this is done using topological sort, which in this case has more than one valid sorted order
   * which means the order below is not a strict order in some cases
   */
  private static final List<String> VANILLA_FEATURE_ORDER = Arrays.asList(
    "minecraft:glow_lichen",
    "minecraft:bamboo",
    "minecraft:bamboo_vegetation",
    "minecraft:patch_tall_grass_2",
    "minecraft:trees_water",
    "minecraft:dark_forest_vegetation",
    "minecraft:forest_flowers",
    "minecraft:trees_birch",
    "minecraft:flower_forest_flowers",
    "minecraft:trees_flower_forest",
    "minecraft:flower_flower_forest",
    "minecraft:trees_birch_and_oak",
    "minecraft:trees_grove",
    "minecraft:trees_snowy",
    "minecraft:bamboo_light",
    "minecraft:trees_jungle",
    "minecraft:lush_caves_ceiling_vegetation",
    "minecraft:cave_vines",
    "minecraft:lush_caves_clay",
    "minecraft:lush_caves_vegetation",
    "minecraft:rooted_azalea_tree",
    "minecraft:spore_blossom",
    "minecraft:classic_vines_cave_feature",
    "minecraft:trees_mangrove",
    "minecraft:mushroom_island_vegetation",
    "minecraft:birch_tall",
    "minecraft:patch_large_fern",
    "minecraft:trees_old_growth_pine_taiga",
    "minecraft:trees_old_growth_spruce_taiga",
    "minecraft:patch_tall_grass",
    "minecraft:trees_savanna",
    "minecraft:trees_taiga",
    "minecraft:trees_sparse_jungle",
    "minecraft:flower_warm",
    "minecraft:patch_grass_savanna",
    "minecraft:patch_grass_jungle",
    "minecraft:patch_sunflower",
    "minecraft:trees_plains",
    "minecraft:flower_plains",
    "minecraft:patch_grass_plain",
    "minecraft:flower_cherry",
    "minecraft:trees_cherry",
    "minecraft:flower_meadow",
    "minecraft:trees_meadow",
    "minecraft:trees_swamp",
    "minecraft:flower_swamp",
    "minecraft:trees_windswept_forest",
    "minecraft:trees_windswept_hills",
    "minecraft:trees_windswept_savanna",
    "minecraft:flower_default",
    "minecraft:patch_grass_forest",
    "minecraft:patch_grass_taiga",
    "minecraft:patch_grass_taiga_2",
    "minecraft:brown_mushroom_taiga",
    "minecraft:red_mushroom_taiga",
    "minecraft:patch_grass_normal",
    "minecraft:patch_dead_bush",
    "minecraft:brown_mushroom_old_growth",
    "minecraft:red_mushroom_old_growth",
    "minecraft:patch_waterlily",
    "minecraft:brown_mushroom_swamp",
    "minecraft:red_mushroom_swamp",
    "minecraft:trees_badlands",
    "minecraft:patch_grass_badlands",
    "minecraft:patch_dead_bush_2",
    "minecraft:patch_dead_bush_badlands",
    "minecraft:spring_lava",
    "minecraft:brown_mushroom_normal",
    "minecraft:red_mushroom_normal",
    "minecraft:patch_sugar_cane_badlands",
    "minecraft:patch_sugar_cane",
    "minecraft:patch_sugar_cane_desert",
    "minecraft:patch_sugar_cane_swamp",
    "minecraft:patch_pumpkin",
    "minecraft:patch_cactus_decorated",
    "minecraft:vines",
    "minecraft:patch_melon",
    "minecraft:seagrass_cold",
    "minecraft:seagrass_deep_cold",
    "minecraft:seagrass_deep_warm",
    "minecraft:seagrass_deep",
    "minecraft:patch_cactus_desert",
    "minecraft:seagrass_normal",
    "minecraft:seagrass_simple",
    "minecraft:kelp_cold",
    "minecraft:patch_berry_common",
    "minecraft:seagrass_river",
    "minecraft:patch_berry_rare",
    "minecraft:patch_melon_sparse",
    "minecraft:seagrass_swamp",
    "minecraft:warm_ocean_vegetation",
    "minecraft:seagrass_warm",
    "minecraft:kelp_warm",
    "minecraft:sea_pickle",
    "minecraft:weeping_vines",
    "minecraft:crimson_fungi",
    "minecraft:crimson_forest_vegetation",
    "minecraft:warped_fungi",
    "minecraft:warped_forest_vegetation",
    "minecraft:nether_sprouts",
    "minecraft:twisting_vines"
  );

  /**
   * Sort features according to vanilla feature order and add to biome generation settings
   * @param biomeGenBuilder
   * @param vegetationFeatures
   */
  public static void addFeatures(BiomeGenerationSettings.Builder biomeGenBuilder, ResourceKey<PlacedFeature>... vegetationFeatures) {
    List<ResourceKey<PlacedFeature>> list = new ArrayList<>(List.of(vegetationFeatures));
    list.sort(Comparator.comparingInt(a -> VANILLA_FEATURE_ORDER.indexOf(a.location().toString())));
    for (ResourceKey<PlacedFeature> feature: list) {
      biomeGenBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, feature);
    }
  }
}