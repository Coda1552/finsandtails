package teamdraco.finsandstails.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.render.*;
import teamdraco.finsandstails.client.screen.CrabCruncherScreen;
import teamdraco.finsandstails.client.screen.MudhorsePouchScreen;
import teamdraco.finsandstails.common.items.charms.SpindlyGemCharm;
import teamdraco.finsandstails.network.TriggerFlyingPacket;
import teamdraco.finsandstails.registry.FTBlocks;
import teamdraco.finsandstails.registry.FTItems;
import teamdraco.finsandstails.registry.FTContainers;
import teamdraco.finsandstails.registry.FTEntities;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    public static void init() {
        EntityRenderers.register(FTEntities.BLU_WEE.get(), BluWeeRenderer::new);
        EntityRenderers.register(FTEntities.PEA_WEE.get(), PeaWeeRenderer::new);
        EntityRenderers.register(FTEntities.BANDED_REDBACK_SHRIMP.get(), BandedRedbackShrimpRenderer::new);
        EntityRenderers.register(FTEntities.TEAL_ARROWFISH.get(), TealArrowfishRenderer::new);
        EntityRenderers.register(FTEntities.SWAMP_MUCKER.get(), SwampMuckerRenderer::new);
        EntityRenderers.register(FTEntities.FLATBACK_SUCKER.get(), FlatbackSuckerRenderer::new);
        EntityRenderers.register(FTEntities.HIGH_FINNED_BLUE.get(), HighFinnedBlueRenderer::new);
        EntityRenderers.register(FTEntities.MUDHORSE.get(), MudhorseRenderer::new);
        EntityRenderers.register(FTEntities.ORNATE_BUGFISH.get(), OrnateBugfishRenderer::new);
        EntityRenderers.register(FTEntities.PHANTOM_NUDIBRANCH.get(), PhantomNudibranchRenderer::new);
        EntityRenderers.register(FTEntities.PENGLIL.get(), PenglilRenderer::new);
        EntityRenderers.register(FTEntities.SPINDLY_GEM_CRAB.get(), SpindlyGemCrabRenderer::new);
        EntityRenderers.register(FTEntities.FLATBACK_LEAF_SNAIL.get(), FlatbackLeafSnailRenderer::new);
        EntityRenderers.register(FTEntities.RUBBER_BELLY_GLIDER.get(), RubberBellyGliderRenderer::new);
        EntityRenderers.register(FTEntities.RED_BULL_CRAB.get(), RedBullCrabRenderer::new);
        EntityRenderers.register(FTEntities.WHITE_BULL_CRAB.get(), WhiteBullCrabRenderer::new);
        EntityRenderers.register(FTEntities.WEE_WEE.get(), WeeWeeRenderer::new);
        EntityRenderers.register(FTEntities.VIBRA_WEE.get(), VibraWeeRenderer::new);
        EntityRenderers.register(FTEntities.GOPJET.get(), GopjetRenderer::new);
        EntityRenderers.register(FTEntities.RIVER_PEBBLE_SNAIL.get(), RiverPebbleSnailRenderer::new);
        EntityRenderers.register(FTEntities.SIDEROL_WHISKERED_SNAIL.get(), SiderolWhiskeredSnailRenderer::new);
        EntityRenderers.register(FTEntities.GOLDEN_RIVER_RAY.get(), GoldenRiverRayRenderer::new);
        EntityRenderers.register(FTEntities.NIGHT_LIGHT_SQUID.get(), NightLightSquidRenderer::new);
        EntityRenderers.register(FTEntities.TEAL_ARROWFISH_ARROW.get(), TealArrowfishArrowRenderer::new);
        EntityRenderers.register(FTEntities.PAPA_WEE.get(), PapaWeeRenderer::new);
        EntityRenderers.register(FTEntities.WHERBLE.get(), WherbleRenderer::new);
        EntityRenderers.register(FTEntities.WANDERING_SAILOR.get(), WanderingSailorRenderer::new);
        EntityRenderers.register(FTEntities.GOLIATH_GARDEN_CRAB.get(), GoliathGardenCrabRenderer::new);
        EntityRenderers.register(FTEntities.GLASS_SKIPPER.get(), GlassSkipperRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(FTBlocks.CHAINED_TENTACLE.get(), RenderType.cutout());
        MenuScreens.register(FTContainers.MUDHORSE_POUCH.get(), MudhorsePouchScreen::new);
        MenuScreens.register(FTContainers.CRAB_CRUNCHER.get(), CrabCruncherScreen::new);
        ItemModelShaper.register(FTItems.GEM_CRAB_AMULET.get(), new ResourceLocation(FinsAndTails.MOD_ID, "broken"), (stack, world, player) -> SpindlyGemCharm.isUsable(stack) ? 0.0F : 1.0F);
    }

    @Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ModBus {
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
    }
}
