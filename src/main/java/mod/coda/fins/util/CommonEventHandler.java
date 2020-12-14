package mod.coda.fins.util;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.entity.PenglilEntity;
import mod.coda.fins.init.FinsItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

import static net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEventHandler {

    @SubscribeEvent
    public static void spawnEntity(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof DolphinEntity) {
            ((DolphinEntity) entity).targetSelector.addGoal(0, new NearestAttackableTargetGoal<>((MobEntity) entity, PenglilEntity.class, true));
        }
    }

    //Thanks to Wolf for helping with the trade code
    @SubscribeEvent
    public static void addWandererTrades(WandererTradesEvent event) {
        List<ITrade> list = event.getGenericTrades();

        list.add(cdForItems(FinsItems.HIGH_FINNED_BLUE_BUCKET.get(), 1, 4, 1));
        list.add(cdForItems(FinsItems.PEA_WEE_BUCKET.get(), 1, 4, 2));
        list.add(cdForItems(FinsItems.BLU_WEE_BUCKET.get(), 1, 4, 1));
        list.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 6), new ItemStack(FinsItems.SPINDLY_GEM_CRAB_GEM.get(), 1), 2, 4, 1.5f));
        list.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 4), new ItemStack(FinsItems.FWIN.get(), 1), 2, 3, 1.5f));
        list.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 7), new ItemStack(FinsItems.MUDHORSE_LEATHER.get(), 1), 2, 4, 1.5f));
        list.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(FinsItems.FLATBACK_LEAF_SNAIL_SHELL.get(), 1), 2, 4, 1.5f));
    }

    private static ITrade cdForItems(ItemStack selling, int maxUses, int xp) {
        return new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 5), selling, maxUses, xp, 0);
    }

    private static ITrade cdForItems(Item item, int count, int maxUses, int xp) {
        return cdForItems(new ItemStack(item, count), maxUses, xp);
    }

    private static class ItemsForItemsTrade implements ITrade {
        private final ItemStack buying1, buying2, selling;
        private final int maxUses, xp;
        private final float priceMultiplier;

        public ItemsForItemsTrade(ItemStack buying1, ItemStack buying2, ItemStack selling, int maxUses, int xp, float priceMultiplier) {
            this.buying1 = buying1;
            this.buying2 = buying2;
            this.selling = selling;
            this.maxUses = maxUses;
            this.xp = xp;
            this.priceMultiplier = priceMultiplier;
        }

        public ItemsForItemsTrade(ItemStack buying1, ItemStack selling, int maxUses, int xp, float priceMultiplier) {
            this(buying1, ItemStack.EMPTY, selling, maxUses, xp, priceMultiplier);
        }

        @Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, Random rand) {
            return new MerchantOffer(buying1, buying2, selling, maxUses, xp, priceMultiplier);
        }
    }
}