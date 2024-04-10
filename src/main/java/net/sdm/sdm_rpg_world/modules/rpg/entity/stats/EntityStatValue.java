package net.sdm.sdm_rpg_world.modules.rpg.entity.stats;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.IntTag;

public class EntityStatValue<T extends Number> extends EntityStatsBase{

    public T value;
    public EntityStatValue(String id, T value) {
        super(id);
        this.value = value;
    }

    public void updateValue(Number value) {
        this.value = (T) value;
    }
    public void addValue(Number value){
        Number vl = this.value.doubleValue() + value.doubleValue();
        this.value = (T) vl;
    }

    @Override
    public EntityStatsBase copy() {
        return new EntityStatValue<T>(this.id, this.value);
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = super.serializeNBT();
        if(value instanceof Float d1){
            nbt.putFloat("value", d1);
        } else if(value instanceof Double d1){
            nbt.putDouble("value", d1);
        } else if(value instanceof Integer d1){
            nbt.putInt("value", d1);
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        super.deserializeNBT(nbt);
        if(nbt.get("value") instanceof IntTag intTag){
            this.value = (T)(Integer)Integer.valueOf(nbt.getInt("value"));
        }
        if(nbt.get("value") instanceof FloatTag intTag){

            this.value = (T)(Float)Float.valueOf(nbt.getFloat("value"));
        }
        if(nbt.get("value") instanceof DoubleTag intTag){

            this.value = (T)(Double)Double.valueOf(nbt.getDouble("value"));
        }
    }

    @Override
    public String toString() {
        return id + " : " + value;
    }
}
