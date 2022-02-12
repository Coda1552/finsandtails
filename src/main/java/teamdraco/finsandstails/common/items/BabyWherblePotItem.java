package teamdraco.finsandstails.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.ForgeEventFactory;
import teamdraco.finsandstails.common.entities.WherbleEntity;
import teamdraco.finsandstails.registry.FTEntities;

import java.util.function.Supplier;

public class BabyWherblePotItem extends FinsBucketItem {
    private final Supplier<? extends EntityType<?>> entityTypeSupplier;
    private final Supplier<? extends Fluid> fluid;

    public BabyWherblePotItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Properties builder) {
        super(entityType, fluid, builder);
        this.entityTypeSupplier = entityType;
        this.fluid = fluid;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        BlockHitResult result = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.NONE);
        InteractionResultHolder<ItemStack> ret = ForgeEventFactory.onBucketUse(playerIn, worldIn, itemstack, result);
        if (ret != null) return ret;
        if (result.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else if (result.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            BlockPos blockpos = result.getBlockPos();
            Direction direction = result.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos1, direction, itemstack)) {
                BlockState blockstate = worldIn.getBlockState(blockpos);
                BlockPos blockpos2 = blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer) blockstate.getBlock()).canPlaceLiquid(worldIn, blockpos, blockstate, fluid.get()) ? blockpos : blockpos1;
                this.emptyContents(playerIn, worldIn, blockpos2, result);
                if (worldIn instanceof ServerLevel) this.placeEntity((ServerLevel)worldIn, itemstack, blockpos2);
                if (playerIn instanceof ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) playerIn, blockpos2, itemstack);
                }

                playerIn.awardStat(Stats.ITEM_USED.get(this));
                return InteractionResultHolder.sidedSuccess(getEmptyItem(itemstack, playerIn), worldIn.isClientSide());
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }

    private void placeEntity(ServerLevel worldIn, ItemStack stack, BlockPos pos) {
        Entity entity = this.entityTypeSupplier.get().spawn(worldIn, stack, null, pos, MobSpawnType.BUCKET, true, false);
        if (entity != null) {
            if (entity instanceof WherbleEntity) {
                ((WherbleEntity)entity).setAge(stack.getTag().getInt("Age"));
                ((WherbleEntity)entity).setVariant(worldIn.random.nextInt(4));
            }
        }
    }

    private EntityType<?> getEntityType() {
        return entityTypeSupplier.get();
    }

    private ItemStack getEmptyItem(ItemStack stack, Player player) {
        if (entityTypeSupplier == FTEntities.FLATBACK_LEAF_SNAIL || entityTypeSupplier == FTEntities.SIDEROL_WHISKERED_SNAIL || entityTypeSupplier == FTEntities.RIVER_PEBBLE_SNAIL) {
            return !player.isCreative() ? new ItemStack(Items.FLOWER_POT) : stack;
        }
        else {
            return !player.isCreative() ? new ItemStack(Items.BUCKET) : stack;
        }
    }
}
