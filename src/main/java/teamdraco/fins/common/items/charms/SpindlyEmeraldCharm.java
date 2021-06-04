package teamdraco.fins.common.items.charms;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.items.FinsArmorMaterial;

import net.minecraft.item.Item.Properties;

public class SpindlyEmeraldCharm extends ArmorItem implements ISpindlyCharmItem {
    public static final IArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":spindly_emerald_charm", 1, new int[]{1, 2, 3, 1}, 8, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, null);

    public SpindlyEmeraldCharm() {
        super(MATERIAL, EquipmentSlotType.CHEST, new Properties().tab(FinsAndTails.GROUP).durability(25).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.isAlive() && player.getHealth() <= 4.0F && !player.getCooldowns().isOnCooldown(this)) {
            player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 100, 0, false, false, true));
            stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(EquipmentSlotType.CHEST));
            player.getCooldowns().addCooldown(this, 200);
        }
    }
}