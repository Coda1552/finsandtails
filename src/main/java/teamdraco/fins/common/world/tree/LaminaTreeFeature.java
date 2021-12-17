package teamdraco.fins.common.world.tree;

import net.minecraft.block.*;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import teamdraco.fins.init.FinsBlocks;

import java.util.ArrayList;
import java.util.Random;

public class LaminaTreeFeature extends Feature<BaseTreeFeatureConfig> {

    public static final Direction[] DIRECTIONS = new Direction[]{Direction.WEST, Direction.NORTH, Direction.SOUTH, Direction.EAST};

    //trunk placement
    public static int minimumTrunkHeight = 0;

    public LaminaTreeFeature() {
        super(BaseTreeFeatureConfig.CODEC);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator chunkGenerator, Random random, BlockPos pos, BaseTreeFeatureConfig config) {
        ArrayList<Entry> filler = new ArrayList<>();
        ArrayList<Entry> leavesFiller = new ArrayList<>();
        BlockState STALK = FinsBlocks.LAMINA_STALK.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);

        int height = minimumTrunkHeight;

        // HEIGHT CALCULATION :nerd:
        for (int i = pos.getY(); i < reader.getSeaLevel(); i++) {
            boolean surface = reader.isWaterAt(pos.offset(0, 1, 0));

            if (surface) {
                height += 1;
            }
        }

        for (int i = 3; i < height - 2; i += 2) {
            for (int j = 0; j < 4; j++) {
                boolean smallPadSuccess = makeSmallPad(reader, pos.offset(0, i, 0), DIRECTIONS[j]);

                if (!smallPadSuccess) {
                    return false;
                }
            }
        }


        for (int i = 3; i < height - 2; i += 4) {
            for (int j = 0; j < 4; j++) {
                boolean bigPadSuccess = makeBigPad(reader, pos.offset(0, i, 0), DIRECTIONS[j]);

                if (!bigPadSuccess) {
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

            if (i == height - 1) {
                makeTopPad(reader, pos, height);
            }
        }

        fill(reader, filler, false);
        fill(reader, leavesFiller, false);
        return false;
    }

    public boolean makeSmallPad(ISeedReader reader, BlockPos pos, Direction direction) {
        BlockState PADS = FinsBlocks.LAMINA_PADS.get().defaultBlockState();
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

    public boolean makeBigPad(ISeedReader reader, BlockPos pos, Direction direction) {
        BlockState PADS = FinsBlocks.LAMINA_PADS.get().defaultBlockState();
        BlockPos offset = pos.offset(0, 2, 0);

        if (!canPlace(reader, pos)) return false;

        switch (direction) {

            // very scuffed
            case NORTH:
                setBlock(reader, offset.north().west(), PADS);
                setBlock(reader, offset.north(), PADS);
                setBlock(reader, offset.west(), PADS);

                setBlock(reader, offset.north(2), PADS);
                setBlock(reader, offset.north(2).west(), PADS);
                setBlock(reader, offset.north().west(2), PADS);
                setBlock(reader, offset.west(2), PADS);

            case EAST:
                setBlock(reader, pos.north().east().east(), PADS);
                setBlock(reader, pos.north().east(), PADS);
                setBlock(reader, pos.east().east(), PADS);

            case SOUTH:
                setBlock(reader, pos.south().south().west(), PADS);
                setBlock(reader, pos.south().south(), PADS);
                setBlock(reader, pos.south().west(), PADS);

                setBlock(reader, offset.south(2), PADS);
                setBlock(reader, offset.south(2).west(), PADS);
                setBlock(reader, offset.south().west(), PADS);
                setBlock(reader, offset.west(2), PADS);

            case WEST:
                setBlock(reader, offset.south().south().east().east(), PADS);
                setBlock(reader, offset.south().south().east(), PADS);
                setBlock(reader, offset.south().east().east(), PADS);

                setBlock(reader, offset.south(3).east(), PADS);
                setBlock(reader, offset.south(3).east(2), PADS);
                setBlock(reader, offset.south(2).east(3), PADS);
                setBlock(reader, offset.south().east(3), PADS);

        }
        return true;
    }

    public boolean makeTopPad(ISeedReader reader, BlockPos pos, int height) {
        BlockState PADS = FinsBlocks.LAMINA_PADS.get().defaultBlockState();
        if (!canPlace(reader, pos)) return false;

        int topSize = 3;
        int notQuiteTopSize = 4;

        for (int i = -topSize; i < topSize; i++) {
            for (int j = -topSize; j < topSize; j++) {
                if (Math.abs(i) == topSize && Math.abs(j) == topSize) {
                    continue;
                }
                BlockPos offset = new BlockPos(pos).south().east().offset(i, height, j);
                if (!canPlace(reader, offset)) {
                    return false;
                }
                reader.setBlock(offset, PADS, 2);

                reader.setBlock(pos.offset(3, height, 3), Blocks.AIR.defaultBlockState(), 2);
                reader.setBlock(pos.offset(-2, height, 3), Blocks.AIR.defaultBlockState(), 2);
                reader.setBlock(pos.offset(3, height, -2), Blocks.AIR.defaultBlockState(), 2);
                reader.setBlock(pos.offset(3, height, 3), Blocks.AIR.defaultBlockState(), 2);

            }
        }

        for (int i = -notQuiteTopSize; i < notQuiteTopSize; i++) {
            for (int j = -notQuiteTopSize; j < notQuiteTopSize; j++) {
                if (Math.abs(i) == notQuiteTopSize && Math.abs(j) == notQuiteTopSize) {
                    continue;
                }

                BlockPos offset = new BlockPos(pos).south().east().below().offset(i, height, j);
                if (!canPlace(reader, offset)) {
                    return false;
                }
                reader.setBlock(offset, PADS.setValue(SlabBlock.TYPE, SlabType.TOP), 2);

                reader.setBlock(pos.offset(4, height - 1, 4), Blocks.AIR.defaultBlockState(), 2);
                reader.setBlock(pos.offset(-3, height - 1, 4), Blocks.AIR.defaultBlockState(), 2);
                reader.setBlock(pos.offset(4, height - 1, -3), Blocks.AIR.defaultBlockState(), 2);
                reader.setBlock(pos.offset(4, height - 1, 4), Blocks.AIR.defaultBlockState(), 2);
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