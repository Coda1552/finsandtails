package teamdraco.finsandstails.registry;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.finsandstails.FinsAndTails;

public class FTBannerPatterns {
    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registry.BANNER_PATTERN_REGISTRY, FinsAndTails.MOD_ID);

    public static final RegistryObject<BannerPattern> MANDIBLES = BANNER_PATTERNS.register("mandibles", () -> new BannerPattern("ftm"));
    public static final RegistryObject<BannerPattern> SHELL = BANNER_PATTERNS.register("shell", () -> new BannerPattern("ftsll"));

}