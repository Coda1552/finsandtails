package teamdraco.fins.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import teamdraco.fins.init.FinsItems;
import teamdraco.fins.init.FinsSounds;

import java.util.Random;

public class CrabCruncherBlock extends Block {
    protected final Random rand = new Random();

    public CrabCruncherBlock(Properties properties) {
        super(properties);
    }

    @Override
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
    }
}
