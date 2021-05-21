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
    public static final IArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":spindly_pearl_charm", 1, new int[]{1, 2, 3, 1}, 8, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, null);

    public SpindlyPearlCharm() {
        super(MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(FinsAndTails.GROUP).maxDamage(25).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.isAlive() && player.getHealth() <= 4.0F && !player.getCooldownTracker().hasCooldown(this)) {
            player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100, 0, false, false, true));
            stack.damageItem(1, player, e -> e.sendBreakAnimation(net.minecraft.inventory.EquipmentSlotType.CHEST));
            player.getCooldownTracker().setCooldown(this, 200);
        }
    }
}