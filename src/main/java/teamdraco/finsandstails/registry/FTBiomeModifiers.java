package teamdraco.finsandstails.registry;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.level.FinsAndTailsBiomeModifier;

public class FTBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, FinsAndTails.MOD_ID);
    public static final RegistryObject<Codec<FinsAndTailsBiomeModifier>> FINS_AND_TAILS_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("fins_and_tails_spawn", () -> Codec.unit(FinsAndTailsBiomeModifier.INSTANCE));

}