package teamdraco.fins.init;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.crafting.CrunchingRecipe;

public class FinsRecipes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FinsAndTails.MOD_ID);

    public static final RegistryObject<CrunchingRecipe.Serializer> CRUNCHING_SERIALIZER = RECIPES.register("crunching", CrunchingRecipe.Serializer::new);
    public static final IRecipeType<CrunchingRecipe> CRUNCHING_TYPE = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(FinsAndTails.MOD_ID, "crunching"), new IRecipeType<CrunchingRecipe>() {
        @Override
        public String toString() {
            return "crunching";
        }
    });
}
