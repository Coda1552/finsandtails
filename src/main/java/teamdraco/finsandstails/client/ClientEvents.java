package teamdraco.finsandstails.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.render.*;
import teamdraco.finsandstails.client.screen.CrabCruncherScreen;
import teamdraco.finsandstails.client.screen.MudhorsePouchScreen;
import teamdraco.finsandstails.common.items.charms.SpindlyGemCharm;
import teamdraco.finsandstails.network.TriggerFlyingPacket;
import teamdraco.finsandstails.registry.FTBlocks;
import teamdraco.finsandstails.registry.FTContainers;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
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
