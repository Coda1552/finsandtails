package teamdraco.finsandstails.common.items.charms;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.FinsArmorMaterial;

import java.util.List;

public class SpindlyGemCharm extends ArmorItem implements ISpindlyCharmItem {
    public static final ArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":gem_crab_amulet", 1, new int[]{1, 2, 3, 1}, 3, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, null);

    public SpindlyGemCharm() {
        super(MATERIAL, EquipmentSlot.CHEST, new Properties().tab(FinsAndTails.GROUP).durability(2).rarity(Rarity.RARE));
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable Level p_77624_2_, List<Component> p_77624_3_, TooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(new TranslatableComponent("finsandtails.spindly_gem_charm.desc").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (player.isAlive() && isUsable(stack) && player.getHealth() <= 4.0F) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 1200, 0, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 0, false, false, true));
            stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(EquipmentSlot.CHEST));
        }
    }

    public static boolean isUsable(ItemStack stack) {
        return !stack.isDamaged();
    }
}