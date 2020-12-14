package mod.coda.fins.init;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.inventory.MudhorsePorchContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FinsContainers {
    public static final DeferredRegister<ContainerType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, FinsAndTails.MOD_ID);

    public static final RegistryObject<ContainerType<MudhorsePorchContainer>> MUDHORSE_POUCH = REGISTER.register("mudhorse_pouch", () -> new ContainerType<>(MudhorsePorchContainer::new));
}
