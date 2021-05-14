package teamdraco.fins.common;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.FinsConfig;
import teamdraco.fins.init.FinsEnchantments;
import teamdraco.fins.init.FinsEntities;
import teamdraco.fins.init.FinsItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent event) {
        if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == FinsItems.GOPJET_JETPACK.get()) {
            if (event.getSource() == DamageSource.FALL) {
                event.setAmount(event.getAmount() / 2f);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerBiomes(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.Category.SWAMP) {
            event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.SWAMP_MUCKER.get(), FinsConfig.Common.INSTANCE.swampMuckerSpawnWeight.get(), 2, 4));
            event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.FLATBACK_SUCKER.get(), FinsConfig.Common.INSTANCE.flatbackSuckerSpawnWeight.get(), 1, 1));
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.MUDHORSE.get(), FinsConfig.Common.INSTANCE.mudhorseSpawnWeight.get(), 2, 3));
        }

        if (event.getCategory() == Biome.Category.JUNGLE) {
            event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.PEA_WEE.get(), FinsConfig.Common.INSTANCE.peaWeeSpawnWeight.get(), 1, 3));
            event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.VIBRA_WEE.get(), FinsConfig.Common.INSTANCE.vibraWeeSpawnWeight.get(), 2, 5));
        }

        if (event.getCategory() == Biome.Category.EXTREME_HILLS) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.SIDEROL_WHISKERED_SNAIL.get(), FinsConfig.Common.INSTANCE.siderolWhiskeredSnailSpawnWeight.get(), 1, 2));
        }

        if (event.getCategory() == Biome.Category.BEACH) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.PENGLIL.get(), FinsConfig.Common.INSTANCE.penglilSpawnWeight.get(), 3, 5));
        }

        if (event.getCategory() == Biome.Category.FOREST) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.FLATBACK_LEAF_SNAIL.get(), FinsConfig.Common.INSTANCE.flatbackLeafSnailSpawnWeight.get(), 1, 2));
        }

        if (event.getName() != null) {
            String name = event.getName().getPath();
            if (name.equals("cold_ocean") || name.equals("deep_cold+_ocean")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.BLU_WEE.get(), FinsConfig.Common.INSTANCE.bluWeeSpawnWeight.get(), 4, 8));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.TEAL_ARROWFISH.get(), FinsConfig.Common.INSTANCE.tealArrowfishSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.PHANTOM_NUDIBRANCH.get(), FinsConfig.Common.INSTANCE.phantomNudibranchSpawnWeight.get(), 1, 1));
            }

            if (name.equals("warm_ocean") || name.equals("deep_warm_ocean")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.BANDED_REDBACK_SHRIMP.get(), FinsConfig.Common.INSTANCE.bandedRedbackShrimpSpawnWeight.get(), 3, 3));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.ORNATE_BUGFISH.get(), FinsConfig.Common.INSTANCE.ornateBugfishSpawnWeight.get(), 5, 5));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.SPINDLY_GEM_CRAB.get(), FinsConfig.Common.INSTANCE.spindlyGemCrabSpawnWeight.get(), 1, 3));
                event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.RUBBER_BELLY_GLIDER.get(), FinsConfig.Common.INSTANCE.rubberBellyGliderSpawnWeight.get(), 1, 2));
            }

            if (name.equals("ocean") || name.equals("deep_ocean")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.HIGH_FINNED_BLUE.get(), FinsConfig.Common.INSTANCE.highFinnedBlueSpawnWeight.get(), 6, 12));
                event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.GOPJET.get(), FinsConfig.Common.INSTANCE.gopjetSpawnWeight.get(), 2, 3));
            }

            if (name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.RED_BULL_CRAB.get(), FinsConfig.Common.INSTANCE.redBullCrabSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.WHITE_BULL_CRAB.get(), FinsConfig.Common.INSTANCE.whiteBullCrabSpawnWeight.get(), 2, 4));
            }

            if (name.equals("frozen_ocean") || name.equals("deep_frozen_ocean")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.NIGHT_LIGHT_SQUID.get(), FinsConfig.Common.INSTANCE.nightLightSquidSpawnWeight.get(), 1, 2));
            }

            if (name.equals("river")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.WEE_WEE.get(), FinsConfig.Common.INSTANCE.weeWeeSpawnWeight.get(), 2, 6));
                event.getSpawns().getSpawner(EntityClassification.AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.RIVER_PEBBLE_SNAIL.get(), FinsConfig.Common.INSTANCE.riverPebbleSnailSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.GOLDEN_RIVER_RAY.get(), FinsConfig.Common.INSTANCE.goldenRiverRaySpawnWeight.get(), 1, 1));
            }
        }
    }

    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) throws IllegalAccessException {
        ResourceLocation name = event.getName();
        if (name.equals(LootTables.GAMEPLAY_FISHING)) {
            LootPool pool = event.getTable().getPool("main");
            addEntry(pool, getInjectEntry(new ResourceLocation("fins:inject/fishing"), 10, 1));
        }
    }

    private static LootEntry getInjectEntry(ResourceLocation location, int weight, int quality) {
        return TableLootEntry.builder(location).weight(weight).quality(quality).build();
    }

    private static void addEntry(LootPool pool, LootEntry entry) throws IllegalAccessException {
        List<LootEntry> lootEntries = ObfuscationReflectionHelper.getPrivateValue(LootPool.class, pool, "field_186453_a");
        if (lootEntries != null) {
            if (lootEntries.stream().anyMatch(e -> e == entry)) {
                throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
            }
            lootEntries.add(entry);
        }
    }

    //Thanks to Wolf for helping with the trade code
    @SubscribeEvent
    public static void addWandererTrades(WandererTradesEvent event) {
        List<VillagerTrades.ITrade> list = event.getGenericTrades();

        list.add(cdForItems(FinsItems.HIGH_FINNED_BLUE_BUCKET.get(), 1, 4, 1));
        list.add(cdForItems(FinsItems.PEA_WEE_BUCKET.get(), 1, 4, 2));
        list.add(cdForItems(FinsItems.BLU_WEE_BUCKET.get(), 1, 4, 1));
        list.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 6), new ItemStack(FinsItems.SPINDLY_GEM_CRAB_GEM.get(), 1), 2, 4, 1.5f));
        list.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 4), new ItemStack(FinsItems.FWIN.get(), 1), 2, 3, 1.5f));
        list.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 7), new ItemStack(FinsItems.MUDHORSE_LEATHER.get(), 1), 2, 4, 1.5f));
        list.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(FinsItems.FLATBACK_LEAF_SNAIL_SHELL.get(), 1), 2, 4, 1.5f));
        list.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(FinsItems.RIVER_PEBBLE_SNAIL_SHELL.get(), 1), 2, 4, 1.5f));
        list.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 4), new ItemStack(FinsItems.SIDEROL_WHISKERED_SNAIL_SHELL.get(), 1), 2, 4, 1.5f));
        list.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 4), new ItemStack(FinsItems.GOPJET_JET.get(), 1), 2, 4, 2.0f));
    }

    private static VillagerTrades.ITrade cdForItems(ItemStack selling, int maxUses, int xp) {
        return new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 5), selling, maxUses, xp, 0);
    }

    private static VillagerTrades.ITrade cdForItems(Item item, int count, int maxUses, int xp) {
        return cdForItems(new ItemStack(item, count), maxUses, xp);
    }

    private static class ItemsForItemsTrade implements VillagerTrades.ITrade {
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
