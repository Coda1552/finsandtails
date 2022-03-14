package teamdraco.finsandstails.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonSittingAttackingPhase;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
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
                    renderPlayerHealth(poseStack, player);
                }
                RenderSystem.depthMask(true);
                RenderSystem.disableBlend();
                poseStack.popPose();
            }
        }

        private static void renderPlayerHealth(PoseStack stack, Player player) {
            Gui gui = Minecraft.getInstance().gui;

            if (player != null) {
                int i = Mth.ceil(player.getHealth());
                boolean flag = gui.healthBlinkTime > (long)gui.tickCount && (gui.healthBlinkTime - (long)gui.tickCount) / 3L % 2L == 1L;
                long j = Util.getMillis();
                if (i < gui.lastHealth && player.invulnerableTime > 0) {
                    gui.lastHealthTime = j;
                    gui.healthBlinkTime = (gui.tickCount + 20);
                } else if (i > gui.lastHealth && player.invulnerableTime > 0) {
                    gui.lastHealthTime = j;
                    gui.healthBlinkTime = gui.tickCount + 10;
                }

                if (j - gui.lastHealthTime > 1000L) {
                    gui.lastHealth = i;
                    gui.displayHealth = i;
                    gui.lastHealthTime = j;
                }

                gui.lastHealth = i;
                int k = gui.displayHealth;
                player.getRandom().setSeed((gui.tickCount * 312871L));
                int i1 = gui.screenWidth / 2 - 91;
                int k1 = gui.screenHeight - 39;
                float f = Math.max((float)player.getAttributeValue(Attributes.MAX_HEALTH), (float)Math.max(k, i));
                int l1 = Mth.ceil(player.getAbsorptionAmount());
                int i2 = Mth.ceil((f + (float)l1) / 2.0F / 10.0F);
                int j2 = Math.max(10 - (i2 - 2), 3);
                int k2 = k1 - (i2 - 1);
                int j3 = -1;
                if (player.hasEffect(MobEffects.REGENERATION)) {
                    j3 = gui.tickCount % Mth.ceil(f + 5.0F);
                }

                for(int k3 = 0; k3 < 4; ++k3) {
                    int l3 = i1 + k3 * 8;
                    gui.blit(stack, l3, k2, 34, 9, 9, 9);

                    // last 2 unneeded?
                    gui.blit(stack, l3, k2, 25, 9, 9, 9);

                    gui.blit(stack, l3, k2, 16, 9, 9, 9);
                }

                gui.minecraft.getProfiler().popPush("health");
                renderHearts(stack, player, i1, k1, j2, j3, f, i, k, l1, flag);
            }
        }

        public static void renderHearts(PoseStack stack, Player player, int p_168691_, int p_168692_, int p_168693_, int p_168694_, float p_168695_, int p_168696_, int p_168697_, int p_168698_, boolean p_168699_) {
            CharmType charm = CharmType.getCharm(player);
            int i = 9 * (player.level.getLevelData().isHardcore() ? 5 : 0);
            int j = Mth.ceil((double) p_168695_ / 2.0D);
            int k = Mth.ceil((double) p_168698_ / 2.0D);

            for (int i1 = j + k - 1; i1 >= 0; --i1) {
                int j1 = i1 / 10;
                int k1 = i1 % 2;
                int l1 = p_168691_ + k1 * 8;
                int i2 = p_168692_ - j1 * p_168693_;
                if (p_168696_ + p_168698_ <= 4) {
                    i2 += player.getRandom().nextInt(2);
                }

                if (i1 < j && i1 == p_168694_) {
                    i2 -= 2;
                }

                int j2 = i1 * 2;

                if (p_168699_ && j2 < p_168697_) {
                    boolean flag2 = j2 + 1 == p_168697_;
                    renderHeart(stack, charm, l1, i2, i, true, flag2);
                }
            }
        }

        public static void renderHeart(PoseStack p_168701_, CharmType type, int p_168703_, int p_168704_, int p_168705_, boolean p_168706_, boolean p_168707_) {
            Minecraft.getInstance().gui.blit(p_168701_, p_168703_, p_168704_, type.getX(p_168707_, p_168706_), p_168705_, 9, 9);
        }

        public enum CharmType {
            RUBY(2),
            EMERALD(4),
            AMBER(6),
            PEARL(8),
            SAPPHIRE(10),
            NORMAL(12);

            private final int index;

            CharmType(int p_168729_) {
                this.index = p_168729_;
            }

            public int getX(boolean p_168735_, boolean p_168736_) {
                int j = p_168735_ ? 1 : 0;
                int k = p_168736_ ? 2 : 0;
                int i = j + k;

                return (index * 2 + i) * 9;
            }

            static CharmType getCharm(Player p_168733_) {
                ItemStack stack = p_168733_.getItemBySlot(EquipmentSlot.CHEST);
                CharmType type;

                if (stack.is(FTItems.SPINDLY_RUBY_CHARM.get())) {
                    type = RUBY;
                }
                if (stack.is(FTItems.SPINDLY_AMBER_CHARM.get())) {
                    type = EMERALD;
                }
                if (stack.is(FTItems.SPINDLY_AMBER_CHARM.get())) {
                    type = AMBER;
                }
                if (stack.is(FTItems.SPINDLY_PEARL_CHARM.get())) {
                    type = PEARL;
                }
                if (stack.is(FTItems.SPINDLY_SAPPHIRE_CHARM.get())) {
                    type = SAPPHIRE;
                }
                else {
                    type = NORMAL;
                }

                return type;
            }
        }
    }
}
