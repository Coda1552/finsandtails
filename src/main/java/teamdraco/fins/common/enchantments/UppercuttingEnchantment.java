package teamdraco.fins.common.enchantments;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import teamdraco.fins.init.FinsItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class UppercuttingEnchantment extends Enchantment {
    public UppercuttingEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType[] slots) {
        super(rarity, type, slots);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return true;
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
    public boolean canApply(ItemStack stack) {
        return stack.getItem() == FinsItems.RED_CLAW_GAUNTLET.get() || stack.getItem() == FinsItems.WHITE_CLAW_GAUNTLET.get() && stack.getItem() != FinsItems.FWINGED_BOOTS.get();
    }

    @Override
    public void onEntityDamaged(LivingEntity user, Entity target, int level) {
        super.onEntityDamaged(user, target, level);
        if (target.isWet() && user.getHeldItemMainhand().getItem() == FinsItems.RED_CLAW_GAUNTLET.get() || user.getHeldItemMainhand().getItem() == FinsItems.WHITE_CLAW_GAUNTLET.get()) {
            if (!user.getEntityWorld().isRemote) {
                target.setMotion(target.getMotion().add(0.0D, 0.3D, 0.0D));
            }
        }
    }
}
