package net.sdm.sdm_rpg_world.modules.rpg.item.genItems;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.sdm.sdm_rpg_world.modules.rpg.item.ItemStatBase;
import net.sdm.sdm_rpg_world.modules.rpg.item.ItemStatRarity;
import net.sdm.sdm_rpg_world.modules.rpg.item.ItemStats;

import java.util.HashMap;
import java.util.Map;

public class LootItemStack {
    public ItemStack item;
    public ItemStatRarity rarity;
    public double maxValues = 0.0;
    public Map<String, ItemStatBase> statBaseList = new HashMap<>();

    public LootItemStack(ItemStack stack, ItemStatRarity rarity) {
        this.item = stack;
        this.rarity = rarity;
    }

    public LootItemStack addStat(ItemStatBase base){
        if(!statBaseList.containsKey(base.name)){
            statBaseList.put(base.name,base);
        } else {
            for (Map.Entry<String, ItemStatBase> d1 : statBaseList.entrySet()) {
                if(d1.getValue().name.equals(base.name)){
                    d1.getValue().value += base.value;
                    break;
                }
            }
        }

        return this;
    }

    public static LootItemStack of(ItemStack stack){
        LootItemStack d1 = new LootItemStack(stack, ItemStatRarity.COMMON);
        if(stack.hasTag()){
            if(stack.getOrCreateTag().contains("sdm_data")){
                ListTag list = (ListTag) stack.getOrCreateTag().get("sdm_data");

                for (int i = 0; i < list.size(); i++) {
                    CompoundTag nbt = list.getCompound(i);
                    for (ItemStatBase itemStatBase : ItemStats.REGISTER) {
                        if(nbt.contains(itemStatBase.name)){
                            ItemStatBase base = itemStatBase.copy();
                            base.value = nbt.getDouble(itemStatBase.name);
                            d1.addStat(base);
                            break;
                        }
                    }
                }
            }
        }

        return d1;
    }

    public ItemStack generateItem(){
        ItemStack stack = item;
        ListTag list = new ListTag();
        for (Map.Entry<String, ItemStatBase> d1 : statBaseList.entrySet()) {
            CompoundTag f2 = new CompoundTag();
            f2.putDouble(d1.getValue().name, d1.getValue().value);
            list.add(f2);
        }
        stack.getOrCreateTag().put("sdm_data", list);

        return stack;
    }
}
