package teamdraco.finsandstails.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.armor.*;
import teamdraco.finsandstails.client.render.*;
import teamdraco.finsandstails.client.screen.CrabCruncherScreen;
import teamdraco.finsandstails.client.screen.MudhorsePouchScreen;
import teamdraco.finsandstails.common.items.FwingedBootsItem;
import teamdraco.finsandstails.common.items.GopjetJetpackItem;
import teamdraco.finsandstails.common.items.SpindlyCharmItem;
import teamdraco.finsandstails.common.items.SpindlyGemCharm;
import teamdraco.finsandstails.network.TriggerFlyingPacket;
import teamdraco.finsandstails.registry.FTBlocks;
import teamdraco.finsandstails.registry.FTContainers;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    private static final ResourceLocation HEARTS_TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/gui/icons.png");

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(FTEntities.BLU_WEE.get(), BluWeeRenderer::new);
        event.registerEntityRenderer(FTEntities.BANDED_REDBACK_SHRIMP.get(), BandedRedbackShrimpRenderer::new);
        event.registerEntityRenderer(FTEntities.PEA_WEE.get(), PeaWeeRenderer::new);
        event.registerEntityRenderer(FTEntities.RED_BULL_CRAB.get(), RedBullCrabRenderer::new);
        event.registerEntityRenderer(FTEntities.WHITE_BULL_CRAB.get(), WhiteBullCrabRenderer::new);
        event.registerEntityRenderer(FTEntities.FLATBACK_LEAF_SNAIL.get(), FlatbackLeafSnailRenderer::new);
        event.registerEntityRenderer(FTEntities.FLATBACK_SUCKER.get(), FlatbackSuckerRenderer::new);
        event.registerEntityRenderer(FTEntities.GOLDEN_RIVER_RAY.get(), GoldenRiverRayRenderer::new);
        event.registerEntityRenderer(FTEntities.GOPJET.get(), GopjetRenderer::new);
        event.registerEntityRenderer(FTEntities.HIGH_FINNED_BLUE.get(), HighFinnedBlueRenderer::new);
        event.registerEntityRenderer(FTEntities.SWAMP_MUCKER.get(), SwampMuckerRenderer::new);
        event.registerEntityRenderer(FTEntities.MUDHORSE.get(), MudhorseRenderer::new);
        event.registerEntityRenderer(FTEntities.WEE_WEE.get(), WeeWeeRenderer::new);
        event.registerEntityRenderer(FTEntities.NIGHT_LIGHT_SQUID.get(), NightlightSquidRenderer::new);
        event.registerEntityRenderer(FTEntities.ORNATE_BUGFISH.get(), OrnateBugfishRenderer::new);
        event.registerEntityRenderer(FTEntities.PAPA_WEE.get(), PapaWeeRenderer::new);
        event.registerEntityRenderer(FTEntities.PENGLIL.get(), PenglilRenderer::new);
        event.registerEntityRenderer(FTEntities.PHANTOM_NUDIBRANCH.get(), PhantomNudibranchRenderer::new);
        event.registerEntityRenderer(FTEntities.RIVER_PEBBLE_SNAIL.get(), RiverPebbleSnailRenderer::new);
        event.registerEntityRenderer(FTEntities.RUBBER_BELLY_GLIDER.get(), RubberBellyGliderRenderer::new);
        event.registerEntityRenderer(FTEntities.SIDEROL_WHISKERED_SNAIL.get(), SiderolWhiskeredSnailRenderer::new);
        event.registerEntityRenderer(FTEntities.TEAL_ARROWFISH_ARROW.get(), TealArrowfishArrowRenderer::new);
        event.registerEntityRenderer(FTEntities.TEAL_ARROWFISH.get(), TealArrowfishRenderer::new);
        event.registerEntityRenderer(FTEntities.VIBRA_WEE.get(), VibraWeeRenderer::new);
        event.registerEntityRenderer(FTEntities.WHERBLE.get(), WherbleRenderer::new);
        event.registerEntityRenderer(FTEntities.WANDERING_SAILOR.get(), WanderingSailorRenderer::new);
        event.registerEntityRenderer(FTEntities.SPINDLY_GEM_CRAB.get(), SpindlyGemCrabRenderer::new);
    }

    @SubscribeEvent
    public static void registerArmorRenders(EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(FwingedBootsItem.class, new ArmorItemRenderer<>(new FwingedBootsModel()));
        GeoArmorRenderer.registerArmorRenderer(GopjetJetpackItem.class, new ArmorItemRenderer<>(new GopjetJetpackModel()));
        GeoArmorRenderer.registerArmorRenderer(SpindlyGemCharm.class, new ArmorItemRenderer<>(new SpindlyGemModel()));
        GeoArmorRenderer.registerArmorRenderer(SpindlyCharmItem.class, new ArmorItemRenderer<>(new SpindlyCharmModel()));
    }

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(FTBlocks.CHAINED_TENTACLE.get(), RenderType.cutout());

        MenuScreens.register(FTContainers.MUDHORSE_POUCH.get(), MudhorsePouchScreen::new);
        MenuScreens.register(FTContainers.CRAB_CRUNCHER.get(), CrabCruncherScreen::new);

        ItemProperties.register(FTItems.GEM_CRAB_AMULET.get(), new ResourceLocation(FinsAndTails.MOD_ID, "broken"), (stack, world, player, i) -> SpindlyGemCharm.isUsable(stack) ? 0.0F : 1.0F);

        FinsAndTails.CALLBACKS.forEach(Runnable::run);
        FinsAndTails.CALLBACKS.clear();
    }

    @Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ForgeBus {
        private static boolean wasJumping;

        @SubscribeEvent
        public static void clientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                Minecraft minecraft = Minecraft.getInstance();
                final LocalPlayer player = minecraft.player;
                if (player != null && player.getItemBySlot(EquipmentSlot.CHEST).getItem() == FTItems.GOPJET_JETPACK.get()) {
                    boolean jumping = minecraft.options.keyJump.isDown();
                    if (jumping != wasJumping) {
                        TriggerFlyingPacket packet = new TriggerFlyingPacket(jumping);
                        packet.handle(player);
                        FinsAndTails.NETWORK.sendToServer(packet);
                    }
                    wasJumping = jumping;
                }
            }
        }

        @SubscribeEvent
        public static void playerHeartRender(RenderGameOverlayEvent.Post event) {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer player = minecraft.player;
            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);

            if (chest.isEmpty()) return;

            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL && !player.isCreative() && !player.isSpectator()) {
                CharmType charm = CharmType.getCharm(player);
                if (charm == null) return;

                PoseStack poseStack = event.getMatrixStack();

                poseStack.pushPose();
                RenderSystem.depthMask(false);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShaderTexture(0, HEARTS_TEXTURE);
                RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

                renderPlayerHealth(poseStack, player, charm);

                RenderSystem.depthMask(true);
                RenderSystem.disableBlend();
                poseStack.popPose();
            }
        }

        private static void renderPlayerHealth(PoseStack stack, Player player, CharmType charm) {
            Gui gui = Minecraft.getInstance().gui;

            if (player != null) {
                int health = Mth.ceil(player.getHealth());
                boolean highlight = gui.healthBlinkTime > (long) gui.tickCount && (gui.healthBlinkTime - (long) gui.tickCount) / 3L % 2L == 1L;
                if (health < gui.lastHealth && player.invulnerableTime > 0) {
                    gui.lastHealthTime = Util.getMillis();
                    gui.healthBlinkTime = (gui.tickCount + 20);
                } else if (health > gui.lastHealth && player.invulnerableTime > 0) {
                    gui.lastHealthTime = Util.getMillis();
                    gui.healthBlinkTime = gui.tickCount + 10;
                }

                if (Util.getMillis() - gui.lastHealthTime > 1000L) {
                    gui.displayHealth = health;
                    gui.lastHealthTime = Util.getMillis();
                }

                gui.lastHealth = health;
                int healthLast = gui.displayHealth;

                int x = gui.screenWidth / 2 - 91;
                int y = gui.screenHeight - 39;
                float healthMax = Math.max((float) player.getAttributeValue(Attributes.MAX_HEALTH), (float) Math.max(healthLast, health));
                int absorption = Mth.ceil(player.getAbsorptionAmount());
                int healthRows = Mth.ceil((healthMax + (float) absorption) / 20.0F);
                int rowHeight = Math.max(10 - (healthRows - 2), 3);
                int regen = -1;

                gui.random.setSeed(gui.tickCount * 312871);
                if (player.hasEffect(MobEffects.REGENERATION)) {
                    regen = gui.tickCount % Mth.ceil(healthMax + 5.0F);
                }

                gui.minecraft.getProfiler().popPush("health");
                renderHearts(stack, charm, x, y, rowHeight, regen, healthMax, health, absorption, highlight);
            }
        }

        public static void renderHearts(PoseStack stack, CharmType charm, int x, int y, int rowHeight, int regen, float healthMax, int health, int absorption, boolean highlight) {
            Gui gui = Minecraft.getInstance().gui;

            int heartCount = Mth.ceil((double) healthMax / 2.0D);
            int absorptionHeartCount = Mth.ceil((double) absorption / 2.0D);

            for (int heart = heartCount + absorptionHeartCount - 1; heart >= 0; --heart) {
                int level = heart / 10;
                int index = heart % 10;
                int heartX = x + index * 8;
                int heartY = y - level * rowHeight;

                if (health + absorption <= 4) {
                    heartY += gui.random.nextInt(2);
                }

                if (heart < heartCount && heart == regen) {
                    heartY -= 2;
                }

                int halfHeartIndex = heart * 2;
                if (highlight && halfHeartIndex < absorption) {
                    renderHeart(stack, charm, heartX, heartY, true, halfHeartIndex + 1 == absorption);
                }

                if (halfHeartIndex < health) {
                    renderHeart(stack, charm, heartX, heartY, false, halfHeartIndex + 1 == health);
                }
            }
        }

        public static void renderHeart(PoseStack stack, CharmType type, int x, int y, boolean highlight, boolean halfHeart) {
            Minecraft.getInstance().gui.blit(stack, x, y, type.getX(halfHeart, highlight), 0, 9, 9);
        }

        public enum CharmType {
            RUBY,
            EMERALD,
            AMBER,
            PEARL,
            SAPPHIRE;

            public int getX(boolean halfHeart, boolean highlight) {
                // todo use highlight to change texture?
                if (halfHeart) return ordinal() * 18 + 9;
                return ordinal() * 18;
            }

            static CharmType getCharm(Player player) {
                ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);

                if (stack.is(FTItems.SPINDLY_RUBY_CHARM.get())) return RUBY;
                else if (stack.is(FTItems.EMERALD_SPINDLY_GEM_CRAB.get())) return EMERALD;
                else if (stack.is(FTItems.SPINDLY_AMBER_CHARM.get())) return AMBER;
                else if (stack.is(FTItems.SPINDLY_PEARL_CHARM.get())) return PEARL;
                else if (stack.is(FTItems.SPINDLY_SAPPHIRE_CHARM.get())) return SAPPHIRE;

                return null;
            }
        }
    }
}
