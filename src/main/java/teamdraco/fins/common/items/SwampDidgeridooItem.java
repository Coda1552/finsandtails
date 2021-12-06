package teamdraco.fins.common.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import javax.annotation.Nullable;
import teamdraco.fins.common.entities.MudhorseEntity;
import teamdraco.fins.init.FinsEntities;
import teamdraco.fins.init.FinsSounds;

import java.util.List;

public class SwampDidgeridooItem extends Item {

    public SwampDidgeridooItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        List<MudhorseEntity> mudhorses = world.getEntities(FinsEntities.MUDHORSE.get(), player.getBoundingBox().inflate(8), entity -> entity.getCommander() == null);
        if (mudhorses.isEmpty()) {
            player.playSound(SoundEvents.VILLAGER_NO, 0.4f, 1);
            addParticleEffect(ParticleTypes.SMOKE, world, player.getX() - 0.5, player.getY() + 1, player.getZ() - 0.5);
            return ActionResult.pass(stack);
        }

        for (MudhorseEntity mudhorseEntity : mudhorses) {
            mudhorseEntity.setCommander(player);
            addParticleEffect(ParticleTypes.HAPPY_VILLAGER, world, mudhorseEntity.getX() - 0.5, mudhorseEntity.getY() + 1.4, mudhorseEntity.getZ() - 0.5);
        }
        player.playSound(FinsSounds.DIDGERIDOO_PLAY.get(), 0.4f, 1);
        player.getCooldowns().addCooldown(this, 600);
        stack.hurtAndBreak(1, player, entity -> entity.broadcastBreakEvent(hand));
        return ActionResult.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(new TranslationTextComponent("fins.swamp_didgeridoo.desc").withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
    }

    private void addParticleEffect(IParticleData particleData, World world, double x, double y, double z) {
        for(int i = 0; i < 10; ++i) {
            double d2 = random.nextGaussian() * 0.02D;
            double d3 = random.nextGaussian() * 0.02D;
            double d4 = random.nextGaussian() * 0.02D;
            double d6 = x + random.nextDouble();
            double d7 = y + random.nextDouble() * 0.5;
            double d8 = z + random.nextDouble();
            world.addParticle(particleData, d6, d7, d8, d2, d3, d4);
        }
    }
}
