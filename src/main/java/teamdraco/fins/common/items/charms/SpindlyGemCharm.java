package teamdraco.fins.common.items.charms;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.Nullable;
import teamdraco.fins.FinsAndTails;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import teamdraco.fins.common.items.FinsArmorMaterial;

import java.util.List;

public class SpindlyGemCharm extends ArmorItem implements ISpindlyCharmItem {
    public static final IArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":gem_crab_amulet", 1, new int[]{1, 2, 3, 1}, 3, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, null);

    public SpindlyGemCharm() {
        super(MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().tab(FinsAndTails.GROUP).durability(2).rarity(Rarity.RARE));
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(new TranslationTextComponent("fins.spindly_gem_charm.desc").withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
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