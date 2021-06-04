package teamdraco.fins.common.container;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class MudhorsePouchInventory extends Inventory {
    private boolean isDirty;

    public MudhorsePouchInventory() {
        super(9);
    }

    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        isDirty = true;
    }

    public void write(ItemStack stack) {
        CompoundNBT tag = new CompoundNBT();
        ListNBT list = new ListNBT();
        for (int i = 0; i < getContainerSize(); i++) {
            CompoundNBT item = new CompoundNBT();
            item.putByte("Slot", (byte) i);
            list.add(getItem(i).save(item));
        }
        tag.put("Items", list);
        stack.setTag(tag);
        isDirty = false;
    }
}
