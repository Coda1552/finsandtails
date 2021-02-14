package mod.coda.fins.init;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.blocks.ChainedTentacleBlock;
import mod.coda.fins.blocks.CrabCruncherBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FinsBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, FinsAndTails.MOD_ID);

    public static final RegistryObject<Block> CRAB_CRUNCHER = REGISTER.register("crab_cruncher", () -> new CrabCruncherBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SPINDLY_GEM_CRAB_GEM_BLOCK = REGISTER.register("spindly_gem_crab_gem_block", () -> new CrabCruncherBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> CHISELED_FLATBACK_SHELL_BRICKS = REGISTER.register("chiseled_flatback_shell_bricks", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> FLATBACK_SHELL_BLOCK = REGISTER.register("flatback_shell_block", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> FLATBACK_SHELL_BRICK_SLAB = REGISTER.register("flatback_shell_brick_slab", () -> new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> FLATBACK_SHELL_BRICK_STAIRS = REGISTER.register("flatback_shell_brick_stairs", () -> new StairsBlock(() -> FinsBlocks.FLATBACK_SHELL_BRICKS.get().getDefaultState(), Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> FLATBACK_SHELL_BRICKS = REGISTER.register("flatback_shell_bricks", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> FLATBACK_SHELL_SLAB = REGISTER.register("flatback_shell_slab", () -> new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> FLATBACK_SHELL_STAIRS = REGISTER.register("flatback_shell_stairs", () -> new StairsBlock(() -> FinsBlocks.FLATBACK_SHELL_BLOCK.get().getDefaultState(), Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> CHISELED_PEBBLE_SHELL_BRICKS = REGISTER.register("chiseled_pebble_shell_bricks", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> PEBBLE_SHELL_BRICK_SLAB = REGISTER.register("pebble_shell_brick_slab", () -> new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> PEBBLE_SHELL_BRICK_STAIRS = REGISTER.register("pebble_shell_brick_stairs", () -> new StairsBlock(() -> FinsBlocks.PEBBLE_SHELL_BRICKS.get().getDefaultState(), Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> PEBBLE_SHELL_BRICKS = REGISTER.register("pebble_shell_bricks", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> PEBBLE_SHELL_PILLAR = REGISTER.register("pebble_shell_pillar", () -> new RotatedPillarBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> CHISELED_SIDEROL_SHELL_BRICKS = REGISTER.register("chiseled_siderol_shell_bricks", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> SIDEROL_SHELL_BRICK_SLAB = REGISTER.register("siderol_shell_brick_slab", () -> new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> SIDEROL_SHELL_BRICK_STAIRS = REGISTER.register("siderol_shell_brick_stairs", () -> new StairsBlock(() -> FinsBlocks.SIDEROL_SHELL_BRICKS.get().getDefaultState(), Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> SIDEROL_SHELL_BRICKS = REGISTER.register("siderol_shell_bricks", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> MIXED_FLATBACK_SHELL_BRICKS = REGISTER.register("mixed_flatback_shell_bricks", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> MIXED_PEBBLE_SHELL_BRICKS = REGISTER.register("mixed_pebble_shell_bricks", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> MIXED_PEBBLE_SHELL_BRICK_SLAB = REGISTER.register("mixed_pebble_shell_brick_slab", () -> new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> MIXED_PEBBLE_SHELL_BRICK_STAIRS = REGISTER.register("mixed_pebble_shell_brick_stairs", () -> new StairsBlock(() -> FinsBlocks.MIXED_PEBBLE_SHELL_BRICKS.get().getDefaultState(), Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> MIXED_FLATBACK_SHELL_BRICK_SLAB = REGISTER.register("mixed_flatback_shell_brick_slab", () -> new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> MIXED_FLATBACK_SHELL_BRICK_STAIRS = REGISTER.register("mixed_flatback_shell_brick_stairs", () -> new StairsBlock(() -> FinsBlocks.MIXED_FLATBACK_SHELL_BRICKS.get().getDefaultState(), Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0F).sound(SoundType.BONE)));
    public static final RegistryObject<Block> CHAINED_TENTACLE = REGISTER.register("chained_tentacle", () -> new ChainedTentacleBlock(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.CHAIN).notSolid()));

}
