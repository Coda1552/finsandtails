package teamdraco.finsandstails.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.FinsConfig;
import teamdraco.finsandstails.common.entities.WanderingSailorEntity;
import teamdraco.finsandstails.common.entities.WherbleEntity;
import teamdraco.finsandstails.registry.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent event) {
        if (event.getEntityLiving().getItemBySlot(EquipmentSlot.CHEST).getItem() == FTItems.GOPJET_JETPACK.get()) {
            if (event.getSource() == DamageSource.FALL) {
                event.setAmount(event.getAmount() / 2f);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickEntity(PlayerInteractEvent.EntityInteract event) {
        InteractionHand hand = InteractionHand.OFF_HAND;
        Entity target = event.getTarget();
        Player player = event.getPlayer();
        ItemStack offhandItem = player.getItemBySlot(EquipmentSlot.OFFHAND);

        if (offhandItem.is(FTTags.CLAW_GAUNTLETS)) {
            player.swing(hand);
            target.hurt(DamageSource.playerAttack(player), (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE));
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickAir(PlayerInteractEvent event) {
        InteractionHand hand = event.getHand();
        Player player = event.getPlayer();
        ItemStack offhandItem = player.getItemBySlot(EquipmentSlot.OFFHAND);

        if (offhandItem.is(FTTags.CLAW_GAUNTLETS)) {
            player.swing(hand);
        }
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Wolf || entity instanceof Fox) {
            ((PathfinderMob) event.getEntity()).targetSelector.addGoal(0, new NearestAttackableTargetGoal<>((PathfinderMob) entity, WherbleEntity.class, true));
        }
        if (entity instanceof AbstractVillager) {
            ((PathfinderMob) event.getEntity()).goalSelector.addGoal(0, new LookAtPlayerGoal((PathfinderMob) entity, WanderingSailorEntity.class, 6.0F));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerBiomes(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.SWAMP) {
            event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.SWAMP_MUCKER.get(), FinsConfig.Common.INSTANCE.swampMuckerSpawnWeight.get(), 2, 4));
            event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.FLATBACK_SUCKER.get(), FinsConfig.Common.INSTANCE.flatbackSuckerSpawnWeight.get(), 1, 1));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.MUDHORSE.get(), FinsConfig.Common.INSTANCE.mudhorseSpawnWeight.get(), 2, 3));
            event.getGeneration().getStructures().add(() -> FTConfiguredStructures.CONFIGURED_SAILORS_SHIP);
        }

        if (event.getCategory() == Biome.BiomeCategory.JUNGLE) {
            event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.PEA_WEE.get(), FinsConfig.Common.INSTANCE.peaWeeSpawnWeight.get(), 1, 3));
            event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.VIBRA_WEE.get(), FinsConfig.Common.INSTANCE.vibraWeeSpawnWeight.get(), 2, 5));
        }

        if (event.getCategory() == Biome.BiomeCategory.EXTREME_HILLS) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.SIDEROL_WHISKERED_SNAIL.get(), FinsConfig.Common.INSTANCE.siderolWhiskeredSnailSpawnWeight.get(), 1, 2));
        }

        if (event.getCategory() == Biome.BiomeCategory.BEACH) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.PENGLIL.get(), FinsConfig.Common.INSTANCE.penglilSpawnWeight.get(), 3, 5));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.RUBBER_BELLY_GLIDER.get(), FinsConfig.Common.INSTANCE.rubberBellyGliderSpawnWeight.get(), 1, 2));
        }

        if (event.getCategory() == Biome.BiomeCategory.FOREST) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.FLATBACK_LEAF_SNAIL.get(), FinsConfig.Common.INSTANCE.flatbackLeafSnailSpawnWeight.get(), 1, 2));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.GLASS_SKIPPER.get(), FinsConfig.Common.INSTANCE.glassSkipperSpawnWeight.get(), 1, 1));
        }

        if (event.getCategory() == Biome.BiomeCategory.ICY) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.WHERBLE.get(), FinsConfig.Common.INSTANCE.wherbleSpawnWeight.get(), 2, 6));
        }

        if (event.getName() != null) {
            String name = event.getName().getPath();
            if (name.equals("cold_ocean") || name.equals("deep_cold_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.BLU_WEE.get(), FinsConfig.Common.INSTANCE.bluWeeSpawnWeight.get(), 4, 8));
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.TEAL_ARROWFISH.get(), FinsConfig.Common.INSTANCE.tealArrowfishSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.PHANTOM_NUDIBRANCH.get(), FinsConfig.Common.INSTANCE.phantomNudibranchSpawnWeight.get(), 1, 1));
            }

            if (name.equals("warm_ocean") || name.equals("deep_warm_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.BANDED_REDBACK_SHRIMP.get(), FinsConfig.Common.INSTANCE.bandedRedbackShrimpSpawnWeight.get(), 3, 3));
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.ORNATE_BUGFISH.get(), FinsConfig.Common.INSTANCE.ornateBugfishSpawnWeight.get(), 5, 5));
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.SPINDLY_GEM_CRAB.get(), FinsConfig.Common.INSTANCE.spindlyGemCrabSpawnWeight.get(), 1, 3));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.RUBBER_BELLY_GLIDER.get(), FinsConfig.Common.INSTANCE.rubberBellyGliderSpawnWeight.get(), 1, 2));
            }

            if (name.equals("ocean") || name.equals("deep_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.HIGH_FINNED_BLUE.get(), FinsConfig.Common.INSTANCE.highFinnedBlueSpawnWeight.get(), 6, 12));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.GOPJET.get(), FinsConfig.Common.INSTANCE.gopjetSpawnWeight.get(), 2, 3));
                event.getGeneration().getStructures().add(() -> FTConfiguredStructures.CONFIGURED_SAILORS_SHIP);
            }

            if (name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.RED_BULL_CRAB.get(), FinsConfig.Common.INSTANCE.redBullCrabSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.WHITE_BULL_CRAB.get(), FinsConfig.Common.INSTANCE.whiteBullCrabSpawnWeight.get(), 2, 4));
            }

            if (name.equals("frozen_ocean") || name.equals("deep_frozen_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.NIGHT_LIGHT_SQUID.get(), FinsConfig.Common.INSTANCE.nightLightSquidSpawnWeight.get(), 1, 2));
            }

            if (name.equals("river")) {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.WEE_WEE.get(), FinsConfig.Common.INSTANCE.weeWeeSpawnWeight.get(), 2, 6));
                event.getSpawns().getSpawner(MobCategory.AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.RIVER_PEBBLE_SNAIL.get(), FinsConfig.Common.INSTANCE.riverPebbleSnailSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.GOLDEN_RIVER_RAY.get(), FinsConfig.Common.INSTANCE.goldenRiverRaySpawnWeight.get(), 1, 1));
            }

            if (name.equals("flower_forest")) {
                event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.GLASS_SKIPPER.get(), FinsConfig.Common.INSTANCE.glassSkipperSpawnWeight.get() * 2, 2, 6));
            }
        }
    }

    @SubscribeEvent
    public static void crabsFavorXPDrops(LivingExperienceDropEvent event) {
        Entity attacker = event.getAttackingPlayer();

        if (attacker != null) {
            LivingEntity livingEntity = (LivingEntity) attacker;
            ItemStack heldItem = livingEntity.getMainHandItem();
            ItemStack heldItemOffhand = livingEntity.getOffhandItem();
            if (EnchantmentHelper.getEnchantments(heldItem).containsKey(FTEnchantments.CRABS_FAVOR.get()) && EnchantmentHelper.getEnchantments(heldItemOffhand).containsKey(FTEnchantments.CRABS_FAVOR.get())) {
                int i = EnchantmentHelper.getItemEnchantmentLevel(FTEnchantments.CRABS_FAVOR.get(), event.getAttackingPlayer().getItemInHand(InteractionHand.MAIN_HAND));
                event.setDroppedExperience(event.getOriginalExperience() * i + attacker.getCommandSenderWorld().random.nextInt(3));
            }
        }
    }

    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) throws IllegalAccessException {
        ResourceLocation name = event.getName();
        if (name.equals(BuiltInLootTables.FISHING_FISH)) {
            LootPool pool = event.getTable().getPool("main");
            if (FinsConfig.Common.INSTANCE.finsFishingLoot.get()) {
                addEntry(pool, getInjectEntry(new ResourceLocation(FinsAndTails.MOD_ID, "inject/fishing"), 10, 1));
            }
            if (name.equals(BuiltInLootTables.FISHERMAN_GIFT)) {
                addEntry(pool, getInjectEntry(new ResourceLocation("fins:inject/fisherman_gift"), 15, 1));
            }
        }
    }

    private static LootPoolEntryContainer getInjectEntry(ResourceLocation location, int weight, int quality) {
        return LootTableReference.lootTableReference(location).setWeight(weight).setQuality(quality).build();
    }

    private static void addEntry(LootPool pool, LootPoolEntryContainer entry) throws IllegalAccessException {
        List<LootPoolEntryContainer> lootEntries = (List<LootPoolEntryContainer>) ObfuscationReflectionHelper.findField(LootPool.class, "field_186453_a").get(pool);
        if (lootEntries.stream().anyMatch(e -> e == entry)) {
            throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
        }
        lootEntries.add(entry);
    }
    
    // Thanks to WolfShotz for helping with the trade code
    @SubscribeEvent
    public static void addWandererTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> list = event.getGenericTrades();

        list.add(cdForItems(FTItems.HIGH_FINNED_BLUE_BUCKET.get(), 1, 4, 1));
        list.add(cdForItems(FTItems.PEA_WEE_BUCKET.get(), 1, 4, 2));
        list.add(cdForItems(FTItems.BLU_WEE_BUCKET.get(), 1, 4, 1));
    }

    private static VillagerTrades.ItemListing cdForItems(ItemStack selling, int maxUses, int xp) {
        return new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 5), selling, maxUses, xp, 0);
    }

    private static VillagerTrades.ItemListing cdForItems(Item item, int count, int maxUses, int xp) {
        return cdForItems(new ItemStack(item, count), maxUses, xp);
    }

    private static class ItemsForItemsTrade implements VillagerTrades.ItemListing {
        private final ItemStack buying1, buying2, selling;
        private final int maxUses, xp;
        private final float priceMultiplier;

        public ItemsForItemsTrade(ItemStack buying1, ItemStack buying2, ItemStack selling, int maxUses, int xp, float priceMultiplier) {
            this.buying1 = buying1;
            this.buying2 = buying2;
            this.selling = selling;
            this.maxUses = maxUses;
            this.xp = xp;
            this.priceMultiplier = priceMultiplier;
        }

        public ItemsForItemsTrade(ItemStack buying1, ItemStack selling, int maxUses, int xp, float priceMultiplier) {
            this(buying1, ItemStack.EMPTY, selling, maxUses, xp, priceMultiplier);
        }

        @Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, Random rand) {
            return new MerchantOffer(buying1, buying2, selling, maxUses, xp, priceMultiplier);
        }
    }
}
