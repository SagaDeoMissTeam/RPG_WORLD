package net.sdm.sdm_rpg_world.modules.rpg.entity.player;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import net.sdm.sdm_rpg_world.modules.api.ITicking;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.rpgworld.entity.player.PlayerLoreMachine")
public class PlayerLoreMachine implements INBTSerializable<SNBTCompoundTag>, ITicking {
    @ZenCodeType.Field
    public PlayerStateMachine stateMachine;

    public PlayerLoreMachine(PlayerStateMachine stateMachine){
        this.stateMachine = stateMachine;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {

    }

    @Override
    public void onTick() {

    }
}
