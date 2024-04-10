package net.sdm.sdm_rpg_world.core.property;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import net.sdm.sdm_rpg_world.core.PropertyList;

public interface IConditionProperty extends INBTSerializable<SNBTCompoundTag> {

    boolean makeLogic(Entity player);

    PropertyList getPRList();


}
