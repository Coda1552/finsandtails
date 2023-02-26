package teamdraco.finsandstails.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BannerPattern;
import teamdraco.finsandstails.FinsAndTails;

public class FTTags {
    public static final TagKey<Item> CLAW_GAUNTLETS = itemTag("claw_gauntlets");
    public static final TagKey<Item> FISH_BUCKETS = itemTag("fish_buckets");
    public static final TagKey<Item> WEES = itemTag("wees");
    public static final TagKey<Item> MUDHORSE_POUCH_BLACKLIST = itemTag("mudhorse_pouch_blacklist");
    public static final TagKey<Item> SPINDLY_GEM_CRABS = itemTag("spindly_gem_crabs");

    private static TagKey<Item> itemTag(String path) {
        return ItemTags.create(new ResourceLocation(FinsAndTails.MOD_ID, path));
    }

    public static final TagKey<BannerPattern> PATTERN_ITEM_MANDIBLES = patternTag("pattern_item/mandibles");
    public static final TagKey<BannerPattern> PATTERN_ITEM_SHELL = patternTag("pattern_item/shell");

    private static TagKey<BannerPattern> patternTag(String path) {
        return TagKey.create(Registry.BANNER_PATTERN_REGISTRY, new ResourceLocation(FinsAndTails.MOD_ID, path));
    }
}