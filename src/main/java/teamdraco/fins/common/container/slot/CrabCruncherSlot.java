package teamdraco.fins.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import teamdraco.fins.init.FinsItems;

public class CrabCruncherSlot extends Slot {
    public CrabCruncherSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == FinsItems.SPINDLY_GEM_CRAB.get() || stack.getItem() == FinsItems.SWAMP_MUCKER.get() || stack.getItem() == FinsItems.ORNATE_BUGFISH.get() || stack.getItem() == FinsItems.NIGHT_LIGHT_SQUID.get();
    }
}
