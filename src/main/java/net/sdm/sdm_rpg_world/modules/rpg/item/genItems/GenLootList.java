package net.sdm.sdm_rpg_world.modules.rpg.item.genItems;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import dev.architectury.registry.forge.ReloadListenerRegistryImpl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.sdm.sdm_rpg_world.core.rarity.RarityType;
import org.joml.Math;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;


@ZenRegister
@ZenCodeType.Name("mods.rpgworld.rpg.item.GenLootList")
public class GenLootList  {

    public static List<GenItemStack> lootTable = new ArrayList<>();

    @ZenCodeType.Method
    public static GenItemStack register(GenItemStack item){
        lootTable.add(item);
        return item;
    }

    public static GenItemStack getRandomItem(){
        double chance = Math.lerp(0, lootTable.size(), Math.random());
        double current = 0;
        for (int i = 0; i < lootTable.size(); i++) {
            if(current <= chance && chance < current + lootTable.get(i).chance){
                return lootTable.get(i);
            }
            current += lootTable.get(i).chance;
        }

        return new GenItemStack(ItemStack.EMPTY, 0,new CompoundTag());
    }
}
