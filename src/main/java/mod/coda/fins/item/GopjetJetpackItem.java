package mod.coda.fins.item;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.GopjetJetpackModel;
import mod.coda.fins.init.FinsItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
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
        if (stack.getMaxDamage() - stack.getDamage() > 1) {
            boolean canFly = world.isRainingAt(player.getPosition());
            int flyingTicksRemaining = 0;
            int stackIndex = -1;
            if (!canFly) {
                for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                    ItemStack inventoryStack = player.inventory.getStackInSlot(i);
                    Item item = inventoryStack.getItem();
                    int ticksJumping = inventoryStack.hasTag() ? inventoryStack.getTag().getInt("FinsFlyingTicks") : 0;
                    if (item == Items.WATER_BUCKET) {
                        flyingTicksRemaining = 100 - ticksJumping;
                    } else if (item == Items.POTION && PotionUtils.getPotionFromItem(inventoryStack) == Potions.EMPTY) {
                        flyingTicksRemaining = 30 - ticksJumping;
                    } else if (Block.getBlockFromItem(item) == Blocks.WET_SPONGE) {
                        flyingTicksRemaining = 40 - ticksJumping;
                    } else {
                        continue;
                    }
                    stackIndex = i;
                    canFly = true;
                    break;
                }
            }
            if (canFly) {
                player.fallDistance = 0;
                if (player.isJumping) {
                    int ticksJumping = player.getPersistentData().getInt("FinsFlyingTicks") + 1;
                    if (ticksJumping % 10 == 0) {
                        stack.damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(EquipmentSlotType.CHEST));
                    }
                    player.getPersistentData().putInt("FinsFlyingTicks", ticksJumping);
                    player.setMotion(player.getMotion().add(0, 0.1, 0));
                    if (stackIndex != -1) {
                        ItemStack flyingStack = player.inventory.getStackInSlot(stackIndex);
                        if (flyingTicksRemaining - 1 <= 0) {
                            Item item = flyingStack.getItem();
                            if (item == Items.WATER_BUCKET) {
                                player.inventory.setInventorySlotContents(stackIndex, new ItemStack(Items.BUCKET));
                            } else if (item == Items.POTION && PotionUtils.getPotionFromItem(flyingStack) == Potions.EMPTY) {
                                player.inventory.setInventorySlotContents(stackIndex, new ItemStack(Items.GLASS_BOTTLE));
                            } else if (Block.getBlockFromItem(item) == Blocks.WET_SPONGE) {
                                player.inventory.setInventorySlotContents(stackIndex, new ItemStack(Blocks.SPONGE));
                            }
                        } else {
                            CompoundNBT tag = flyingStack.getOrCreateTag();
                            tag.putInt("FinsFlyingTicks", tag.getInt("FinsFlyingTicks") + 1);
                        }
                    }
                }
            }
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
