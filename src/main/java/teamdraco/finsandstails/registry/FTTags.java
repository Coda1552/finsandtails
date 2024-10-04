package teamdraco.finsandstails.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BannerPattern;
import teamdraco.finsandstails.FinsAndTails;

public class FTTags {
    // Item Tags
    public static final TagKey<Item> CLAW_GAUNTLETS = itemTag("claw_gauntlets");
    public static final TagKey<Item> FISH_BUCKETS = itemTag("fish_buckets");
    public static final TagKey<Item> WEES = itemTag("wees");
    public static final TagKey<Item> MUDHORSE_POUCH_BLACKLIST = itemTag("mudhorse_pouch_blacklist");
    public static final TagKey<Item> SPINDLY_GEM_CRABS = itemTag("spindly_gem_crabs");
    public static final TagKey<Item> GOPJET = itemTag("gopjet");

    // Banner Pattern Tags
    public static final TagKey<BannerPattern> PATTERN_ITEM_MANDIBLES = patternTag("pattern_item/mandibles");
    public static final TagKey<BannerPattern> PATTERN_ITEM_SHELL = patternTag("pattern_item/shell");

    // Entity Type Tags
    public static final TagKey<EntityType<?>> PREDATORS_HIGH_FINNED_BLUE = entityTag("predators/high_finned_blue");

    private static TagKey<BannerPattern> patternTag(String path) {
        return TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(FinsAndTails.MOD_ID, path));
    }

    private static TagKey<Item> itemTag(String path) {
        return ItemTags.create(new ResourceLocation(FinsAndTails.MOD_ID, path));
    }

    private static TagKey<EntityType<?>> entityTag(String path) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(FinsAndTails.MOD_ID, path));
    }
}