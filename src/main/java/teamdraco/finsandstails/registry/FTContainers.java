package teamdraco.finsandstails.registry;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.container.CrabCruncherContainer;
import teamdraco.finsandstails.common.container.MudhorsePouchContainer;

public class FTContainers {
    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, FinsAndTails.MOD_ID);

    public static final RegistryObject<MenuType<MudhorsePouchContainer>> MUDHORSE_POUCH = REGISTER.register("mudhorse_pouch", () -> new MenuType<>(MudhorsePouchContainer::new));
    public static final RegistryObject<MenuType<CrabCruncherContainer>> CRAB_CRUNCHER = REGISTER.register("crab_cruncher", () ->  new MenuType<>(CrabCruncherContainer::new));
}
