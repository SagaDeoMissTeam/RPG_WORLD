package net.sdm.sdm_rpg_world.datasaver;

import dev.ftb.mods.ftblibrary.snbt.SNBT;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;

import java.io.File;
import java.nio.file.Path;

public class SaveLoad {

    public static void savePlayerData(ServerPlayer player){
        PlayerStateMachine state = PlayerStateMachine.of(player);

        Path path = player.server.getWorldPath(LevelResource.PLAYER_DATA_DIR).resolve("sdm");
        SNBT.write(path.resolve(player.getStringUUID() + ".snbt"), state.serializeNBT());
    }

    public static void loadPlayerData(ServerPlayer player){
        Path path = player.server.getWorldPath(LevelResource.PLAYER_DATA_DIR).resolve("sdm").resolve(player.getStringUUID()  + ".snbt");
        SNBTCompoundTag tag = SNBT.read(path);
        if(tag != null){
            PlayerStateMachine state = PlayerStateMachine.of(player);
            state.deserializeNBT(tag);
            state.syncData();
        }
    }
}
