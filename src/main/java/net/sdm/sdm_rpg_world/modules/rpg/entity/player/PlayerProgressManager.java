package net.sdm.sdm_rpg_world.modules.rpg.entity.player;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import net.sdm.sdm_rpg_world.craftTweaker.Manager;
import net.sdm.sdm_rpg_world.modules.api.ISyncData;
import net.sdm.sdm_rpg_world.modules.api.ITicking;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.rpgworld.entity.player.PlayerProgressManager")
public class PlayerProgressManager implements INBTSerializable<SNBTCompoundTag>, ITicking {
    @ZenCodeType.Field
    public PlayerStateMachine stateMachine;
    @ZenCodeType.Field
    public boolean isProgressMode = false;
    @ZenCodeType.Field
    public boolean isMapUnlocked = false;
    @ZenCodeType.Field
    public boolean isMiniMapUnlocked = false;
    @ZenCodeType.Field
    public boolean isJadeUnlocked = false;
    @ZenCodeType.Field
    public boolean isCurioUnlocked = false;
    @ZenCodeType.Field
    public boolean isSkillTreeUnlocked = false;
    @ZenCodeType.Field
    public boolean isSanity = false;
    @ZenCodeType.Field
    public boolean isThirst = false;
    @ZenCodeType.Field
    public boolean isCanUseCommand = false;

    public PlayerProgressManager(PlayerStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        nbt.putBoolean("journey_map", isMapUnlocked);
        nbt.putBoolean("journey_mini_map", isMiniMapUnlocked);
        nbt.putBoolean("jade", isJadeUnlocked);
        nbt.putBoolean("unlockCuriios", isCurioUnlocked);
        nbt.putBoolean("unlockSkillTree", isSkillTreeUnlocked);
        nbt.putBoolean("sanity", isSanity);
        nbt.putBoolean("thirsty", isThirst);
        nbt.putBoolean("isCanUseCommand", isCanUseCommand);
        nbt.putBoolean("isProgressMode", isProgressMode);
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        this.isMapUnlocked = nbt.getBoolean("journey_map");
        this.isMiniMapUnlocked = nbt.getBoolean("journey_mini_map");
        this.isJadeUnlocked = nbt.getBoolean("jade");
        this.isCurioUnlocked = nbt.getBoolean("unlockCuriios");
        this.isSkillTreeUnlocked = nbt.getBoolean("unlockSkillTree");
        this.isSanity = nbt.getBoolean("sanity");
        this.isThirst = nbt.getBoolean("thirsty");
        this.isCanUseCommand = nbt.getBoolean("isCanUseCommand");
        this.isProgressMode = nbt.getBoolean("isProgressMode");
    }

    @Override
    public void onTick() {

    }


    public void unlockProgressMode(){
        this.isProgressMode = true;
        stateMachine.syncData();
    }

    @ZenCodeType.Method
    public void unlockCommands(){
        this.isCanUseCommand = true;
//        Manager.sendUnlock(stateMachine.player, "sdm.unlock.journey_map", "sdm.unlock.journey_map.description");
        stateMachine.syncData();
    }
    @ZenCodeType.Method
    public void unlockThirst(){
        this.isThirst = true;
//        Manager.sendUnlock(stateMachine.player, "sdm.unlock.journey_map", "sdm.unlock.journey_map.description");
        stateMachine.syncData();
    }
    @ZenCodeType.Method
    public void unlockSanity(){
        this.isSanity = true;
//        Manager.sendUnlock(stateMachine.player, "sdm.unlock.journey_map", "sdm.unlock.journey_map.description");
        stateMachine.syncData();
    }

    @ZenCodeType.Method
    public void unlockMap(){
        this.isMapUnlocked = true;
        Manager.sendUnlock(stateMachine.player, "sdm.unlock.journey_map", "sdm.unlock.journey_map.description");
        stateMachine.syncData();
    }
    @ZenCodeType.Method
    public void unlockMiniMap(){
        this.isMiniMapUnlocked = true;
        Manager.sendUnlock(stateMachine.player, "sdm.unlock.journey_mini_map", "sdm.unlock.journey_mini_map.description");
        stateMachine.syncData();
    }
    @ZenCodeType.Method
    public void unlockJade(){
        this.isJadeUnlocked = true;
        Manager.sendUnlock(stateMachine.player, "sdm.unlock.jade", "sdm.unlock.jade.description");
        stateMachine.syncData();
    }
    @ZenCodeType.Method
    public void unlockCurios(){
        this.isCurioUnlocked = true;
        Manager.sendUnlock(stateMachine.player, "sdm.unlock.curios", "sdm.unlock.curios.description");
        stateMachine.syncData();
    }
    @ZenCodeType.Method
    public void unlockSkillTree(){
        this.isSkillTreeUnlocked = true;
        Manager.sendUnlock(stateMachine.player, "sdm.unlock.skill_tree", "sdm.unlock.skill_tree.description");
        stateMachine.syncData();
    }
    @ZenCodeType.Method
    public void lockMap(){
        this.isMapUnlocked = false;
        stateMachine.syncData();
    }
    @ZenCodeType.Method
    public void lockMiniMap(){
        this.isMiniMapUnlocked = false;
        stateMachine.syncData();
    }
    @ZenCodeType.Method
    public void lockJade(){
        this.isJadeUnlocked = false;
        stateMachine.syncData();
    }
    @ZenCodeType.Method
    public void lockCurios(){
        this.isCurioUnlocked = false;
        stateMachine.syncData();
    }
    @ZenCodeType.Method
    public void lockSkillTree(){
        this.isSkillTreeUnlocked = false;
        stateMachine.syncData();
    }

}
