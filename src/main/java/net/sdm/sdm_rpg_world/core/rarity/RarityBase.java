package net.sdm.sdm_rpg_world.core.rarity;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Random;

public class RarityBase implements INBTSerializable<SNBTCompoundTag> {
    public RarityType type;
    public int countBuff = 0;
    public RarityBase(RarityType type){
        this.type = type;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        nbt.putString("rarity", type.toString());
        nbt.putInt("countBuff", countBuff);
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        type = RarityType.valueOf(nbt.getString("rarity"));
        countBuff =  nbt.getInt("countBuff");
    }
}
