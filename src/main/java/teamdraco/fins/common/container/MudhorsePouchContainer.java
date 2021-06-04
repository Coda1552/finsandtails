package teamdraco.fins.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import teamdraco.fins.init.FinsContainers;
import teamdraco.fins.init.FinsItems;

public class MudhorsePouchContainer extends Container {
    private final MudhorsePouchInventory stackInventory;
    private ItemStack itemStack;

    public MudhorsePouchContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, ItemStack.EMPTY);
    }

    public MudhorsePouchContainer(int id, PlayerInventory playerInventory, ItemStack inventoryStack) {
        super(FinsContainers.MUDHORSE_POUCH.get(), id);
        MudhorsePouchInventory inventory = getStackInventory(inventoryStack);
        checkContainerSize(inventory, 9);
        this.stackInventory = inventory;
        this.itemStack = inventoryStack;
        inventory.startOpen(playerInventory.player);

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(inventory, j + i * 3, 62 + j * 18, 17 + i * 18) {
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return super.mayPlace(stack) && stack.getItem() != FinsItems.MUDHORSE_POUCH.get();
                    }
                });
            }
        }

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new PlayerInventorySlot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new PlayerInventorySlot(playerInventory, l, 8 + l * 18, 142));
        }
    }

    private static MudhorsePouchInventory getStackInventory(ItemStack stack) {
        MudhorsePouchInventory inventory = new MudhorsePouchInventory();
        if (!stack.isEmpty() && stack.hasTag()) {
            ListNBT items = stack.getTag().getList("Items", 10);
            for (int i = 0; i < items.size(); i++) {
                CompoundNBT item = items.getCompound(i);
                inventory.setItem(item.getByte("Slot"), ItemStack.of(item));
            }
        }
        return inventory;
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return this.stackInventory.stillValid(playerIn);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack resultStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            resultStack = slotStack.copy();
            if (index < 9) {
                if (!this.moveItemStackTo(slotStack, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(slotStack, 0, 9, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotStack.getCount() == resultStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, slotStack);
        }

        return resultStack;
    }

    @Override
    public void removed(PlayerEntity playerIn) {
        super.removed(playerIn);
        this.stackInventory.stopOpen(playerIn);
        if (!playerIn.level.isClientSide && stackInventory.isDirty()) {
            stackInventory.write(itemStack);
        }
    }

    private class PlayerInventorySlot extends Slot {
        public PlayerInventorySlot(IInventory inventory, int index, int xPosition, int yPosition) {
            super(inventory, index, xPosition, yPosition);
        }

        //Kind of a weird workaround, can't really think of other ways to do this but this should work for now
        @Override
        public void set(ItemStack stack) {
            if (!itemStack.isEmpty() && getItem() == itemStack) {
                itemStack = ItemStack.EMPTY;
            } else if (stack.getItem() == FinsItems.MUDHORSE_POUCH.get()) {
                itemStack = stack;
            }
            super.set(stack);
        }
    }
}
