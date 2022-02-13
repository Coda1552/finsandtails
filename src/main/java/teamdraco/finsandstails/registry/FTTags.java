package teamdraco.finsandstails.registry;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import teamdraco.finsandstails.FinsAndTails;

public class FTTags {
    public static final Tag.Named<Item> CLAW_GAUNTLETS = itemTag("claw_gauntlets");
    public static final Tag.Named<Item> FISH_BUCKETS = itemTag("fish_buckets");

    private static Tag.Named<Item> itemTag(String path) {
        return ItemTags.bind(FinsAndTails.MOD_ID + ":" + path);
    }
}