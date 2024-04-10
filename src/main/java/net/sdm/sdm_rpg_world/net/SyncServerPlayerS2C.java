package net.sdm.sdm_rpg_world.net;

import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;

import java.util.Locale;
import java.util.UUID;

public class SyncServerPlayerS2C extends BaseS2CMessage {
    private final CompoundTag nbt;

    public SyncServerPlayerS2C(CompoundTag nbt) {
        this.nbt = nbt;
    }
    public SyncServerPlayerS2C(FriendlyByteBuf buf) {
        this.nbt = buf.readAnySizeNbt();
    }

    @Override
    public MessageType getType() {
        return NetHandler.SYNC_SERVERPLAYER;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNbt(nbt);
    }

    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if(packetContext.getEnv().isClient()){
            PlayerStateMachine stateMachine = new PlayerStateMachine(Minecraft.getInstance().player);
            stateMachine.deserializeNBT(SNBTCompoundTag.of(nbt));
            SNBTCompoundTag nbt = stateMachine.serializeNBT();
            CompoundTag data = new CompoundTag();
            data.put("playerStateMachine", nbt);
            Minecraft.getInstance().player.getPersistentData().put("sdmData", data);
        }
    }
}
