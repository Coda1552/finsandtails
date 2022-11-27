package teamdraco.finsandstails.common.entities.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CrownedHorateeNavigator extends GroundAndSwimmerNavigator {

    public CrownedHorateeNavigator(PathfinderMob entity, Level world) {
        super(entity, world);
    }

    @Override
    public boolean isStableDestination(BlockPos pos) {
        BlockPos blockPos = pos.below();
        BlockState state = this.level.getBlockState(blockPos);
        return this.level.getBlockState(pos).is(Blocks.WATER) && !this.mob.isOnGround() || !state.isAir();
    }
}
