package net.sdm.sdm_rpg_world.net;

import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;

public class SendDataS2C extends BaseS2CMessage {
    public CompoundTag nbt;

    public SendDataS2C(CompoundTag nbt){
        this.nbt = nbt;
    }
    public SendDataS2C(FriendlyByteBuf buf){
        this.nbt = buf.readAnySizeNbt();
    }

    @Override
    public MessageType getType() {
        return NetHandler.SEND_DATA_CLIENT;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNbt(nbt);
    }

    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if(packetContext.getEnv().isClient()) {
            PlayerStateMachine stateMachine = new PlayerStateMachine(Minecraft.getInstance().player);
            stateMachine.deserializeNBT(SNBTCompoundTag.of(nbt));
            stateMachine.syncData();
        }
    }
}
