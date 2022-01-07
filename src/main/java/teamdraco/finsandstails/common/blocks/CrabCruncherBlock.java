package teamdraco.finsandstails.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.container.CrabCruncherContainer;

import java.util.Properties;

public class CrabCruncherBlock extends Block {
    private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container." + FinsAndTails.MOD_ID + "crab_cruncher");

    public CrabCruncherBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide) {
            NetworkHooks.openGui((ServerPlayerEntity) player, state.getMenuProvider(worldIn, pos));
            return ActionResultType.CONSUME;
        } else {
            return ActionResultType.SUCCESS;
        }
    }

    @Override
    public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) -> new CrabCruncherContainer(id, inventory, IWorldPosCallable.create(worldIn, pos)), CONTAINER_NAME);
    }

    @Override
    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof IInventory) {
                InventoryHelper.dropContents(worldIn, pos, (IInventory)tileentity);
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }


/*    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack heldItem = player.getHeldItem(handIn);
        if(!worldIn.isRemote) {
            if(heldItem.getItem() == FinsItems.SPINDLY_GEM_CRAB.get()) {
                player.playSound(FinsSounds.CRAB_CRUNCH.get(), SoundCategory.BLOCKS, 1.0f, 0.4f);
                if(!player.isCreative()) heldItem.shrink(1);
                player.swing(handIn, true);
                if (this.rand.nextInt(3) == 0) {
                    ItemEntity itemEntity = new ItemEntity(worldIn.getServer().getWorld(worldIn.getDimensionKey()), (double) pos.getX() + 0.5D, (double) (pos.getY() + 1), (double) pos.getZ() + 0.5D, new ItemStack(FinsItems.SPINDLY_GEM_CRAB_GEM.get(), 1));
                    worldIn.getServer().getWorld(worldIn.getDimensionKey()).addEntity(itemEntity);
                }
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }*/
}
