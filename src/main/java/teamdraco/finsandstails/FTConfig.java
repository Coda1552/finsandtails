package teamdraco.finsandstails;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FTConfig {
    public static boolean finsFishingLoot;
    public static int peaWeeSpawnWeight;
    public static int vibraWeeSpawnWeight;
    public static int flatbackSuckerSpawnWeight;
    public static int swampMuckerSpawnWeight;
    public static int mudhorseSpawnWeight;
    public static int siderolWhiskeredSnailSpawnWeight;
    public static int penglilSpawnWeight;
    public static int flatbackLeafSnailSpawnWeight;
    public static int bluWeeSpawnWeight;
    public static int tealArrowfishSpawnWeight;
    public static int phantomNudibranchSpawnWeight;
    public static int bandedRedbackShrimpSpawnWeight;
    public static int ornateBugfishSpawnWeight;
    public static int spindlyGemCrabSpawnWeight;
    public static int highFinnedBlueSpawnWeight;
    public static int whiteBullCrabSpawnWeight;
    public static int redBullCrabSpawnWeight;
    public static int nightLightSquidSpawnWeight;
    public static int weeWeeSpawnWeight;
    public static int riverPebbleSnailSpawnWeight;
    public static int goldenRiverRaySpawnWeight;
    public static int rubberBellyGliderSpawnWeight;
    public static int gopjetSpawnWeight;
    public static int wherbleSpawnWeight;
    public static int glassSkipperSpawnWeight;
    public static int crownedHorateeWeight;

    @SubscribeEvent
    public static void configLoad(ModConfigEvent.Reloading event) {
        try {
            IConfigSpec spec = event.getConfig().getSpec();
            if (spec == Common.SPEC) Common.reload();
        }
        catch (Throwable e) {
            FinsAndTails.LOGGER.error("Something went wrong updating the Fins and Tails config, using previous or default values! {}", e.toString());
        }
    }

    public static class Common {
        public static final Common INSTANCE;
        public static final ForgeConfigSpec SPEC;

        static {
            Pair<Common, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Common::new);
            INSTANCE = pair.getLeft();
            SPEC = pair.getRight();
        }

        public final ForgeConfigSpec.BooleanValue finsFishingLoot;
        public final ForgeConfigSpec.IntValue peaWeeSpawnWeight;
        public final ForgeConfigSpec.IntValue vibraWeeSpawnWeight;
        public final ForgeConfigSpec.IntValue flatbackSuckerSpawnWeight;
        public final ForgeConfigSpec.IntValue swampMuckerSpawnWeight;
        public final ForgeConfigSpec.IntValue mudhorseSpawnWeight;
        public final ForgeConfigSpec.IntValue siderolWhiskeredSnailSpawnWeight;
        public final ForgeConfigSpec.IntValue penglilSpawnWeight;
        public final ForgeConfigSpec.IntValue flatbackLeafSnailSpawnWeight;
        public final ForgeConfigSpec.IntValue bluWeeSpawnWeight;
        public final ForgeConfigSpec.IntValue tealArrowfishSpawnWeight;
        public final ForgeConfigSpec.IntValue phantomNudibranchSpawnWeight;
        public final ForgeConfigSpec.IntValue bandedRedbackShrimpSpawnWeight;
        public final ForgeConfigSpec.IntValue ornateBugfishSpawnWeight;
        public final ForgeConfigSpec.IntValue spindlyGemCrabSpawnWeight;
        public final ForgeConfigSpec.IntValue highFinnedBlueSpawnWeight;
        public final ForgeConfigSpec.IntValue whiteBullCrabSpawnWeight;
        public final ForgeConfigSpec.IntValue redBullCrabSpawnWeight;
        public final ForgeConfigSpec.IntValue nightLightSquidSpawnWeight;
        public final ForgeConfigSpec.IntValue weeWeeSpawnWeight;
        public final ForgeConfigSpec.IntValue riverPebbleSnailSpawnWeight;
        public final ForgeConfigSpec.IntValue goldenRiverRaySpawnWeight;
        public final ForgeConfigSpec.IntValue rubberBellyGliderSpawnWeight;
        public final ForgeConfigSpec.IntValue gopjetSpawnWeight;
        public final ForgeConfigSpec.IntValue wherbleSpawnWeight;
        public final ForgeConfigSpec.IntValue glassSkipperSpawnWeight;
        public final ForgeConfigSpec.IntValue crownedHorateeWeight;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("General");
            finsFishingLoot = builder.comment("Should Fins and Tails fish should appear in the fishing loot table?").define("fins_fishing_loot", true);
            builder.pop();

            builder.push("Jungle Creature Spawn Weight");
            peaWeeSpawnWeight = builder.comment("Spawn weight of Pea Wees").defineInRange("pea_wee_spawn_weight", 10, 1, 1000);
            vibraWeeSpawnWeight = builder.comment("Spawn weight of Vibra Wees").defineInRange("vibra_wee_spawn_weight", 5, 1, 1000);
            builder.pop();

            builder.push("Swamp Creature Spawn Weight");
            flatbackSuckerSpawnWeight = builder.comment("Spawn weight of Flatback Suckers").defineInRange("flatback_sucker_spawn_weight", 1, 1, 1000);
            swampMuckerSpawnWeight = builder.comment("Spawn weight of Swamp Muckers").defineInRange("swamp_mucker_spawn_weight", 1, 1, 1000);
            mudhorseSpawnWeight = builder.comment("Spawn weight of Mudhorses").defineInRange("mudhorse_spawn_weight", 4, 1, 1000);
            builder.pop();

            builder.push("Mountain Creature Spawn Weight");
            siderolWhiskeredSnailSpawnWeight = builder.comment("Spawn weight of Siderol Whiskered Snails").defineInRange("siderol_whiskered_snail_spawn_weight", 2, 1, 1000);
            builder.pop();

            builder.push("Beach Creature Spawn Weight");
            penglilSpawnWeight = builder.comment("Spawn weight of Penglils").defineInRange("penglil_spawn_weight", 2, 1, 1000);
            builder.pop();

            builder.push("Forest Creature Spawn Weight");
            flatbackLeafSnailSpawnWeight = builder.comment("Spawn weight of Flatback Leaf Snails").defineInRange("flatback_leaf_snail_spawn_weight", 6, 1, 1000);
            glassSkipperSpawnWeight = builder.comment("Spawn weight of Glass Skippers").defineInRange("glass_skipper_spawn_weight", 3, 1, 1000);
            builder.pop();

            builder.push("Cold Ocean Creature Spawn Weight");
            bluWeeSpawnWeight = builder.comment("Spawn weight of Blu Wees").defineInRange("blu_wee_spawn_weight", 6, 1, 1000);
            tealArrowfishSpawnWeight = builder.comment("Spawn weight of Teal Arrowfish").defineInRange("teal_arrowfish_spawn_weight", 4, 1, 1000);
            phantomNudibranchSpawnWeight = builder.comment("Spawn weight of Phantom Nudibranch").defineInRange("phantom_nudibranch_spawn_weight", 3, 1, 1000);
            builder.pop();

            builder.push("Warm Ocean Creature Spawn Weight");
            bandedRedbackShrimpSpawnWeight = builder.comment("Spawn weight of Banded Redback Shrimp").defineInRange("banded_redback_shrimp_spawn_weight", 6, 1, 1000);
            ornateBugfishSpawnWeight = builder.comment("Spawn weight of Ornate Bugfish").defineInRange("orante_bugfish_spawn_weight", 1, 1, 1000);
            spindlyGemCrabSpawnWeight = builder.comment("Spawn weight of Spindly Gem Crabs").defineInRange("spindly_gem_crab_spawn_weight", 7, 1, 1000);
            rubberBellyGliderSpawnWeight = builder.comment("Spawn weight of Rubber Belly Gliders").defineInRange("rubber_belly_glider_spawn_weight", 3, 1, 1000);
            crownedHorateeWeight = builder.comment("Spawn weight of Crowned Horatee").defineInRange("crowned_horatee_spawn_weight", 4, 1, 1000);
            builder.pop();

            builder.push("Ocean Creature Spawn Weight");
            highFinnedBlueSpawnWeight = builder.comment("Spawn weight of High-finned Blue").defineInRange("high_finned_blue_spawn_weight", 2, 1, 1000);
            gopjetSpawnWeight = builder.comment("Spawn weight of Gopjets").defineInRange("gopjet_spawn_weight", 1, 1, 1000);
            builder.pop();

            builder.push("Lukewarm Ocean Creature Spawn Weight");
            whiteBullCrabSpawnWeight = builder.comment("Spawn weight of White Bull Crabs").defineInRange("white_bull_crabs_spawn_weight", 2, 1, 1000);
            redBullCrabSpawnWeight = builder.comment("Spawn weight of Red Bull Crabs").defineInRange("red_bull_crabs_spawn_weight", 2, 1, 1000);
            builder.pop();

            builder.push("Frozen Ocean Creature Spawn Weight");
            nightLightSquidSpawnWeight = builder.comment("Spawn weight of Night Light Squid").defineInRange("night_light_squids_spawn_weight", 2, 1, 1000);
            builder.pop();

            builder.push("River Creature Spawn Weight");
            weeWeeSpawnWeight = builder.comment("Spawn weight of Wee Wees").defineInRange("wee_wee_spawn_weight", 2, 1, 1000);
            riverPebbleSnailSpawnWeight = builder.comment("Spawn weight of River Pebble Snails").defineInRange("river_pebble_snail_spawn_weight", 2, 1, 1000);
            goldenRiverRaySpawnWeight = builder.comment("Spawn weight of Golden River Rays").defineInRange("golden_river_ray_spawn_weight", 2, 1, 1000);
            builder.pop();

            builder.push("Icy Spawn Weight");
            wherbleSpawnWeight = builder.comment("Spawn weight of Wherbles").defineInRange("wherble_spawn_weight", 3, 1, 1000);
            builder.pop();
        }

        public static void reload() {
            FTConfig.finsFishingLoot = INSTANCE.finsFishingLoot.get();
            FTConfig.peaWeeSpawnWeight = INSTANCE.peaWeeSpawnWeight.get();
            FTConfig.vibraWeeSpawnWeight = INSTANCE.vibraWeeSpawnWeight.get();
            FTConfig.flatbackSuckerSpawnWeight = INSTANCE.flatbackSuckerSpawnWeight.get();
            FTConfig.swampMuckerSpawnWeight = INSTANCE.swampMuckerSpawnWeight.get();
            FTConfig.mudhorseSpawnWeight = INSTANCE.mudhorseSpawnWeight.get();
            FTConfig.siderolWhiskeredSnailSpawnWeight = INSTANCE.siderolWhiskeredSnailSpawnWeight.get();
            FTConfig.penglilSpawnWeight = INSTANCE.penglilSpawnWeight.get();
            FTConfig.flatbackLeafSnailSpawnWeight = INSTANCE.flatbackLeafSnailSpawnWeight.get();
            FTConfig.bluWeeSpawnWeight = INSTANCE.bluWeeSpawnWeight.get();
            FTConfig.tealArrowfishSpawnWeight = INSTANCE.tealArrowfishSpawnWeight.get();
            FTConfig.phantomNudibranchSpawnWeight = INSTANCE.phantomNudibranchSpawnWeight.get();
            FTConfig.bandedRedbackShrimpSpawnWeight = INSTANCE.bandedRedbackShrimpSpawnWeight.get();
            FTConfig.ornateBugfishSpawnWeight = INSTANCE.ornateBugfishSpawnWeight.get();
            FTConfig.spindlyGemCrabSpawnWeight = INSTANCE.spindlyGemCrabSpawnWeight.get();
            FTConfig.highFinnedBlueSpawnWeight = INSTANCE.highFinnedBlueSpawnWeight.get();
            FTConfig.whiteBullCrabSpawnWeight = INSTANCE.whiteBullCrabSpawnWeight.get();
            FTConfig.redBullCrabSpawnWeight = INSTANCE.redBullCrabSpawnWeight.get();
            FTConfig.nightLightSquidSpawnWeight = INSTANCE.nightLightSquidSpawnWeight.get();
            FTConfig.weeWeeSpawnWeight = INSTANCE.weeWeeSpawnWeight.get();
            FTConfig.riverPebbleSnailSpawnWeight = INSTANCE.riverPebbleSnailSpawnWeight.get();
            FTConfig.goldenRiverRaySpawnWeight = INSTANCE.goldenRiverRaySpawnWeight.get();
            FTConfig.rubberBellyGliderSpawnWeight = INSTANCE.rubberBellyGliderSpawnWeight.get();
            FTConfig.gopjetSpawnWeight = INSTANCE.gopjetSpawnWeight.get();
            FTConfig.wherbleSpawnWeight = INSTANCE.wherbleSpawnWeight.get();
            FTConfig.glassSkipperSpawnWeight = INSTANCE.glassSkipperSpawnWeight.get();
            FTConfig.crownedHorateeWeight = INSTANCE.crownedHorateeWeight.get();
        }
    }
}