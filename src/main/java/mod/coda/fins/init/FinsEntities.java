package mod.coda.fins.init;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FinsEntities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, FinsAndTails.MOD_ID);

    public static final RegistryObject<EntityType<BluWeeEntity>> BLU_WEE = create("blu_wee", EntityType.Builder.create(BluWeeEntity::new, EntityClassification.WATER_AMBIENT).size(0.3f, 0.2f));
    public static final RegistryObject<EntityType<PeaWeeEntity>> PEA_WEE = create("pea_wee", EntityType.Builder.create(PeaWeeEntity::new, EntityClassification.WATER_AMBIENT).size(0.3f, 0.2f));
    public static final RegistryObject<EntityType<BandedRedbackShrimpEntity>> BANDED_REDBACK_SHRIMP = create("banded_redback_shrimp", EntityType.Builder.create(BandedRedbackShrimpEntity::new, EntityClassification.WATER_AMBIENT).size( 0.5f, 0.3f));
    public static final RegistryObject<EntityType<TealArrowfishEntity>> TEAL_ARROWFISH = create("teal_arrowfish", EntityType.Builder.create(TealArrowfishEntity::new, EntityClassification.WATER_AMBIENT).size(0.4f, 0.2f));
    public static final RegistryObject<EntityType<SwampMuckerEntity>> SWAMP_MUCKER = create("swamp_mucker", EntityType.Builder.create(SwampMuckerEntity::new, EntityClassification.WATER_AMBIENT).size(0.4f, 0.2f));
    public static final RegistryObject<EntityType<FlatbackSuckerEntity>> FLATBACK_SUCKER = create("flatback_sucker", EntityType.Builder.create(FlatbackSuckerEntity::new, EntityClassification.WATER_AMBIENT).size(0.4f, 0.2f));
    public static final RegistryObject<EntityType<HighFinnedBlueEntity>> HIGH_FINNED_BLUE = create("high_finned_blue", EntityType.Builder.create(HighFinnedBlueEntity::new, EntityClassification.WATER_AMBIENT).size(0.2f, 0.5f));
    public static final RegistryObject<EntityType<MudhorseEntity>> MUDHORSE = create("mudhorse", EntityType.Builder.create(MudhorseEntity::new, EntityClassification.CREATURE).size(1.3f, 2.2f));
    public static final RegistryObject<EntityType<OrnateBugfishEntity>> ORNATE_BUGFISH = create("ornate_bugfish", EntityType.Builder.create(OrnateBugfishEntity::new, EntityClassification.WATER_AMBIENT).size(0.5f, 0.5f));
    public static final RegistryObject<EntityType<PhantomNudibranchEntity>> PHANTOM_NUDIBRANCH = create("phantom_nudibranch", EntityType.Builder.create(PhantomNudibranchEntity::new, EntityClassification.WATER_AMBIENT).size(0.3f, 0.3f));
    public static final RegistryObject<EntityType<PenglilEntity>> PENGLIL = create("penglil", EntityType.Builder.create(PenglilEntity::new, EntityClassification.WATER_CREATURE).size( 0.5f, 0.5f));
    public static final RegistryObject<EntityType<SpindlyGemCrabEntity>> SPINDLY_GEM_CRAB = create("spindly_gem_crab", EntityType.Builder.create(SpindlyGemCrabEntity::new, EntityClassification.WATER_AMBIENT).size(0.4f, 0.3f));
    public static final RegistryObject<EntityType<FlatbackLeafSnailEntity>> FLATBACK_LEAF_SNAIL = create("flatback_leaf_snail", EntityType.Builder.create(FlatbackLeafSnailEntity::new, EntityClassification.CREATURE).size(0.6f, 0.3f));
//    public static final RegistryObject<EntityType<RubberBellyGliderEntity>> RUBBER_BELLY_GLIDER = create("rubber_belly_glider", EntityType.Builder.create(RubberBellyGliderEntity::new, EntityClassification.WATER_CREATURE).size(0.7f, 0.4f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return REGISTER.register(name, () -> builder.build(FinsAndTails.MOD_ID + "." + name));
    }
}
