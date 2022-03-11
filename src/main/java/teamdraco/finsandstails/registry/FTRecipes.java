package teamdraco.finsandstails.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.crafting.CrunchingRecipe;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FTRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FinsAndTails.MOD_ID);

    public static final RegistryObject<CrunchingRecipe.Serializer> CRUNCHING_SERIALIZER = SERIALIZERS.register("crunching", CrunchingRecipe.Serializer::new);

    public static RecipeType<CrunchingRecipe> CRUNCHING_TYPE = null;

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        CRUNCHING_TYPE = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(FinsAndTails.MOD_ID, "crunching"), new RecipeType<CrunchingRecipe>() {
            @Override
            public String toString() {
                return "crunching";
            }
        });
    }
}
