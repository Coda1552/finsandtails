package teamdraco.fins.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.container.MudhorsePouchContainer;

public class MudhorsePouchItem extends Item {
    public MudhorsePouchItem() {
        super(new Item.Properties().tab(FinsAndTails.GROUP).stacksTo(1));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!world.isClientSide) {
            player.openMenu(new SimpleNamedContainerProvider((windowId, inventory, owner) -> new MudhorsePouchContainer(windowId, inventory, stack), stack.getHoverName()));
        }
        return ActionResult.success(stack);
    }
}
