package net.sdm.sdm_rpg_world.modules.rpg.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.sdm.sdm_rpg_world.modules.rpg.item.genItems.GenItemStack;
import org.joml.Math;

public enum ItemStatRarity {

    COMMON(1.0),
    UNCOMMON(0.8),
    RARE(0.6),
    EPIC(0.4),
    LEGENDARY(0.2),
    ANCIENT(0.08);

    double chance;
    ItemStatRarity(double chance){
        this.chance = chance;
    }

    public static ItemStatRarity getRarity(){
        double chance = Math.lerp(0, ItemStatRarity.values().length, Math.random());
        double current = 0;
        for (int i = 0; i < ItemStatRarity.values().length; i++) {
            if(current <= chance && chance < current + ItemStatRarity.values()[i].chance){
                return ItemStatRarity.values()[i];
            }
            current += ItemStatRarity.values()[i].chance;
        }

        return ItemStatRarity.COMMON;
    }
}
