package net.sdm.sdm_rpg_world.core.bestiary.content;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.sdm.sdm_rpg_world.core.bestiary.ContentType;
import net.sdm.sdm_rpg_world.core.bestiary.IBestiaryContent;

public class SpaceContent implements IBestiaryContent {
    public SpaceContent(){

    }
    @Override
    public CompoundTag getCondition() {
        return null;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        nbt.putString("type", ContentType.SPACE.name());
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {

    }
}
