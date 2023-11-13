package teamdraco.finsandstails.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;
import teamdraco.finsandstails.FinsAndTails;

import java.util.List;

public class SpindlyCharmItem extends GeoArmorItem implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private final String materialName;
    private final MobEffect effect;

    public SpindlyCharmItem(String materialName, MobEffect effect) {
        super(
                new FinsArmorMaterial(FinsAndTails.MOD_ID + ":spindly_" + materialName + "_charm", 1, new int[]{1, 2, 3, 1}, 8, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, () -> Ingredient.EMPTY),
                EquipmentSlot.CHEST,
                new Properties().tab(FinsAndTails.GROUP).durability(25).rarity(Rarity.UNCOMMON)
        );
        this.materialName = materialName;
        this.effect = effect;
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable Level p_77624_2_, List<Component> p_77624_3_, TooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(Component.translatable("finsandtails.spindly_charm.desc").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (player.isAlive() && player.getHealth() <= 8.0F && !player.getCooldowns().isOnCooldown(this)) {
            player.addEffect(new MobEffectInstance(effect, 100, 0, false, false, true));
            stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(EquipmentSlot.CHEST));
            player.getCooldowns().addCooldown(this, 200);
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public String getTypeName() {
        return materialName;
    }
}
