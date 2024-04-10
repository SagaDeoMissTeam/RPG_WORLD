package net.sdm.sdm_rpg_world.core.bestiary.content;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.core.bestiary.ContentType;
import net.sdm.sdm_rpg_world.core.bestiary.IBestiaryContent;

public class MessageContent implements IBestiaryContent {
    public Component text;

    public MessageContent(){
        text = Component.empty();
    }
    public MessageContent(Component text){
        this.text = text;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        nbt.putString("type", ContentType.MESSAGE.name());
        nbt.putString("text", text.getString());
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        this.text = Component.translatable(nbt.getString("text"));
    }
}
