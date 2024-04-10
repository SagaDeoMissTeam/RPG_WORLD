package net.sdm.sdm_rpg_world.core.rarity;

public enum RarityType {
    NONE,
    COMMON(0.80),
    UNCOMMON(0.66),
    RARE(0.33),
    EPIC(0.25),
    LEGENDARY(0.10);


    double chance;
    RarityType(){
        this.chance = 1.0;
    }
    RarityType(double chance){
        this.chance = chance;
    }

    public double getChance() {
        return chance;
    }
}
