package teamdraco.finsandstails.common.container;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import teamdraco.finsandstails.common.crafting.CrunchingRecipe;
import teamdraco.finsandstails.registry.FTBlocks;
import teamdraco.finsandstails.registry.FTContainers;
import teamdraco.finsandstails.registry.FTRecipes;

import java.util.List;

public class CrabCruncherContainer extends ItemCombinerMenu {
    private final List<CrunchingRecipe> recipes;
    private final ContainerLevelAccess access;
    private final Player player;
    private CrunchingRecipe selectedRecipe;

    public CrabCruncherContainer(int windowId, Inventory playerInventory) {
        this(windowId, playerInventory, ContainerLevelAccess.NULL);
    }

    public CrabCruncherContainer(int windowId, Inventory playerInventory, ContainerLevelAccess access) {
        super(FTContainers.CRAB_CRUNCHER.get(), windowId, playerInventory, access);
        this.player = playerInventory.player;
        this.access = access;
        this.recipes = player.level.getRecipeManager().getAllRecipesFor(FTRecipes.CRUNCHING_TYPE);
    }

    protected boolean isValidBlock(BlockState p_40266_) {
        return p_40266_.is(FTBlocks.CRAB_CRUNCHER.get());
    }

    protected boolean mayPickup(Player p_40268_, boolean p_40269_) {
        return this.selectedRecipe != null && this.selectedRecipe.matches(this.inputSlots, player.level);
    }

    protected void onTake(Player p_150663_, ItemStack p_150664_) {
        p_150664_.onCraftedBy(p_150663_.level, p_150663_, p_150664_.getCount());
        this.resultSlots.awardUsedRecipes(p_150663_);
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.access.execute((p_40263_, p_40264_) -> {
            p_40263_.levelEvent(1044, p_40264_, 0);
        });
    }

    private void shrinkStackInSlot(int p_40271_) {
        ItemStack itemstack = this.inputSlots.getItem(p_40271_);
        itemstack.shrink(1);
        this.inputSlots.setItem(p_40271_, itemstack);
    }

    public void createResult() {
        List<CrunchingRecipe> list = player.level.getRecipeManager().getRecipesFor(FTRecipes.CRUNCHING_TYPE, this.inputSlots, player.level);
        if (list.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        } else {
            this.selectedRecipe = list.get(0);
            ItemStack itemstack = this.selectedRecipe.assemble(this.inputSlots);
            this.resultSlots.setRecipeUsed(this.selectedRecipe);
            this.resultSlots.setItem(0, itemstack);
        }

    }

    public void removed(Player p_39790_) {
        super.removed(p_39790_);
        this.access.execute((p_39796_, p_39797_) -> {
            this.clearContainer(p_39790_, this.inputSlots);
        });
    }

    protected boolean shouldQuickMoveToAdditionalSlot(ItemStack p_40255_) {
        return this.recipes.stream().anyMatch((p_40261_) -> p_40261_.isAdditionIngredient(p_40255_));
    }

    public boolean canTakeItemForPickAll(ItemStack p_40257_, Slot p_40258_) {
        return p_40258_.container != this.resultSlots && super.canTakeItemForPickAll(p_40257_, p_40258_);
    }
}

