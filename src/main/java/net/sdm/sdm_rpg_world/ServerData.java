package net.sdm.sdm_rpg_world;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.sdm.sdm_rpg_world.core.structure.StructureBase;
import net.sdm.sdm_rpg_world.net.SyncStructDataS2C;

import java.util.HashMap;
import java.util.Map;

public class ServerData {

    public static Map<BoundingBox, StructureBase> STRUCTURE_DATA = new HashMap<>();


    public static void sync(MinecraftServer server){
        if(!STRUCTURE_DATA.isEmpty()){
            CompoundTag nbt = new CompoundTag();
            ListTag data = new ListTag();
            for (Map.Entry<BoundingBox, StructureBase> boundingBoxStructureBaseEntry : STRUCTURE_DATA.entrySet()) {
                data.add(boundingBoxStructureBaseEntry.getValue().serializeNBT());
            }
            nbt.put("structures", data);
            new SyncStructDataS2C(nbt).sendToAll(server);
        }
    }
    public static void sync(ServerPlayer player){
        if(!STRUCTURE_DATA.isEmpty()){
            CompoundTag nbt = new CompoundTag();
            ListTag data = new ListTag();
            for (Map.Entry<BoundingBox, StructureBase> boundingBoxStructureBaseEntry : STRUCTURE_DATA.entrySet()) {
                data.add(boundingBoxStructureBaseEntry.getValue().serializeNBT());
            }
            nbt.put("structures", data);
            new SyncStructDataS2C(nbt).sendTo(player);
        }
    }
}
