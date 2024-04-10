package net.sdm.sdm_rpg_world.mixin;


import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.MinecraftForge;
import net.sdm.sdm_rpg_world.core.ChunkHelper;
import net.sdm.sdm_rpg_world.core.events.ChunkLoadEvent;
import net.sdm.sdm_rpg_world.core.events.ChunkUnloadEvent;
import org.apache.commons.lang3.mutable.MutableObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkMap.class)
public class ChunkMapMixin {

    @Inject(method = "playerLoadedChunk", at = @At("HEAD"))
    public void sdm$playerLoadChunk(ServerPlayer player, MutableObject<ClientboundLevelChunkWithLightPacket> p_183762_, LevelChunk p_183763_, CallbackInfo ci){
        ChunkHelper.clearUnloadPlayer();
        ChunkHelper.setLoadChunkPlayer(player);
        MinecraftForge.EVENT_BUS.post(new ChunkLoadEvent(player));
    }
    @Inject(method = "playerLoadedChunk", at = @At("TAIL"))
    public void sdm$playerLoadChunkBefore(ServerPlayer player, MutableObject<ClientboundLevelChunkWithLightPacket> p_183762_, LevelChunk p_183763_, CallbackInfo ci){
        ChunkHelper.clearLoadChunkPlayer();
        ChunkHelper.setUnloadChunkPlayer(player);
        MinecraftForge.EVENT_BUS.post(new ChunkUnloadEvent(player));
    }
}
