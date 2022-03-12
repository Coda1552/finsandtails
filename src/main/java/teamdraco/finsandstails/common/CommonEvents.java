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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamdraco.finsandstails.FTConfig;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.PenglilEntity;
import teamdraco.finsandstails.common.entities.WanderingSailorEntity;
import teamdraco.finsandstails.common.entities.WherbleEntity;
import teamdraco.finsandstails.common.entities.item.TealArrowfishArrowEntity;
import teamdraco.finsandstails.registry.*;

import java.util.List;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent event) {
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();

        if (entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == FTItems.GOPJET_JETPACK.get()) {
            if (source == DamageSource.FALL) {
                event.setAmount(event.getAmount() / 2f);
            }
        }

        if (source.isProjectile() && source.getDirectEntity() instanceof TealArrowfishArrowEntity) {
            List<PenglilEntity> penglils = entity.level.getEntitiesOfClass(PenglilEntity.class, entity.getBoundingBox().inflate(25));

            for (PenglilEntity penglil : penglils) {

                if (penglil.isTame() && !penglil.getOwner().equals(entity)) return;

                penglil.setTarget(entity);
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
            event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.SWAMP_MUCKER.get(), FTConfig.Common.INSTANCE.swampMuckerSpawnWeight.get(), 2, 4));
            event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.FLATBACK_SUCKER.get(), FTConfig.Common.INSTANCE.flatbackSuckerSpawnWeight.get(), 1, 1));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.MUDHORSE.get(), FTConfig.Common.INSTANCE.mudhorseSpawnWeight.get(), 2, 3));
        }

        if (event.getCategory() == Biome.BiomeCategory.JUNGLE) {
            event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.PEA_WEE.get(), FTConfig.Common.INSTANCE.peaWeeSpawnWeight.get(), 1, 3));
            event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.VIBRA_WEE.get(), FTConfig.Common.INSTANCE.vibraWeeSpawnWeight.get(), 2, 5));
        }

        if (event.getCategory() == Biome.BiomeCategory.EXTREME_HILLS) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.SIDEROL_WHISKERED_SNAIL.get(), FTConfig.Common.INSTANCE.siderolWhiskeredSnailSpawnWeight.get(), 1, 2));
        }

        if (event.getCategory() == Biome.BiomeCategory.BEACH) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.PENGLIL.get(), FTConfig.Common.INSTANCE.penglilSpawnWeight.get(), 3, 5));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.RUBBER_BELLY_GLIDER.get(), FTConfig.Common.INSTANCE.rubberBellyGliderSpawnWeight.get(), 1, 2));
        }

        if (event.getCategory() == Biome.BiomeCategory.FOREST) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.FLATBACK_LEAF_SNAIL.get(), FTConfig.Common.INSTANCE.flatbackLeafSnailSpawnWeight.get(), 1, 2));
        }

        if (event.getCategory() == Biome.BiomeCategory.ICY) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.WHERBLE.get(), FTConfig.Common.INSTANCE.wherbleSpawnWeight.get(), 2, 6));
        }

        if (event.getName() != null) {
            String name = event.getName().getPath();
            if (name.equals("cold_ocean") || name.equals("deep_cold_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.BLU_WEE.get(), FTConfig.Common.INSTANCE.bluWeeSpawnWeight.get(), 4, 8));
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.TEAL_ARROWFISH.get(), FTConfig.Common.INSTANCE.tealArrowfishSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.PHANTOM_NUDIBRANCH.get(), FTConfig.Common.INSTANCE.phantomNudibranchSpawnWeight.get(), 1, 1));
            }

            if (name.equals("warm_ocean") || name.equals("deep_warm_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.BANDED_REDBACK_SHRIMP.get(), FTConfig.Common.INSTANCE.bandedRedbackShrimpSpawnWeight.get(), 3, 3));
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.ORNATE_BUGFISH.get(), FTConfig.Common.INSTANCE.ornateBugfishSpawnWeight.get(), 5, 5));
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.SPINDLY_GEM_CRAB.get(), FTConfig.Common.INSTANCE.spindlyGemCrabSpawnWeight.get(), 1, 3));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.RUBBER_BELLY_GLIDER.get(), FTConfig.Common.INSTANCE.rubberBellyGliderSpawnWeight.get(), 1, 2));
            }

            if (name.equals("ocean") || name.equals("deep_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.HIGH_FINNED_BLUE.get(), FTConfig.Common.INSTANCE.highFinnedBlueSpawnWeight.get(), 6, 12));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.GOPJET.get(), FTConfig.Common.INSTANCE.gopjetSpawnWeight.get(), 2, 3));
            }

            if (name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.RED_BULL_CRAB.get(), FTConfig.Common.INSTANCE.redBullCrabSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.WHITE_BULL_CRAB.get(), FTConfig.Common.INSTANCE.whiteBullCrabSpawnWeight.get(), 2, 4));
            }

            if (name.equals("frozen_ocean") || name.equals("deep_frozen_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.NIGHT_LIGHT_SQUID.get(), FTConfig.Common.INSTANCE.nightLightSquidSpawnWeight.get(), 1, 2));
            }

            if (name.equals("river")) {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.WEE_WEE.get(), FTConfig.Common.INSTANCE.weeWeeSpawnWeight.get(), 2, 6));
                event.getSpawns().getSpawner(MobCategory.AMBIENT).add(new MobSpawnSettings.SpawnerData(FTEntities.RIVER_PEBBLE_SNAIL.get(), FTConfig.Common.INSTANCE.riverPebbleSnailSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(FTEntities.GOLDEN_RIVER_RAY.get(), FTConfig.Common.INSTANCE.goldenRiverRaySpawnWeight.get(), 1, 1));
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
