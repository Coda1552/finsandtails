package teamdraco.fins.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import teamdraco.fins.init.FinsItems;

public class CrabCruncherSlot extends Slot {
    public CrabCruncherSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return  stack.getItem() == FinsItems.AMBER_SPINDLY_GEM_CRAB.get()
                || stack.getItem() == FinsItems.RUBY_SPINDLY_GEM_CRAB.get()
                || stack.getItem() == FinsItems.EMERALD_SPINDLY_GEM_CRAB.get()
                || stack.getItem() == FinsItems.SAPPHIRE_SPINDLY_GEM_CRAB.get()
                || stack.getItem() == FinsItems.PEARL_SPINDLY_GEM_CRAB.get()
                || stack.getItem() == FinsItems.NIGHT_LIGHT_SQUID.get()
                || stack.getItem() == FinsItems.SPINDLY_GEM_CRAB_GEM_BLOCK.get()
                || stack.getItem() == FinsItems.EMPTY_CHARM.get()
                || stack.getItem() == FinsItems.SPINDLY_AMBER.get()
                || stack.getItem() == FinsItems.SPINDLY_EMERALD.get()
                || stack.getItem() == FinsItems.SPINDLY_PEARL.get()
                || stack.getItem() == FinsItems.SPINDLY_SAPPHIRE.get()
                || stack.getItem() == FinsItems.SPINDLY_RUBY.get()
                || stack.getItem() == Items.BOOK;
    }
}
