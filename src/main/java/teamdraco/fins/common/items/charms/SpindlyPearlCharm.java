package teamdraco.fins.common.items.charms;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.items.FinsArmorMaterial;

public class SpindlyPearlCharm extends ArmorItem implements ISpindlyCharmItem {
    public static final IArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":spindly_pearl_charm", 1, new int[]{1, 2, 3, 1}, 8, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, null);

    public SpindlyPearlCharm() {
        super(MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().tab(FinsAndTails.GROUP).durability(25).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.isAlive() && player.getHealth() <= 4.0F && !player.getCooldowns().isOnCooldown(this)) {
            player.addEffect(new EffectInstance(Effects.REGENERATION, 100, 0, false, false, true));
            stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(net.minecraft.inventory.EquipmentSlotType.CHEST));
            player.getCooldowns().addCooldown(this, 200);
        }
    }
}