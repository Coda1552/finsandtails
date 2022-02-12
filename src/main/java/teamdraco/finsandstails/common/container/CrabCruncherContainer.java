package teamdraco.finsandstails.common.container;

import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamdraco.finsandstails.common.container.slot.CrabCruncherResultSlot;
import teamdraco.finsandstails.common.container.slot.CrabCruncherSlot;
import teamdraco.finsandstails.common.crafting.CrunchingRecipe;
import teamdraco.finsandstails.registry.FTBlocks;
import teamdraco.finsandstails.registry.FTContainers;
import teamdraco.finsandstails.registry.FTRecipes;
import teamdraco.finsandstails.registry.FtSounds;

import java.util.Optional;

public class CrabCruncherContainer extends AbstractContainerMenu {
    private final CraftingContainer inventory = new CraftingContainer(this, 2, 1);
    private final ResultContainer craftResult = new ResultContainer();
    private final ContainerLevelAccess canInteractWithCallable;
    private final Player player;

    public CrabCruncherContainer(int windowId, Inventory playerInventory) {
        this(windowId, playerInventory, ContainerLevelAccess.NULL, new SimpleContainerData(3));
    }

    public CrabCruncherContainer(int windowId, Inventory playerInventory, ContainerLevelAccess access, ContainerData data) {
        super(FTContainers.CRAB_CRUNCHER.get(), windowId);
        this.player = playerInventory.player;
        this.canInteractWithCallable = access;

        // Result Slot
        this.addSlot(new CrabCruncherResultSlot(player, inventory, craftResult, 2, 134, 47));

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
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();

            if (index < 2) {
                if (!this.moveItemStackTo(stack1, 0, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(stack1, 0, 10, true)) {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }
            else {
                slot.setChanged();
            }
        }

        return stack;
    }

    protected void updateCraftingResult(Level world) {
        if (!world.isClientSide) {
            ServerPlayer serverplayerentity = (ServerPlayer)player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<CrunchingRecipe> optional = world.getServer().getRecipeManager().getRecipeFor(FTRecipes.CRUNCHING_TYPE, inventory, world);
            if (optional.isPresent()) {
                itemstack = optional.get().assemble(inventory);
            }
            world.playSound(player, player.blockPosition(), FtSounds.CRAB_CRUNCH.get(), SoundSource.BLOCKS, 0.6F, 1.0F);

            craftResult.setItem(0, itemstack);
            serverplayerentity.connection.send(new ClientboundContainerSetSlotPacket(containerId, 0, 0, itemstack));
        }
    }

    @Override
    public void slotsChanged(Container inventoryIn) {
        this.canInteractWithCallable.execute((p_217069_1_, p_217069_2_) -> updateCraftingResult(p_217069_1_));
    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        this.canInteractWithCallable.execute((p_217068_2_, p_217068_3_) -> this.clearContainer(playerIn, inventory));
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.canInteractWithCallable, player, FTBlocks.CRAB_CRUNCHER.get());
    }
}

