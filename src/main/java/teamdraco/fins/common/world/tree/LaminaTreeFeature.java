package teamdraco.fins.common.world.tree;

import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import teamdraco.fins.init.FinsBlocks;

import java.util.ArrayList;
import java.util.Random;

public class LaminaTreeFeature extends Feature<NoFeatureConfig> {
    private static final BlockState STALK = FinsBlocks.LAMINA_STALK.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);
    private static final BlockState PADS = FinsBlocks.LAMINA_PADS.get().defaultBlockState();

    public static final Direction[] DIRECTIONS = new Direction[]{Direction.WEST, Direction.NORTH, Direction.SOUTH, Direction.EAST};

    //trunk placement
    public static int minimumTrunkHeight = 4;
    public static int trunkHeightExtra = 1;

    //branches on the trunk placement
    public static int minimumBranchHeight = 2;
    public static int branchHeightExtra = 0;

    //branches on the top trunk placement
    public static int minimumTopBranchHeight = 1;
    public static int topBranchHeightExtra = 0;

    public LaminaTreeFeature()
    {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    public boolean place(ISeedReader iSeedReader, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, NoFeatureConfig noFeatureConfig) {
        ArrayList<Entry> filler = new ArrayList<>();
        ArrayList<Entry> leavesFiller = new ArrayList<>();
        int trunkHeight = minimumTrunkHeight + random.nextInt(trunkHeightExtra + 1);
        for (int i = 0; i < trunkHeight; i++) {
            for (int j = 0; j < 4; j++) {
                int xOffset = j % 2;
                int zOffset = j / 2;
                BlockPos trunkPos = blockPos.offset(xOffset, i, zOffset);
                if (i == 0 && !canGrowTree(iSeedReader, trunkPos)) {
                    return false;
                }
                boolean success = makeSlice(filler, iSeedReader, trunkPos, 1);
                if (!success) {
                    return false;
                }
                if (i == trunkHeight - 1) {
                    BlockPos branchPos = trunkPos.relative(DIRECTIONS[j].getOpposite(), 3);
                    success = makeBranch(filler, leavesFiller, iSeedReader, branchPos, minimumBranchHeight + random.nextInt(branchHeightExtra + 1));
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        // int trunkTopHeight = minimumTrunkTopHeight + random.nextInt(trunkTopHeightExtra + 1);
        int trunkTopHeight = 1;

        for (int i = 0; i < trunkTopHeight; i++) {
            int yOffset = trunkHeight + i;
            for (int j = 0; j < 4; j++) {
                int xOffset = j % 2;
                int zOffset = j / 2;
                BlockPos trunkTopPos = blockPos.offset(xOffset, yOffset, zOffset);

                if (!canPlace(iSeedReader, trunkTopPos)) {
                    return false;
                }
                filler.add(new Entry(trunkTopPos, STALK));
                if (i == trunkTopHeight - 1) {
                    int branchHeight = minimumTopBranchHeight + random.nextInt(topBranchHeightExtra + 1);

                    boolean success = makeBranch(filler, leavesFiller, iSeedReader, trunkTopPos.relative(DIRECTIONS[j]).above(), branchHeight);
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        fill(iSeedReader, filler, false);
        fill(iSeedReader, leavesFiller, true);
        return false;
    }

    public static boolean makeBranch(ArrayList<Entry> filler, ArrayList<Entry> leavesFiller, ISeedReader reader, BlockPos pos, int height) {
        for (int k = 0; k < height; k++) {
            BlockPos branchPos = pos.above(k);
            if (!canPlace(reader, branchPos)) {
                return false;
            }
            filler.add(new Entry(branchPos, STALK));
            if (k == height - 1) {
                for (Direction direction : DIRECTIONS) {
                    BlockPos leavesPos =  branchPos.relative(direction);
                    leavesFiller.add(new Entry(leavesPos, PADS));
                }
                leavesFiller.add(new Entry(branchPos.above(), PADS));

            }
        }
        return true;
    }

    public static boolean makeSlice(ArrayList<Entry> filler, ISeedReader reader, BlockPos pos, int sliceSize) {
        for (int x = -sliceSize; x <= sliceSize; x++) {
            for (int z = -sliceSize; z <= sliceSize; z++) {
                if (Math.abs(x) == sliceSize && Math.abs(z) == sliceSize) {
                    continue;
                }
                BlockPos slicePos = new BlockPos(pos).offset(x, 0, z);
                if (!canPlace(reader, slicePos)) {
                    return false;
                }
                filler.add(new Entry(slicePos, STALK));
            }
        }
        return true;
    }

    public static void fill(ISeedReader reader, ArrayList<Entry> filler, boolean careful) {
        for (Entry entry : filler) {
            if (careful && !canPlace(reader, entry.pos)) {
                continue;
            }
            reader.setBlock(entry.pos, entry.state, 3);
        }
    }

    public static boolean canGrowTree(ISeedReader reader, BlockPos pos) {
        if (!reader.getBlockState(pos.below()).getBlock().equals(Blocks.SAND)) {
            return false;
        }
        for (Direction direction : DIRECTIONS) {
            BlockPos sandPos = pos.below().relative(direction);
            if (!reader.getBlockState(sandPos).getBlock().equals(Blocks.SAND)) {
                return false;
            }
        }
        return canPlace(reader, pos);
    }

    public static boolean canPlace(ISeedReader reader, BlockPos pos) {
        //todo implement some more proper 'is outside of world' check, mekanism has one
        if (pos.getY() > reader.getMaxBuildHeight() || pos.getY() < 0) {
            return false;
        }
        return !reader.getFluidState(pos).isEmpty();
    }

    public static class Entry {
        public final BlockPos pos;
        public final BlockState state;

        public Entry(BlockPos pos, BlockState state) {
            this.pos = pos;
            this.state = state;
        }
    }
}