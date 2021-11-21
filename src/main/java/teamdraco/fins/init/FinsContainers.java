package teamdraco.fins.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.container.CrabCruncherContainer;
import teamdraco.fins.common.container.MudhorsePouchContainer;

public class FinsContainers {
    public static final DeferredRegister<ContainerType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, FinsAndTails.MOD_ID);

    public static final RegistryObject<ContainerType<MudhorsePouchContainer>> MUDHORSE_POUCH = REGISTER.register("mudhorse_pouch", () -> new ContainerType<>(MudhorsePouchContainer::new));
    public static final RegistryObject<ContainerType<CrabCruncherContainer>> CRAB_CRUNCHER = REGISTER.register("crab_cruncher", () ->  new ContainerType<>(CrabCruncherContainer::new));
}
