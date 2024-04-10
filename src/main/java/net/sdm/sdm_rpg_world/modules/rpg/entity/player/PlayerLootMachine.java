package net.sdm.sdm_rpg_world.modules.rpg.entity.player;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;
import net.sdm.sdm_rpg_world.modules.api.ITicking;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name("mods.rpgworld.entity.player.PlayerLootMachine")
public class PlayerLootMachine implements INBTSerializable<SNBTCompoundTag>, ITicking {
    @ZenCodeType.Field
    public PlayerStateMachine stateMachine;
    @ZenCodeType.Field
    public List<String> UNLOCKED_MODS = new ArrayList<>();
    public PlayerLootMachine(PlayerStateMachine stateMachine){
        this.stateMachine = stateMachine;
    }

    @ZenCodeType.Method
    public PlayerLootMachine unlockedMods(String mod){
        UNLOCKED_MODS.add(mod);
        return this;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        ListTag UNLOCKED_MODS_TAGS = new ListTag();
        for (String unlockedMod : UNLOCKED_MODS) {
            UNLOCKED_MODS_TAGS.add(StringTag.valueOf(unlockedMod));
        }
        nbt.put("UNLOCKED_MODS", UNLOCKED_MODS_TAGS);
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        UNLOCKED_MODS.clear();
        ListTag UNLOCKED_MODS_TAGS = (ListTag) nbt.get("UNLOCKED_MODS");
        for (Tag unlockedModsTag : UNLOCKED_MODS_TAGS) {
            UNLOCKED_MODS.add(((StringTag)unlockedModsTag).getAsString());
        }
    }

    @Override
    public void onTick() {

    }
}
