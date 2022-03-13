package teamdraco.finsandstails.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
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
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.render.*;
import teamdraco.finsandstails.client.screen.CrabCruncherScreen;
import teamdraco.finsandstails.client.screen.MudhorsePouchScreen;
import teamdraco.finsandstails.common.items.FwingedBootsItem;
import teamdraco.finsandstails.common.items.GopjetJetpackItem;
import teamdraco.finsandstails.common.items.charms.SpindlyGemCharm;
import teamdraco.finsandstails.network.TriggerFlyingPacket;
import teamdraco.finsandstails.registry.FTBlocks;
import teamdraco.finsandstails.registry.FTContainers;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    private static ResourceLocation HEARTS_TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/gui/icons.png");

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
    }

    @SubscribeEvent
    public static void registerArmorRenders(EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(FwingedBootsItem.class, new FwingedBootsRenderer());
        GeoArmorRenderer.registerArmorRenderer(GopjetJetpackItem.class, new GopjetJetpackRenderer());
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
                PoseStack poseStack = event.getMatrixStack();

                float absorb = Mth.ceil(player.getAbsorptionAmount());
                float maxHealth = (float) player.getAttribute(Attributes.MAX_HEALTH).getValue();

                int left = event.getWindow().getGuiScaledWidth() / 2 - 91;
                int top = event.getWindow().getGuiScaledHeight() - ((ForgeIngameGui) Minecraft.getInstance().gui).left_height;

                int healthRows = Mth.ceil((maxHealth + absorb) / 2.0F / 10.0F);
                int rowHeight = Math.max(10 - (healthRows - 2), 3);

                poseStack.pushPose();
                RenderSystem.depthMask(false);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShaderTexture(0, HEARTS_TEXTURE);
                RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

                for (int i = 0; i < 4; i++) {
                    int row = (int) (Math.ceil(i) / 10f);
                    int x = left + i % 10 * 8;
                    int y = top - row * 3 + rowHeight * 2 - 5;

                    int u = 0;
                    int v = 0;

                    // Some of these values are being assigned to what they already are, but I'm keeping it because otherwise it annoys me :)
                    if (chest.is(FTItems.SPINDLY_RUBY_CHARM.get())) {
                        if (i * 2 > player.getHealth()) {
                            // half heart
                            u = 9;
                            v = 0;
                        } else {
                            // full heart
                            u = 0;
                            v = 0;
                        }
                    }
                    if (chest.is(FTItems.SPINDLY_EMERALD_CHARM.get())) {
                        if (i * 2 > player.getHealth()) {
                            u = 9;
                            v = 9;
                        } else {
                            u = 0;
                            v = 9;
                        }
                    }
                    if (chest.is(FTItems.SPINDLY_AMBER_CHARM.get())) {
                        if (i * 2 > player.getHealth()) {
                            u = 9;
                            v = 18;
                        } else {
                            u = 0;
                            v = 18;
                        }
                    }
                    if (chest.is(FTItems.SPINDLY_PEARL_CHARM.get())) {
                        if (i * 2 > player.getHealth()) {
                            u = 9;
                            v = 27;
                        } else {
                            u = 0;
                            v = 27;
                        }
                    }
                    if (chest.is(FTItems.SPINDLY_SAPPHIRE_CHARM.get())) {
                        if (i * 2 > player.getHealth()) {
                            u = 9;
                            v = 36;
                        } else {
                            u = 0;
                            v = 36;
                        }
                    }

                    double health0 = Math.ceil(player.getHealth());

                    if (health0 <= 8) {
                        // 1 heart
                        if (health0 >= 2 && i == 0) {

                            Screen.blit(poseStack, x, y + 3, u, v, 9, 9, 256, 256);
                        }
                       else if (health0 >= 1 && health0 < 2 && i == 0) {
                            Screen.blit(poseStack, x, y + 3, u + 9, v, 9, 9, 256, 256);
                       }
                       else if (health0 < 1 && i == 0) {
                            Screen.blit(poseStack, x, y + 3, u + 18, v, 9, 9, 256, 256);
                        }

                        // 2 hearts
                        if (health0 >= 4 && i == 1) {
                            Screen.blit(poseStack, x, y + 3, u, v, 9, 9, 256, 256);
                        }
                        else if (health0 >= 3 && health0 < 4 && i == 1) {
                            Screen.blit(poseStack, x, y + 3, u + 9, v, 9, 9, 256, 256);
                        }
                        else if (health0 < 3 && i == 1) {
                            Screen.blit(poseStack, x, y + 3, u + 18, v, 9, 9, 256, 256);
                        }

                        // 3 hearts
                        if (health0 >= 6 && i == 2) {
                            Screen.blit(poseStack, x, y + 3, u, v, 9, 9, 256, 256);
                        }
                        else if (health0 >= 5 && health0 < 6 && i == 2) {
                            Screen.blit(poseStack, x, y + 3, u + 9, v, 9, 9, 256, 256);
                        }
                        else if (health0 < 5 && i == 2) {
                            Screen.blit(poseStack, x, y + 3, u + 18, v, 9, 9, 256, 256);
                        }

                        // 4 hearts
                        if (health0 >= 8 && i == 3) {
                            Screen.blit(poseStack, x, y + 3, u, v, 9, 9, 256, 256);
                        }
                        else if (health0 >= 7 && health0 < 8 && i == 3) {
                            Screen.blit(poseStack, x, y + 3, u + 9, v, 9, 9, 256, 256);
                        }
                        else if (health0 < 7 && i == 3) {
                            Screen.blit(poseStack, x, y + 3, u + 18, v, 9, 9, 256, 256);
                        }
                    }
                    else {
                        Screen.blit(poseStack, x, y + 3, u, v, 9, 9, 256, 256);
                    }

                }
                RenderSystem.depthMask(true);
                RenderSystem.disableBlend();
                poseStack.popPose();
            }
        }
    }
}
