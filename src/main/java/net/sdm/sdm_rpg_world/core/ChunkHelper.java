package net.sdm.sdm_rpg_world.core;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Cancelable;
import org.jetbrains.annotations.Nullable;

@Cancelable
public class ChunkHelper {

    public static @Nullable Player loadChunkPlayer = null;
    public static @Nullable Player unloadChunkPlayer = null;


    public static void setLoadChunkPlayer(Player player){
        loadChunkPlayer = player;
    }
    public static void clearLoadChunkPlayer(){
        loadChunkPlayer = null;
    }

    public static void setUnloadChunkPlayer(Player player){
        unloadChunkPlayer = player;
    }
    public static void clearUnloadPlayer(){
        unloadChunkPlayer = null;
    }
}
