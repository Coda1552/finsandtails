package teamdraco.fins.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class PapaWeeItem extends Item {

    public PapaWeeItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
        ItemStack itemstack = super.finishUsingItem(stack, world, entity);

        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (!player.abilities.instabuild) {
                if (player.getItemInHand(Hand.MAIN_HAND).isEmpty()) {
                    player.setItemInHand(Hand.MAIN_HAND, stack);
                }
                if (!player.inventory.add(new ItemStack(Items.BONE))) {
                    player.drop(new ItemStack(Items.BONE), false);
                }
            }
        }
        return itemstack;
    }
}
