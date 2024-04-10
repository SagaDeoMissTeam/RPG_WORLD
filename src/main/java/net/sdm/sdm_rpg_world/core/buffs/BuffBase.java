package net.sdm.sdm_rpg_world.core.buffs;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.sdm.sdm_rpg_world.core.BuffsList;
import  net.minecraft.network.chat.Component;
import java.awt.*;

public abstract class BuffBase implements IBuff{
    public boolean positive = false;
    public BuffSide side = BuffSide.ENTITY;
    public BuffBase(){

    }
    public BuffBase(boolean positive){
        this.positive = positive;
    }

    @Override
    public BuffsList getBuff() {
        return null;
    }

    @Override
    public boolean positive() {
        return positive;
    }

    @Override
    public BuffSide getSide() {
        return side;
    }

    @Override
    public boolean negative() {
        return !positive;
    }

    @Override
    public BuffBase generated() {
        return this;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        nbt.putString("type", getBuff().toString());
        nbt.putBoolean("positive", positive);
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        positive = nbt.getBoolean("positive");
    }
}
