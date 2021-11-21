package teamdraco.fins.init;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import teamdraco.fins.FinsAndTails;

public class FinsTags {
    public static final ITag.INamedTag<Item> CLAW_GAUNTLETS = itemTag("claw_gauntlets");

    private static ITag.INamedTag<Item> itemTag(String path) {
        return ItemTags.bind(FinsAndTails.MOD_ID + ":" + path);
    }
}