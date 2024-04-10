package net.sdm.sdm_rpg_world.net;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.UUID;

public class SyncEntityDataS2C extends BaseS2CMessage {
    public UUID uuid;
    public CompoundTag nbt;
    public SyncEntityDataS2C(FriendlyByteBuf buf){
        uuid = buf.readUUID();
        nbt = buf.readAnySizeNbt();
    }

    public SyncEntityDataS2C(LivingEntity entity){
        uuid = entity.getUUID();
        nbt = ExpandEntity.getCustomData(entity).getInternal();
    }

    @Override
    public MessageType getType() {
        return NetHandler.SYNC_ENTITY_DATA;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNbt(nbt);
        friendlyByteBuf.writeUUID(uuid);
    }

    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if (packetContext.getEnv().isClient()) {
            ClientLevel level = Minecraft.getInstance().level;
            if (level == null) return;
            Entity entity = null;
            for (int i = 0; i < level.getEntityCount(); i++) {
                entity = level.getEntity(i);
                if (entity.getUUID().equals(uuid) || entity.getStringUUID().equals(uuid.toString())) {
                    CraftTweakerAPI.getLogger("SD").error("SYNC");
                    ExpandEntity.updateCustomData(entity, new MapData(nbt));
                    CraftTweakerAPI.getLogger("SDM").info(ExpandEntity.getCustomData(entity).toString());
                }
            }
        }
    }
}
