package teamdraco.fins.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.IWorldPosCallable;
import teamdraco.fins.common.container.slot.CrabCruncherSlot;
import teamdraco.fins.init.FinsBlocks;
import teamdraco.fins.init.FinsContainers;

public class CrabCruncherContainer extends Container {
    private final IWorldPosCallable worldPosCallable;
    private final PlayerEntity player;
    private final CrabCruncherInventory stackInventory;

    public CrabCruncherContainer(final int windowId, PlayerInventory playerInventory) {
        this(windowId, playerInventory, IWorldPosCallable.DUMMY, ItemStack.EMPTY);
    }

    public CrabCruncherContainer(final int windowId, PlayerInventory playerInventory, IWorldPosCallable worldPosCallable, ItemStack inventoryStack) {
        super(FinsContainers.CRAB_CRUNCHER.get(), windowId);
        CrabCruncherInventory inventory = getStackInventory(inventoryStack);
        this.worldPosCallable = worldPosCallable;
        this.player = playerInventory.player;
        this.stackInventory = inventory;

        // Input Slots
        this.addSlot(new CrabCruncherSlot(inventory, 0, 27, 47));
        this.addSlot(new CrabCruncherSlot(inventory, 1, 76, 47));

        // Result Slot
        this.addSlot(new FurnaceResultSlot(playerInventory.player, inventory, 10, 134, 47));

        // Main Player Inv
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // Player Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    private static CrabCruncherInventory getStackInventory(ItemStack stack) {
        CrabCruncherInventory inventory = new CrabCruncherInventory();
        if (!stack.isEmpty() && stack.hasTag()) {
            ListNBT items = stack.getTag().getList("Items", 10);
            for (int i = 0; i < items.size(); i++) {
                CompoundNBT item = items.getCompound(i);
                inventory.setInventorySlotContents(item.getByte("Slot"), ItemStack.read(item));
            }
        }
        return inventory;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(worldPosCallable, playerIn, FinsBlocks.CRAB_CRUNCHER.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if (index < 3) {
                if (!this.mergeItemStack(stack1, 0, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(stack1, 0, 2, true)) {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }
            else {
                slot.onSlotChanged();
            }
        }

        return stack;
    }
}
