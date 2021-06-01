package teamdraco.fins.init;

import teamdraco.fins.FinsAndTails;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FinsSounds {

    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FinsAndTails.MOD_ID);

    public static final RegistryObject<SoundEvent> MUDHORSE_DEATH = REGISTER.register("mudhorse.death", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "mudhorse.death")));
    public static final RegistryObject<SoundEvent> MUDHORSE_HURT = REGISTER.register("mudhorse.hurt", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "mudhorse.hurt")));
    public static final RegistryObject<SoundEvent> MUDHORSE_AMBIENT = REGISTER.register("mudhorse.ambient", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "mudhorse.ambient")));
    public static final RegistryObject<SoundEvent> RUBBER_BELLY_GLIDER_DEATH = REGISTER.register("rubber_belly_glider.death", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "rubber_belly_glider.death")));
    public static final RegistryObject<SoundEvent> RUBBER_BELLY_GLIDER_HURT = REGISTER.register("rubber_belly_glider.hurt", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "rubber_belly_glider.hurt")));
    public static final RegistryObject<SoundEvent> RUBBER_BELLY_GLIDER_AMBIENT = REGISTER.register("rubber_belly_glider.ambient", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "rubber_belly_glider.ambient")));
    public static final RegistryObject<SoundEvent> PENGLIL_DEATH = REGISTER.register("penglil.death", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "penglil.death")));
    public static final RegistryObject<SoundEvent> PENGLIL_HURT = REGISTER.register("penglil.hurt", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "penglil.hurt")));
    public static final RegistryObject<SoundEvent> PENGLIL_AMBIENT = REGISTER.register("penglil.ambient", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "penglil.ambient")));
    public static final RegistryObject<SoundEvent> CRAB_DEATH = REGISTER.register("crab.death", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "crab.death")));
    public static final RegistryObject<SoundEvent> CRAB_CRUNCH = REGISTER.register("crab.crunch", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "crab.crunch")));
    public static final RegistryObject<SoundEvent> DIDGERIDOO_PLAY = REGISTER.register("didgeridoo.play", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "didgeridoo.play")));
    public static final RegistryObject<SoundEvent> CRASHING_TIDES = REGISTER.register("music_disc.crashing_tides", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "music_disc.crashing_tides")));
    public static final RegistryObject<SoundEvent> JETPACK_USE = REGISTER.register("jetpack.use", () -> new SoundEvent(new ResourceLocation(FinsAndTails.MOD_ID, "jetpack.use")));
}