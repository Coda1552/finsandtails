package teamdraco.fins.common.items.charms;

import teamdraco.fins.FinsAndTails;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import teamdraco.fins.common.items.FinsArmorMaterial;

public class SpindlyGemCharm extends ArmorItem implements ISpindlyCharmItem {
    public static final IArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":gem_crab_amulet", 1, new int[]{1, 2, 3, 1}, 3, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, null);

    public SpindlyGemCharm() {
        super(MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().tab(FinsAndTails.GROUP).durability(2).rarity(Rarity.RARE));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.isAlive() && isUsable(stack) && player.getHealth() <= 4.0F) {
            player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 1200, 0, false, false, true));
            player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 600, 0, false, false, true));
            player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 1200, 0, false, false, true));
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 1200, 0, false, false, true));
            player.addEffect(new EffectInstance(Effects.REGENERATION, 400, 0, false, false, true));
            stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(net.minecraft.inventory.EquipmentSlotType.CHEST));
        }
    }

    public static boolean isUsable(ItemStack stack) {
        return !stack.isDamaged();
    }
}