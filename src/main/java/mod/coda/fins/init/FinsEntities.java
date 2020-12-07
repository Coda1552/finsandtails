package mod.coda.fins.init;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.entity.*;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FinsEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, FinsAndTails.MOD_ID);

    public static final EntityType<BluWeeEntity> BLU_WEE = create("blu_wee", BluWeeEntity::new, EntityClassification.WATER_AMBIENT, 0.3f, 0.2f, 0x3f74a2, 0x2a4b8c);
    public static final EntityType<PeaWeeEntity> PEA_WEE = create("pea_wee", PeaWeeEntity::new, EntityClassification.WATER_AMBIENT, 0.3f, 0.2f, 0x31a643, 0x1a7a3e);
    public static final EntityType<BandedRedbackShrimpEntity> BANDED_REDBACK_SHRIMP = create("banded_redback_shrimp", BandedRedbackShrimpEntity::new, EntityClassification.WATER_AMBIENT, 0.5f, 0.3f, 0x155556, 0x921a2f);
    public static final EntityType<TealArrowfishEntity> TEAL_ARROWFISH = create("teal_arrowfish", TealArrowfishEntity::new, EntityClassification.WATER_AMBIENT, 0.4f, 0.2f, 0x004d40, 0x427c8a);
    public static final EntityType<SwampMuckerEntity> SWAMP_MUCKER = create("swamp_mucker", SwampMuckerEntity::new, EntityClassification.WATER_AMBIENT, 0.4f, 0.2f, 0x5e663b, 0x997d45);
    public static final EntityType<FlatbackSuckerEntity> FLATBACK_SUCKER = create("flatback_sucker", FlatbackSuckerEntity::new, EntityClassification.WATER_AMBIENT, 0.4f, 0.2f, 0x898364, 0x4b7486);
    public static final EntityType<HighfinnedBlueEntity> HIGHFINNED_BLUE = create("highfinned_blue", HighfinnedBlueEntity::new, EntityClassification.WATER_AMBIENT, 0.2f, 0.5f, 0x4b6e81, 0x425e85);
    public static final EntityType<MudhorseEntity> MUDHORSE = create("mudhorse", MudhorseEntity::new, EntityClassification.CREATURE, 1.3f, 2.2f, 0xc39858, 0x653e2c);
    public static final EntityType<OrnateBugfishEntity> ORNATE_BUGFISH = create("ornate_bugfish", OrnateBugfishEntity::new, EntityClassification.WATER_AMBIENT, 0.5f, 0.5f, 0xe5d7ce, 0xf27a00);
    public static final EntityType<PhantomNudibranchEntity> PHANTOM_NUDIBRANCH = create("phantom_nudibranch", PhantomNudibranchEntity::new, EntityClassification.WATER_AMBIENT, 0.3f, 0.3f, 0xcfe9ff, 0x8692bf);
    public static final EntityType<PenglilEntity> PENGLIL = create("penglil", PenglilEntity::new, EntityClassification.WATER_CREATURE, 0.5f, 0.5f, 0x536c6d, 0xb3d7de);
    public static final EntityType<SpindlyGemCrabEntity> SPINDLY_GEM_CRAB = create("spindly_gem_crab", SpindlyGemCrabEntity::new, EntityClassification.WATER_AMBIENT, 0.4f, 0.3f, 0xe4ad4c, 0x6be263);
    //public static final EntityType<RubberBellyGliderEntity> RUBBER_BELLY_GLIDER = create("rubber_belly_glider", RubberBellyGliderEntity::new, EntityClassification.WATER_CREATURE, 0.6f, 0.4f, 0x71a869, 0xe3b334);
    //public static final EntityType<GopjetEntity> GOPJET = create("gopjet", GopjetEntity::new, EntityClassification.WATER_AMBIENT, 0.3f, 0.3f, 0xb6e0e8, 0x355c7f);

    private static <T extends CreatureEntity> EntityType<T> create(String name, EntityType.IFactory<T> factory, EntityClassification classification, float width, float height, int pri, int sec) {
        final Item.Properties properties = new Item.Properties().group(FinsAndTails.GROUP);
        EntityType<T> type = create(name, factory, classification, width, height);
        FinsItems.REGISTRY.register(name + "_spawn_egg", () -> new SpawnEggItem(type, pri, sec, properties));
        return type;
    }

    private static <T extends Entity> EntityType<T> create(String name, EntityType.IFactory<T> factory, EntityClassification classification, float width, float height) {
        EntityType<T> type = EntityType.Builder.create(factory, classification).size(width, height).setTrackingRange(128).build(name);
        REGISTRY.register(name, () -> type);
        return type;
    }
}
