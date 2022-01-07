package teamdraco.finsandstails.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
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
import teamdraco.finsandstails.registry.FinsContainers;
import teamdraco.finsandstails.registry.FinsEntities;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    public static void init() {
        EntityRenderers.register(FinsEntities.BLU_WEE.get(), BluWeeRenderer::new);
        EntityRenderers.register(FinsEntities.PEA_WEE.get(), PeaWeeRenderer::new);
        EntityRenderers.register(FinsEntities.BANDED_REDBACK_SHRIMP.get(), BandedRedbackShrimpRenderer::new);
        EntityRenderers.register(FinsEntities.TEAL_ARROWFISH.get(), TealArrowfishRenderer::new);
        EntityRenderers.register(FinsEntities.SWAMP_MUCKER.get(), SwampMuckerRenderer::new);
        EntityRenderers.register(FinsEntities.FLATBACK_SUCKER.get(), FlatbackSuckerRenderer::new);
        EntityRenderers.register(FinsEntities.HIGH_FINNED_BLUE.get(), HighFinnedBlueRenderer::new);
        EntityRenderers.register(FinsEntities.MUDHORSE.get(), MudhorseRenderer::new);
        EntityRenderers.register(FinsEntities.ORNATE_BUGFISH.get(), OrnateBugfishRenderer::new);
        EntityRenderers.register(FinsEntities.PHANTOM_NUDIBRANCH.get(), PhantomNudibranchRenderer::new);
        EntityRenderers.register(FinsEntities.PENGLIL.get(), PenglilRenderer::new);
        EntityRenderers.register(FinsEntities.SPINDLY_GEM_CRAB.get(), SpindlyGemCrabRenderer::new);
        EntityRenderers.register(FinsEntities.FLATBACK_LEAF_SNAIL.get(), FlatbackLeafSnailRenderer::new);
        EntityRenderers.register(FinsEntities.RUBBER_BELLY_GLIDER.get(), RubberBellyGliderRenderer::new);
        EntityRenderers.register(FinsEntities.RED_BULL_CRAB.get(), RedBullCrabRenderer::new);
        EntityRenderers.register(FinsEntities.WHITE_BULL_CRAB.get(), WhiteBullCrabRenderer::new);
        EntityRenderers.register(FinsEntities.WEE_WEE.get(), WeeWeeRenderer::new);
        EntityRenderers.register(FinsEntities.VIBRA_WEE.get(), VibraWeeRenderer::new);
        EntityRenderers.register(FinsEntities.GOPJET.get(), GopjetRenderer::new);
        EntityRenderers.register(FinsEntities.RIVER_PEBBLE_SNAIL.get(), RiverPebbleSnailRenderer::new);
        EntityRenderers.register(FinsEntities.SIDEROL_WHISKERED_SNAIL.get(), SiderolWhiskeredSnailRenderer::new);
        EntityRenderers.register(FinsEntities.GOLDEN_RIVER_RAY.get(), GoldenRiverRayRenderer::new);
        EntityRenderers.register(FinsEntities.NIGHT_LIGHT_SQUID.get(), NightLightSquidRenderer::new);
        EntityRenderers.register(FinsEntities.TEAL_ARROWFISH_ARROW.get(), TealArrowfishArrowRenderer::new);
        EntityRenderers.register(FinsEntities.PAPA_WEE.get(), PapaWeeRenderer::new);
        EntityRenderers.register(FinsEntities.WHERBLE.get(), WherbleRenderer::new);
        EntityRenderers.register(FinsEntities.WANDERING_SAILOR.get(), WanderingSailorRenderer::new);
        EntityRenderers.register(FinsEntities.GOLIATH_GARDEN_CRAB.get(), GoliathGardenCrabRenderer::new);
        EntityRenderers.register(FinsEntities.GLASS_SKIPPER.get(), GlassSkipperRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(FTBlocks.CHAINED_TENTACLE.get(), RenderType.cutout());
        ScreenManager.register(FinsContainers.MUDHORSE_POUCH.get(), MudhorsePouchScreen::new);
        ScreenManager.register(FinsContainers.CRAB_CRUNCHER.get(), CrabCruncherScreen::new);
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
