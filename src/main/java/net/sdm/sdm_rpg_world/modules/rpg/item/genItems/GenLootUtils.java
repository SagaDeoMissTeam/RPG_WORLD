package net.sdm.sdm_rpg_world.modules.rpg.item.genItems;

import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.sdm.sdm_rpg_world.modules.rpg.item.ItemStatBase;
import net.sdm.sdm_rpg_world.modules.rpg.item.ItemStatRarity;
import net.sdm.sdm_rpg_world.modules.rpg.item.ItemStatType;
import net.sdm.sdm_rpg_world.modules.rpg.item.ItemStats;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GenLootUtils {
    public static @Nullable Player genLootPlayer = null;
    public static RandomSource randomSource = RandomSource.create();



    public static void generateLootOnChest(Container container){

    }

    public static void generateLootOnChestInStructure(Container container){

    }

    public static void deleteModsLootByModId(Container container, List<String> modId){
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack item = container.getItem(i);
            if(!modId.contains(ForgeRegistries.ITEMS.getKey(item.getItem()).getNamespace())){
                container.setItem(i, ItemStack.EMPTY);
                container.setChanged();
            }
        }
    }

    public static void deleteModsLoot(Container container) {
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack item = container.getItem(i);
            if(!ForgeRegistries.ITEMS.getKey(item.getItem()).getNamespace().equals("minecraft")){
                container.setItem(i, ItemStack.EMPTY);
                container.setChanged();
            }
        }
    }

    public static LootItemStack createItem(){
        return generateStats(GenLootList.getRandomItem().item);
    }


    protected static LootItemStack generateStats(ItemStack stack){
        LootItemStack item = new LootItemStack(stack, ItemStatRarity.getRarity());
        List<ItemStatBase> stats = ItemStats.getStatsByType(ItemStatType.WEAPON);
        item.maxValues = 200.0;


        ItemStatBase d1;
        double d2 = 0.0;
        while (d2 <= item.maxValues) {
            if (randomSource.nextBoolean()) {
                d1 = stats.get(randomSource.nextInt(stats.size()));
                double d3 = randomSource.nextDouble();
                d2 += d3;
                d1.value += d3;
            } else {
                d1 = stats.get(randomSource.nextInt(stats.size()));
                double d3 = -randomSource.nextDouble();
                d2 += d3;
                d1.value += d3;
            }
            item.addStat(d1);
        }

        return item;
    }
}
