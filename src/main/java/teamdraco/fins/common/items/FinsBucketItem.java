package teamdraco.fins.common.items;

import teamdraco.fins.FinsAndTails;
import teamdraco.fins.init.FinsEntities;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.item.Item.Properties;

public class FinsBucketItem extends BucketItem {
    private final boolean hasTooltip;
    private final Supplier<? extends Fluid> fluid;

    public FinsBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Properties builder) {
        this(entityType, fluid, builder, true);
    }

    public FinsBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Properties builder, boolean hasTooltip) {
        super(fluid, builder);
        this.fluid = fluid;
        this.hasTooltip = hasTooltip;
        this.entityTypeSupplier = entityType;
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> FinsAndTails.CALLBACKS.add(() -> ItemModelsProperties.register(this, new ResourceLocation(FinsAndTails.MOD_ID, "variant"), (stack, world, player) -> stack.hasTag() ? stack.getTag().getInt("Variant") : 0)));
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        BlockRayTraceResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, RayTraceContext.FluidMode.NONE);
        ActionResult<ItemStack> ret = ForgeEventFactory.onBucketUse(playerIn, worldIn, itemstack, raytraceresult);
        if (ret != null) return ret;
        if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(itemstack);
        } else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.pass(itemstack);
        } else {
            BlockPos blockpos = raytraceresult.getBlockPos();
            Direction direction = raytraceresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos1, direction, itemstack)) {
                BlockState blockstate = worldIn.getBlockState(blockpos);
                BlockPos blockpos2 = blockstate.getBlock() instanceof ILiquidContainer && ((ILiquidContainer) blockstate.getBlock()).canPlaceLiquid(worldIn, blockpos, blockstate, fluid.get()) ? blockpos : blockpos1;
                this.emptyBucket(playerIn, worldIn, blockpos2, raytraceresult);
                if (worldIn instanceof ServerWorld) this.placeEntity((ServerWorld)worldIn, itemstack, blockpos2);
                if (playerIn instanceof ServerPlayerEntity) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerIn, blockpos2, itemstack);
                }

                playerIn.awardStat(Stats.ITEM_USED.get(this));
                return ActionResult.sidedSuccess(this.getEmptySuccessItem(itemstack, playerIn), worldIn.isClientSide());
            } else {
                return ActionResult.fail(itemstack);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (hasTooltip && stack.hasTag()) {
            tooltip.add(new TranslationTextComponent(getEntityType().getDescriptionId() + "." + stack.getTag().getInt("Variant")).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
        }
    }

    private void placeEntity(ServerWorld worldIn, ItemStack stack, BlockPos pos) {
        Entity entity = this.entityTypeSupplier.get().spawn(worldIn, stack, null, pos, SpawnReason.BUCKET, true, false);
        if (entity != null) {
            if (entity instanceof AbstractFishEntity) {
                ((AbstractFishEntity)entity).setFromBucket(true);
            }
        }
    }

    private final java.util.function.Supplier<? extends EntityType<?>> entityTypeSupplier;
    protected EntityType<?> getEntityType() {
        return entityTypeSupplier.get();
    }

    @Override
    protected ItemStack getEmptySuccessItem(ItemStack stack, PlayerEntity player) {
        if (entityTypeSupplier == FinsEntities.FLATBACK_LEAF_SNAIL || entityTypeSupplier == FinsEntities.SIDEROL_WHISKERED_SNAIL || entityTypeSupplier == FinsEntities.RIVER_PEBBLE_SNAIL) {
            return !player.abilities.instabuild ? new ItemStack(Items.FLOWER_POT) : stack;
        }
        else {
            return !player.abilities.instabuild ? new ItemStack(Items.BUCKET) : stack;
        }
    }
}
