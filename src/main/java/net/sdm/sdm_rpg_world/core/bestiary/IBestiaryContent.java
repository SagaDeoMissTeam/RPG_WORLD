package net.sdm.sdm_rpg_world.core.bestiary;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IBestiaryContent extends INBTSerializable<SNBTCompoundTag> {
   default CompoundTag getCondition(){
       return new CompoundTag();
   }


}
