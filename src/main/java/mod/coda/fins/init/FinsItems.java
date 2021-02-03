package mod.coda.fins.init;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.item.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FinsItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, FinsAndTails.MOD_ID);

    //Fish
    public static final RegistryObject<Item> BLU_WEE = REGISTER.register("blu_wee", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final RegistryObject<Item> PEA_WEE = REGISTER.register("pea_wee", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final RegistryObject<Item> BANDED_REDBACK_SRHIMP = REGISTER.register("banded_redback_shrimp", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final RegistryObject<Item> TEAL_ARROWFISH = REGISTER.register("teal_arrowfish", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final RegistryObject<Item> SWAMP_MUCKER = REGISTER.register("swamp_mucker", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final RegistryObject<Item> FLATBACK_SUCKER = REGISTER.register("flatback_sucker", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final RegistryObject<Item> HIGH_FINNED_BLUE = REGISTER.register("high_finned_blue", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final RegistryObject<Item> ORNATE_BUGFISH = REGISTER.register("ornate_bugfish", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final RegistryObject<Item> SPINDLY_GEM_CRAB = REGISTER.register("spindly_gem_crab", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> WEE_WEE = REGISTER.register("wee_wee", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(1).saturation(0.1F).build())));
    public static final RegistryObject<Item> VIBRA_WEE = REGISTER.register("vibra_wee", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.2F).build())));
    public static final RegistryObject<Item> NIGHT_LIGHT_SQUID = REGISTER.register("night_light_squid", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(1).saturation(0.1F).build())));

    //Drops
    public static final RegistryObject<Item> MUDHORSE_LEATHER = REGISTER.register("mudhorse_leather", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> FWIN = REGISTER.register("fwin", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> SPINDLY_GEM_CRAB_GEM = REGISTER.register("spindly_gem_crab_gem", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> FLATBACK_LEAF_SNAIL_SHELL = REGISTER.register("flatback_leaf_snail_shell", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> RED_BULL_CRAB_CLAW = REGISTER.register("red_bull_crab_claw", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    //public static final RegistryObject<Item> GOPJET_JET = REGISTER.register("gopjet_jet", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> RIVER_PEBBLE_SNAIL_SHELL = REGISTER.register("river_pebble_snail_shell", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> SIDEROL_WHISKERED_SNAIL_SHELL = REGISTER.register("siderol_whiskered_snail_shell", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> NIGHT_LIGHT_SQUID_TENTACLE = REGISTER.register("night_light_squid_tentacle", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP)));

    //Food
    public static final RegistryObject<Item> COOKED_BANDED_REDBACK_SHRIMP = REGISTER.register("cooked_banded_redback_shrimp", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(6).saturation(0.5F).build())));
    public static final RegistryObject<Item> BUGMEAT = REGISTER.register("bugmeat", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(3).saturation(0.15F).build())));
    public static final RegistryObject<Item> COOKED_BUGMEAT = REGISTER.register("cooked_bugmeat", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(8).saturation(0.6F).build())));
    public static final RegistryObject<Item> RAW_GOLDEN_RIVER_RAY_WING = REGISTER.register("raw_golden_river_ray_wing", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(3).saturation(0.3F).build())));
    public static final RegistryObject<Item> GOLDEN_RIVER_RAY_WING_FILLET = REGISTER.register("golden_river_ray_wing_fillet", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(8).saturation(0.8F).build())));
    public static final RegistryObject<Item> WHITE_BULL_CRAB_CLAW = REGISTER.register("white_bull_crab_claw", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> COOKED_BULL_CRAB_CLAW = REGISTER.register("cooked_bull_crab_claw", () -> new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(6).saturation(0.6f).build())));

    //Gear
    public static final RegistryObject<Item> MUDHORSE_POUCH = REGISTER.register("mudhorse_pouch", MudhorsePouchItem::new);
    public static final RegistryObject<Item> FWINGED_BOOTS = REGISTER.register("fwinged_boots", FwingedBootsItem::new);
    public static final RegistryObject<Item> GEM_CRAB_AMULET = REGISTER.register("gem_crab_amulet", GemCrabAmuletItem::new);
    public static final RegistryObject<Item> WHITE_CLAW_GAUNTLET = REGISTER.register("white_claw_gauntlet", () -> new CrabGauntletItem(ItemTier.STONE, 2, -0.6F, new Item.Properties().group(FinsAndTails.GROUP).maxDamage(200)));
    public static final RegistryObject<Item> RED_CLAW_GAUNTLET = REGISTER.register("red_claw_gauntlet", () -> new CrabGauntletItem(ItemTier.STONE, 3, -1.4F, new Item.Properties().group(FinsAndTails.GROUP).maxDamage(130)));
    //public static final RegistryObject<Item> GOPJET_JETPACK = REGISTER.register("gopjet_jetpack", GopjetJetpackItem::new);
    public static final RegistryObject<Item> BUGFISH_MANDIBLES = REGISTER.register("bugfish_mandibles", () -> new ShearsItem(new Item.Properties().group(FinsAndTails.GROUP).maxDamage(156)));

    //Buckets
    public static final RegistryObject<Item> BLU_WEE_BUCKET = REGISTER.register("blu_wee_bucket", () -> new FishBucketItem(FinsEntities.BLU_WEE, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> PEA_WEE_BUCKET = REGISTER.register("pea_wee_bucket", () -> new FishBucketItem(FinsEntities.PEA_WEE, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> BANDED_REDBACK_SHRIMP_BUCKET = REGISTER.register("banded_redback_shrimp_bucket", () -> new FishBucketItem(FinsEntities.BANDED_REDBACK_SHRIMP, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> TEAL_ARROWFISH_BUCKET = REGISTER.register("teal_arrowfish_bucket", () -> new FishBucketItem(FinsEntities.TEAL_ARROWFISH, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> SWAMP_MUCKER_BUCKET = REGISTER.register("swamp_mucker_bucket", () -> new FishBucketItem(FinsEntities.SWAMP_MUCKER, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> FLATBACK_SUCKER_BUCKET = REGISTER.register("flatback_sucker_bucket", () -> new FishBucketItem(FinsEntities.FLATBACK_SUCKER, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> HIGH_FINNED_BLUE_BUCKET = REGISTER.register("high_finned_blue_bucket", () -> new FishBucketItem(FinsEntities.HIGH_FINNED_BLUE, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> PHANTOM_NUDIBRANCH_BUCKET = REGISTER.register("phantom_nudibranch_bucket", () -> new FishBucketItem(FinsEntities.PHANTOM_NUDIBRANCH, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> ORNATE_BUGFISH_BUCKET = REGISTER.register("ornate_bugfish_bucket", () -> new FishBucketItem(FinsEntities.ORNATE_BUGFISH, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> PENGLIL_BUCKET = REGISTER.register("penglil_bucket", () -> new FinsBucketItem(FinsEntities.PENGLIL, () -> Fluids.EMPTY, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> SPINDLY_GEM_CRAB_BUCKET = REGISTER.register("spindly_gem_crab_bucket", () -> new FinsBucketItem(FinsEntities.SPINDLY_GEM_CRAB, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> WEE_WEE_BUCKET = REGISTER.register("wee_wee_bucket", () -> new FishBucketItem(FinsEntities.WEE_WEE, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> RED_BULL_CRAB_BUCKET = REGISTER.register("red_bull_crab_bucket", () -> new FishBucketItem(FinsEntities.RED_BULL_CRAB, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> WHITE_BULL_CRAB_BUCKET = REGISTER.register("white_bull_crab_bucket", () -> new FishBucketItem(FinsEntities.WHITE_BULL_CRAB, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> VIBRA_WEE_BUCKET = REGISTER.register("vibra_wee_bucket", () -> new FinsBucketItem(FinsEntities.VIBRA_WEE, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> GOLDEN_RIVER_RAY_BUCKET = REGISTER.register("golden_river_ray_bucket", () -> new FinsBucketItem(FinsEntities.GOLDEN_RIVER_RAY, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> FLATBACK_LEAF_SNAIL_POT = REGISTER.register("flatback_leaf_snail_pot", () -> new FishBucketItem(FinsEntities.FLATBACK_LEAF_SNAIL, () -> Fluids.EMPTY, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> RIVER_PEBBLE_SNAIL_POT = REGISTER.register("river_pebble_snail_pot", () -> new FinsBucketItem(FinsEntities.RIVER_PEBBLE_SNAIL, () -> Fluids.EMPTY, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> SIDEROL_WHISKERED_SNAIL_POT = REGISTER.register("siderol_whiskered_snail_pot", () -> new FishBucketItem(FinsEntities.SIDEROL_WHISKERED_SNAIL, () -> Fluids.EMPTY, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> NIGHT_LIGHT_SQUID_BUCKET = REGISTER.register("night_light_squid_bucket", () -> new FinsBucketItem(FinsEntities.NIGHT_LIGHT_SQUID, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));

    //Spawn Eggs
    public static final RegistryObject<Item> PEA_WEE_SPAWN_EGG = REGISTER.register("pea_wee_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.PEA_WEE, 0x31a643, 0x1a7a3e, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> BLU_WEE_SPAWN_EGG = REGISTER.register("blu_wee_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.BLU_WEE, 0x3f74a2, 0x2a4b8c, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> BANDED_REDBACK_SHRIMP_SPAWN_EGG = REGISTER.register("banded_redback_shrimp_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.BANDED_REDBACK_SHRIMP, 0x155556, 0x921a2f, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> TEAL_ARROWFISH_SPAWN_EGG = REGISTER.register("teal_arrowfish_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.TEAL_ARROWFISH, 0x004d40, 0x427c8a, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> SWAMP_MUCKER_SPAWN_EGG = REGISTER.register("swamp_mucker_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.SWAMP_MUCKER, 0x5e663b, 0x997d45, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> FLATBACK_SUCKER_SPAWN_EGG = REGISTER.register("flatback_sucker_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.FLATBACK_SUCKER, 0x898364, 0x4b7486, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> HIGH_FINNED_BLUE_SPAWN_EGG = REGISTER.register("high_finned_blue_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.HIGH_FINNED_BLUE, 0x4b6e81, 0x425e85, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> MUDHORSE_SPAWN_EGG = REGISTER.register("mudhorse_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.MUDHORSE, 0xc39858, 0x653e2c, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> ORNATE_BUGFISH_SPAWN_EGG = REGISTER.register("ornate_bugfish_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.ORNATE_BUGFISH, 0xe5d7ce, 0xf27a00, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> PHANTOM_NUDIBRANCH_SPAWN_EGG = REGISTER.register("phantom_nudibranch_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.PHANTOM_NUDIBRANCH, 0xcfe9ff, 0x8692bf, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> PENGLIL_SPAWN_EGG = REGISTER.register("penglil_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.PENGLIL,0x234040, 0xa7d7c1, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> SPINDLY_GEM_CRAB_SPAWN_EGG = REGISTER.register("spindly_gem_crab_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.SPINDLY_GEM_CRAB, 0xe4ad4c, 0x6be263, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> FLATBACK_LEAF_SNAIL_SPAWN_EGG = REGISTER.register("flatback_leaf_snail_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.FLATBACK_LEAF_SNAIL, 0x4a2c1a, 0xd1a06b, new Item.Properties().group(FinsAndTails.GROUP)));
    //public static final RegistryObject<Item> RUBBER_BELLY_GLIDER_SPAWN_EGG = REGISTER.register("rubber_belly_glider_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.RUBBER_BELLY_GLIDER, 0x81b676, 0xe3b334, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> RED_BULL_CRAB_SPAWN_EGG = REGISTER.register("red_bull_crab_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.RED_BULL_CRAB, 0x6e4c3d, 0xa62d2b, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> WHITE_BULL_CRAB_SPAWN_EGG = REGISTER.register("white_bull_crab_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.WHITE_BULL_CRAB, 0x6e4c3d, 0xdddddd, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> WEE_WEE_SPAWN_EGG = REGISTER.register("wee_wee_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.WEE_WEE, 0x8b9da5, 0x516279, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> VIBRA_WEE_SPAWN_EGG = REGISTER.register("vibra_wee_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.VIBRA_WEE, 0x2a1b18, 0x7f4f42, new Item.Properties().group(FinsAndTails.GROUP)));
    //public static final RegistryObject<Item> GOPJET_SPAWN_EGG = REGISTER.register("gopjet_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.GOPJET, 0x000000, 0x000000, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> RIVER_PEBBLE_SNAIL_SPAWN_EGG = REGISTER.register("river_pebble_snail_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.RIVER_PEBBLE_SNAIL, 0xb8b2ab, 0x665852, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> SIDEROL_WHISKERED_SNAIL_SPAWN_EGG = REGISTER.register("siderol_whiskered_snail_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.SIDEROL_WHISKERED_SNAIL, 0x9e8372, 0x92402b, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> GOLDEN_RIVER_RAY_SPAWN_EGG = REGISTER.register("golden_river_ray_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.GOLDEN_RIVER_RAY, 0xae9e63, 0x503a2f, new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<Item> NIGHT_LIGHT_SQUID_SPAWN_EGG = REGISTER.register("night_light_squid_spawn_egg", () -> new FinsSpawnEggItem(FinsEntities.NIGHT_LIGHT_SQUID, 0x280827, 0xd6f7eb, new Item.Properties().group(FinsAndTails.GROUP)));

    //Blocks
    public static final RegistryObject<BlockItem> CRAB_CRUNCHER = REGISTER.register("crab_cruncher", () -> new BlockItem(FinsBlocks.CRAB_CRUNCHER.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> SPINDLY_GEM_CRAB_GEM_BLOCK = REGISTER.register("spindly_gem_crab_gem_block", () -> new BlockItem(FinsBlocks.SPINDLY_GEM_CRAB_GEM_BLOCK.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> CHISELED_FLATBACK_SHELL_BRICKS = REGISTER.register("chiseled_flatback_shell_bricks", () -> new BlockItem(FinsBlocks.CHISELED_FLATBACK_SHELL_BRICKS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> FLATBACK_SHELL_BLOCK = REGISTER.register("flatback_shell_block", () -> new BlockItem(FinsBlocks.FLATBACK_SHELL_BLOCK.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> FLATBACK_SHELL_BRICK_SLAB = REGISTER.register("flatback_shell_brick_slab", () -> new BlockItem(FinsBlocks.FLATBACK_SHELL_BRICK_SLAB.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> FLATBACK_SHELL_BRICK_STAIRS = REGISTER.register("flatback_shell_brick_stairs", () -> new BlockItem(FinsBlocks.FLATBACK_SHELL_BRICK_STAIRS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> FLATBACK_SHELL_BRICKS = REGISTER.register("flatback_shell_bricks", () -> new BlockItem(FinsBlocks.FLATBACK_SHELL_BRICKS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> FLATBACK_SHELL_SLAB = REGISTER.register("flatback_shell_slab", () -> new BlockItem(FinsBlocks.FLATBACK_SHELL_SLAB.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> FLATBACK_SHELL_STAIRS = REGISTER.register("flatback_shell_stairs", () -> new BlockItem(FinsBlocks.FLATBACK_SHELL_STAIRS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> CHISELED_PEBBLE_SHELL_BRICKS = REGISTER.register("chiseled_pebble_shell_bricks", () -> new BlockItem(FinsBlocks.CHISELED_PEBBLE_SHELL_BRICKS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> PEBBLE_SHELL_BRICK_SLAB = REGISTER.register("pebble_shell_brick_slab", () -> new BlockItem(FinsBlocks.PEBBLE_SHELL_BRICK_SLAB.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> PEBBLE_SHELL_BRICK_STAIRS = REGISTER.register("pebble_shell_brick_stairs", () -> new BlockItem(FinsBlocks.PEBBLE_SHELL_BRICK_STAIRS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> PEBBLE_SHELL_BRICKS = REGISTER.register("pebble_shell_bricks", () -> new BlockItem(FinsBlocks.PEBBLE_SHELL_BRICKS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> PEBBLE_SHELL_PILLAR = REGISTER.register("pebble_shell_pillar", () -> new BlockItem(FinsBlocks.PEBBLE_SHELL_PILLAR.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> CHISELED_SIDEROL_SHELL_BRICKS = REGISTER.register("chiseled_siderol_shell_bricks", () -> new BlockItem(FinsBlocks.CHISELED_SIDEROL_SHELL_BRICKS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> SIDEROL_SHELL_BRICK_SLAB = REGISTER.register("siderol_shell_brick_slab", () -> new BlockItem(FinsBlocks.SIDEROL_SHELL_BRICK_SLAB.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> SIDEROL_SHELL_BRICK_STAIRS = REGISTER.register("siderol_shell_brick_stairs", () -> new BlockItem(FinsBlocks.SIDEROL_SHELL_BRICK_STAIRS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> SIDEROL_SHELL_BRICKS = REGISTER.register("siderol_shell_bricks", () -> new BlockItem(FinsBlocks.SIDEROL_SHELL_BRICKS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> MIXED_PEBBLE_SHELL_BRICKS = REGISTER.register("mixed_pebble_shell_bricks", () -> new BlockItem(FinsBlocks.MIXED_PEBBLE_SHELL_BRICKS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> MIXED_PEBBLE_SHELL_BRICK_SLAB = REGISTER.register("mixed_pebble_shell_brick_slab", () -> new BlockItem(FinsBlocks.MIXED_PEBBLE_SHELL_BRICK_SLAB.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> MIXED_PEBBLE_SHELL_BRICK_STAIRS = REGISTER.register("mixed_pebble_shell_brick_stairs", () -> new BlockItem(FinsBlocks.MIXED_PEBBLE_SHELL_BRICK_STAIRS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> MIXED_FLATBACK_SHELL_BRICKS = REGISTER.register("mixed_flatback_shell_bricks", () -> new BlockItem(FinsBlocks.MIXED_FLATBACK_SHELL_BRICKS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> MIXED_FLATBACK_SHELL_BRICK_SLAB = REGISTER.register("mixed_flatback_shell_brick_slab", () -> new BlockItem(FinsBlocks.MIXED_FLATBACK_SHELL_BRICK_SLAB.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> MIXED_FLATBACK_SHELL_BRICK_STAIRS = REGISTER.register("mixed_flatback_shell_brick_stairs", () -> new BlockItem(FinsBlocks.MIXED_FLATBACK_SHELL_BRICK_STAIRS.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> CHAINED_TENTACLE = REGISTER.register("chained_tentacle", () -> new BlockItem(FinsBlocks.CHAINED_TENTACLE.get(), new Item.Properties().group(FinsAndTails.GROUP)));
}   