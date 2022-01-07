package teamdraco.finsandstails.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.World;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.container.MudhorsePouchContainer;

public class MudhorsePouchItem extends Item {
    public MudhorsePouchItem() {
        super(new Item.Properties().tab(FinsAndTails.GROUP).stacksTo(1));
    }

    @Override
    public ActionResult<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!world.isClientSide) {
            player.openMenu(new SimpleNamedContainerProvider((windowId, inventory, owner) -> new MudhorsePouchContainer(windowId, inventory, stack), stack.getHoverName()));
        }
        return ActionResult.success(stack);
    }
}
