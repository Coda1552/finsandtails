package teamdraco.fins;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import teamdraco.fins.client.ClientEvents;
import teamdraco.fins.init.*;
import teamdraco.fins.network.INetworkPacket;
import teamdraco.fins.network.TriggerFlyingPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamdraco.fins.common.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Mod(FinsAndTails.MOD_ID)
public class FinsAndTails {
    public static final String MOD_ID = "fins";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final SimpleChannel NETWORK = INetworkPacket.makeChannel("network", "1");
    public static final List<Runnable> CALLBACKS = new ArrayList<>();
    private static int currentNetworkId;
    private static boolean wasJumping;

    public void playerTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean jumping = minecraft.gameSettings.keyBindJump.isKeyDown();
            if (jumping != wasJumping) {
                TriggerFlyingPacket packet = new TriggerFlyingPacket(jumping);
                packet.handle(minecraft.player);
                FinsAndTails.NETWORK.sendToServer(packet);
            }
            wasJumping = jumping;
        }
    }

    public FinsAndTails() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        bus.addListener(this::registerClient);
        bus.addListener(this::registerCommon);
        forgeBus.addListener(this::playerTick);

        FinsEnchantments.REGISTER.register(bus);
        FinsItems.REGISTER.register(bus);
        FinsBlocks.REGISTER.register(bus);
        FinsContainers.REGISTER.register(bus);
        FinsEntities.REGISTER.register(bus);
        FinsSounds.REGISTER.register(bus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FinsConfig.Common.SPEC);
        registerMessage(TriggerFlyingPacket.class, TriggerFlyingPacket::new, LogicalSide.SERVER);
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        registerEntityAttributes();
        EntitySpawnPlacementRegistry.register(FinsEntities.BLU_WEE.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.PEA_WEE.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.BANDED_REDBACK_SHRIMP.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.SWAMP_MUCKER.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.TEAL_ARROWFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.FLATBACK_SUCKER.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.HIGH_FINNED_BLUE.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.MUDHORSE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(FinsEntities.PHANTOM_NUDIBRANCH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.ORNATE_BUGFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.PENGLIL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PenglilEntity::canPenglilSpawn);
        EntitySpawnPlacementRegistry.register(FinsEntities.SPINDLY_GEM_CRAB.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.FLATBACK_LEAF_SNAIL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(FinsEntities.RED_BULL_CRAB.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RedBullCrabEntity::canCrabSpawn);
        EntitySpawnPlacementRegistry.register(FinsEntities.WHITE_BULL_CRAB.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RedBullCrabEntity::canCrabSpawn);
        EntitySpawnPlacementRegistry.register(FinsEntities.WEE_WEE.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.VIBRA_WEE.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.RIVER_PEBBLE_SNAIL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(FinsEntities.SIDEROL_WHISKERED_SNAIL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(FinsEntities.GOLDEN_RIVER_RAY.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.NIGHT_LIGHT_SQUID.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, NightLightSquidEntity::func_223365_b);
        EntitySpawnPlacementRegistry.register(FinsEntities.RUBBER_BELLY_GLIDER.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RubberBellyGliderEntity::canGliderSpawn);

        BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD)), Ingredient.fromItems(FinsItems.NIGHT_LIGHT_SQUID.get()), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.NIGHT_VISION));
    }

    private void registerEntityAttributes() {
        GlobalEntityTypeAttributes.put(FinsEntities.BANDED_REDBACK_SHRIMP.get(), BandedRedbackShrimpEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.BLU_WEE.get(), BluWeeEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.FLATBACK_SUCKER.get(), FlatbackSuckerEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.HIGH_FINNED_BLUE.get(), HighFinnedBlueEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.MUDHORSE.get(), MudhorseEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.ORNATE_BUGFISH.get(), OrnateBugfishEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.PEA_WEE.get(), PeaWeeEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.PENGLIL.get(), PenglilEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.PHANTOM_NUDIBRANCH.get(), PhantomNudibranchEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.SPINDLY_GEM_CRAB.get(), SpindlyGemCrabEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.SWAMP_MUCKER.get(), SwampMuckerEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.TEAL_ARROWFISH.get(), TealArrowfishEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.FLATBACK_LEAF_SNAIL.get(), FlatbackLeafSnailEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.RUBBER_BELLY_GLIDER.get(), RubberBellyGliderEntity.registerRBGAttributes().create());
        GlobalEntityTypeAttributes.put(FinsEntities.RED_BULL_CRAB.get(), RedBullCrabEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.WHITE_BULL_CRAB.get(), WhiteBullCrabEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.WEE_WEE.get(), WhiteBullCrabEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.VIBRA_WEE.get(), WhiteBullCrabEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.GOPJET.get(), GopjetEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.RIVER_PEBBLE_SNAIL.get(), RiverPebbleSnailEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.SIDEROL_WHISKERED_SNAIL.get(), SiderolWhiskeredSnailEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.GOLDEN_RIVER_RAY.get(), GoldenRiverRayEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(FinsEntities.NIGHT_LIGHT_SQUID.get(), NightLightSquidEntity.func_234227_m_().create());
    }

    private void registerClient(FMLClientSetupEvent event) {
        ClientEvents.init();
        CALLBACKS.forEach(Runnable::run);
        CALLBACKS.clear();
    }

    private <T extends INetworkPacket> void registerMessage(Class<T> message, Supplier<T> supplier, LogicalSide side) {
        NETWORK.registerMessage(currentNetworkId++, message, INetworkPacket::write, buffer -> {
            T msg = supplier.get();
            msg.read(buffer);
            return msg;
        }, (msg, contextSupplier) -> {
            NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> msg.handle(context.getDirection().getOriginationSide().isServer() ? getClientPlayer() : context.getSender()));
            context.setPacketHandled(true);
        }, Optional.of(side.isClient() ? NetworkDirection.PLAY_TO_CLIENT : NetworkDirection.PLAY_TO_SERVER));
    }

    @OnlyIn(Dist.CLIENT)
    private static PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }

    public final static ItemGroup GROUP = new ItemGroup(MOD_ID) {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(FinsItems.BLU_WEE.get());
        }

        @Override
        public void fill(NonNullList<ItemStack> items) {
            for (int i = 0; i <= 4; i++) {
                ItemStack stack = new ItemStack(FinsItems.SPINDLY_GEM_CRAB.get());
                CompoundNBT tag = stack.getOrCreateTag();
                tag.putInt("Crab", i);
                items.add(stack);
            }

            super.fill(items);
        }
    };
}
