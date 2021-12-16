package teamdraco.fins.common.world.tree;

import com.mojang.serialization.Codec;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import teamdraco.fins.init.FinsBlocks;

import java.util.ArrayList;
import java.util.Random;

public class LaminaTreeFeature extends Feature<ProbabilityConfig> {
    private static final BlockState STALK = FinsBlocks.LAMINA_STALK.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);
    private static final BlockState WATERLOGGED_PADS = FinsBlocks.LAMINA_PADS.get().defaultBlockState().setValue(SlabBlock.WATERLOGGED, true);
    private static final BlockState PADS = FinsBlocks.LAMINA_PADS.get().defaultBlockState();

    public static final Direction[] DIRECTIONS = new Direction[]{Direction.WEST, Direction.NORTH, Direction.SOUTH, Direction.EAST};

    //trunk placement
    public static int minimumTrunkHeight = 6;
    public static int trunkHeightExtra = 2;

    public LaminaTreeFeature(Codec<ProbabilityConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, ProbabilityConfig config) {
        ArrayList<Entry> filler = new ArrayList<>();
        ArrayList<Entry> leavesFiller = new ArrayList<>();
        int trunkHeight = minimumTrunkHeight + random.nextInt(trunkHeightExtra + 1);
        for (int i = 0; i < trunkHeight; i++) {
            for (int j = 0; j < 4; j++) {
                int xOffset = j % 2;
                int zOffset = j / 2;
                BlockPos trunkPos = blockPos.offset(xOffset, i, zOffset);
                if (i == 0 && !canGrowTree(reader, trunkPos)) {
                    return false;
                }
                boolean success = filler.add(new Entry(trunkPos, STALK));
                if (!success) {
                    return false;
                }

                int leavesHeight = trunkHeight;

                BlockPos leavesPos = blockPos.offset(2, leavesHeight, 2).relative(DIRECTIONS[j]);
                boolean leavesSuccess = makeLeavesSlice(leavesFiller, reader, leavesPos, 2);

                if (!leavesSuccess) {
                    return false;
                }
            }
        }

        fill(reader, filler, false);
        fill(reader, leavesFiller, true);
        return false;
    }

    public static boolean makeLeavesSlice(ArrayList<Entry> filler, ISeedReader reader, BlockPos pos, int sliceSize) {
        for (int x = -sliceSize; x <= sliceSize; x++) {
            for (int z = -sliceSize; z <= sliceSize; z++) {
                if (Math.abs(x) == sliceSize && Math.abs(z) == sliceSize) {
                    continue;
                }
                int xOffset = x % 2;
                int zOffset = z / 2;

                BlockPos slicePos = new BlockPos(pos).offset(xOffset, 0, zOffset);

                if (!canPlace(reader, slicePos)) {
                    return false;
                }
                filler.add(new Entry(slicePos, PADS));
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
        BlockPos sandPos = pos.below();

        if (!reader.getBlockState(sandPos).getBlock().equals(Blocks.SAND)) {
            return false;
        }
        if (!reader.getBlockState(sandPos).getBlock().equals(Blocks.SAND)) {
            return false;
        }
        return canPlace(reader, pos);
    }

    public static boolean canPlace(ISeedReader reader, BlockPos pos) {
        //todo implement some more proper 'is outside of world' check, mekanism has one
        if (pos.getY() > reader.getMaxBuildHeight() || pos.getY() < 0) {
            return false;
        }
        return reader.isWaterAt(pos) || reader.getBlockState(pos).isAir();
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