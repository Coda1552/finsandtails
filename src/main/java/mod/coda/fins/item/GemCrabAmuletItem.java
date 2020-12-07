package mod.coda.fins.item;

import mod.coda.fins.FinsAndTails;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class GemCrabAmuletItem extends ArmorItem {
    public static final IArmorMaterial MATERIAL = new FwingedArmorMaterial(FinsAndTails.MOD_ID + ":gem_crab_amulet", 1, new int[]{1, 2, 3, 1}, 3, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, null);

    public GemCrabAmuletItem() {
        super(MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(FinsAndTails.GROUP));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.isAlive()) {
            player.addPotionEffect(new EffectInstance(Effects.LUCK, 60, 0, false, false, true));
        }
    }
}