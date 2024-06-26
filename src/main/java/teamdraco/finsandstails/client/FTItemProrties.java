package teamdraco.finsandstails.client;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.FinsBucketItem;
import teamdraco.finsandstails.common.items.FinsPotItem;
import teamdraco.finsandstails.common.items.SpindlyGemCharmItem;
import teamdraco.finsandstails.registry.FTItems;

public class FTItemProrties {

    public static void setupItemProperties() {

        registerBroken(FTItems.GEM_CRAB_AMULET.get());
        for (RegistryObject<Item> item : FTItems.ITEMS.getEntries()) {
            if (item.get() instanceof FinsBucketItem || item.get() instanceof FinsPotItem) {
                registerVariant(item.get());
            }
        }

    }


    private static void registerVariant(Item item) {
        ItemProperties.register(item, new ResourceLocation(FinsAndTails.MOD_ID, "variant"), (stack, world, player, i) -> stack.hasTag() ? stack.getTag().getInt("Variant") : 0);
    }

    private static void registerBroken(Item item) {
        ItemProperties.register(item, new ResourceLocation(FinsAndTails.MOD_ID, "broken"), (stack, world, player, i) -> SpindlyGemCharmItem.isUsable(stack) ? 0.0F : 1.0F);
    }
}
