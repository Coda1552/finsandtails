package mod.coda.fins.init;

import mod.coda.fins.FinsAndTails;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FinsSounds {

    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FinsAndTails.MOD_ID);

    public static final RegistryObject<SoundEvent> MUDHORSE_DEATH = REGISTRY.register("mudhorse.death", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "mudhorse.death")));
    public static final RegistryObject<SoundEvent> MUDHORSE_HURT = REGISTRY.register("mudhorse.hurt", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "mudhorse.hurt")));
    public static final RegistryObject<SoundEvent> MUDHORSE_AMBIENT = REGISTRY.register("mudhorse.ambient", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "mudhorse.ambient")));

    public static final RegistryObject<SoundEvent> RUBBER_BELLY_GLIDER_DEATH = REGISTRY.register("rubber_belly_glider.death", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "rubber_belly_glider.death")));
    public static final RegistryObject<SoundEvent> RUBBER_BELLY_GLIDER_HURT = REGISTRY.register("rubber_belly_glider.hurt", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "rubber_belly_glider.hurt")));
    public static final RegistryObject<SoundEvent> RUBBER_BELLY_GLIDER_AMBIENT = REGISTRY.register("rubber_belly_glider.ambient", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "rubber_belly_glider.ambient")));

    public static final RegistryObject<SoundEvent> PENGLIL_DEATH = REGISTRY.register("penglil.death", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "penglil.death")));
    public static final RegistryObject<SoundEvent> PENGLIL_HURT = REGISTRY.register("penglil.hurt", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "penglil.hurt")));
    public static final RegistryObject<SoundEvent> PENGLIL_AMBIENT = REGISTRY.register("penglil.ambient", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "penglil.ambient")));

    public static final RegistryObject<SoundEvent> CRAB_DEATH = REGISTRY.register("crab.death", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "crab.death")));
    public static final RegistryObject<SoundEvent> CRAB_CRUNCH = REGISTRY.register("crab.crunch", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "crab.crunch")));
}