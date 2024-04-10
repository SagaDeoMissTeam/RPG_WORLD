package net.sdm.sdm_rpg_world.net;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.client.unlock.TestUnlock;

public class SendUnlockMessageS2C extends BaseS2CMessage {
    public CompoundTag nbt;
    public SendUnlockMessageS2C(CompoundTag nbt){
        this.nbt = nbt;
    }

    public SendUnlockMessageS2C(FriendlyByteBuf buf){
        this.nbt = buf.readNbt();
    }

    @Override
    public MessageType getType() {
        return NetHandler.SEND_UNLOCK_MESSAGE;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNbt(nbt);
    }

    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if(packetContext.getEnv().isClient()){
            if(!nbt.isEmpty()) {
                TestUnlock test;
                if(nbt.contains("audio")){
                    ResourceLocation rs = new ResourceLocation(nbt.getString("audio"));
                    test = new TestUnlock(nbt.getString("message"), nbt.getString("title"), nbt.getString("subtitle"), rs);
                } else {
                    test = new TestUnlock(nbt.getString("message"), nbt.getString("title"), nbt.getString("subtitle"));
                }
                SDMRpgWorld.SDMClient.unlockGUI.renders.add(test);
            }
        }
    }
}
