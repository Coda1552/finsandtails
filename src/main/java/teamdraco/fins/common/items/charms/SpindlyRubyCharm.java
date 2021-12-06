package teamdraco.fins.common.items.charms;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import javax.annotation.Nullable;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.items.FinsArmorMaterial;

import java.util.List;

public class SpindlyRubyCharm extends ArmorItem implements ISpindlyCharmItem {
    public static final IArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":spindly_ruby_charm", 1, new int[]{1, 2, 3, 1}, 8, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, null);

    public SpindlyRubyCharm() {
        super(MATERIAL, EquipmentSlotType.CHEST, new Properties().tab(FinsAndTails.GROUP).durability(25).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(new TranslationTextComponent("fins.spindly_charm.desc").withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.isAlive() && player.getHealth() <= 4.0F && !player.getCooldowns().isOnCooldown(this)) {
            player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 140, 0, false, false, true));
            stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(EquipmentSlotType.CHEST));
            player.getCooldowns().addCooldown(this, 200);
        }
    }
}