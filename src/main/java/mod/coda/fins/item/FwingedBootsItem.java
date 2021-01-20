package mod.coda.fins.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.FwingedBootsModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nullable;

public class FwingedBootsItem extends ArmorItem {
    public static final IArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":fwinged", 1, new int[]{1, 2, 3, 1}, 3, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(Items.LEATHER));
    public static final Lazy<Multimap<Attribute, AttributeModifier>> SWIM_MODIFIER = Lazy.of(() -> ImmutableMultimap.of(ForgeMod.SWIM_SPEED.get(), new AttributeModifier("Swim modifier", 1.25, AttributeModifier.Operation.ADDITION)));

    public FwingedBootsItem() {
        super(MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(FinsAndTails.GROUP));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        return slot == EquipmentSlotType.FEET ? SWIM_MODIFIER.get() : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.DEPTH_STRIDER && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) FwingedBootsModel.INSTANCE;
    }
}
