package teamdraco.fins.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.GopjetJetpackModel;
import teamdraco.fins.init.FinsItems;
import teamdraco.fins.init.FinsSounds;

import javax.annotation.Nullable;

import net.minecraft.item.Item.Properties;

public class GopjetJetpackItem extends ArmorItem {
    public static final IArmorMaterial MATERIAL = new FinsArmorMaterial(FinsAndTails.MOD_ID + ":gopjet_jetpack", 0, new int[]{0, 0, 0, 0}, 1, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.of(FinsItems.GOPJET_JET.get()));
    private int bubbleSoundTime;

    public GopjetJetpackItem() {
        super(MATERIAL, EquipmentSlotType.CHEST, new Properties().tab(FinsAndTails.GROUP).stacksTo(1).durability(128));
    }

    private BlockPos getBlockUnderPlayer(PlayerEntity player) {
        final BlockPos.Mutable position = player.blockPosition().mutable();
        BlockState state;
        while ((!(state = player.level.getBlockState(position)).getMaterial().blocksMotion() && state.getFluidState().isEmpty()) || state.getBlock() instanceof LeavesBlock) {
            position.move(Direction.DOWN);
            if (position.getY() <= 0) return BlockPos.ZERO;
        }
        return position;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (stack.getMaxDamage() - stack.getDamageValue() > 1 || player.abilities.instabuild) {
            boolean canFly = world.isRainingAt(player.blockPosition());
            int flyingTicksRemaining = 0;
            int stackIndex = -1;

            if (!canFly) {
                if (canFly || player.blockPosition().getY() > 0 && world.getBlockState(getBlockUnderPlayer(player)).getMaterial() == Material.WATER) {
                    canFly = true;
                } else {
                    for (int i = 0; i < player.inventory.getContainerSize(); i++) {
                        ItemStack inventoryStack = player.inventory.getItem(i);
                        Item item = inventoryStack.getItem();
                        int ticksJumping = inventoryStack.hasTag() ? inventoryStack.getTag().getInt("FinsFlyingTicks") : 0;
                        if (item == Items.WATER_BUCKET) {
                            flyingTicksRemaining = 100 - ticksJumping;
                        } else if (item == Items.POTION && PotionUtils.getPotion(inventoryStack) == Potions.WATER) {
                            flyingTicksRemaining = 30 - ticksJumping;
                        } else {
                            continue;
                        }
                        stackIndex = i;
                        canFly = true;
                        break;
                    }
                }
            }
            CompoundNBT persistentData = player.getPersistentData();
            if (persistentData.getBoolean("FinsFlying")) {
                if (canFly || player.blockPosition().getY() > 0 && world.getBlockState(getBlockUnderPlayer(player)).getMaterial() == Material.WATER) {
                    player.fallDistance = 0;
                    int ticksJumping = persistentData.getInt("FinsFlyingTicks") + 1;
                    if (ticksJumping % 10 == 0) {
                        stack.hurtAndBreak(1, player, playerEntity -> playerEntity.broadcastBreakEvent(EquipmentSlotType.CHEST));
                    }
                    persistentData.putInt("FinsFlyingTicks", ticksJumping);
                    player.setDeltaMovement(player.getDeltaMovement().add(0, 0.1, 0));
                }
                if (canFly || player.blockPosition().getY() > 0 && world.getBlockState(getBlockUnderPlayer(player)).getMaterial() == Material.WATER) {
                    if (random.nextInt(100) < this.bubbleSoundTime++) {
                        this.bubbleSoundTime = 0;
                        world.playSound(player, player.blockPosition(), FinsSounds.JETPACK_USE.get(), SoundCategory.AMBIENT, 1.0F, 1.0F);
                    }

                    if (world.isClientSide) {
                        for (int i = 0; i < 4; i++) {
                            float sign = Math.signum(i - 2);
                            if (sign == 0) {
                                sign = 1;
                            }
                            double playerRotation = Math.toRadians(player.yBodyRot + 35 * sign);
                            double xOffset = random.nextGaussian() * 0.05;
                            double yOffset = random.nextGaussian() * 0.01;
                            double zOffset = random.nextGaussian() * 0.05;
                            double xPos = player.getX() + xOffset - Math.sin(-playerRotation) * 0.35;
                            double yPos = player.getY() + yOffset + 0.7;
                            double zPos = player.getZ() + zOffset - Math.cos(playerRotation) * 0.35;
                            for (int j = 0; j <= 8; j++) {
                                world.addParticle(random.nextInt(2) == 0 ? ParticleTypes.SPLASH : ParticleTypes.BUBBLE, xPos, yPos, zPos, 0, -0.10, 0);
                            }
                        }
                    }
                    if (stackIndex != -1) {
                        ItemStack flyingStack = player.inventory.getItem(stackIndex);
                        if (flyingTicksRemaining - 1 <= 0) {
                            Item item = flyingStack.getItem();
                            flyingStack.shrink(1);
                            ItemStack newStack = null;
                            if (item == Items.WATER_BUCKET) {
                                newStack = new ItemStack(Items.BUCKET);
                            } else if (item == Items.POTION && PotionUtils.getPotion(flyingStack) == Potions.WATER) {
                                newStack = new ItemStack(Items.GLASS_BOTTLE);
                            } else if (Block.byItem(item) == Blocks.WET_SPONGE) {
                                newStack = new ItemStack(Blocks.SPONGE);
                            }
                            if (newStack != null) {
                                if (flyingStack.isEmpty()) {
                                    player.inventory.setItem(stackIndex, newStack);
                                } else if (!player.inventory.add(newStack)) {
                                    player.drop(newStack, false);
                                }
                                flyingStack.getOrCreateTag().remove("FinsFlyingTicks");
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

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.UNBREAKING && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) GopjetJetpackModel.INSTANCE;
    }
}