package mod.coda.fins.item;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.GopjetJetpackModel;
import mod.coda.fins.init.FinsItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
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
                if (world.getBlockState(world.getHeight(Heightmap.Type.MOTION_BLOCKING, player.getPosition()).down()).getMaterial() == Material.WATER) {
                    canFly = true;
                } else {
                    for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                        ItemStack inventoryStack = player.inventory.getStackInSlot(i);
                        Item item = inventoryStack.getItem();
                        int ticksJumping = inventoryStack.hasTag() ? inventoryStack.getTag().getInt("FinsFlyingTicks") : 0;
                        if (item == Items.WATER_BUCKET) {
                            flyingTicksRemaining = 100 - ticksJumping;
                        } else if (item == Items.POTION && PotionUtils.getPotionFromItem(inventoryStack) == Potions.WATER) {
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
            }
            if (player.isJumping) {
                if (canFly) {
                    player.fallDistance = 0;
                    int ticksJumping = player.getPersistentData().getInt("FinsFlyingTicks") + 1;
                    if (ticksJumping % 10 == 0) {
                        int amount = 1;
                        if (!player.abilities.isCreativeMode) {
                            int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack);

                            for (int k = 0; i > 0 && k < 1; ++k) {
                                if (UnbreakingEnchantment.negateDamage(stack, i, random)) {
                                    amount = 0;
                                    break;
                                }
                            }

                            if (amount > 0) {
                                int l = stack.getDamage() + amount;
                                stack.setDamage(l);
                                stack.damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(EquipmentSlotType.CHEST));
                            }
                        }
                    }
                    player.getPersistentData().putInt("FinsFlyingTicks", ticksJumping);
                    player.setMotion(player.getMotion().add(0, 0.1, 0));
                    if (stackIndex != -1) {
                        ItemStack flyingStack = player.inventory.getStackInSlot(stackIndex);
                        if (flyingTicksRemaining - 1 <= 0) {
                            Item item = flyingStack.getItem();
                            flyingStack.shrink(1);
                            ItemStack newStack = null;
                            if (item == Items.WATER_BUCKET) {
                                newStack = new ItemStack(Items.BUCKET);
                            } else if (item == Items.POTION && PotionUtils.getPotionFromItem(flyingStack) == Potions.WATER) {
                                newStack = new ItemStack(Items.GLASS_BOTTLE);
                            } else if (Block.getBlockFromItem(item) == Blocks.WET_SPONGE) {
                                newStack = new ItemStack(Blocks.SPONGE);
                            }
                            if (newStack != null) {
                                if (flyingStack.isEmpty()) {
                                    player.inventory.setInventorySlotContents(stackIndex, newStack);
                                } else if (player.inventory.addItemStackToInventory(newStack)) {
                                    player.dropItem(newStack, false);
                                }
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
