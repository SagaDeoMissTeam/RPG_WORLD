package net.sdm.sdm_rpg_world.net;

import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseC2SMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.sdm.sdm_rpg_world.core.events.DifficultSelectedEvent;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;

public class SendDataC2S extends BaseC2SMessage {
    public CompoundTag nbt;

    public SendDataC2S(CompoundTag nbt){
        this.nbt = nbt;
    }
    public SendDataC2S(FriendlyByteBuf buf){
        this.nbt = buf.readAnySizeNbt();
    }
    @Override
    public MessageType getType() {
        return NetHandler.SEND_DATA;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNbt(nbt);
    }

    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if(!packetContext.getEnv().isClient()) {
            Player player = packetContext.getPlayer();
            PlayerStateMachine stateMachine = PlayerStateMachine.of(player);
            stateMachine.unlockByNBT(nbt);
            MinecraftForge.EVENT_BUS.post(new DifficultSelectedEvent(player, stateMachine));
        }
    }
}
