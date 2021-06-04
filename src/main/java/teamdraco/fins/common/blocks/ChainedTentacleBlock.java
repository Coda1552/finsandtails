package teamdraco.fins.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChainBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import net.minecraft.block.AbstractBlock.Properties;

public class ChainedTentacleBlock extends ChainBlock {
    public ChainedTentacleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 12;
    }
}
