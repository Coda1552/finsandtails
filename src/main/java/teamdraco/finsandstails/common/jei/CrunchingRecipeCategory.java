package teamdraco.finsandstails.common.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.crafting.CrunchingRecipe;
import teamdraco.finsandstails.registry.FTBlocks;

public class CrunchingRecipeCategory implements IRecipeCategory<CrunchingRecipe> {
    public static final RecipeType<CrunchingRecipe> CRUNCHING = RecipeType.create(FinsAndTails.MOD_ID, "crunching", CrunchingRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;

    public CrunchingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(new ResourceLocation(FinsAndTails.MOD_ID, "textures/gui/crab_cruncher.png"), 26, 46, 125, 18);
        this.icon = helper.createDrawableItemStack(new ItemStack(FTBlocks.CRAB_CRUNCHER.get().asItem()));
    }

    @Override
    public RecipeType<CrunchingRecipe> getRecipeType() {
        return CRUNCHING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("category." + FinsAndTails.MOD_ID + ".crunching_recipe");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrunchingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 0, 0)
                .addIngredients(recipe.getIngredients().get(0));
        if (recipe.getIngredients().size() >= 2) {
            builder.addSlot(RecipeIngredientRole.INPUT, 49, 0)
                    .addIngredients(recipe.getIngredients().get(1));
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 107, 0)
                .addItemStack(recipe.getResultItem());
    }
}
