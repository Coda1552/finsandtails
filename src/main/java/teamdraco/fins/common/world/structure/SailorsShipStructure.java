package teamdraco.fins.common.world.structure;

import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.BarrelTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.entities.*;
import teamdraco.fins.init.FinsEntities;
import teamdraco.fins.init.FinsStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


// TODO: Fix all of the crashes
public class SailorsShipStructure extends Structure<NoFeatureConfig> {
    private static final ResourceLocation SAILOR_LOOT = new ResourceLocation(FinsAndTails.MOD_ID, "gameplay/wandering_sailor_ship");

    private static final ResourceLocation OCEAN = new ResourceLocation(FinsAndTails.MOD_ID, "sailors_ship_ocean");
    private static final ResourceLocation COLD_OCEAN = new ResourceLocation(FinsAndTails.MOD_ID, "sailors_ship_cold_ocean");
    private static final ResourceLocation FROZEN_OCEAN = new ResourceLocation(FinsAndTails.MOD_ID, "sailors_ship_frozen_ocean");
    private static final ResourceLocation LUKEWARM_OCEAN = new ResourceLocation(FinsAndTails.MOD_ID, "sailors_ship_lukewarm_ocean");
    private static final ResourceLocation WARM_OCEAN = new ResourceLocation(FinsAndTails.MOD_ID, "sailors_ship_warm_ocean");
    private static final ResourceLocation SWAMP = new ResourceLocation(FinsAndTails.MOD_ID, "sailors_ship_swamp");

    public SailorsShipStructure(Codec<NoFeatureConfig> p_i231977_1_) {
        super(p_i231977_1_);
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeProvider biomeSource, long seed, SharedSeedRandom rand, int chunkPosX, int chunkPosZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        return chunkPosX > 4 && chunkPosZ > 4;
    }

    public static class Start extends StructureStart<NoFeatureConfig>  {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig config) {
            //String ocean = Biomes.OCEAN.getRegistryName().getNamespace();
            //String deepOcean = Biomes.DEEP_OCEAN.getRegistryName().getNamespace();
            //String coldOcean = Biomes.COLD_OCEAN.getRegistryName().getNamespace();
            //String deepColdOcean = Biomes.DEEP_COLD_OCEAN.getRegistryName().getNamespace();
            //String frozenOcean = Biomes.FROZEN_OCEAN.getRegistryName().getNamespace();
            //String deepFrozenOcean = Biomes.DEEP_FROZEN_OCEAN.getRegistryName().getNamespace();
            //String lukewarmOcean = Biomes.LUKEWARM_OCEAN.getRegistryName().getNamespace();
            //String deepLukewarmOcean = Biomes.DEEP_LUKEWARM_OCEAN.getRegistryName().getNamespace();
            //String warmOcean = Biomes.WARM_OCEAN.getRegistryName().getNamespace();
            //String swamp = Biomes.SWAMP.getRegistryName().getNamespace();

            ResourceLocation loc;
            String name = biome.getRegistryName().getPath();

            System.out.println(name);
            if (name.equals("ocean") || name.equals("deep_ocean")) {
                loc = OCEAN;
            }
            else if (name.equals("cold_ocean") || name.equals("deep_cold_ocean")) {
                loc = COLD_OCEAN;
            }
            else if (name.equals("frozen_ocean") || name.equals("deep_frozen_ocean")) {
                loc = FROZEN_OCEAN;
            }
            else if (name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean")) {
                loc = LUKEWARM_OCEAN;
            }
            else if (name.equals("warm_ocean")) {
                loc = WARM_OCEAN;
            }
            else if (name.equals("swamp")) {
                loc = SWAMP;
            }
            else {
                loc = OCEAN;
            }

            Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            int surfaceY = Math.max(generator.getFirstOccupiedHeight(x + 12, z + 12, Heightmap.Type.WORLD_SURFACE_WG), generator.getSpawnHeight());
            BlockPos blockpos = new BlockPos(x, surfaceY - 5, z);

            Piece.start(loc, templateManager, blockpos, rotation, this.pieces, this.random);
            this.calculateBoundingBox();
        }
    }

    public static class Piece extends TemplateStructurePiece {
        private ResourceLocation resourceLocation;
        private Rotation rotation;

        public Piece(TemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotationIn) {
            super(FinsStructures.SAILORS_SHIP_PIECE, 0);
            this.resourceLocation = resourceLocationIn;
            this.templatePosition = pos;
            this.rotation = rotationIn;
            this.setupPiece(templateManagerIn);
        }

        public Piece(TemplateManager templateManagerIn, CompoundNBT tagCompound) {
            super(FinsStructures.SAILORS_SHIP_PIECE, tagCompound);
            this.resourceLocation = new ResourceLocation(tagCompound.getString("Template"));
            this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
            this.setupPiece(templateManagerIn);
        }

        public static void start(ResourceLocation loc, TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList, Random random) {
            int x = pos.getX();
            int z = pos.getZ();
            BlockPos rotationOffSet = new BlockPos(0, 0, 0).rotate(rotation);
            BlockPos blockpos = rotationOffSet.offset(x, pos.getY(), z);
            pieceList.add(new Piece(templateManager, loc, blockpos, rotation));
        }

        private void setupPiece(TemplateManager templateManager) {
            Template template = templateManager.getOrCreate(this.resourceLocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
            this.setup(template, this.templatePosition, placementsettings);
        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT tagCompound) {
            super.addAdditionalSaveData(tagCompound);
            tagCompound.putString("Template", this.resourceLocation.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        @Override
        public boolean postProcess(ISeedReader seedReader, StructureManager structureManager, ChunkGenerator chunkGenerator, Random randomIn, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos pos) {
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_AND_AIR);
            BlockPos blockpos = BlockPos.ZERO;
            this.templatePosition.offset(Template.calculateRelativePosition(placementsettings, new BlockPos(-blockpos.getX(), -1, -blockpos.getZ())));
            return super.postProcess(seedReader, structureManager, chunkGenerator, randomIn, structureBoundingBoxIn, chunkPos, pos);
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {
            if ("barrel".equals(function)) {
                worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                TileEntity tileentity = worldIn.getBlockEntity(pos.below());
                if (tileentity instanceof BarrelTileEntity) {
                    ((BarrelTileEntity) tileentity).setLootTable(SAILOR_LOOT, rand.nextLong());
                }
            }
            if ("sailor".equals(function)) {
                worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                WanderingSailorEntity entity = FinsEntities.WANDERING_SAILOR.get().create(worldIn.getLevel());
                if (entity != null) {
                    entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                    entity.finalizeSpawn(worldIn, worldIn.getCurrentDifficultyAt(pos), SpawnReason.STRUCTURE, null, null);
                    worldIn.addFreshEntity(entity);
                }
            }
            if ("fish".equals(function)) {
                Map<Integer, MobEntity> map = Util.make(Maps.newHashMap(), (hashMap) -> {
                    hashMap.put(0, FinsEntities.NIGHT_LIGHT_SQUID.get().create(worldIn.getLevel()));
                    hashMap.put(1, FinsEntities.GOPJET.get().create(worldIn.getLevel()));
                    hashMap.put(2, FinsEntities.SPINDLY_GEM_CRAB.get().create(worldIn.getLevel()));
                    hashMap.put(3, FinsEntities.PHANTOM_NUDIBRANCH.get().create(worldIn.getLevel()));
                    hashMap.put(4, FinsEntities.ORNATE_BUGFISH.get().create(worldIn.getLevel()));
                });

                worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);

                int whichFish = rand.nextInt(5);

                MobEntity entity = map.get(whichFish);
                if (map.getOrDefault(whichFish, map.get(2)) != null) {
                    entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                    entity.finalizeSpawn(worldIn, worldIn.getCurrentDifficultyAt(pos), SpawnReason.STRUCTURE, null, null);

                    if (whichFish == 0) {
                        ((NightLightSquidEntity) entity).setVariant(rand.nextInt(4));
                    }
                    if (whichFish == 2) {
                        ((SpindlyGemCrabEntity) entity).setVariant(rand.nextInt(5));
                    }
                    switch (whichFish) {
                        case 0 : worldIn.addFreshEntity(map.get(0));
                            break;
                        case 1 : worldIn.addFreshEntity(map.get(1));
                            break;
                        case 2 : worldIn.addFreshEntity(map.get(2));
                            break;
                        case 3 : worldIn.addFreshEntity(map.get(3));
                            break;
                        case 4 : worldIn.addFreshEntity(map.get(4));
                            break;
                    }
                }
            }
        }
    }
}
