package net.sdm.sdm_rpg_world.net;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.core.bestiary.BestiaryPage;
import net.sdm.sdm_rpg_world.core.bestiary.BestiaryPageType;
import net.sdm.sdm_rpg_world.core.bestiary.EntityBestiaryPage;

public class SyncBestiaryDataS2C extends BaseS2CMessage {

    public CompoundTag nbt;
    public SyncBestiaryDataS2C(SNBTCompoundTag nbt){
        this.nbt = nbt;
    }
    public SyncBestiaryDataS2C(FriendlyByteBuf buf){
        this.nbt = buf.readAnySizeNbt();
    }

    @Override
    public MessageType getType() {
        return NetHandler.SYNC_BESTIARY_DATA;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNbt(nbt);
    }

    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if(packetContext.getEnv().isClient()){
            SNBTCompoundTag data = SNBTCompoundTag.of(nbt);
            switch (BestiaryPageType.valueOf(data.getString("type"))){
                case BASIC -> {
                    BestiaryPage page = new BestiaryPage();
                    page.deserializeNBT(data);
                    SDMRpgWorld.SDMClient.bestiaryPages.add(page);
                    SDMRpgWorld.SDMClient.bestiaryPageMap.put(page.registryID,page);
                }
                case ENTITY -> {
                    EntityBestiaryPage page = new EntityBestiaryPage();
                    page.deserializeNBT(data);
                    SDMRpgWorld.SDMClient.bestiaryPages.add(page);
                    SDMRpgWorld.SDMClient.bestiaryPageMap.put(page.registryID,page);
                }
            }
        }
    }
}
