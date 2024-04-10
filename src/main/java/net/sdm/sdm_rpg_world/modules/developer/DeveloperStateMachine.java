package net.sdm.sdm_rpg_world.modules.developer;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import net.sdm.sdm_rpg_world.craftTweaker.Manager;
import net.sdm.sdm_rpg_world.modules.api.ISyncData;

public class DeveloperStateMachine implements INBTSerializable<SNBTCompoundTag>, ISyncData {

    public Player player;
    public boolean isActivate;
    public int devLevel;

    protected DeveloperStateMachine(Player player){
        this.player = player;
        this.isActivate = false;
        this.devLevel = Developers.getLevel(player);
    }

    public static DeveloperStateMachine of(Player player){
        if(Developers.isDeveloper(player)){
            DeveloperStateMachine state = new DeveloperStateMachine(player);
            CompoundTag nbt = player.getPersistentData();
            if(nbt.contains("developerData")){
                state.deserializeNBT(SNBTCompoundTag.of(nbt.getCompound("developerData")));
            } else {
                state.syncData();
            }
            return state;
        }

        return null;
    }

    public boolean executeCommand(String command) {
        if(isActivate){
            if(command.equals("sdmDevMode")){
                this.isActivate = false;
                syncData();
                player.sendSystemMessage(Component.literal("Режим разработчика Деактивирован !"));
                return true;
            }

            if(DevCommands.COMMANDS.containsKey(command)){
               return DevCommands.COMMANDS.get(command).run(player);
            }

        } else {
            if(command.equals("sdmDevMode")){
                this.isActivate = true;
                syncData();
                player.sendSystemMessage(Component.literal("Режим разработчика Активирован !"));
                return true;
            }
        }

        return false;
    }

    @Override
    public void syncData() {
        SNBTCompoundTag nbt = serializeNBT();
        player.getPersistentData().put("developerData", nbt);
        Manager.syncClientData((ServerPlayer) player);
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        nbt.putBoolean("isActivate", isActivate);
        nbt.putInt("devLevel", devLevel);
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        this.devLevel = nbt.getInt("devLevel");
        this.isActivate = nbt.getBoolean("isActivate");
    }
}
