package net.sdm.sdm_rpg_world.core.property;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.sdm.engine_core.utils.serializer.helper.NBTUtilsHelper;
import net.sdm.sdm_rpg_world.core.PropertyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArmorNeededProperty extends ConditionBase {
    public List<ItemStack> armor = new ArrayList<>();
    public boolean needAll = false;

    public ArmorNeededProperty(){}
    public ArmorNeededProperty(ItemStack[] armor){
        this(armor, false);
    }
    public ArmorNeededProperty(ItemStack[] armor, boolean needAll){
        this.armor = Arrays.stream(armor).toList();
    }

    @Override
    public boolean makeLogic(Entity entity) {
        int founded = 0;
        for (ItemStack armorSlot : entity.getArmorSlots()) {
            if(needAll && armor.contains(armorSlot.getItem().getDefaultInstance())) {
                return true;
            } else{
                if(armor.contains(armorSlot.getItem().getDefaultInstance())){
                    founded++;
                }
            }
        }
        if(founded >= armor.size()){
            return true;
        }
        return false;
    }

    @Override
    public PropertyList getPRList() {
        return PropertyList.ArmorNeededProperty;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = super.serializeNBT();
        NBTUtilsHelper.putItemStackList(nbt,"itmes", armor);
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        armor = NBTUtilsHelper.getItemStackList(nbt, "items");
    }
}
