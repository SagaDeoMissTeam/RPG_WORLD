package net.sdm.sdm_rpg_world.core.property;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.world.entity.Entity;
import net.sdm.sdm_rpg_world.core.PropertyList;

public class ConditionBase implements IConditionProperty{

    public ConditionBase(){

    }
    @Override
    public boolean makeLogic(Entity player) {
        return false;
    }

    @Override
    public PropertyList getPRList() {
        return null;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        nbt.putString("type", getPRList().toString());
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {

    }
}
