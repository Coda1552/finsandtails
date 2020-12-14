package mod.coda.fins.init;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.block.CrabCruncherBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FinsBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, FinsAndTails.MOD_ID);

    public static final RegistryObject<Block> CRAB_CRUNCHER = REGISTER.register("crab_cruncher", () -> new CrabCruncherBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SPINDLY_GEM_CRAB_GEM_BLOCK = REGISTER.register("spindly_gem_crab_gem_block", () -> new CrabCruncherBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
}
