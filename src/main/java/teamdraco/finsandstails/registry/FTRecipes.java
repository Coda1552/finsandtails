package teamdraco.finsandstails.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.crafting.CrunchingRecipe;

public class FTRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FinsAndTails.MOD_ID);

    public static final RegistryObject<CrunchingRecipe.Serializer> CRUNCHING_SERIALIZER = SERIALIZERS.register("crunching", CrunchingRecipe.Serializer::new);
    public static final RecipeType<CrunchingRecipe> CRUNCHING_TYPE = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(FinsAndTails.MOD_ID, "crunching"), new RecipeType<CrunchingRecipe>() {
        @Override
        public String toString() {
            return "crunching";
        }
    });
}
