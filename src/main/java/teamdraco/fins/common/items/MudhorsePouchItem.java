package teamdraco.fins.common.items;

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
            player.openContainer(new INamedContainerProvider() {
                @Override
                public ITextComponent getDisplayName() {
                    return stack.getDisplayName();
                }

                @Override
                public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
                    return new MudhorsePouchContainer(p_createMenu_1_, p_createMenu_2_, stack);
                }
            });
        }
        return ActionResult.resultSuccess(stack);
    }
}
