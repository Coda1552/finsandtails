package teamdraco.finsandstails.common.entities.util.goals;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.JumpGoal;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import teamdraco.finsandstails.common.entities.SwampMuckerEntity;

public class SwampMuckerJumpGoal extends JumpGoal {
    private static final int[] JUMP_DISTANCES = new int[]{0, 1, 4, 5, 6, 7};
    private final SwampMuckerEntity swampMucker;
    private final int interval;
    private boolean breached;

    public SwampMuckerJumpGoal(SwampMuckerEntity p_i50329_1_, int p_i50329_2_) {
        this.swampMucker = p_i50329_1_;
        this.interval = p_i50329_2_;
    }

    public boolean canUse() {
        if (this.swampMucker.getRandom().nextInt(this.interval) != 0) {
            return false;
        } else {
            Direction direction = this.swampMucker.getMotionDirection();
            int i = direction.getStepX();
            int j = direction.getStepZ();
            BlockPos blockpos = this.swampMucker.blockPosition();

            for(int k : JUMP_DISTANCES) {
                if (!this.canJumpTo(blockpos, i, j, k) || !this.isAirAbove(blockpos, i, j, k)) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean canJumpTo(BlockPos pos, int dx, int dz, int scale) {
        BlockPos blockpos = pos.offset(dx * scale, 0, dz * scale);
        return this.swampMucker.level.getFluidState(blockpos).is(FluidTags.WATER) && !this.swampMucker.level.getBlockState(blockpos).getMaterial().blocksMotion();
    }

    private boolean isAirAbove(BlockPos pos, int dx, int dz, int scale) {
        return this.swampMucker.level.getBlockState(pos.offset(dx * scale, 1, dz * scale)).isAir() && this.swampMucker.level.getBlockState(pos.offset(dx * scale, 2, dz * scale)).isAir();
    }

    public boolean canContinueToUse() {
        double d0 = this.swampMucker.getDeltaMovement().y;
        return (!(d0 * d0 < (double)0.03F) || this.swampMucker.xRot == 0.0F || !(Math.abs(this.swampMucker.xRot) < 10.0F) || !this.swampMucker.isInWater()) && !this.swampMucker.isOnGround();
    }

    public boolean isInterruptable() {
        return false;
    }

    public void start() {
        Direction direction = this.swampMucker.getMotionDirection();
        this.swampMucker.setDeltaMovement(this.swampMucker.getDeltaMovement().add((double)direction.getStepX() * 0.6D, 0.7D, (double)direction.getStepZ() * 0.6D));
        this.swampMucker.getNavigation().stop();
    }

    public void stop() {
        this.swampMucker.xRot = 0.0F;
    }

    public void tick() {
        boolean flag = this.breached;
        if (!flag) {
            FluidState ifluidstate = this.swampMucker.level.getFluidState(new BlockPos(this.swampMucker.blockPosition()));
            this.breached = ifluidstate.is(FluidTags.WATER);
        }

        if (this.breached && !flag) {
            this.swampMucker.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
        }

        Vector3d vector3d = this.swampMucker.getDeltaMovement();
        if (vector3d.y * vector3d.y < (double)0.03F && this.swampMucker.xRot != 0.0F) {
            this.swampMucker.xRot = MathHelper.rotlerp(this.swampMucker.xRot, 0.0F, 0.2F);
        } else {
            double d0 = Math.sqrt(Entity.getHorizontalDistanceSqr(vector3d));
            double d1 = Math.signum(-vector3d.y) * Math.acos(d0 / vector3d.length()) * (double)(180F / (float)Math.PI);
            this.swampMucker.xRot = (float)d1;
        }

    }
}
