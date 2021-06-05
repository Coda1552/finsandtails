package teamdraco.fins.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import teamdraco.fins.common.entities.WherbleEntity;

import java.util.function.Supplier;

public class BabyWherblePotItem extends FinsBucketItem {
    private final java.util.function.Supplier<? extends EntityType<?>> entityTypeSupplier;
    private final Supplier<? extends Fluid> fluid;

    public BabyWherblePotItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Properties builder) {
        super(entityType, fluid, builder);
        this.entityTypeSupplier = entityType;
        this.fluid = fluid;
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

    private void placeEntity(ServerWorld worldIn, ItemStack stack, BlockPos pos) {
        Entity entity = this.entityTypeSupplier.get().spawn(worldIn, stack, (PlayerEntity)null, pos, SpawnReason.BUCKET, true, false);
        if (entity != null) {
            if (entity instanceof WherbleEntity) {
                ((WherbleEntity)entity).setAge(-24000);
                ((WherbleEntity)entity).setVariant(random.nextInt(4));
            }
        }
    }

    protected EntityType<?> getEntityType() {
        return entityTypeSupplier.get();
    }

    @Override
    protected ItemStack getEmptySuccessItem(ItemStack stack, PlayerEntity player) {
        return !player.abilities.instabuild ? new ItemStack(Items.BUCKET) : stack;
    }
}
