package net.sdm.sdm_rpg_world.modules.rpg.item;

public class ItemStatBase {

    public String name;
    public ItemStatType[] type;
    public double value = 0.0;
    public ItemStatRarity rarity;

    public ItemStatBase(String name, ItemStatType[] type, ItemStatRarity rarity){
        this.name = name;
        this.type = type;
        this.rarity = rarity;
    }

    public ItemStatBase(String name, double value, ItemStatType[] type, ItemStatRarity rarity ){
        this.name = name;
        this.type = type;
        this.value = value;
        this.rarity = rarity;
    }

    public static ItemStatBase of(String name, ItemStatType[] type, ItemStatRarity rarity){
        return new ItemStatBase(name, type, rarity);
    }

    public ItemStatBase instance(double value){
        this.value = value;
        return this;
    }

    public ItemStatBase copy(){
        return new ItemStatBase(this.name, this.value, this.type, this.rarity);
    }
}
