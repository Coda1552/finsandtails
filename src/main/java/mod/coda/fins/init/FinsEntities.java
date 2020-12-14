package mod.coda.fins.init;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.entity.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FinsEntities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, FinsAndTails.MOD_ID);

    public static final RegistryObject<EntityType<BluWeeEntity>> BLU_WEE = REGISTER.register("blu_wee", () -> EntityType.Builder.<BluWeeEntity>create(BluWeeEntity::new, EntityClassification.WATER_AMBIENT).size(0.3f, 0.2f).build(new ResourceLocation(FinsAndTails.MOD_ID, "blu_wee").toString()));
    public static final RegistryObject<EntityType<PeaWeeEntity>> PEA_WEE = REGISTER.register("pea_wee", () -> EntityType.Builder.<PeaWeeEntity>create(PeaWeeEntity::new, EntityClassification.WATER_AMBIENT).size(0.3f, 0.2f).build(new ResourceLocation(FinsAndTails.MOD_ID, "pea_wee").toString()));
    public static final RegistryObject<EntityType<BandedRedbackShrimpEntity>> BANDED_REDBACK_SHRIMP = REGISTER.register("banded_redback_shrimp", () -> EntityType.Builder.<BandedRedbackShrimpEntity>create(BandedRedbackShrimpEntity::new, EntityClassification.WATER_AMBIENT).size( 0.5f, 0.3f).build(new ResourceLocation(FinsAndTails.MOD_ID, "banded_redback_shrimp").toString()));
    public static final RegistryObject<EntityType<TealArrowfishEntity>> TEAL_ARROWFISH = REGISTER.register("teal_arrowfish", () -> EntityType.Builder.<TealArrowfishEntity>create(TealArrowfishEntity::new, EntityClassification.WATER_AMBIENT).size(0.4f, 0.2f).build(new ResourceLocation(FinsAndTails.MOD_ID, "teal_arrowfish").toString()));
    public static final RegistryObject<EntityType<SwampMuckerEntity>> SWAMP_MUCKER = REGISTER.register("swamp_mucker", () -> EntityType.Builder.<SwampMuckerEntity>create(SwampMuckerEntity::new, EntityClassification.WATER_AMBIENT).size(0.4f, 0.2f).build(new ResourceLocation(FinsAndTails.MOD_ID, "swamp_mucker").toString()));
    public static final RegistryObject<EntityType<FlatbackSuckerEntity>> FLATBACK_SUCKER = REGISTER.register("flatback_sucker", () -> EntityType.Builder.<FlatbackSuckerEntity>create(FlatbackSuckerEntity::new, EntityClassification.WATER_AMBIENT).size(0.4f, 0.2f).build(new ResourceLocation(FinsAndTails.MOD_ID, "flatback_sucker").toString()));
    public static final RegistryObject<EntityType<HighFinnedBlueEntity>> HIGH_FINNED_BLUE = REGISTER.register("high_finned_blue", () -> EntityType.Builder.<HighFinnedBlueEntity>create(HighFinnedBlueEntity::new, EntityClassification.WATER_AMBIENT).size(0.2f, 0.5f).build(new ResourceLocation(FinsAndTails.MOD_ID, "high_finned_blue").toString()));
    public static final RegistryObject<EntityType<MudhorseEntity>> MUDHORSE = REGISTER.register("mudhorse", () -> EntityType.Builder.<MudhorseEntity>create(MudhorseEntity::new, EntityClassification.CREATURE).size(1.3f, 2.2f).build(new ResourceLocation(FinsAndTails.MOD_ID, "mudhorse").toString()));
    public static final RegistryObject<EntityType<OrnateBugfishEntity>> ORNATE_BUGFISH = REGISTER.register("ornate_bugfish", () -> EntityType.Builder.<OrnateBugfishEntity>create(OrnateBugfishEntity::new, EntityClassification.WATER_AMBIENT).size(0.5f, 0.5f).build(new ResourceLocation(FinsAndTails.MOD_ID, "ornate_bugfish").toString()));
    public static final RegistryObject<EntityType<PhantomNudibranchEntity>> PHANTOM_NUDIBRANCH = REGISTER.register("phantom_nudibranch", () -> EntityType.Builder.<PhantomNudibranchEntity>create(PhantomNudibranchEntity::new, EntityClassification.WATER_AMBIENT).size(0.3f, 0.3f).build(new ResourceLocation(FinsAndTails.MOD_ID, "phantom_nudibranch").toString()));
    public static final RegistryObject<EntityType<PenglilEntity>> PENGLIL = REGISTER.register("penglil", () -> EntityType.Builder.<PenglilEntity>create(PenglilEntity::new, EntityClassification.WATER_CREATURE).size( 0.5f, 0.5f).build(new ResourceLocation(FinsAndTails.MOD_ID, "penglil").toString()));
    public static final RegistryObject<EntityType<SpindlyGemCrabEntity>> SPINDLY_GEM_CRAB = REGISTER.register("spindly_gem_crab", () -> EntityType.Builder.<SpindlyGemCrabEntity>create(SpindlyGemCrabEntity::new, EntityClassification.WATER_AMBIENT).size(0.4f, 0.3f).build(new ResourceLocation(FinsAndTails.MOD_ID, "spindly_gem_crab").toString()));
    public static final RegistryObject<EntityType<FlatbackLeafSnailEntity>> FLATBACK_LEAF_SNAIL = REGISTER.register("flatback_leaf_snail", () -> EntityType.Builder.<FlatbackLeafSnailEntity>create(FlatbackLeafSnailEntity::new, EntityClassification.CREATURE).size(0.6f, 0.3f).build(new ResourceLocation(FinsAndTails.MOD_ID, "flatback_leaf_snail").toString()));
}
