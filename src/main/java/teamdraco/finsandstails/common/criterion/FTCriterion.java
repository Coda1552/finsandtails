package teamdraco.finsandstails.common.criterion;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.fml.common.Mod;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.registry.FTCriteriaTriggers;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID)
public class FTCriterion {

    public static final FTCriteriaTriggers THROW_WHERBLING_IN_THE_VOID = CriteriaTriggers.register(new FTCriteriaTriggers("throw_wherbling_in_the_void"));

}