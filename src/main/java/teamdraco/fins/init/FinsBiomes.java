package teamdraco.fins.init;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.world.biome.SchnauzBedsBiome;

public class FinsBiomes {
    public static final DeferredRegister<Biome> REGISTER = DeferredRegister.create(ForgeRegistries.BIOMES, FinsAndTails.MOD_ID);

    public static final RegistryObject<Biome> SCHNAUZ_BEDS = REGISTER.register("schnauz_beds", () -> new SchnauzBedsBiome().getBiome());

    public static void registerBiomes() {
        registerBiome(SCHNAUZ_BEDS.get(), BiomeManager.BiomeType.WARM, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.OVERWORLD);
    }
    public static void registerBiome(Biome biome, BiomeManager.BiomeType type, BiomeDictionary.Type... types) {
        BiomeDictionary.addTypes(RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("fins:schnauz_beds")), types);
        if (ModList.get().isLoaded("abnormals_core")) {
            RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("fins:schnauz_beds"));
            //BiomeUtil.addOceanBiome(BiomeUtil.OceanType.WARM, key, key, 500);
        }
        else {
            BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("fins:schnauz_beds")), 500));
        }

    }
}
