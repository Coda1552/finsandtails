package teamdraco.finsandstails.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.enchantments.CrabsFavorEnchantment;
import teamdraco.finsandstails.common.enchantments.FlukedEdgeEnchantment;
import teamdraco.finsandstails.common.enchantments.UppercuttingEnchantment;

public class FinsEnchantments {
    public static final DeferredRegister<Enchantment> REGISTER = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, FinsAndTails.MOD_ID);

    public static final RegistryObject<Enchantment> FLUKED_EDGE = REGISTER.register("fluked_edge", () -> new FlukedEdgeEnchantment(Enchantment.Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[] {EquipmentSlotType.FEET}));
    public static final RegistryObject<Enchantment> UPPERCUTTING = REGISTER.register("uppercutting", () -> new UppercuttingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND}));
    public static final RegistryObject<Enchantment> CRABS_FAVOR = REGISTER.register("crabs_favor", () -> new CrabsFavorEnchantment(Enchantment.Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND}));
}
