package net.sdm.sdm_rpg_world.net;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.network.FriendlyByteBuf;
import net.sdm.sdm_rpg_world.SDMRpgWorld;

public class ResetBestiaryDataS2C extends BaseS2CMessage {

    public ResetBestiaryDataS2C(){

    }

    public ResetBestiaryDataS2C(FriendlyByteBuf buf){

    }

    @Override
    public MessageType getType() {
        return NetHandler.RESET_BESTIARY_DATA;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {

    }

    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if(packetContext.getEnv().isClient()) {
            SDMRpgWorld.SDMClient.bestiaryPages.clear();
            SDMRpgWorld.SDMClient.bestiaryPageMap.clear();
        }
    }
}
