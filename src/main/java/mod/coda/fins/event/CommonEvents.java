package mod.coda.fins.event;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.init.FinsEntities;
import mod.coda.fins.init.FinsItems;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;

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
            event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.SWAMP_MUCKER.get(), 3, 2, 4));
            event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.FLATBACK_SUCKER.get(), 3, 1, 1));
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.MUDHORSE.get(), 4, 2, 3));
        }

        if (event.getCategory() == Biome.Category.JUNGLE) {
            event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.PEA_WEE.get(), 40, 1, 3));
        }

        if (event.getCategory() == Biome.Category.BEACH) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.PENGLIL.get(), 2, 3, 5));
        }

        if (event.getName() != null) {
            String name = event.getName().getPath();
            if (name.equals("cold_ocean") || event.getName().equals(new ResourceLocation("minecraft", "deep_cold_ocean"))) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.BLU_WEE.get(), 20, 4, 8));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.TEAL_ARROWFISH.get(), 15, 1, 1));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.PHANTOM_NUDIBRANCH.get(), 3, 1, 1));
            }

            if (name.equals("warm_ocean") || event.getName().equals(new ResourceLocation("minecraft", "deep_warm_ocean"))) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.BANDED_REDBACK_SHRIMP.get(), 10, 3, 3));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.ORNATE_BUGFISH.get(), 3, 5, 5));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.SPINDLY_GEM_CRAB.get(), 7, 1, 3));
            }

            if (name.equals("deep_ocean") || event.getName().equals(new ResourceLocation("minecraft", "ocean"))) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.HIGH_FINNED_BLUE.get(), 8, 6, 12));
            }

            if (name.equals("lukewarm_ocean") || event.getName().equals(new ResourceLocation("minecraft", "deep_lukewarm_ocean"))) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.RED_BULL_CRAB.get(), 2, 1, 1));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.WHITE_BULL_CRAB.get(), 4, 2, 4));
            }
        }

        if (event.getCategory() == Biome.Category.FOREST) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.FLATBACK_LEAF_SNAIL.get(), 8, 1, 2));
        }
    }

    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) throws IllegalAccessException {
        ResourceLocation name = event.getName();
        if (name.equals(LootTables.GAMEPLAY_FISHING)) {
            LootPool pool = event.getTable().getPool("main");
            if (pool != null) {
                addEntry(pool, getInjectEntry(new ResourceLocation("fins:inject/fishing"), 10, 1));
            }
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
}
