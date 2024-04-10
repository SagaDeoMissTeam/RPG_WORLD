package net.sdm.sdm_rpg_world.core.difficults;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public class DifficultProperty implements ISelecteble{
    public int id;
    public boolean isSelected = false;
    public Component title;
    public Component subtitle;
    public CompoundTag nbt;

    public DifficultProperty(int id, Component title){
        this(id,title,Component.empty());
    }
    public DifficultProperty(int id, Component title, CompoundTag nbt){
        this(id,title,Component.empty(), nbt);
    }
    public DifficultProperty(int id, Component title, Component subtitle){
        this(id, title, subtitle, new CompoundTag());
    }
    public DifficultProperty(int id, Component title, Component subtitle,CompoundTag nbt){
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.nbt = nbt;
    }

    @Override
    public CompoundTag getNbt() {
        return nbt;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }
}
