package mod.coda.fins.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class FinsBucketItem extends FishBucketItem {
    private final boolean hasTooltip;
    private final Supplier<? extends Fluid> fluid;

    public FinsBucketItem(Supplier<? extends EntityType<?>> fishType, Supplier<? extends Fluid> fluid, Properties builder) {
        this(fishType, fluid, builder, true);
    }

    public FinsBucketItem(Supplier<? extends EntityType<?>> fishType, Supplier<? extends Fluid> fluid, Properties builder, boolean hasTooltip) {
        super(fishType, fluid, builder);
        this.fluid = fluid;
        this.hasTooltip = hasTooltip;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        BlockRayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.NONE);
        ActionResult<ItemStack> ret = ForgeEventFactory.onBucketUse(playerIn, worldIn, itemstack, raytraceresult);
        if (ret != null) return ret;
        if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.resultPass(itemstack);
        } else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.resultPass(itemstack);
        } else {
            BlockPos blockpos = raytraceresult.getPos();
            Direction direction = raytraceresult.getFace();
            BlockPos blockpos1 = blockpos.offset(direction);
            if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos1, direction, itemstack)) {
                BlockState blockstate = worldIn.getBlockState(blockpos);
                BlockPos blockpos2 = blockstate.getBlock() instanceof ILiquidContainer && ((ILiquidContainer) blockstate.getBlock()).canContainFluid(worldIn, blockpos, blockstate, fluid.get()) ? blockpos : blockpos1;
                this.tryPlaceContainedLiquid(playerIn, worldIn, blockpos2, raytraceresult);
                this.onLiquidPlaced(worldIn, itemstack, blockpos2);
                if (playerIn instanceof ServerPlayerEntity) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerIn, blockpos2, itemstack);
                }

                playerIn.addStat(Stats.ITEM_USED.get(this));
                return ActionResult.func_233538_a_(this.emptyBucket(itemstack, playerIn), worldIn.isRemote());
            } else {
                return ActionResult.resultFail(itemstack);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        if (hasTooltip && stack.hasTag()) {
            tooltip.add(new TranslationTextComponent(getFishType().getTranslationKey() + "." + stack.getTag().getInt("BucketVariantTag")).mergeStyle(TextFormatting.GRAY).mergeStyle(TextFormatting.ITALIC));
        }
    }
}
