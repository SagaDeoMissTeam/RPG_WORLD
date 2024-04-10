package net.sdm.sdm_rpg_world.modules.rpg.entity.stats;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class EntityStatsBase implements INBTSerializable<SNBTCompoundTag> {

    public String id;

    public EntityStatsBase(String id){
        this.id = id;
    }

    public EntityStatsBase copy(){
        return new EntityStatsBase(id);
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        nbt.putString("id", id);
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
       this.id = nbt.getString("id");
    }

    @Override
    public String toString() {
        return id;
    }
}
