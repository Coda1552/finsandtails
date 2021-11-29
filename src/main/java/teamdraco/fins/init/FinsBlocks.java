package teamdraco.fins.init;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.blocks.ChainedTentacleBlock;
import teamdraco.fins.common.blocks.CrabCruncherBlock;

public class FinsBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, FinsAndTails.MOD_ID);

    public static final RegistryObject<Block> CRAB_CRUNCHER = REGISTER.register("crab_cruncher", () -> new CrabCruncherBlock(AbstractBlock.Properties.of(Material.STONE).strength(5.0f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SPINDLY_GEM_CRAB_GEM_BLOCK = REGISTER.register("spindly_gem_crab_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SPINDLY_EMERALD_BLOCK = REGISTER.register("spindly_emerald_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SPINDLY_PEARL_BLOCK = REGISTER.register("spindly_pearl_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SPINDLY_SAPPHIRE_BLOCK = REGISTER.register("spindly_sapphire_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SPINDLY_RUBY_BLOCK = REGISTER.register("spindly_ruby_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SPINDLY_AMBER_BLOCK = REGISTER.register("spindly_amber_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> CHISELED_FLATBACK_SHELL_BRICKS = REGISTER.register("chiseled_flatback_shell_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> FLATBACK_SHELL_BLOCK = REGISTER.register("flatback_shell_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> FLATBACK_SHELL_BRICK_SLAB = REGISTER.register("flatback_shell_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> FLATBACK_SHELL_BRICK_STAIRS = REGISTER.register("flatback_shell_brick_stairs", () -> new StairsBlock(() -> FinsBlocks.FLATBACK_SHELL_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> FLATBACK_SHELL_BRICKS = REGISTER.register("flatback_shell_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> FLATBACK_SHELL_SLAB = REGISTER.register("flatback_shell_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> FLATBACK_SHELL_STAIRS = REGISTER.register("flatback_shell_stairs", () -> new StairsBlock(() -> FinsBlocks.FLATBACK_SHELL_BLOCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> CHISELED_PEBBLE_SHELL_BRICKS = REGISTER.register("chiseled_pebble_shell_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> PEBBLE_SHELL_BRICK_SLAB = REGISTER.register("pebble_shell_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> PEBBLE_SHELL_BRICK_STAIRS = REGISTER.register("pebble_shell_brick_stairs", () -> new StairsBlock(() -> FinsBlocks.PEBBLE_SHELL_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> PEBBLE_SHELL_BRICKS = REGISTER.register("pebble_shell_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> PEBBLE_SHELL_PILLAR = REGISTER.register("pebble_shell_pillar", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> CHISELED_SIDEROL_SHELL_BRICKS = REGISTER.register("chiseled_siderol_shell_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> SIDEROL_SHELL_BRICK_SLAB = REGISTER.register("siderol_shell_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> SIDEROL_SHELL_BRICK_STAIRS = REGISTER.register("siderol_shell_brick_stairs", () -> new StairsBlock(() -> FinsBlocks.SIDEROL_SHELL_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> SIDEROL_SHELL_BRICKS = REGISTER.register("siderol_shell_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> MIXED_FLATBACK_SHELL_BRICKS = REGISTER.register("mixed_flatback_shell_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> MIXED_PEBBLE_SHELL_BRICKS = REGISTER.register("mixed_pebble_shell_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> MIXED_PEBBLE_SHELL_BRICK_SLAB = REGISTER.register("mixed_pebble_shell_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> MIXED_PEBBLE_SHELL_BRICK_STAIRS = REGISTER.register("mixed_pebble_shell_brick_stairs", () -> new StairsBlock(() -> FinsBlocks.MIXED_PEBBLE_SHELL_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> MIXED_FLATBACK_SHELL_BRICK_SLAB = REGISTER.register("mixed_flatback_shell_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> MIXED_FLATBACK_SHELL_BRICK_STAIRS = REGISTER.register("mixed_flatback_shell_brick_stairs", () -> new StairsBlock(() -> FinsBlocks.MIXED_FLATBACK_SHELL_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> CHAINED_TENTACLE = REGISTER.register("chained_tentacle", () -> new ChainedTentacleBlock(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion()));
    public static final RegistryObject<Block> LAMINA_PADS = REGISTER.register("lamina_pads", () -> new SlabBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.6F).sound(SoundType.WET_GRASS)));
    public static final RegistryObject<Block> LAMINA_STALK = REGISTER.register("lamina_stalk", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_LAMINA_STALK = REGISTER.register("stripped_lamina_stalk", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

}
