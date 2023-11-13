package teamdraco.finsandstails.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamdraco.finsandstails.FTConfig;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.IHydrate;
import teamdraco.finsandstails.common.entities.WanderingSailorEntity;
import teamdraco.finsandstails.common.entities.WherbleEntity;
import teamdraco.finsandstails.registry.FTEnchantments;
import teamdraco.finsandstails.registry.FTTags;

import java.util.List;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void onPlayerFished(ItemFishedEvent event) {
        Player player = event.getEntity();
        InteractionHand mainHand = InteractionHand.MAIN_HAND;
        InteractionHand offHand = InteractionHand.OFF_HAND;

        if ((player.getItemInHand(mainHand).is(Items.FISHING_ROD) && player.getItemInHand(offHand).is(FTTags.SPINDLY_GEM_CRABS)) || (player.getItemInHand(offHand).is(Items.FISHING_ROD) && player.getItemInHand(mainHand).is(FTTags.SPINDLY_GEM_CRABS))) {
            List<ItemStack> drops = event.getDrops();
            List<ItemStack> items = player.getServer().getLootTables().get(BuiltInLootTables.FISHING_TREASURE).getRandomItems(new LootContext.Builder((ServerLevel) player.level).withRandom(player.getRandom()).create(LootContextParamSets.EMPTY));

            if (drops.get(0).getEntityRepresentation() instanceof ItemEntity entity) {
                entity.setItem(items.get(0));
                System.out.println(items.get(0));
            }

        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickEntity(PlayerInteractEvent.EntityInteract event) {
        InteractionHand hand = InteractionHand.OFF_HAND;
        Entity target = event.getTarget();
        Player player = event.getEntity();
        ItemStack offhandItem = player.getItemBySlot(EquipmentSlot.OFFHAND);

        if (offhandItem.is(FTTags.CLAW_GAUNTLETS)) {
            player.swing(hand);
            target.hurt(DamageSource.playerAttack(player), (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE));
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickAir(PlayerInteractEvent event) {
        InteractionHand hand = event.getHand();
        Player player = event.getEntity();
        ItemStack offhandItem = player.getItemBySlot(EquipmentSlot.OFFHAND);

        if (offhandItem.is(FTTags.CLAW_GAUNTLETS)) {
            player.swing(hand);
        }
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Wolf || entity instanceof Fox) {
            ((PathfinderMob) event.getEntity()).targetSelector.addGoal(0, new NearestAttackableTargetGoal<>((PathfinderMob) entity, WherbleEntity.class, true));
        }
        if (entity instanceof AbstractVillager) {
            ((PathfinderMob) event.getEntity()).goalSelector.addGoal(0, new LookAtPlayerGoal((PathfinderMob) entity, WanderingSailorEntity.class, 6.0F));
        }
    }

    @SubscribeEvent
    public static void onHitWaterPotion(ProjectileImpactEvent event) {
        if (event.getProjectile() instanceof ThrownPotion potionEntity) {
            ItemStack itemstack = potionEntity.getItem();
            Potion potion = PotionUtils.getPotion(itemstack);
            List<MobEffectInstance> list = PotionUtils.getMobEffects(itemstack);
            boolean flag = potion == Potions.WATER && list.isEmpty();
            if (flag) {
                applyWater(potionEntity);
            }
        }
    }

    private static void applyWater(Projectile projectile) {
        AABB aabb = projectile.getBoundingBox().inflate(4.0D, 2.0D, 4.0D);

        for (LivingEntity livingEntity : projectile.level.getEntitiesOfClass(LivingEntity.class, aabb)) {
            if (livingEntity instanceof IHydrate hydrate) {
                hydrate.rehydrate();
            }
        }

    }

    @SubscribeEvent
    public static void crabsFavorXPDrops(LivingExperienceDropEvent event) {
        LivingEntity attacker = event.getAttackingPlayer();

        if (attacker != null) {
            ItemStack heldItem = attacker.getMainHandItem();
            ItemStack heldItemOffhand = attacker.getOffhandItem();
            if (EnchantmentHelper.getEnchantments(heldItem).containsKey(FTEnchantments.CRABS_FAVOR.get()) && EnchantmentHelper.getEnchantments(heldItemOffhand).containsKey(FTEnchantments.CRABS_FAVOR.get())) {
                int i = EnchantmentHelper.getItemEnchantmentLevel(FTEnchantments.CRABS_FAVOR.get(), event.getAttackingPlayer().getItemInHand(InteractionHand.MAIN_HAND));
                event.setDroppedExperience(event.getOriginalExperience() * i + attacker.getCommandSenderWorld().random.nextInt(3));
            }
        }
    }

    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        LootPool pool = event.getTable().getPool("main");
        if (name.equals(BuiltInLootTables.FISHING_FISH)) {
            if (FTConfig.Common.INSTANCE.finsFishingLoot.get()) {
                addEntry(pool, getInjectEntry(new ResourceLocation(FinsAndTails.MOD_ID, "inject/fishing"), 10, 1));
            }
        }
        if (name.equals(BuiltInLootTables.FISHERMAN_GIFT)) {
            addEntry(pool, getInjectEntry(new ResourceLocation("finsandtails:inject/fisherman_gift"), 15, 1));
        }
    }

    private static LootPoolEntryContainer getInjectEntry(ResourceLocation location, int weight, int quality) {
        return LootTableReference.lootTableReference(location).setWeight(weight).setQuality(quality).build();
    }

    private static void addEntry(LootPool pool, LootPoolEntryContainer entry) {
        LootPoolEntryContainer[] newEntries = new LootPoolEntryContainer[pool.entries.length + 1];
        System.arraycopy(pool.entries, 0, newEntries, 0, pool.entries.length);
        newEntries[pool.entries.length] = entry;

        pool.entries = newEntries;
    }
}
