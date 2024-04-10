package net.sdm.sdm_rpg_world.core.bestiary.content;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.sdm.sdm_rpg_world.core.bestiary.ContentType;

public class ImageMessageContent extends MessageContent{
    public ResourceLocation iconID;

    public ImageMessageContent(){
        super(Component.literal(""));
    }
    public ImageMessageContent(Component text, ResourceLocation iconID) {
        super(text);
        this.iconID = iconID;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        nbt.putString("type", ContentType.IMAGEMESSAGE.name());
        nbt.putString("text", text.getString());
        nbt.putString("iconID", iconID.toString());
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        this.iconID = new ResourceLocation(nbt.getString("iconID"));
        this.text = Component.translatable(nbt.getString("text"));
    }
}
