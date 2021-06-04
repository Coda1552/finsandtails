package teamdraco.fins.common.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.FwingedBootsModel;
import teamdraco.fins.init.FinsEnchantments;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nullable;

public class FwingedBootsItem extends ArmorItem {
    public static final IArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":fwinged", 3, new int[]{1, 2, 3, 1}, 3, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.of(Items.LEATHER));
    public static final Lazy<Multimap<Attribute, AttributeModifier>> SWIM_MODIFIER = Lazy.of(() -> ImmutableMultimap.of(ForgeMod.SWIM_SPEED.get(), new AttributeModifier("Swim modifier", 1.25, AttributeModifier.Operation.ADDITION)));

    public FwingedBootsItem() {
        super(MATERIAL, EquipmentSlotType.FEET, new Item.Properties().tab(FinsAndTails.GROUP));
    }

    @Override
    public void onArmorTick(ItemStack stack, World worldIn, PlayerEntity player) {
        if (!player.isInWater()) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 1, 0));
        }

        int j = EnchantmentHelper.getItemEnchantmentLevel(FinsEnchantments.FLUKED_EDGE.get(), stack);
        if (EnchantmentHelper.getEnchantments(stack).containsKey(FinsEnchantments.FLUKED_EDGE.get())) {
            if (j <= 0 || worldIn.getBlockState(player.blockPosition().below()).is(Blocks.WATER) && worldIn.getBlockState(player.blockPosition()).isAir() /*&& player.isSwimming()*/ && player.getDeltaMovement().y > 0.25) {

                if (j > 0) {
                    float f7 = player.yRot;
                    float f = player.xRot;
                    float f1 = -MathHelper.sin(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f * ((float)Math.PI / 180F));
                    float f2 = -MathHelper.sin(f * ((float)Math.PI / 180F));
                    float f3 = MathHelper.cos(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f * ((float)Math.PI / 180F));
                    float f4 = MathHelper.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
                    float f5 = 3.0F * ((1.0F + (float)j) / 4.0F);
                    f1 = f1 * (f5 / f4);
                    f2 = f2 * (f5 / f4);
                    f3 = f3 * (f5 / f4);
                    if (j > 1) player.push((double) f1 / 3.5, (double) f2 / 2, (double) f3 / 3.5);
                    else if (j == 1) player.push((double) f1 / 1.5, (double) f2 / 2, (double) f3 / 1.5);
                    player.startAutoSpinAttack(1);
                    if (player.isOnGround()) {
                        float f6 = 1.1999999F;
                        player.move(MoverType.SELF, new Vector3d(0.0D, (double) 1.1999999F, 0.0D));
                    }
                }
            }
        }
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
