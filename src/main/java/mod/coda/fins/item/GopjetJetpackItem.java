package mod.coda.fins.item;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.GopjetJetpackModel;
import mod.coda.fins.init.FinsItems;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class GopjetJetpackItem extends ArmorItem {
    public static final IArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":gopjet_jetpack", 0, new int[]{0, 0, 0, 0}, 1, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(FinsItems.GOPJET_JET.get()));

    public GopjetJetpackItem() {
        super(MATERIAL, EquipmentSlotType.CHEST, new Properties().group(FinsAndTails.GROUP).maxStackSize(1).maxDamage(128));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        player.fallDistance = 0;
        if (player.isJumping) {
            int ticksJumping = player.getPersistentData().getInt("FinsFlyingTicks") + 1;
            if (ticksJumping % 10 == 0) {
                stack.damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(EquipmentSlotType.CHEST));
            }
            player.getPersistentData().putInt("FinsFlyingTicks", ticksJumping);
            player.setMotion(player.getMotion().add(0, 0.1, 0));
        }
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) GopjetJetpackModel.INSTANCE;
    }
}
