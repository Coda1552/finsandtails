package teamdraco.finsandstails.common;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import teamdraco.finsandstails.common.items.CrabGauntletItem;

public class SpindlyGauntletItem extends CrabGauntletItem {

    public SpindlyGauntletItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int time) {
        if (entity instanceof Player player) {
            int i = this.getUseDuration(stack) - time;

            if (i < 0) return;

            float headRot = player.yHeadRot;



        }
        super.releaseUsing(stack, level, entity, time);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 72000;
    }
}
