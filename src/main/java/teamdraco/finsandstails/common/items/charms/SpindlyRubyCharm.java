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
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.FinsArmorMaterial;

import java.util.List;

public class SpindlyRubyCharm extends GeoArmorItem implements IAnimatable {
    public static final ArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":spindly_ruby_charm", 1, new int[]{1, 2, 3, 1}, 8, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, null);
    private final AnimationFactory factory = new AnimationFactory(this);

    public SpindlyRubyCharm() {
        super(MATERIAL, EquipmentSlot.CHEST, new Properties().tab(FinsAndTails.GROUP).durability(25).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable Level p_77624_2_, List<Component> p_77624_3_, TooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(new TranslatableComponent("finsandtails.spindly_charm.desc").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (player.isAlive() && player.getHealth() <= 4.0F && !player.getCooldowns().isOnCooldown(this)) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 140, 0, false, false, true));
            stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(EquipmentSlot.CHEST));
            player.getCooldowns().addCooldown(this, 200);
        }
    }

    @Override
    public void registerControllers(AnimationData data) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}