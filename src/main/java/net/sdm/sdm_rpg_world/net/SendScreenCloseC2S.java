package net.sdm.sdm_rpg_world.net;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseC2SMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.common.MinecraftForge;
import net.sdm.sdm_rpg_world.core.events.ScreenCloseEvent;

public class SendScreenCloseC2S extends BaseC2SMessage {

    public SendScreenCloseC2S(FriendlyByteBuf buf){

    }

    public SendScreenCloseC2S(){

    }

    @Override
    public MessageType getType() {
        return NetHandler.SEND_SCREEN_CLOSE;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {

    }

    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if(!packetContext.getEnv().isClient()){
            MinecraftForge.EVENT_BUS.post(new ScreenCloseEvent(packetContext.getPlayer()));
        }
    }
}
