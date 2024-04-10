package net.sdm.sdm_rpg_world.modules.rpg.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemStats {
    public static final List<ItemStatBase> REGISTER = new ArrayList<>();
    public static final ItemStatBase VOID_DAMAGE  = register(ItemStatBase.of("void_damage", new ItemStatType[]{ItemStatType.WEAPON}, ItemStatRarity.RARE));
    public static final ItemStatBase VOID_DAMAGE_BUFF  = register(ItemStatBase.of("void_damage_buff", new ItemStatType[]{ItemStatType.WEAPON}, ItemStatRarity.RARE));
    public static final ItemStatBase DIVINE_DAMAGE  = register(ItemStatBase.of("divine_damage", new ItemStatType[]{ItemStatType.WEAPON}, ItemStatRarity.RARE));
    public static final ItemStatBase DIVINE_DAMAGE_BUFF  = register(ItemStatBase.of("divine_damage_buff", new ItemStatType[]{ItemStatType.WEAPON}, ItemStatRarity.RARE));
    public static final ItemStatBase CHAOS_DAMAGE  = register(ItemStatBase.of("chaos_damage", new ItemStatType[]{ItemStatType.WEAPON}, ItemStatRarity.RARE));
    public static final ItemStatBase CHAOS_DAMAGE_BUFF  = register(ItemStatBase.of("chaos_damage_buff", new ItemStatType[]{ItemStatType.WEAPON}, ItemStatRarity.RARE));
    public static final ItemStatBase VOID_RESIST  = register(ItemStatBase.of("void_resist", new ItemStatType[]{ItemStatType.ARMOR}, ItemStatRarity.RARE));
    public static final ItemStatBase DIVINE_RESIST  = register(ItemStatBase.of("divine_resist", new ItemStatType[]{ItemStatType.ARMOR}, ItemStatRarity.RARE));
    public static final ItemStatBase CHAOS_RESIST  = register(ItemStatBase.of("chaos_resist", new ItemStatType[]{ItemStatType.ARMOR}, ItemStatRarity.RARE));
    public static final ItemStatBase CRITICAL_HIT_CHANCE  = register(ItemStatBase.of("critical_hit_chance", new ItemStatType[]{ItemStatType.ARMOR}, ItemStatRarity.RARE));

    public static ItemStatBase register(ItemStatBase stat){
        REGISTER.add(stat);
        return stat;
    }

    public static List<String> getKeys(){
        List<String> str = new ArrayList<>();
        for (ItemStatBase itemStatBase : REGISTER) {
            str.add(itemStatBase.name);
        }

        return str;
    }

    public static List<ItemStatBase> getStatsByType(ItemStatType type){
        List<ItemStatBase> stat = new ArrayList<>();
        for (ItemStatBase itemStatBase : REGISTER) {
            if(Arrays.stream(itemStatBase.type).toList().contains(type)){
                stat.add(itemStatBase);
            }
        }
        return stat;
    }
}
