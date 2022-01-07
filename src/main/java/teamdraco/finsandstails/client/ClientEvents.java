package teamdraco.finsandstails.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.render.*;
import teamdraco.finsandstails.client.screen.CrabCruncherScreen;
import teamdraco.finsandstails.client.screen.MudhorsePouchScreen;
import teamdraco.finsandstails.common.items.FinsSpawnEggItem;
import teamdraco.finsandstails.registry.FTBlocks;
import teamdraco.finsandstails.registry.FinsContainers;
import teamdraco.finsandstails.registry.FinsEntities;
import teamdraco.finsandstails.registry.FTItems;
import teamdraco.finsandstails.network.TriggerFlyingPacket;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.BLU_WEE.get(), BluWeeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.PEA_WEE.get(), PeaWeeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.BANDED_REDBACK_SHRIMP.get(), BandedRedbackShrimpRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.TEAL_ARROWFISH.get(), TealArrowfishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.SWAMP_MUCKER.get(), SwampMuckerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.FLATBACK_SUCKER.get(), FlatbackSuckerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.HIGH_FINNED_BLUE.get(), HighFinnedBlueRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.MUDHORSE.get(), MudhorseRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.ORNATE_BUGFISH.get(), OrnateBugfishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.PHANTOM_NUDIBRANCH.get(), PhantomNudibranchRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.PENGLIL.get(), PenglilRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.SPINDLY_GEM_CRAB.get(), SpindlyGemCrabRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.FLATBACK_LEAF_SNAIL.get(), FlatbackLeafSnailRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.RUBBER_BELLY_GLIDER.get(), RubberBellyGliderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.RED_BULL_CRAB.get(), RedBullCrabRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.WHITE_BULL_CRAB.get(), WhiteBullCrabRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.WEE_WEE.get(), WeeWeeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.VIBRA_WEE.get(), VibraWeeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.GOPJET.get(), GopjetRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.RIVER_PEBBLE_SNAIL.get(), RiverPebbleSnailRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.SIDEROL_WHISKERED_SNAIL.get(), SiderolWhiskeredSnailRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.GOLDEN_RIVER_RAY.get(), GoldenRiverRayRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.NIGHT_LIGHT_SQUID.get(), NightLightSquidRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.TEAL_ARROWFISH_ARROW.get(), TealArrowfishArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.PAPA_WEE.get(), PapaWeeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.WHERBLE.get(), WherbleRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.WANDERING_SAILOR.get(), WanderingSailorRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.GOLIATH_GARDEN_CRAB.get(), GoliathGardenCrabRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.GLASS_SKIPPER.get(), GlassSkipperRenderer::new);

        RenderTypeLookup.setRenderLayer(FTBlocks.CHAINED_TENTACLE.get(), RenderType.cutout());
        ScreenManager.register(FinsContainers.MUDHORSE_POUCH.get(), MudhorsePouchScreen::new);
        ScreenManager.register(FinsContainers.CRAB_CRUNCHER.get(), CrabCruncherScreen::new);
    }

    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        IItemColor eggColor = (stack, tintIndex) -> ((FinsSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (FinsSpawnEggItem e : FinsSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }

    @Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ModBus {
        private static boolean wasJumping;

        @SubscribeEvent
        public static void clientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                Minecraft minecraft = Minecraft.getInstance();
                final ClientPlayerEntity player = minecraft.player;
                if (player != null && player.getItemBySlot(EquipmentSlotType.CHEST).getItem() == FTItems.GOPJET_JETPACK.get()) {
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
