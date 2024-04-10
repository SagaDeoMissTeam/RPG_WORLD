package net.sdm.sdm_rpg_world.net;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.core.structure.StructureBase;

public class SyncStructDataS2C extends BaseS2CMessage {

    public CompoundTag data;
    public SyncStructDataS2C(CompoundTag data){
        this.data = data;
    }
    public SyncStructDataS2C(FriendlyByteBuf buf){
        data = buf.readAnySizeNbt();
    }

    @Override
    public MessageType getType() {
        return NetHandler.SYNC_DATA_STRUCT;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNbt(data);
    }


    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if(packetContext.getEnv().isClient()){
            ListTag list = data.getList("structures", Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                SNBTCompoundTag nbt = SNBTCompoundTag.of(list.getCompound(i));
                StructureBase base = new StructureBase(new ResourceLocation(""));
                base.deserializeNBT(nbt);
                SDMRpgWorld.SDMClient.STRUCUTRES_DATA.put(base.structureZone, base);
            }
        }
    }
}
