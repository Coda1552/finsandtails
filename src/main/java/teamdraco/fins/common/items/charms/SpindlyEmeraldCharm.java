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

public class SpindlyEmeraldCharm extends ArmorItem implements ISpindlyCharmItem {
    public static final IArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":spindly_emerald_charm", 1, new int[]{1, 2, 3, 1}, 8, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, null);

    public SpindlyEmeraldCharm() {
        super(MATERIAL, EquipmentSlotType.CHEST, new Properties().group(FinsAndTails.GROUP).maxDamage(25).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.isAlive() && player.getHealth() <= 4.0F && !player.getCooldownTracker().hasCooldown(this)) {
            player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 100, 0, false, false, true));
            stack.damageItem(1, player, e -> e.sendBreakAnimation(EquipmentSlotType.CHEST));
            player.getCooldownTracker().setCooldown(this, 200);
        }
    }
}