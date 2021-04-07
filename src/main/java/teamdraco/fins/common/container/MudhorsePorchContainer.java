package teamdraco.fins.common.container;

import teamdraco.fins.init.FinsContainers;
import teamdraco.fins.init.FinsItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class MudhorsePorchContainer extends Container {
    private final MudhorsePorchInventory stackInventory;
    private final ItemStack itemStack;

    public MudhorsePorchContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, ItemStack.EMPTY);
    }

    public MudhorsePorchContainer(int id, PlayerInventory playerInventory, ItemStack inventoryStack) {
        super(FinsContainers.MUDHORSE_POUCH.get(), id);
        MudhorsePorchInventory inventory = getStackInventory(inventoryStack);
        assertInventorySize(inventory, 9);
        this.stackInventory = inventory;
        this.itemStack = inventoryStack;
        inventory.openInventory(playerInventory.player);

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(inventory, j + i * 3, 62 + j * 18, 17 + i * 18) {
                    @Override
                    public boolean isItemValid(ItemStack stack) {
                        return super.isItemValid(stack) && stack.getItem() != FinsItems.MUDHORSE_POUCH.get();
                    }
                });
            }
        }

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 142));
        }
    }

    private static MudhorsePorchInventory getStackInventory(ItemStack stack) {
        MudhorsePorchInventory inventory = new MudhorsePorchInventory();
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
        return this.stackInventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack resultStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            resultStack = slotStack.copy();
            if (index < 9) {
                if (!this.mergeItemStack(slotStack, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(slotStack, 0, 9, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.getCount() == resultStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, slotStack);
        }

        return resultStack;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.stackInventory.closeInventory(playerIn);
        if (!playerIn.world.isRemote && stackInventory.isDirty()) {
            stackInventory.write(itemStack);
        }
    }
}
