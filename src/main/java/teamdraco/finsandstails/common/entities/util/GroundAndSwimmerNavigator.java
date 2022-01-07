package teamdraco.finsandstails.common.entities.util;

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
    protected boolean canUpdatePath() {
        return true;
    }

    @Override
    protected PathFinder createPathFinder(int p_179679_1_) {
        this.nodeEvaluator = new WalkAndSwimNodeProcessor();
        return new PathFinder(this.nodeEvaluator, p_179679_1_);
    }

    @Override
    public boolean isStableDestination(BlockPos pos) {
        BlockPos blockPos = pos.below();
        BlockState state = this.level.getBlockState(blockPos);
        return this.level.getBlockState(pos).is(Blocks.WATER) || !state.getBlock().isAir(state, level, blockPos);
    }
}
