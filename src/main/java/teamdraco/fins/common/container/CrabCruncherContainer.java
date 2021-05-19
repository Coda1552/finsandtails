package teamdraco.fins.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import teamdraco.fins.common.container.slot.CrabCruncherResultSlot;
import teamdraco.fins.common.container.slot.CrabCruncherSlot;
import teamdraco.fins.common.crafting.CrunchingRecipe;
import teamdraco.fins.init.FinsBlocks;
import teamdraco.fins.init.FinsContainers;
import teamdraco.fins.init.FinsRecipes;

import java.util.Optional;

public class CrabCruncherContainer extends Container {
    private final IWorldPosCallable worldPosCallable;
    private final PlayerEntity player;
    private final CraftingInventory inventory = new CraftingInventory(this, 2, 1);
    private final CraftResultInventory craftResult = new CraftResultInventory();

    public CrabCruncherContainer(final int windowId, PlayerInventory playerInventory) {
        this(windowId, playerInventory, IWorldPosCallable.DUMMY);
    }

    public CrabCruncherContainer(final int windowId, PlayerInventory playerInventory, IWorldPosCallable worldPosCallable) {
        super(FinsContainers.CRAB_CRUNCHER.get(), windowId);
        this.worldPosCallable = worldPosCallable;
        this.player = playerInventory.player;

        // Result Slot
        this.addSlot(new CrabCruncherResultSlot(player, inventory, craftResult, 0, 134, 47));

        // Input Slots
        this.addSlot(new CrabCruncherSlot(inventory, 0, 27, 47));
        this.addSlot(new CrabCruncherSlot(inventory, 1, 76, 47));

        // Main Player Inv
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // Player Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(worldPosCallable, playerIn, FinsBlocks.CRAB_CRUNCHER.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if (index < 3) {
                if (!this.mergeItemStack(stack1, 0, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(stack1, 0, 2, true)) {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }
            else {
                slot.onSlotChanged();
            }
        }

        return stack;
    }

    protected void updateCraftingResult(World world) {
        if (!world.isRemote) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<CrunchingRecipe> optional = world.getServer().getRecipeManager().getRecipe(FinsRecipes.CRUNCHING_TYPE, inventory, world);
            if (optional.isPresent()) {
                itemstack = optional.get().getCraftingResult(inventory);
            }

            craftResult.setInventorySlotContents(0, itemstack);
            serverplayerentity.connection.sendPacket(new SSetSlotPacket(windowId, 0, itemstack));
        }
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.worldPosCallable.consume((p_217069_1_, p_217069_2_) -> updateCraftingResult(p_217069_1_));
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.worldPosCallable.consume((p_217068_2_, p_217068_3_) -> this.clearContainer(playerIn, p_217068_2_, inventory));
    }
}
