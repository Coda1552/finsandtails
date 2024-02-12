package teamdraco.finsandstails.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.blocks.ChainedTentacleBlock;
import teamdraco.finsandstails.common.blocks.CrabCruncherBlock;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class FTBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FinsAndTails.MOD_ID);

    public static final RegistryObject<Block> CRAB_CRUNCHER = register("crab_cruncher", () -> new CrabCruncherBlock(BlockBehaviour.Properties.of().strength(5.0f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SPINDLY_GEM_CRAB_GEM_BLOCK = register("spindly_gem_block", () -> new Block(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SPINDLY_EMERALD_BLOCK = register("spindly_emerald_block", () -> new Block(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SPINDLY_PEARL_BLOCK = register("spindly_pearl_block", () -> new Block(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SPINDLY_SAPPHIRE_BLOCK = register("spindly_sapphire_block", () -> new Block(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SPINDLY_RUBY_BLOCK = register("spindly_ruby_block", () -> new Block(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SPINDLY_AMBER_BLOCK = register("spindly_amber_block", () -> new Block(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> CHISELED_FLATBACK_SHELL_BRICKS = register("chiseled_flatback_shell_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> FLATBACK_SHELL_BLOCK = register("flatback_shell_block", () -> new Block(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> FLATBACK_SHELL_BRICK_SLAB = register("flatback_shell_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> FLATBACK_SHELL_BRICK_STAIRS = register("flatback_shell_brick_stairs", () -> new StairBlock(() -> FTBlocks.FLATBACK_SHELL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> FLATBACK_SHELL_BRICKS = register("flatback_shell_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> FLATBACK_SHELL_SLAB = register("flatback_shell_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> FLATBACK_SHELL_STAIRS = register("flatback_shell_stairs", () -> new StairBlock(() -> FTBlocks.FLATBACK_SHELL_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> CHISELED_PEBBLE_SHELL_BRICKS = register("chiseled_pebble_shell_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> PEBBLE_SHELL_BRICK_SLAB = register("pebble_shell_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> PEBBLE_SHELL_BRICK_STAIRS = register("pebble_shell_brick_stairs", () -> new StairBlock(() -> FTBlocks.PEBBLE_SHELL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> PEBBLE_SHELL_BRICKS = register("pebble_shell_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> PEBBLE_SHELL_PILLAR = register("pebble_shell_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> CHISELED_SIDEROL_SHELL_BRICKS = register("chiseled_siderol_shell_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> SIDEROL_SHELL_BRICK_SLAB = register("siderol_shell_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> SIDEROL_SHELL_BRICK_STAIRS = register("siderol_shell_brick_stairs", () -> new StairBlock(() -> FTBlocks.SIDEROL_SHELL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> SIDEROL_SHELL_BRICKS = register("siderol_shell_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> MIXED_FLATBACK_SHELL_BRICKS = register("mixed_flatback_shell_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> MIXED_PEBBLE_SHELL_BRICKS = register("mixed_pebble_shell_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> MIXED_PEBBLE_SHELL_BRICK_SLAB = register("mixed_pebble_shell_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> MIXED_PEBBLE_SHELL_BRICK_STAIRS = register("mixed_pebble_shell_brick_stairs", () -> new StairBlock(() -> FTBlocks.MIXED_PEBBLE_SHELL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> MIXED_FLATBACK_SHELL_BRICK_SLAB = register("mixed_flatback_shell_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> MIXED_FLATBACK_SHELL_BRICK_STAIRS = register("mixed_flatback_shell_brick_stairs", () -> new StairBlock(() -> FTBlocks.MIXED_FLATBACK_SHELL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5f, 6.0F).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> CHAINED_TENTACLE = register("chained_tentacle", () -> new ChainedTentacleBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion()));


    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        return register(name, block, new Item.Properties());
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, Item.Properties itemProperties) {
        return register(name, block, BlockItem::new, itemProperties);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, BiFunction<Block, Item.Properties, BlockItem> item, Item.Properties itemProperties) {
        final RegistryObject<T> registryObject = BLOCKS.register(name, block);
        if (itemProperties != null) FTItems.ITEMS.register(name, () -> item == null ? new BlockItem(registryObject.get(), itemProperties) : item.apply(registryObject.get(), itemProperties));
        return registryObject;
    }
}
