package teamdraco.finsandstails.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.Nullable;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.FlatbackLeafSnailEntity;
import teamdraco.finsandstails.common.entities.RiverPebbleSnailEntity;
import teamdraco.finsandstails.common.entities.SiderolWhiskeredSnailEntity;
import teamdraco.finsandstails.registry.FTEntities;

import java.util.List;
import java.util.function.Supplier;

public class FinsBucketItem extends BucketItem {
    private final Supplier<? extends EntityType<?>> entityTypeSupplier;
    private final Supplier<? extends Fluid> fluid;
    private final boolean hasTooltip;

    public FinsBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Properties builder) {
        this(entityType, fluid, builder, true);
    }

    public FinsBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Properties builder, boolean hasTooltip) {
        super(fluid, builder);
        this.fluid = fluid;
        this.hasTooltip = hasTooltip;
        this.entityTypeSupplier = entityType;
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> FinsAndTails.CALLBACKS.add(() -> ItemProperties.register(this, new ResourceLocation(FinsAndTails.MOD_ID, "variant"), (stack, world, player, i) -> stack.hasTag() ? stack.getTag().getInt("Variant") : 0)));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        BlockHitResult result = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.NONE);
        InteractionResultHolder<ItemStack> ret = ForgeEventFactory.onBucketUse(playerIn, worldIn, itemstack, result);
        if (ret != null) return ret;
        if (result.getType() == BlockHitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else if (result.getType() != BlockHitResult.Type.BLOCK) {
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

                    if (entityTypeSupplier instanceof FlatbackLeafSnailEntity || entityTypeSupplier instanceof RiverPebbleSnailEntity || entityTypeSupplier instanceof SiderolWhiskeredSnailEntity) {

                    }
                }

                playerIn.awardStat(Stats.ITEM_USED.get(this));
                return InteractionResultHolder.sidedSuccess(getEmptyItem(itemstack, playerIn), worldIn.isClientSide());
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (hasTooltip && stack.hasTag()) {
            tooltip.add(Component.translatable(getEntityType().getDescriptionId() + "." + stack.getTag().getInt("Variant")).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
    }

    private void placeEntity(ServerLevel worldIn, ItemStack stack, BlockPos pos) {
        Entity entity = this.entityTypeSupplier.get().spawn(worldIn, stack, null, pos, MobSpawnType.BUCKET, true, false);
        if (entity != null) {
            if (entity instanceof AbstractFish) {
                ((AbstractFish)entity).setFromBucket(true);
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
