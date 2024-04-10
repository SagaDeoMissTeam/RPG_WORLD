package net.sdm.sdm_rpg_world.net;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.core.rarity.RarityType;
import net.sdm.sdm_rpg_world.core.structure.StructureBase;

public class SendStructureInfoS2C extends BaseS2CMessage {
    public CompoundTag nbt;
    public SendStructureInfoS2C(StructureBase base){
        nbt = base.serializeNBT();
    }
    public SendStructureInfoS2C(CompoundTag base){
        nbt = base;
    }
    public SendStructureInfoS2C(FriendlyByteBuf buf){
        nbt = buf.readAnySizeNbt();
    }
    @Override
    public MessageType getType() {
        return NetHandler.STRUCTURE_INFO;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNbt(nbt);
    }

    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if(packetContext.getEnv().isClient()) {
            if (!nbt.isEmpty()) {
                StructureBase base = new StructureBase(new ResourceLocation(""));
                base.deserializeNBT(SNBTCompoundTag.of(nbt));
                SDMRpgWorld.SDMClient.structureGUI.text = Component.translatable("structure" + "." + base.structureID.getNamespace() + "." + base.structureID.getPath());
                SDMRpgWorld.SDMClient.structureGUI.structureBase = base;
                for (int i = 0; i < RarityType.values().length; i++) {
                    if (RarityType.values()[i].equals(base.rarityBase.type)) {
                        SDMRpgWorld.SDMClient.structureGUI.difficultCount = i;
                        break;
                    }
                }
            } else {
                SDMRpgWorld.SDMClient.structureGUI.structureBase = null;
                SDMRpgWorld.SDMClient.structureGUI.text = Component.empty();
                SDMRpgWorld.SDMClient.structureGUI.difficultCount = 0;
            }
        }
    }
}
