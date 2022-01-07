package teamdraco.finsandstails.common.container.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.hooks.BasicEventHooks;
import teamdraco.finsandstails.registry.FTRecipes;

public class CrabCruncherResultSlot extends Slot {
   private final CraftingInventory craftMatrix;
   private final PlayerEntity player;
   private int amountCrafted;

   public CrabCruncherResultSlot(PlayerEntity player, CraftingInventory craftingInventory, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
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
         BasicEventHooks.firePlayerCraftingEvent(this.player, stack, this.craftMatrix);
      }

      if (this.container instanceof IRecipeHolder) {
         ((IRecipeHolder)this.container).awardUsedRecipes(this.player);
      }

      this.amountCrafted = 0;
   }

   public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
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
            } else if (!this.player.inventory.add(itemstack1)) {
               this.player.drop(itemstack1, false);
            }
         }
      }

      return stack;
   }
}
