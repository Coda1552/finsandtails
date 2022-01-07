package teamdraco.finsandstails.common.enchantments;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import teamdraco.finsandstails.registry.FTItems;

public class UppercuttingEnchantment extends Enchantment {
    public UppercuttingEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot[] slots) {
        super(rarity, type, slots);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() == FTItems.RED_CLAW_GAUNTLET.get() || stack.getItem() == FTItems.WHITE_CLAW_GAUNTLET.get();
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
        if (target.isInWaterOrRain() && user.getMainHandItem().getItem() == FTItems.RED_CLAW_GAUNTLET.get() || user.getMainHandItem().getItem() == FTItems.WHITE_CLAW_GAUNTLET.get()) {
            if (!user.getCommandSenderWorld().isClientSide) {
                target.setDeltaMovement(target.getDeltaMovement().add(0.0D, 0.3D, 0.0D));
            }
        }
    }
}
