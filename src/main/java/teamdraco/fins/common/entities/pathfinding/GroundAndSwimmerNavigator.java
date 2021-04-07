package teamdraco.fins.common.entities.pathfinding;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.pathfinding.WalkAndSwimNodeProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GroundAndSwimmerNavigator extends SwimmerPathNavigator {
    public GroundAndSwimmerNavigator(MobEntity entity, World world) {
        super(entity, world);
    }

    @Override
    protected boolean canNavigate() {
        return true;
    }

    @Override
    protected PathFinder getPathFinder(int p_179679_1_) {
        this.nodeProcessor = new WalkAndSwimNodeProcessor();
        return new PathFinder(this.nodeProcessor, p_179679_1_);
    }

    @Override
    public boolean canEntityStandOnPos(BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockState state = this.world.getBlockState(blockPos);
        return this.world.getBlockState(pos).isIn(Blocks.WATER) || !state.getBlock().isAir(state, world, blockPos);
    }
}
