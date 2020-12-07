package mod.coda.fins.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.FwingedBootsModel;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
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

import javax.annotation.Nullable;
import java.util.UUID;

public class FwingedBootsItem extends ArmorItem {
    public static final IArmorMaterial MATERIAL = new FwingedArmorMaterial(FinsAndTails.MOD_ID + ":fwinged", 1, new int[]{1, 2, 3, 1}, 3, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(Items.LEATHER));

    public FwingedBootsItem() {
        super(MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(FinsAndTails.GROUP));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.isAlive() && !player.isInWater()) {
            player.addPotionEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 120, 0, false, false, true));
            player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 1, 0, false, false, true));
        }
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) FwingedBootsModel.INSTANCE;
    }
}
