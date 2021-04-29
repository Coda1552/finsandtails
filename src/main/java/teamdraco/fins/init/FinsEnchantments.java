package teamdraco.fins.init;

import teamdraco.fins.FinsAndTails;
import teamdraco.fins.common.enchantments.FlukedEdgeEnchantment;
import teamdraco.fins.common.enchantments.UppercuttingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FinsEnchantments {
    public static final DeferredRegister<Enchantment> REGISTER = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, FinsAndTails.MOD_ID);

    public static final RegistryObject<Enchantment> FLUKED_EDGE = REGISTER.register("fluked_edge", () -> new FlukedEdgeEnchantment(Enchantment.Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[] {EquipmentSlotType.FEET}));
    public static final RegistryObject<Enchantment> UPPERCUTTING = REGISTER.register("uppercutting", () -> new UppercuttingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND}));

}