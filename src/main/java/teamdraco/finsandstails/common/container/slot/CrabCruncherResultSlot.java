package teamdraco.finsandstails.common.container.slot;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import teamdraco.finsandstails.registry.FTRecipes;

public class CrabCruncherResultSlot extends Slot {
   private final ResultContainer craftMatrix;
   private final Player player;
   private int amountCrafted;

   public CrabCruncherResultSlot(Player player, ResultContainer craftingInventory, Container inventoryIn, int slotIndex, int xPosition, int yPosition) {
      super(inventoryIn, slotIndex, xPosition, yPosition);
      this.player = player;
      this.craftMatrix = craftingInventory;
   }

   public boolean mayPlace(ItemStack stack) {
      return false;
   }

   public ItemStack remove(int amount) {
      if (this.hasItem()) {
         this.amountCrafted += Math.min(amount, this.getItem().getCount());
      }

      return super.remove(amount);
   }

   protected void onQuickCraft(ItemStack stack, int amount) {
      this.amountCrafted += amount;
      this.checkTakeAchievements(stack);
   }

   protected void onSwapCraft(int numItemsCrafted) {
      this.amountCrafted += numItemsCrafted;
   }

   protected void checkTakeAchievements(ItemStack stack) {
      if (this.amountCrafted > 0) {
         stack.onCraftedBy(this.player.level, this.player, this.amountCrafted);
      }

      if (this.container instanceof RecipeHolder) {
         ((RecipeHolder)this.container).awardUsedRecipes(this.player);
      }

      this.amountCrafted = 0;
   }

   public void onTake(Player thePlayer, ItemStack stack) {
      this.checkTakeAchievements(stack);
      ForgeHooks.setCraftingPlayer(thePlayer);
      NonNullList<ItemStack> nonnulllist = thePlayer.level.getRecipeManager().getRemainingItemsFor(FTRecipes.CRUNCHING_TYPE, this.craftMatrix, thePlayer.level);
      ForgeHooks.setCraftingPlayer(null);
      for(int i = 0; i < nonnulllist.size(); ++i) {
         ItemStack itemstack = this.craftMatrix.getItem(i);
         ItemStack itemstack1 = nonnulllist.get(i);
         if (!itemstack.isEmpty()) {
            this.craftMatrix.removeItem(i, 1);
            itemstack = this.craftMatrix.getItem(i);
         }

         if (!itemstack1.isEmpty()) {
            if (itemstack.isEmpty()) {
               this.craftMatrix.setItem(i, itemstack1);
            } else if (ItemStack.isSame(itemstack, itemstack1) && ItemStack.tagMatches(itemstack, itemstack1)) {
               itemstack1.grow(itemstack.getCount());
               this.craftMatrix.setItem(i, itemstack1);
            } else if (!this.player.getInventory().add(itemstack1)) {
               this.player.drop(itemstack1, false);
            }
         }
      }
   }
}
