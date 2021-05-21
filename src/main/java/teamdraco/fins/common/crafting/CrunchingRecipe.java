package teamdraco.fins.common.crafting;

import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import teamdraco.fins.init.FinsBlocks;
import teamdraco.fins.init.FinsRecipes;

public class CrunchingRecipe implements IRecipe<CraftingInventory> {
    private final Ingredient base;
    private final Ingredient addition;
    private final ItemStack result;
    private final ResourceLocation recipeId;

    public CrunchingRecipe(ResourceLocation recipeId, Ingredient base, Ingredient addition, ItemStack result) {
        this.recipeId = recipeId;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        return this.base.test(inv.getStackInSlot(0)) && this.addition.test(inv.getStackInSlot(1));
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        ItemStack itemstack = this.result.copy();
        return itemstack;
    }

    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    public ItemStack getRecipeOutput() {
        return this.result;
    }

    public ItemStack getIcon() {
        return new ItemStack(FinsBlocks.CRAB_CRUNCHER.get());
    }

    public ResourceLocation getId() {
        return this.recipeId;
    }

    public IRecipeSerializer<?> getSerializer() {
        return FinsRecipes.CRUNCHING_SERIALIZER.get();
    }

    public IRecipeType<?> getType() {
        return FinsRecipes.CRUNCHING_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CrunchingRecipe> {

        @Override
        public CrunchingRecipe read(ResourceLocation recipeId, JsonObject json) {
            Ingredient ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "base"));
             Ingredient ingredient1 = Ingredient.deserialize(JSONUtils.getJsonObject(json, "addition"));
            ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            return new CrunchingRecipe(recipeId, ingredient, ingredient1, itemstack);
        }

        @Override
        public CrunchingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient ingredient = Ingredient.read(buffer);
            Ingredient ingredient1 = Ingredient.read(buffer);
            ItemStack itemstack = buffer.readItemStack();
            return new CrunchingRecipe(recipeId, ingredient, ingredient1, itemstack);
        }

        @Override
        public void write(PacketBuffer buffer, CrunchingRecipe recipe) {
            recipe.base.write(buffer);
            recipe.addition.write(buffer);
            buffer.writeItemStack(recipe.result);
        }
    }
}
