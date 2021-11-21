package teamdraco.fins.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import teamdraco.fins.init.FinsItems;

public class UppercuttingEnchantment extends Enchantment {
    public UppercuttingEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType[] slots) {
        super(rarity, type, slots);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() == FinsItems.RED_CLAW_GAUNTLET.get() || stack.getItem() == FinsItems.WHITE_CLAW_GAUNTLET.get();
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
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        super.doPostAttack(user, target, level);
        if (target.isInWaterOrRain() && user.getMainHandItem().getItem() == FinsItems.RED_CLAW_GAUNTLET.get() || user.getMainHandItem().getItem() == FinsItems.WHITE_CLAW_GAUNTLET.get()) {
            if (!user.getCommandSenderWorld().isClientSide) {
                target.setDeltaMovement(target.getDeltaMovement().add(0.0D, 0.3D, 0.0D));
            }
        }
    }
}
