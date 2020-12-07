package mod.coda.fins.entity.ai;

import mod.coda.fins.entity.SwampMuckerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.JumpGoal;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

public class SwampMuckerJumpGoal extends JumpGoal {
    private static final int[] JUMP_DISTANCES = new int[]{0, 1, 4, 5, 6, 7};
    private final SwampMuckerEntity swampMucker;
    private final int field_220712_c;
    private boolean field_220713_d;

    public SwampMuckerJumpGoal(SwampMuckerEntity p_i50329_1_, int p_i50329_2_) {
        this.swampMucker = p_i50329_1_;
        this.field_220712_c = p_i50329_2_;
    }

    public boolean shouldExecute() {
        if (this.swampMucker.getRNG().nextInt(this.field_220712_c) != 0) {
            return false;
        } else {
            Direction direction = this.swampMucker.getAdjustedHorizontalFacing();
            int i = direction.getXOffset();
            int j = direction.getZOffset();
            BlockPos blockpos = this.swampMucker.getPosition();

            for(int k : JUMP_DISTANCES) {
                if (!this.canJumpTo(blockpos, i, j, k) || !this.isAirAbove(blockpos, i, j, k)) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean canJumpTo(BlockPos pos, int dx, int dz, int scale) {
        BlockPos blockpos = pos.add(dx * scale, 0, dz * scale);
        return this.swampMucker.world.getFluidState(blockpos).isTagged(FluidTags.WATER) && !this.swampMucker.world.getBlockState(blockpos).getMaterial().blocksMovement();
    }

    private boolean isAirAbove(BlockPos pos, int dx, int dz, int scale) {
        return this.swampMucker.world.getBlockState(pos.add(dx * scale, 1, dz * scale)).isAir() && this.swampMucker.world.getBlockState(pos.add(dx * scale, 2, dz * scale)).isAir();
    }

    public boolean shouldContinueExecuting() {
        double d0 = this.swampMucker.getMotion().y;
        return (!(d0 * d0 < (double)0.03F) || this.swampMucker.rotationPitch == 0.0F || !(Math.abs(this.swampMucker.rotationPitch) < 10.0F) || !this.swampMucker.isInWater()) && !this.swampMucker.isOnGround();
    }

    public boolean isPreemptible() {
        return false;
    }

    public void startExecuting() {
        Direction direction = this.swampMucker.getAdjustedHorizontalFacing();
        this.swampMucker.setMotion(this.swampMucker.getMotion().add((double)direction.getXOffset() * 0.6D, 0.7D, (double)direction.getZOffset() * 0.6D));
        this.swampMucker.getNavigator().clearPath();
    }

    public void resetTask() {
        this.swampMucker.rotationPitch = 0.0F;
    }

    public void tick() {
        boolean flag = this.field_220713_d;
        if (!flag) {
            FluidState ifluidstate = this.swampMucker.world.getFluidState(new BlockPos(this.swampMucker.getPosition()));
            this.field_220713_d = ifluidstate.isTagged(FluidTags.WATER);
        }

        if (this.field_220713_d && !flag) {
            this.swampMucker.playSound(SoundEvents.ENTITY_DOLPHIN_JUMP, 1.0F, 1.0F);
        }

        Vector3d vector3d = this.swampMucker.getMotion();
        if (vector3d.y * vector3d.y < (double)0.03F && this.swampMucker.rotationPitch != 0.0F) {
            this.swampMucker.rotationPitch = MathHelper.rotLerp(this.swampMucker.rotationPitch, 0.0F, 0.2F);
        } else {
            double d0 = Math.sqrt(Entity.horizontalMag(vector3d));
            double d1 = Math.signum(-vector3d.y) * Math.acos(d0 / vector3d.length()) * (double)(180F / (float)Math.PI);
            this.swampMucker.rotationPitch = (float)d1;
        }

    }
}
