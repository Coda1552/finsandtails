package teamdraco.finsandstails.common.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.crafting.CrunchingRecipe;
import teamdraco.finsandstails.registry.FTItems;

public class CrunchingRecipeCategory implements IRecipeCategory<CrunchingRecipe> {
    public static final ResourceLocation ID = new ResourceLocation(FinsAndTails.MOD_ID, ".crunching_recipe_category");
    private final IDrawable background;
    private final IDrawable icon;

    public CrunchingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(new ResourceLocation(FinsAndTails.MOD_ID, "textures/gui/crab_cruncher.png"), 26, 46, 125, 18);
        this.icon = helper.createDrawableIngredient(new ItemStack(FTItems.CRAB_CRUNCHER.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends CrunchingRecipe> getRecipeClass() {
        return CrunchingRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("category." + FinsAndTails.MOD_ID + ".crunching_recipe").getString();
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
    public void setIngredients(CrunchingRecipe crunchingRecipe, IIngredients iIngredients) {
        iIngredients.setInputIngredients(crunchingRecipe.getIngredients());
        iIngredients.setOutput(VanillaTypes.ITEM, crunchingRecipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, CrunchingRecipe crunchingRecipe, IIngredients iIngredients) {
        IGuiItemStackGroup itemStackGroup = iRecipeLayout.getItemStacks();

        itemStackGroup.init(0, true, 0, 0);
        itemStackGroup.init(1, true, 49, 0);
        itemStackGroup.init(2, false, 107, 0);
        itemStackGroup.set(iIngredients);
    }
}
