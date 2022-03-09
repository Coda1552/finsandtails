package teamdraco.finsandstails.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import teamdraco.finsandstails.FinsAndTails;

public class FTTags {
    public static final TagKey<Item> CLAW_GAUNTLETS = itemTag("claw_gauntlets");
    public static final TagKey<Item> FISH_BUCKETS = itemTag("fish_buckets");
    public static final TagKey<Item> WEES = itemTag("wees");

    private static TagKey<Item> itemTag(String path) {
        return ItemTags.create(new ResourceLocation(FinsAndTails.MOD_ID, path));
    }
}