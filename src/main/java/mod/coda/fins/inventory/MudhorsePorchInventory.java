package mod.coda.fins.inventory;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class MudhorsePorchInventory extends Inventory {
    private boolean isDirty;

    public MudhorsePorchInventory() {
        super(9);
    }

    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public void markDirty() {
        super.markDirty();
        isDirty = true;
    }

    public void write(ItemStack stack) {
        CompoundNBT tag = new CompoundNBT();
        ListNBT list = new ListNBT();
        for (int i = 0; i < getSizeInventory(); i++) {
            CompoundNBT item = new CompoundNBT();
            item.putByte("Slot", (byte) i);
            list.add(getStackInSlot(i).write(item));
        }
        tag.put("Items", list);
        stack.setTag(tag);
        isDirty = false;
    }
}
