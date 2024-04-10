package net.sdm.sdm_rpg_world.core.structure;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraftforge.common.util.INBTSerializable;
import net.sdm.engine_core.utils.serializer.helper.NBTUtilsHelper;
import net.sdm.sdm_rpg_world.TimeUtils;
import net.sdm.sdm_rpg_world.core.buffs.BuffBase;
import net.sdm.sdm_rpg_world.core.buffs.BuffSide;
import net.sdm.sdm_rpg_world.core.buffs.IBuff;
import net.sdm.sdm_rpg_world.core.property.IConditionProperty;
import net.sdm.sdm_rpg_world.core.rarity.RarityBase;
import net.sdm.sdm_rpg_world.core.rarity.RarityType;
import net.sdm.sdm_rpg_world.core.utils.PropertyHelper;

import java.util.ArrayList;
import java.util.List;

public class StructureBase implements INBTSerializable<SNBTCompoundTag> {

    public ResourceLocation structureID;
    public RarityBase rarityBase;
    public List<IConditionProperty> structureProperty = new ArrayList<>();
    public List<IBuff> buffsEntity = new ArrayList<>();
    public List<IBuff> buffsEvent = new ArrayList<>();

    public BoundingBox structureZone = new BoundingBox(BlockPos.ZERO);

    public StructureBase(ResourceLocation structureID){
        this.structureID = structureID;
        this.rarityBase = new RarityBase(RarityType.COMMON);
    }
    public StructureBase(ResourceLocation structureID, RarityBase rarityBase) {
        this.structureID = structureID;
        this.rarityBase = rarityBase;

    }

    public StructureBase addProperty(IConditionProperty property){
        this.structureProperty.add(property);
        return this;
    }

    public StructureBase addBuff(IBuff buff){
        switch (buff.getSide()){
            case EVENT -> {
                this.buffsEvent.add(buff);
            }
            case ENTITY -> {
                this.buffsEntity.add(buff);
            }
        }
        return this;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        NBTUtilsHelper.putResourceLocation(nbt, "id", structureID);
        ListTag conditionList = new ListTag();
        ListTag buffsList = new ListTag();
        ListTag buffsEventList = new ListTag();
        for (IConditionProperty iConditionProperty : structureProperty) {
            conditionList.add(iConditionProperty.serializeNBT());
        }
        for (IBuff buff : buffsEntity) {
            buffsList.add(buff.serializeNBT());
        }
        for (IBuff buff : buffsEvent) {
            buffsEventList.add(buff.serializeNBT());
        }
        TimeUtils.putBoundingBox(nbt, "zone", structureZone);
        nbt.put("property", conditionList);
        if(!buffsEntity.isEmpty())
            nbt.put("buffsEntity", buffsList);
        if(!buffsEvent.isEmpty())
            nbt.put("buffsEvent", buffsEventList);
        nbt.put("rarity", rarityBase.serializeNBT());
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        structureID = NBTUtilsHelper.getResourceLocation(nbt, "id");

        ListTag conditionList = nbt.getList("property", Tag.TAG_COMPOUND);
        for (int i = 0; i < conditionList.size(); i++) {
            SNBTCompoundTag tag = SNBTCompoundTag.of(conditionList.getCompound(i));
            if(PropertyHelper.getConditionProperty(tag) != null)
                structureProperty.add(PropertyHelper.getConditionProperty(tag));
        }
        if(nbt.contains("buffsEntity")) {
            ListTag buffList = nbt.getList("buffsEntity", Tag.TAG_COMPOUND);
            for (int i = 0; i < buffList.size(); i++) {
                SNBTCompoundTag tag = SNBTCompoundTag.of(buffList.getCompound(i));
                if (PropertyHelper.getBuff(tag) != null)
                    buffsEntity.add(PropertyHelper.getBuff(tag));
            }
        }
        if(nbt.contains("buffsEvent")) {
            ListTag buffsEventList = nbt.getList("buffsEvent", Tag.TAG_COMPOUND);
            for (int i = 0; i < buffsEventList.size(); i++) {
                SNBTCompoundTag tag = SNBTCompoundTag.of(buffsEventList.getCompound(i));
                if (PropertyHelper.getBuff(tag) != null)
                    buffsEvent.add(PropertyHelper.getBuff(tag));
            }
        }
        structureZone = TimeUtils.getBoundingBox(nbt, "zone");
        RarityBase frarityBase = new RarityBase(RarityType.EPIC);
        frarityBase.deserializeNBT(nbt.getCompound("rarity"));
        rarityBase = frarityBase;
    }
    public Component structureString(){
        return Component.translatable("structure" + "." + this.structureID.getNamespace() + "." + this.structureID.getPath());
    }
}
