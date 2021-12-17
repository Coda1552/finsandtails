package teamdraco.fins.common;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;
import net.minecraft.world.server.ServerWorld;
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
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.FinsConfig;
import teamdraco.fins.common.entities.WanderingSailorEntity;
import teamdraco.fins.common.entities.WherbleEntity;
import teamdraco.fins.init.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent event) {
        if (event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem() == FinsItems.GOPJET_JETPACK.get()) {
            if (event.getSource() == DamageSource.FALL) {
                event.setAmount(event.getAmount() / 2f);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickEntity(PlayerInteractEvent.EntityInteract event) {
        Hand hand = Hand.OFF_HAND;
        Entity target = event.getTarget();
        PlayerEntity player = event.getPlayer();
        ItemStack offhandItem = player.getItemBySlot(EquipmentSlotType.OFFHAND);

        if (offhandItem.getItem().is(FinsTags.CLAW_GAUNTLETS)) {
            player.swing(hand);
            attack(player, target);
            target.hurt(DamageSource.playerAttack(player), (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE));
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickAir(PlayerInteractEvent event) {
        Hand hand = event.getHand();
        PlayerEntity player = event.getPlayer();
        ItemStack offhandItem = player.getItemBySlot(EquipmentSlotType.OFFHAND);

        if (offhandItem.getItem().is(FinsTags.CLAW_GAUNTLETS)) {
            player.swing(hand);
        }
    }

    // This is literally all from PlayerEntity...
    public static void attack(PlayerEntity player, Entity p_71059_1_) {
        if (!net.minecraftforge.common.ForgeHooks.onPlayerAttackTarget(player, p_71059_1_)) return;
        if (p_71059_1_.isAttackable()) {
            if (!p_71059_1_.skipAttackInteraction(player)) {
                float f = (float)player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                float f1;
                if (p_71059_1_ instanceof LivingEntity) {
                    f1 = EnchantmentHelper.getDamageBonus(player.getOffhandItem(), ((LivingEntity)p_71059_1_).getMobType());
                } else {
                    f1 = EnchantmentHelper.getDamageBonus(player.getOffhandItem(), CreatureAttribute.UNDEFINED);
                }

                float f2 = player.getAttackStrengthScale(0.5F);
                f = f * (0.2F + f2 * f2 * 0.8F);
                f1 = f1 * f2;
                player.resetAttackStrengthTicker();
                if (f > 0.0F || f1 > 0.0F) {
                    boolean flag = f2 > 0.9F;
                    boolean flag1 = false;
                    int i = 0;
                    i = i + EnchantmentHelper.getKnockbackBonus(player);
                    if (player.isSprinting() && flag) {
                        player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_KNOCKBACK, player.getSoundSource(), 1.0F, 1.0F);
                        ++i;
                        flag1 = true;
                    }

                    boolean flag2 = flag && player.fallDistance > 0.0F && !player.isOnGround() && !player.onClimbable() && !player.isInWater() && !player.hasEffect(Effects.BLINDNESS) && !player.isPassenger() && p_71059_1_ instanceof LivingEntity;
                    flag2 = flag2 && !player.isSprinting();
                    net.minecraftforge.event.entity.player.CriticalHitEvent hitResult = net.minecraftforge.common.ForgeHooks.getCriticalHit(player, p_71059_1_, flag2, flag2 ? 1.5F : 1.0F);
                    flag2 = hitResult != null;
                    if (flag2) {
                        f *= hitResult.getDamageModifier();
                    }

                    f = f + f1;
                    boolean flag3 = false;
                    double d0 = player.walkDist - player.walkDistO;
                    if (flag && !flag2 && !flag1 && player.isOnGround() && d0 < (double)player.getSpeed()) {
                        ItemStack itemstack = player.getItemInHand(Hand.OFF_HAND);
                        if (itemstack.getItem() instanceof SwordItem) {
                            flag3 = true;
                        }
                    }

                    float f4 = 0.0F;
                    boolean flag4 = false;
                    int j = EnchantmentHelper.getFireAspect(player);
                    if (p_71059_1_ instanceof LivingEntity) {
                        f4 = ((LivingEntity)p_71059_1_).getHealth();
                        if (j > 0 && !p_71059_1_.isOnFire()) {
                            flag4 = true;
                            p_71059_1_.setSecondsOnFire(1);
                        }
                    }

                    Vector3d vector3d = p_71059_1_.getDeltaMovement();
                    boolean flag5 = p_71059_1_.hurt(DamageSource.playerAttack(player), f);
                    if (flag5) {
                        if (i > 0) {
                            if (p_71059_1_ instanceof LivingEntity) {
                                ((LivingEntity)p_71059_1_).knockback((float)i * 0.5F, MathHelper.sin(player.yRot * ((float)Math.PI / 180F)), -MathHelper.cos(player.yRot * ((float)Math.PI / 180F)));
                            } else {
                                p_71059_1_.push(-MathHelper.sin(player.yRot * ((float)Math.PI / 180F)) * (float)i * 0.5F, 0.1D, MathHelper.cos(player.yRot * ((float)Math.PI / 180F)) * (float)i * 0.5F);
                            }

                            player.setDeltaMovement(player.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
                            player.setSprinting(false);
                        }

                        if (flag3) {
                            float f3 = 1.0F + EnchantmentHelper.getSweepingDamageRatio(player) * f;

                            for(LivingEntity livingentity : player.level.getEntitiesOfClass(LivingEntity.class, p_71059_1_.getBoundingBox().inflate(1.0D, 0.25D, 1.0D))) {
                                if (livingentity != player && livingentity != p_71059_1_ && !player.isAlliedTo(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity)livingentity).isMarker()) && player.distanceToSqr(livingentity) < 9.0D) {
                                    livingentity.knockback(0.4F, MathHelper.sin(player.yRot * ((float)Math.PI / 180F)), -MathHelper.cos(player.yRot * ((float)Math.PI / 180F)));
                                    livingentity.hurt(DamageSource.playerAttack(player), f3);
                                }
                            }

                            player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(), 1.0F, 1.0F);
                            player.sweepAttack();
                        }

                        if (p_71059_1_ instanceof ServerPlayerEntity && p_71059_1_.hurtMarked) {
                            ((ServerPlayerEntity)p_71059_1_).connection.send(new SEntityVelocityPacket(p_71059_1_));
                            p_71059_1_.hurtMarked = false;
                            p_71059_1_.setDeltaMovement(vector3d);
                        }

                        if (flag2) {
                            player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, player.getSoundSource(), 1.0F, 1.0F);
                            player.crit(p_71059_1_);
                        }

                        if (!flag2 && !flag3) {
                            if (flag) {
                                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_STRONG, player.getSoundSource(), 1.0F, 1.0F);
                            } else {
                                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_WEAK, player.getSoundSource(), 1.0F, 1.0F);
                            }
                        }

                        if (f1 > 0.0F) {
                            player.magicCrit(p_71059_1_);
                        }

                        player.setLastHurtMob(p_71059_1_);
                        if (p_71059_1_ instanceof LivingEntity) {
                            EnchantmentHelper.doPostHurtEffects((LivingEntity)p_71059_1_, player);
                        }

                        EnchantmentHelper.doPostDamageEffects(player, p_71059_1_);
                        ItemStack itemstack1 = player.getOffhandItem();
                        Entity entity = p_71059_1_;
                        if (p_71059_1_ instanceof net.minecraftforge.entity.PartEntity) {
                            entity = ((net.minecraftforge.entity.PartEntity<?>) p_71059_1_).getParent();
                        }

                        if (!player.level.isClientSide && !itemstack1.isEmpty() && entity instanceof LivingEntity) {
                            ItemStack copy = itemstack1.copy();
                            itemstack1.hurtEnemy((LivingEntity)entity, player);
                            if (itemstack1.isEmpty()) {
                                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, copy, Hand.OFF_HAND);
                                player.setItemInHand(Hand.OFF_HAND, ItemStack.EMPTY);
                            }
                        }

                        if (p_71059_1_ instanceof LivingEntity) {
                            float f5 = f4 - ((LivingEntity)p_71059_1_).getHealth();
                            player.awardStat(Stats.DAMAGE_DEALT, Math.round(f5 * 10.0F));
                            if (j > 0) {
                                p_71059_1_.setSecondsOnFire(j * 4);
                            }

                            if (player.level instanceof ServerWorld && f5 > 2.0F) {
                                int k = (int)((double)f5 * 0.5D);
                                ((ServerWorld)player.level).sendParticles(ParticleTypes.DAMAGE_INDICATOR, p_71059_1_.getX(), p_71059_1_.getY(0.5D), p_71059_1_.getZ(), k, 0.1D, 0.0D, 0.1D, 0.2D);
                            }
                        }

                        player.causeFoodExhaustion(0.1F);
                    } else {
                        player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_NODAMAGE, player.getSoundSource(), 1.0F, 1.0F);
                        if (flag4) {
                            p_71059_1_.clearFire();
                        }
                    }
                }

            }
        }
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof WolfEntity || event.getEntity() instanceof FoxEntity) {
            ((MobEntity) event.getEntity()).targetSelector.addGoal(0, new NearestAttackableTargetGoal<>((MobEntity) event.getEntity(), WherbleEntity.class, true));
        }
        if (event.getEntity() instanceof VillagerEntity) {
            ((MobEntity) event.getEntity()).goalSelector.addGoal(0, new LookAtGoal((MobEntity) event.getEntity(), WanderingSailorEntity.class, 6.0F));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerBiomes(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.Category.SWAMP) {
            event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.SWAMP_MUCKER.get(), FinsConfig.Common.INSTANCE.swampMuckerSpawnWeight.get(), 2, 4));
            event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.FLATBACK_SUCKER.get(), FinsConfig.Common.INSTANCE.flatbackSuckerSpawnWeight.get(), 1, 1));
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.MUDHORSE.get(), FinsConfig.Common.INSTANCE.mudhorseSpawnWeight.get(), 2, 3));
            event.getGeneration().getStructures().add(() -> FinsConfiguredStructures.CONFIGURED_SAILORS_SHIP);
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
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.RUBBER_BELLY_GLIDER.get(), FinsConfig.Common.INSTANCE.rubberBellyGliderSpawnWeight.get(), 1, 2));
        }

        if (event.getCategory() == Biome.Category.FOREST) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.FLATBACK_LEAF_SNAIL.get(), FinsConfig.Common.INSTANCE.flatbackLeafSnailSpawnWeight.get(), 1, 2));
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.GLASS_SKIPPER.get(), FinsConfig.Common.INSTANCE.glassSkipperSpawnWeight.get(), 1, 1));
        }

        if (event.getCategory() == Biome.Category.ICY) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.WHERBLE.get(), FinsConfig.Common.INSTANCE.wherbleSpawnWeight.get(), 2, 6));
        }

        if (event.getName() != null) {
            String path = event.getName().getPath();

            if (event.getName().equals(new ResourceLocation("fins:schanuz_beds"))) {
//                event.getGeneration().addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, FinsConfiguredFeatures.CONFIGURED_LAMINA_TREE.configured(new ProbabilityConfig(0.5F)).count(32).decorated(Placement.COUNT_NOISE_BIASED.configured(new TopSolidWithNoiseConfig(80, 80.0D, 0.0D))));
            }

            if (path.equals("cold_ocean") || path.equals("deep_cold_ocean")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.BLU_WEE.get(), FinsConfig.Common.INSTANCE.bluWeeSpawnWeight.get(), 4, 8));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.TEAL_ARROWFISH.get(), FinsConfig.Common.INSTANCE.tealArrowfishSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.PHANTOM_NUDIBRANCH.get(), FinsConfig.Common.INSTANCE.phantomNudibranchSpawnWeight.get(), 1, 1));
                event.getGeneration().getStructures().add(() -> FinsConfiguredStructures.CONFIGURED_SAILORS_SHIP);
            }

            if (path.equals("warm_ocean")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.BANDED_REDBACK_SHRIMP.get(), FinsConfig.Common.INSTANCE.bandedRedbackShrimpSpawnWeight.get(), 3, 3));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.ORNATE_BUGFISH.get(), FinsConfig.Common.INSTANCE.ornateBugfishSpawnWeight.get(), 5, 5));
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.SPINDLY_GEM_CRAB.get(), FinsConfig.Common.INSTANCE.spindlyGemCrabSpawnWeight.get(), 1, 3));
                event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.RUBBER_BELLY_GLIDER.get(), FinsConfig.Common.INSTANCE.rubberBellyGliderSpawnWeight.get(), 1, 2));
                event.getGeneration().getStructures().add(() -> FinsConfiguredStructures.CONFIGURED_SAILORS_SHIP);
            }

            if (path.equals("ocean") || path.equals("deep_ocean")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.HIGH_FINNED_BLUE.get(), FinsConfig.Common.INSTANCE.highFinnedBlueSpawnWeight.get(), 6, 12));
                event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.GOPJET.get(), FinsConfig.Common.INSTANCE.gopjetSpawnWeight.get(), 2, 3));
                event.getGeneration().getStructures().add(() -> FinsConfiguredStructures.CONFIGURED_SAILORS_SHIP);
            }

            if (path.equals("lukewarm_ocean") || path.equals("deep_lukewarm_ocean")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.RED_BULL_CRAB.get(), FinsConfig.Common.INSTANCE.redBullCrabSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.WHITE_BULL_CRAB.get(), FinsConfig.Common.INSTANCE.whiteBullCrabSpawnWeight.get(), 2, 4));
                event.getGeneration().getStructures().add(() -> FinsConfiguredStructures.CONFIGURED_SAILORS_SHIP);
            }

            if (path.equals("frozen_ocean") || path.equals("deep_frozen_ocean")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.NIGHT_LIGHT_SQUID.get(), FinsConfig.Common.INSTANCE.nightLightSquidSpawnWeight.get(), 1, 2));
                event.getGeneration().getStructures().add(() -> FinsConfiguredStructures.CONFIGURED_SAILORS_SHIP);
            }

            if (path.equals("river")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.WEE_WEE.get(), FinsConfig.Common.INSTANCE.weeWeeSpawnWeight.get(), 2, 6));
                event.getSpawns().getSpawner(EntityClassification.AMBIENT).add(new MobSpawnInfo.Spawners(FinsEntities.RIVER_PEBBLE_SNAIL.get(), FinsConfig.Common.INSTANCE.riverPebbleSnailSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.GOLDEN_RIVER_RAY.get(), FinsConfig.Common.INSTANCE.goldenRiverRaySpawnWeight.get(), 1, 1));
            }

            if (path.equals("flower_forest")) {
                event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FinsEntities.GLASS_SKIPPER.get(), FinsConfig.Common.INSTANCE.glassSkipperSpawnWeight.get() * 2, 2, 6));
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
            if (EnchantmentHelper.getEnchantments(heldItem).containsKey(FinsEnchantments.CRABS_FAVOR.get()) && EnchantmentHelper.getEnchantments(heldItemOffhand).containsKey(FinsEnchantments.CRABS_FAVOR.get())) {
                int i = EnchantmentHelper.getItemEnchantmentLevel(FinsEnchantments.CRABS_FAVOR.get(), event.getAttackingPlayer().getItemInHand(Hand.MAIN_HAND));
                event.setDroppedExperience(event.getOriginalExperience() * i + attacker.getCommandSenderWorld().random.nextInt(3));
            }
        }
    }

    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) throws IllegalAccessException {
        ResourceLocation name = event.getName();
        if (name.equals(LootTables.FISHING_FISH)) {
            LootPool pool = event.getTable().getPool("main");
            if (FinsConfig.Common.INSTANCE.finsFishingLoot.get()) {
                addEntry(pool, getInjectEntry(new ResourceLocation(FinsAndTails.MOD_ID, "inject/fishing"), 10, 1));
            }
            if (name.equals(LootTables.FISHERMAN_GIFT)) {
                 addEntry(pool, getInjectEntry(new ResourceLocation("fins:inject/fisherman_gift"), 15, 1));
            }
        }
    }

    private static LootEntry getInjectEntry(ResourceLocation location, int weight, int quality) {
        return TableLootEntry.lootTableReference(location).setWeight(weight).setQuality(quality).build();
    }

    private static void addEntry(LootPool pool, LootEntry entry) throws IllegalAccessException {
        List<LootEntry> lootEntries = (List<LootEntry>) ObfuscationReflectionHelper.findField(LootPool.class, "field_186453_a").get(pool);
        if (lootEntries.stream().anyMatch(e -> e == entry)) {
            throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
        }
        lootEntries.add(entry);
    }

    // Thanks to WolfShotz for helping with the trade code
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
