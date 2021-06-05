package teamdraco.fins.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class PapaWeeItem extends Item {

    public PapaWeeItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_77654_1_, World p_77654_2_, LivingEntity p_77654_3_) {
        ItemStack itemstack = super.finishUsingItem(p_77654_1_, p_77654_2_, p_77654_3_);
        return p_77654_3_ instanceof PlayerEntity && ((PlayerEntity)p_77654_3_).abilities.instabuild ? itemstack : new ItemStack(Items.BONE);
    }
}
