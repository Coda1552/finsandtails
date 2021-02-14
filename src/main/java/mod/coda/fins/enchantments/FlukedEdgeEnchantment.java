package mod.coda.fins.enchantments;

import mod.coda.fins.init.FinsItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class FlukedEdgeEnchantment extends Enchantment {
    public FlukedEdgeEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType[] slots) {
        super(rarity, type, slots);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return !ench.equals(Enchantments.DEPTH_STRIDER);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() == FinsItems.FWINGED_BOOTS.get();
    }
}
