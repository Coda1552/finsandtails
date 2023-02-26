package teamdraco.finsandstails.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import teamdraco.finsandstails.common.entities.MudhorseEntity;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTSounds;

import java.util.List;
import java.util.Random;

public class SwampDidgeridooItem extends Item {

    public SwampDidgeridooItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        List<MudhorseEntity> mudhorses = world.getEntities(FTEntities.MUDHORSE.get(), player.getBoundingBox().inflate(8), entity -> entity.getCommander() == null);
        if (mudhorses.isEmpty()) {
            player.playSound(SoundEvents.VILLAGER_NO, 0.4f, 1);
            addParticleEffect(ParticleTypes.SMOKE, world, player.getX() - 0.5, player.getY() + 1, player.getZ() - 0.5);
            return InteractionResultHolder.pass(stack);
        }

        for (MudhorseEntity mudhorseEntity : mudhorses) {
            mudhorseEntity.setCommander(player);
            addParticleEffect(ParticleTypes.HAPPY_VILLAGER, world, mudhorseEntity.getX() - 0.5, mudhorseEntity.getY() + 1.4, mudhorseEntity.getZ() - 0.5);
        }
        player.playSound(FTSounds.DIDGERIDOO_PLAY.get(), 0.4f, 1);
        player.getCooldowns().addCooldown(this, 600);
        stack.hurtAndBreak(1, player, entity -> entity.broadcastBreakEvent(hand));
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable Level p_77624_2_, List<Component> p_77624_3_, TooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(Component.translatable("finsandtails.swamp_didgeridoo.desc").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    private void addParticleEffect(SimpleParticleType particleData, Level world, double x, double y, double z) {
        Random random = new Random();
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
