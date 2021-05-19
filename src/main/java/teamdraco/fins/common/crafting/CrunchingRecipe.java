package teamdraco.fins.common.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.registries.ForgeRegistryEntry;
import teamdraco.fins.common.container.CrabCruncherInventory;
import teamdraco.fins.init.FinsRecipes;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CrunchingRecipe implements IRecipe<CraftingInventory> {
    private final ResourceLocation id;
    private final ItemStack recipeOutput;
    private final NonNullList<Ingredient> recipeItems;
    private final boolean isSimple;

    public CrunchingRecipe(ResourceLocation idIn, ItemStack recipeOutput, NonNullList<Ingredient> recipeItems) {
        this.id = idIn;
        this.recipeOutput = recipeOutput;
        this.recipeItems = recipeItems;
        this.isSimple = recipeItems.stream().allMatch(Ingredient::isSimple);
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        for (int j = 0; j < inv.getSizeInventory() - 1; ++j) {
            ItemStack itemstack = inv.getStackInSlot(j);
            if (!itemstack.isEmpty()) {
                ++i;
                if (isSimple)
                    recipeitemhelper.func_221264_a(itemstack, 1);
                else inputs.add(itemstack);
            }
        }

        return i == this.recipeItems.size() && (isSimple ? recipeitemhelper.canCraft(this, null) : RecipeMatcher.findMatches(inputs, this.recipeItems) != null);
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        return this.recipeOutput.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeType<?> getType() {
        return FinsRecipes.CRUNCHING_TYPE;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return FinsRecipes.CRUNCHING_SERIALIZER.get();
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CrunchingRecipe> {
        @Override
        public CrunchingRecipe read(ResourceLocation recipeId, JsonObject json) {
            final NonNullList<Ingredient> items = NonNullList.create();
            for (JsonElement element : json.getAsJsonArray("ingredients")) {
                items.add(Ingredient.deserialize(element));
            }
            return new CrunchingRecipe(recipeId, ShapedRecipe.deserializeItem(json.getAsJsonObject("result")), items);
        }

        @Nullable
        @Override
        public CrunchingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            final NonNullList<Ingredient> items = NonNullList.withSize(buffer.readVarInt(), Ingredient.EMPTY);
            for (int i = 0; i < items.size(); i++) {
                items.set(i, Ingredient.read(buffer));
            }
            return new CrunchingRecipe(recipeId, buffer.readItemStack(), items);
        }

        @Override
        public void write(PacketBuffer buffer, CrunchingRecipe recipe) {
            buffer.writeVarInt(recipe.recipeItems.size());
            for (Ingredient recipeItem : recipe.recipeItems) {
                recipeItem.write(buffer);
            }
            buffer.writeItemStack(recipe.recipeOutput);
        }
    }
}