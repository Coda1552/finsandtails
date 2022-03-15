package teamdraco.finsandstails.common.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SpindlyGauntletItem extends CrabGauntletItem {

    public SpindlyGauntletItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int t) {
        if (entity instanceof Player player) {
            int i = this.getUseDuration(stack) - t;
            if (i < 0) return;


            List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(40F));

            for (LivingEntity potentialTarget : list) {
                if (player.hasLineOfSight(potentialTarget) && lookAt(player, potentialTarget, 1.0)) {
                    for (int j = 0; j < 20; j++) {

                        Vec3 playerPos = player.position();
                        Vec3 targetPos = potentialTarget.position();

                        final Vector3f color = new Vector3f(Vec3.fromRGB24(6485602));

                        double x = targetPos.x - playerPos.x;
                        double y = targetPos.y - playerPos.y;
                        double z = targetPos.z - playerPos.z;

                        System.out.println(x);
                        System.out.println(y);
                        System.out.println(z);

                        double xSpeed = 0;
                        double ySpeed = 0;
                        double zSpeed = 0;

                        float size = Math.max(j * 0.5F, 0.5F);

                        level.addParticle(new DustParticleOptions(color, size), x, y, z, xSpeed, ySpeed, zSpeed);
                    }
                }
            }
        }
    }

    private static boolean lookAt(Player player, Entity entity, double range) {
        Vec3 vec3 = player.getViewVector(1.0F).normalize();
        Vec3 vec31 = new Vec3(entity.getX() - player.getX(), entity.getY() - player.getEyeY(), entity.getZ() - player.getZ());
        double d0 = vec31.length();
        vec31 = vec31.normalize();
        double d1 = vec3.dot(vec31);
        return d1 > 1.0D - range / d0;
    }

    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 200;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }
}
