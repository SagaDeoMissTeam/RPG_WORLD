package net.sdm.sdm_rpg_world.core.config;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.sdm.engine_core.utils.serializer.helper.NBTUtilsHelper;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.core.rarity.RarityType;

import java.util.ArrayList;
import java.util.List;

public class ConfigProperty implements IConfigElement{

    public ResourceLocation structure;

    public int minLevel = 0;
    public int maxLevel = 0;
    public double rarityChance = 0;
    public List<String> property = new ArrayList<>();

    public ConfigProperty(){

    }

    public ConfigProperty(ResourceLocation structure, int minLevel, int maxLevel){
        this.structure = structure;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    public ConfigProperty add(String... property){
        this.property.addAll(List.of(property));
        return this;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        ListTag listTag = new ListTag();
        NBTUtilsHelper.putResourceLocation(nbt, "structureID", structure);
        for (String s : property) {
            listTag.add(StringTag.valueOf(s));
        }
        nbt.put("property", listTag);
        nbt.putInt("minLevel", minLevel);
        nbt.putInt("maxLevel", maxLevel);
        nbt.putDouble("rarityChance", rarityChance);
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        if(nbt.contains("structureID")) structure = NBTUtilsHelper.getResourceLocation(nbt, "structureID");
        if(nbt.contains("property")) {
            ListTag nbtList = nbt.getList("property", Tag.TAG_STRING);
            for (int i = 0; i < nbtList.size(); i++) {
                property.add(nbtList.getString(i).toLowerCase());
            }
        }
        if(nbt.contains("minLevel")) minLevel = nbt.getInt("minLevel");
        else minLevel = 0;

        if(nbt.contains("maxLevel")) maxLevel = nbt.getInt("maxLevel");
        else maxLevel = RarityType.values().length;

        if(nbt.contains("rarityChance")) rarityChance = nbt.getDouble("rarityChance");
        else rarityChance = 0.0;
    }
}
