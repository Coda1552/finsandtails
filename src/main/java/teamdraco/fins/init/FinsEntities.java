package teamdraco.fins.init;

import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.entities.item.TealArrowfishArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.fins.common.entities.*;
import teamdraco.fins.common.entities.GoliathGardenCrabEntity;

public class FinsEntities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, FinsAndTails.MOD_ID);

    public static final RegistryObject<EntityType<BluWeeEntity>> BLU_WEE = create("blu_wee", EntityType.Builder.of(BluWeeEntity::new, EntityClassification.WATER_AMBIENT).sized(0.3f, 0.2f));
    public static final RegistryObject<EntityType<PeaWeeEntity>> PEA_WEE = create("pea_wee", EntityType.Builder.of(PeaWeeEntity::new, EntityClassification.WATER_AMBIENT).sized(0.3f, 0.2f));
    public static final RegistryObject<EntityType<BandedRedbackShrimpEntity>> BANDED_REDBACK_SHRIMP = create("banded_redback_shrimp", EntityType.Builder.of(BandedRedbackShrimpEntity::new, EntityClassification.WATER_AMBIENT).sized( 0.5f, 0.3f));
    public static final RegistryObject<EntityType<TealArrowfishEntity>> TEAL_ARROWFISH = create("teal_arrowfish", EntityType.Builder.of(TealArrowfishEntity::new, EntityClassification.WATER_AMBIENT).sized(0.4f, 0.2f));
    public static final RegistryObject<EntityType<SwampMuckerEntity>> SWAMP_MUCKER = create("swamp_mucker", EntityType.Builder.of(SwampMuckerEntity::new, EntityClassification.WATER_AMBIENT).sized(0.4f, 0.2f));
    public static final RegistryObject<EntityType<FlatbackSuckerEntity>> FLATBACK_SUCKER = create("flatback_sucker", EntityType.Builder.of(FlatbackSuckerEntity::new, EntityClassification.WATER_AMBIENT).sized(0.4f, 0.2f));
    public static final RegistryObject<EntityType<HighFinnedBlueEntity>> HIGH_FINNED_BLUE = create("high_finned_blue", EntityType.Builder.of(HighFinnedBlueEntity::new, EntityClassification.WATER_AMBIENT).sized(0.2f, 0.5f));
    public static final RegistryObject<EntityType<MudhorseEntity>> MUDHORSE = create("mudhorse", EntityType.Builder.of(MudhorseEntity::new, EntityClassification.CREATURE).sized(0.9f, 1.6f));
    public static final RegistryObject<EntityType<OrnateBugfishEntity>> ORNATE_BUGFISH = create("ornate_bugfish", EntityType.Builder.of(OrnateBugfishEntity::new, EntityClassification.WATER_AMBIENT).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<PhantomNudibranchEntity>> PHANTOM_NUDIBRANCH = create("phantom_nudibranch", EntityType.Builder.of(PhantomNudibranchEntity::new, EntityClassification.WATER_AMBIENT).sized(0.3f, 0.3f));
    public static final RegistryObject<EntityType<PenglilEntity>> PENGLIL = create("penglil", EntityType.Builder.of(PenglilEntity::new, EntityClassification.WATER_CREATURE).sized( 0.5f, 0.5f));
    public static final RegistryObject<EntityType<SpindlyGemCrabEntity>> SPINDLY_GEM_CRAB = create("spindly_gem_crab", EntityType.Builder.of(SpindlyGemCrabEntity::new, EntityClassification.WATER_AMBIENT).sized(0.4f, 0.3f));
    public static final RegistryObject<EntityType<FlatbackLeafSnailEntity>> FLATBACK_LEAF_SNAIL = create("flatback_leaf_snail", EntityType.Builder.of(FlatbackLeafSnailEntity::new, EntityClassification.CREATURE).sized(0.6f, 0.3f));
    public static final RegistryObject<EntityType<RedBullCrabEntity>> RED_BULL_CRAB = create("red_bull_crab", EntityType.Builder.of(RedBullCrabEntity::new, EntityClassification.WATER_CREATURE).sized(0.5f, 0.3f));
    public static final RegistryObject<EntityType<WhiteBullCrabEntity>> WHITE_BULL_CRAB = create("white_bull_crab", EntityType.Builder.of(WhiteBullCrabEntity::new, EntityClassification.WATER_CREATURE).sized(0.5f, 0.3f));
    public static final RegistryObject<EntityType<WeeWeeEntity>> WEE_WEE = create("wee_wee", EntityType.Builder.of(WeeWeeEntity::new, EntityClassification.WATER_AMBIENT).sized(0.3f, 0.3f));
    public static final RegistryObject<EntityType<VibraWeeEntity>> VIBRA_WEE = create("vibra_wee", EntityType.Builder.of(VibraWeeEntity::new, EntityClassification.WATER_AMBIENT).sized(0.3f, 0.4f));
    public static final RegistryObject<EntityType<RiverPebbleSnailEntity>> RIVER_PEBBLE_SNAIL = create("river_pebble_snail", EntityType.Builder.of(RiverPebbleSnailEntity::new, EntityClassification.AMBIENT).sized(0.3f, 0.3f));
    public static final RegistryObject<EntityType<SiderolWhiskeredSnailEntity>> SIDEROL_WHISKERED_SNAIL = create("siderol_whiskered_snail", EntityType.Builder.of(SiderolWhiskeredSnailEntity::new, EntityClassification.CREATURE).sized(0.3f, 0.4f));
    public static final RegistryObject<EntityType<GoldenRiverRayEntity>> GOLDEN_RIVER_RAY = create("golden_river_ray", EntityType.Builder.of(GoldenRiverRayEntity::new, EntityClassification.WATER_CREATURE).sized(0.7f, 0.3f));
    public static final RegistryObject<EntityType<NightLightSquidEntity>> NIGHT_LIGHT_SQUID = create("night_light_squid", EntityType.Builder.of(NightLightSquidEntity::new, EntityClassification.WATER_CREATURE).sized(0.4f, 0.3f));
    public static final RegistryObject<EntityType<RubberBellyGliderEntity>> RUBBER_BELLY_GLIDER = create("rubber_belly_glider", EntityType.Builder.of(RubberBellyGliderEntity::new, EntityClassification.WATER_CREATURE).sized(0.7f, 0.285f));
    public static final RegistryObject<EntityType<TealArrowfishArrowEntity>> TEAL_ARROWFISH_ARROW = create("teal_arrowfish_arrow", EntityType.Builder.<TealArrowfishArrowEntity>of(TealArrowfishArrowEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<GopjetEntity>> GOPJET = create("gopjet", EntityType.Builder.of(GopjetEntity::new, EntityClassification.WATER_CREATURE).sized(0.7f, 0.5f));
    public static final RegistryObject<EntityType<PapaWeeEntity>> PAPA_WEE = create("papa_wee", EntityType.Builder.of(PapaWeeEntity::new, EntityClassification.WATER_CREATURE).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<WherbleEntity>> WHERBLE = create("wherble", EntityType.Builder.of(WherbleEntity::new, EntityClassification.CREATURE).sized(0.6f, 0.4f));
    public static final RegistryObject<EntityType<WanderingSailorEntity>> WANDERING_SAILOR = create("wandering_sailor", EntityType.Builder.of(WanderingSailorEntity::new, EntityClassification.CREATURE).sized(0.6f, 1.65f));
    public static final RegistryObject<EntityType<GoliathGardenCrabEntity>> GOLIATH_GARDEN_CRAB = create("goliath_garden_crab", EntityType.Builder.of(GoliathGardenCrabEntity::new, EntityClassification.WATER_AMBIENT).sized(3.5f, 2.5f));
    public static final RegistryObject<EntityType<GlassSkipperEntity>> GLASS_SKIPPER = create("glass_skipper", EntityType.Builder.of(GlassSkipperEntity::new, EntityClassification.CREATURE).sized(0.5f, 0.85f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return REGISTER.register(name, () -> builder.build(FinsAndTails.MOD_ID + "." + name));
    }
}
