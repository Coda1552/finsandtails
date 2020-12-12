package mod.coda.fins.init;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.item.FwingedBootsItem;
import mod.coda.fins.item.GemCrabAmuletItem;
import mod.coda.fins.item.MudhorsePouchItem;
import mod.coda.fins.item.PenglilBucketItem;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FinsItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, FinsAndTails.MOD_ID);

    public static final Item BLU_WEE = register("blu_wee", new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final Item BLU_WEE_BUCKET = register("blu_wee_bucket", new FishBucketItem(() -> FinsEntities.BLU_WEE, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final Item PEA_WEE = register("pea_wee", new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final Item PEA_WEE_BUCKET = register("pea_wee_bucket", new FishBucketItem(() -> FinsEntities.PEA_WEE, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final Item BANDED_REDBACK_SRHIMP = register("banded_redback_shrimp", new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final Item BANDED_REDBACK_SHRIMP_BUCKET = register("banded_redback_shrimp_bucket", new FishBucketItem(() -> FinsEntities.BANDED_REDBACK_SHRIMP, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final Item COOKED_BANDED_REDBACK_SHRIMP = register("cooked_banded_redback_shrimp", new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(6).saturation(0.5F).build())));
    public static final Item TEAL_ARROWFISH = register("teal_arrowfish", new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final Item TEAL_ARROWFISH_BUCKET = register("teal_arrowfish_bucket", new FishBucketItem(() -> FinsEntities.TEAL_ARROWFISH, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final Item SWAMP_MUCKER = register("swamp_mucker", new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final Item SWAMP_MUCKER_BUCKET = register("swamp_mucker_bucket", new FishBucketItem(() -> FinsEntities.SWAMP_MUCKER, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final Item FLATBACK_SUCKER = register("flatback_sucker", new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final Item FLATBACK_SUCKER_BUCKET = register("flatback_sucker_bucket", new FishBucketItem(() -> FinsEntities.FLATBACK_SUCKER, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final Item HIGHFINNED_BLUE = register("highfinned_blue", new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final Item HIGHFINNED_BLUE_BUCKET = register("highfinned_blue_bucket", new FishBucketItem(() -> FinsEntities.HIGHFINNED_BLUE, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final Item PHANTOM_NUDIBRANCH_BUCKET = register("phantom_nudibranch_bucket", new FishBucketItem(() -> FinsEntities.PHANTOM_NUDIBRANCH, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final Item ORNATE_BUGFISH = register("ornate_bugfish", new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final Item ORNATE_BUGFISH_BUCKET = register("ornate_bugfish_bucket", new FishBucketItem(() -> FinsEntities.ORNATE_BUGFISH, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final Item BUGMEAT = register("bugmeat", new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(3).saturation(0.15F).build())));
    public static final Item COOKED_BUGMEAT = register("cooked_bugmeat", new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(8).saturation(0.6F).build())));
    public static final Item MUDHORSE_LEATHER = register("mudhorse_leather", new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    public static final Item MUDHORSE_POUCH = register("mudhorse_pouch", new MudhorsePouchItem());
    public static final Item FWINGED_BOOTS = register("fwinged_boots", new FwingedBootsItem());
    public static final Item FWIN = register("fwin", new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    public static final Item PENGLIL_BUCKET = register("penglil_bucket", new PenglilBucketItem(FinsEntities.PENGLIL, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final Item SPINDLY_GEM_CRAB_BUCKET = register("spindly_gem_crab_bucket", new FishBucketItem(() -> FinsEntities.SPINDLY_GEM_CRAB, () -> Fluids.WATER, new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1)));
    public static final Item SPINDLY_GEM_CRAB = register("spindly_gem_crab", new Item(new Item.Properties().group(FinsAndTails.GROUP).food(new Food.Builder().hunger(2).saturation(0.1F).build())));
    public static final Item SPINDLY_GEM_CRAB_GEM = register("spindly_gem_crab_gem", new Item(new Item.Properties().group(FinsAndTails.GROUP)));
    public static final Item GEM_CRAB_AMULET = register("gem_crab_amulet", new GemCrabAmuletItem());
//    public static final Item BUGFISH_MANDIBLES = register("bugfish_mandibles", new ShearsItem(new Item.Properties().group(FinsAndTails.GROUP).maxDamage(156)));
    public static final Item FLATBACK_LEAF_SNAIL_SHELL = register("flatback_leaf_snail_shell", new Item(new Item.Properties().group(FinsAndTails.GROUP)));

    public static final RegistryObject<BlockItem> CRAB_CRUNCHER = REGISTRY.register("crab_cruncher", () -> new BlockItem(FinsBlocks.CRAB_CRUNCHER.get(), new Item.Properties().group(FinsAndTails.GROUP)));
    public static final RegistryObject<BlockItem> SPINDLY_GEM_CRAB_GEM_BLOCK = REGISTRY.register("spindly_gem_crab_gem_block", () -> new BlockItem(FinsBlocks.SPINDLY_GEM_CRAB_GEM_BLOCK.get(), new Item.Properties().group(FinsAndTails.GROUP)));


    private static Item register(String name, Item item) {
        REGISTRY.register(name, () -> item);
        return item;
    }
}
