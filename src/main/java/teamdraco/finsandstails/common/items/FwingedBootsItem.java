package teamdraco.finsandstails.common.items;

import coda.dracoshoard.common.items.DHArmorMaterial;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;
import software.bernie.finsandtails.geckolib3.core.IAnimatable;
import software.bernie.finsandtails.geckolib3.core.manager.AnimationData;
import software.bernie.finsandtails.geckolib3.core.manager.AnimationFactory;
import software.bernie.finsandtails.geckolib3.item.GeoArmorItem;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.registry.FTEnchantments;

public class FwingedBootsItem extends GeoArmorItem implements IAnimatable {
    public static final ArmorMaterial MATERIAL = new DHArmorMaterial(FinsAndTails.MOD_ID + ":fwinged", 3, new int[]{1, 2, 3, 1}, 3, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.of(Items.LEATHER));
    public static final Lazy<Multimap<Attribute, AttributeModifier>> SWIM_MODIFIER = Lazy.of(() -> ImmutableMultimap.of(ForgeMod.SWIM_SPEED.get(), new AttributeModifier("Swim modifier", 1.25, AttributeModifier.Operation.ADDITION)));
    //public static final Lazy<Multimap<Attribute, AttributeModifier>> MOVEMENT_MODIFIER = Lazy.of(() -> ImmutableMultimap.of(Attributes.MOVEMENT_SPEED, new AttributeModifier("Movement modifier", 0.85, AttributeModifier.Operation.MULTIPLY_BASE)));
    private final AnimationFactory factory = new AnimationFactory(this);

    public FwingedBootsItem() {
        super(MATERIAL, EquipmentSlot.FEET, new Properties().tab(FinsAndTails.GROUP));
    }

    @Override
    public void onArmorTick(ItemStack stack, Level worldIn, Player player) {
        if (!player.isInWater()) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1, 0));
        }

        int j = EnchantmentHelper.getItemEnchantmentLevel(FTEnchantments.FLUKED_EDGE.get(), stack);
        if (EnchantmentHelper.getEnchantments(stack).containsKey(FTEnchantments.FLUKED_EDGE.get())) {
            if (j <= 0 || worldIn.getBlockState(player.blockPosition().below()).is(Blocks.WATER) && worldIn.getBlockState(player.blockPosition()).isAir() /*&& player.isSwimming()*/ && player.getDeltaMovement().y > 0.25) {

                if (j > 0) {
                    float f7 = player.yRot;
                    float f = player.xRot;
                    float f1 = -Mth.sin(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
                    float f2 = -Mth.sin(f * ((float)Math.PI / 180F));
                    float f3 = Mth.cos(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
                    float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
                    float f5 = 3.0F * ((1.0F + (float)j) / 4.0F);
                    f1 = f1 * (f5 / f4);
                    f2 = f2 * (f5 / f4);
                    f3 = f3 * (f5 / f4);
                    if (j > 1) player.push((double) f1 / 3.5, (double) f2 / 2, (double) f3 / 3.5);
                    else player.push((double) f1 / 1.5, (double) f2 / 2, (double) f3 / 1.5);
                    player.startAutoSpinAttack(1);
                    if (player.isOnGround()) {
                        float f6 = 1.1999999F;
                        player.move(MoverType.SELF, new Vec3(0.0D, (double) 1.1999999F, 0.0D));
                    }
                }
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.FEET ? SWIM_MODIFIER.get() : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.DEPTH_STRIDER && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public void registerControllers(AnimationData animationData) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
