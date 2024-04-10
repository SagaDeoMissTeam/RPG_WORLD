package net.sdm.sdm_rpg_world.core.config;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

public class ConfigBase implements INBTSerializable<SNBTCompoundTag> {
    public List<ConfigProperty> propertyList = new ArrayList<>();
    public ConfigBase(){

    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        ListTag nbtList = new ListTag();
        for (ConfigProperty configProperty : propertyList) {
            nbtList.add(configProperty.serializeNBT());
        }
        nbt.put("property", nbtList);
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        if(nbt.contains("property")){
            ListTag nbtList = nbt.getList("property", Tag.TAG_COMPOUND);
            for (int i = 0; i < nbtList.size(); i++) {
                SNBTCompoundTag tag = SNBTCompoundTag.of(nbtList.getCompound(i));
                ConfigProperty property = new ConfigProperty();
                property.deserializeNBT(tag);
                propertyList.add(property);
            }
        }
    }
}
