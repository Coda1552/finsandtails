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
    public static int minimumTrunkHeight = 0;

    public LaminaTreeFeature(Codec<ProbabilityConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator chunkGenerator, Random random, BlockPos pos, ProbabilityConfig config) {
        ArrayList<Entry> filler = new ArrayList<>();

        int height = minimumTrunkHeight;

        // HEIGHT CALCULATION :nerd:
        for (int i = pos.getY(); i < reader.getSeaLevel(); i++) {
            boolean surface = reader.isWaterAt(pos.offset(0, 1, 0));

            if (surface) {
                height += 1;
            }
        }

        for (int i = 0; i < height; i += 2) {

            for (int j = 0; j < 4; j++) {
                boolean padSuccess = makeSmallPad(reader, pos.offset(0, i, 0), DIRECTIONS[j], random, i);
                if (!padSuccess) {
                    return false;
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < 4; j++) {
                int xOffset = j % 2;
                int zOffset = j / 2;
                BlockPos trunkPos = pos.offset(xOffset, i, zOffset);
                if (i == 0 && !canGrowTree(reader, trunkPos)) {
                    return false;
                }

                boolean success = filler.add(new Entry(trunkPos, STALK));
                if (!success) {
                    return false;
                }
            }
        }

        fill(reader, filler, false);
        return false;
    }

    public boolean makeSmallPad(ISeedReader reader, BlockPos pos, Direction direction, Random random, int height) {
        BlockPos offset = pos.offset(0, 1, 0);

        if (!canPlace(reader, pos)) return false;

        switch (direction) {

            // very scuffed
            case NORTH:
                setBlock(reader, offset.north().west(), PADS);
                setBlock(reader, offset.north(), PADS);
                setBlock(reader, offset.west(), PADS);

            case EAST:
                setBlock(reader, pos.north().east().east(), PADS);
                setBlock(reader, pos.north().east(), PADS);
                setBlock(reader, pos.east().east(), PADS);

            case SOUTH:
                setBlock(reader, pos.south().south().west(), PADS);
                setBlock(reader, pos.south().south(), PADS);
                setBlock(reader, pos.south().west(), PADS);

            case WEST:
                setBlock(reader, offset.south().south().east().east(), PADS);
                setBlock(reader, offset.south().south().east(), PADS);
                setBlock(reader, offset.south().east().east(), PADS);
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