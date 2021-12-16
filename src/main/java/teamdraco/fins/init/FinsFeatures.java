package teamdraco.fins.init;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.world.tree.LaminaTreeFeature;

public class FinsFeatures {
    public static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, FinsAndTails.MOD_ID);

    public static final RegistryObject<LaminaTreeFeature> LAMINA_TREE = REGISTER.register("lamina_tree", LaminaTreeFeature::new);
}
