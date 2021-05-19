package teamdraco.fins.common.items;

import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.container.MudhorsePouchContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class MudhorsePouchItem extends Item {
    public MudhorsePouchItem() {
        super(new Item.Properties().group(FinsAndTails.GROUP).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote) {
            player.openContainer(new SimpleNamedContainerProvider((windowId, inventory, owner) -> new MudhorsePouchContainer(windowId, inventory, stack), stack.getDisplayName()));
        }
        return ActionResult.resultSuccess(stack);
    }
}
