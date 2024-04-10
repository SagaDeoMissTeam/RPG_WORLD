package net.sdm.sdm_rpg_world.core.difficults;

import com.blamejared.crafttweaker.api.data.MapData;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public class DifficultBase implements ISelecteble{
    public Component tittle;
    public Component subtittle;
    public String nbtid = "";
    public String unlock;
    public int id = -1;
    public boolean isSelected;
    public Color4I color4I = Color4I.empty();

    public DifficultBase(Component tittle, Component subtittle){
        this.tittle = tittle;
        this.unlock = "";
        this.isSelected = false;
        this.subtittle = subtittle;
    }
    public DifficultBase(Component tittle, Component subtittle, int id){
        this.tittle = tittle;
        this.unlock = "";
        this.isSelected = false;
        this.id = id;
        this.subtittle = subtittle;
    }
    public DifficultBase(Component tittle, Component subtittle, String unlock){
        this.tittle = tittle;
        this.unlock = unlock;
        this.isSelected = false;
        this.subtittle = subtittle;
    }
    public DifficultBase(Component tittle, Component subtittle, String unlock, int id){
        this.tittle = tittle;
        this.unlock = unlock;
        this.isSelected = false;
        this.subtittle = subtittle;
        this.id = id;
    }
    public DifficultBase(Component tittle, String unlock, boolean isSelected){
        this.tittle = tittle;
        this.unlock = unlock;
        this.isSelected = isSelected;
    }

    public DifficultBase setColor(Color4I color4I){
        this.color4I = color4I;
        return this;
    }

    @Override
    public CompoundTag getNbt() {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean(nbtid, true);
        return nbt;
    }

    public DifficultBase setNBT(String id){
        this.nbtid = id;
        return this;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
