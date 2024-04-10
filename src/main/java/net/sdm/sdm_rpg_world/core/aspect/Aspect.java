package net.sdm.sdm_rpg_world.core.aspect;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import javax.swing.*;

public class Aspect {
    public String id;
    public Icon icon;
    public Color4I color;
    public Aspect(String id, Icon icon){
        this.id = id;
        this.icon = icon;
        this.color = Color4I.WHITE;
    }
    public Aspect(String id, Icon icon, Color4I color){
        this.id = id;
        this.icon = icon;
        this.color = color;
    }
}
