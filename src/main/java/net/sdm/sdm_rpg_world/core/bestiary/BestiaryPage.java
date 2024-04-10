package net.sdm.sdm_rpg_world.core.bestiary;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.util.INBTSerializable;
import net.sdm.engine_core.utils.serializer.helper.NBTUtilsHelper;
import net.sdm.sdm_rpg.core.utils.snbt.NBTUtils;
import net.sdm.sdm_rpg_world.core.bestiary.content.ButtonContent;
import net.sdm.sdm_rpg_world.core.bestiary.content.ImageMessageContent;
import net.sdm.sdm_rpg_world.core.bestiary.content.MessageContent;
import net.sdm.sdm_rpg_world.core.bestiary.content.SpaceContent;

import java.util.ArrayList;
import java.util.List;

public class BestiaryPage implements INBTSerializable<SNBTCompoundTag> {

    public int numID = 0;
    public String registryID;
    public Component name;
    public List<IBestiaryContent> contentList = new ArrayList<>();
    public BestiaryPage(){}
    public BestiaryPage(String registryID, Component name){
        this.registryID = registryID;
        this.name = name;
    }

    public BestiaryPage addContent(MessageContent content){
        this.contentList.add(content);
        return this;
    }

    public BestiaryPageType getType(){
        return BestiaryPageType.BASIC;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        nbt.putString("registryID", registryID);
        nbt.putString("name", name.getString());
        nbt.putString("type", getType().name());
        ListTag contentListData = new ListTag();
        for (IBestiaryContent messageContent : contentList) {
            contentListData.add(messageContent.serializeNBT());
        }
        nbt.put("content", contentListData);
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        registryID = nbt.getString("registryID");
        name = Component.translatable(nbt.getString("name"));

        ListTag tag = (ListTag) nbt.get("content");
        for (Tag tag1 : tag) {
            SNBTCompoundTag data = SNBTCompoundTag.of(tag1);
            switch (ContentType.valueOf(data.getString("type"))){
                case BUTTON -> {
                    ButtonContent content = new ButtonContent("");
                    content.deserializeNBT(data);
                    contentList.add(content);
                }
                case IMAGEMESSAGE -> {
                    ImageMessageContent content = new ImageMessageContent();
                    content.deserializeNBT(data);
                    contentList.add(content);
                }
                case MESSAGE -> {
                    MessageContent content = new MessageContent();
                    content.deserializeNBT(data);
                    contentList.add(content);
                }
                case SPACE -> {
                    SpaceContent content = new SpaceContent();
                    content.deserializeNBT(data);
                    contentList.add(content);
                }
            }
        }
    }
}
