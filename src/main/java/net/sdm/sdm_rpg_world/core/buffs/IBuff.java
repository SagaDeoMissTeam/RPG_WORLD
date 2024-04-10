package net.sdm.sdm_rpg_world.core.buffs;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.util.INBTSerializable;
import net.sdm.sdm_rpg_world.core.BuffsList;

import java.util.ArrayList;
import java.util.List;

public interface IBuff extends INBTSerializable<SNBTCompoundTag> {

    BuffBase generated();
    default BuffSide getSide() {
        return BuffSide.ENTITY;
    };
    BuffsList getBuff();
    boolean positive();
    boolean negative();

    default Object calculate(){
        return null;
    }

    default String toText(){
        return "NULL";
    }
    default List<Component> tooltips(){
        return new ArrayList<>();
    }
    default void accept(Object a){

    }
}
