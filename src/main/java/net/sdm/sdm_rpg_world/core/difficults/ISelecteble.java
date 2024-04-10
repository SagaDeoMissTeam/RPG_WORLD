package net.sdm.sdm_rpg_world.core.difficults;

import net.minecraft.nbt.CompoundTag;

public interface ISelecteble {


    default CompoundTag getNbt(){
        return new CompoundTag();
    }
    int getID();
    boolean isSelected();
}
