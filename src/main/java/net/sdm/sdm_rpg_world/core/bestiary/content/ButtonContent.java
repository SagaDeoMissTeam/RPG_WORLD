package net.sdm.sdm_rpg_world.core.bestiary.content;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.sdm.sdm_rpg_world.core.bestiary.ContentType;
import net.sdm.sdm_rpg_world.core.bestiary.IBestiaryContent;

public class ButtonContent implements IBestiaryContent {
    public String idToMove;
    public ButtonContent(String idToMove){
        this.idToMove = idToMove;
    }


    @Override
    public SNBTCompoundTag serializeNBT(){
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        nbt.putString("type", ContentType.BUTTON.name());
        nbt.putString("idToMove", idToMove);
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        idToMove = nbt.getString("idToMove");
    }
}
