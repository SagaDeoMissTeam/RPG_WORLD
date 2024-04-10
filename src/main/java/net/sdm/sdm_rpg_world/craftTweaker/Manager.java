package net.sdm.sdm_rpg_world.craftTweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBundlePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.sdm.sdm_rpg_world.ServerData;
import net.sdm.sdm_rpg_world.core.structure.StructureBase;
import net.sdm.sdm_rpg_world.net.*;
import net.silentchaos512.scalinghealth.ScalingHealth;
import net.silentchaos512.scalinghealth.capability.DifficultySourceCapability;
import net.silentchaos512.scalinghealth.capability.IDifficultySource;
import net.silentchaos512.scalinghealth.utils.config.SHDifficulty;
import org.jetbrains.annotations.NotNull;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

@ZenRegister
@ZenCodeType.Name("mods.rpgworld.world.Manager")
public class Manager {

    @ZenCodeType.Method
    public static void openScreen(ServerPlayer player){
        new OpenScreenS2C(player.getUUID()).sendTo(player);
    }

    @ZenCodeType.Method
    public static void openOldScreen(ServerPlayer player){
        new OpenOldScreenS2C(player.getUUID()).sendTo(player);
    }

    @ZenCodeType.Method
    public static void openSelectLoreScreen(ServerPlayer player){
        new OpenSelectLoreScreenS2C(player.getUUID()).sendTo(player);
    }

    @ZenCodeType.Method
    public static void openLoreScreen(ServerPlayer player){
        syncData(player);
        new OpenLoreScreenS2C(player.getUUID()).sendTo(player);
    }

    @ZenCodeType.Method
    public static void syncData(ServerPlayer player) {
        new SendDataS2C(ExpandEntity.getCustomData(player).getInternal()).sendTo(player);
    }

    @ZenCodeType.Method
    public static void syncClientData(ServerPlayer player){
        new SyncServerPlayerS2C(player.getPersistentData()).sendTo(player);
    }

//    @ZenCodeType.Method
//    public static int getDifficultOnPos(BlockPos pos){
//        for (Map.Entry<BoundingBox, StructureBase> boundingBoxStructureBaseEntry : ServerData.STRUCTURE_DATA.entrySet()) {
//            if(boundingBoxStructureBaseEntry.getKey().isInside(pos)){
//                return boundingBoxStructureBaseEntry.getValue().rarityBase.type.ordinal();
//            }
//        }
//        return -1;
//    }
//
//    @ZenCodeType.Method
//    public static void syncEntityData(LivingEntity entity, MinecraftServer server) {
//
//        //new SyncEntityDataS2C(entity).sendToAll(server);
//    }

    @ZenCodeType.Method
    public static void sendUnlock(Player player, String tittle, String subtitle){
        CompoundTag nbt = new CompoundTag();
        nbt.putString("message", "sdm.unlock.gui.open");
        nbt.putString("title", tittle);
        nbt.putString("subtitle", subtitle);
        nbt.putString("audio", "minecraft:entity.player.levelup");
        new SendUnlockMessageS2C(nbt).sendTo((ServerPlayer) player);
    }
    @ZenCodeType.Method
    public static void sendUnlock(Player player, String tittle, String subtitle, ResourceLocation id){
        CompoundTag nbt = new CompoundTag();
        nbt.putString("message", "sdm.unlock.gui.open");
        nbt.putString("title", tittle);
        nbt.putString("subtitle", subtitle);
        nbt.putString("audio", id.toString());
        new SendUnlockMessageS2C(nbt).sendTo((ServerPlayer) player);
    }
    @ZenCodeType.Method
    public static void sendUnlock(Player player,String message, String tittle, String subtitle){
        CompoundTag nbt = new CompoundTag();
        nbt.putString("message", message);
        nbt.putString("title", tittle);
        nbt.putString("subtitle", subtitle);
        nbt.putString("audio", "minecraft:entity.player.levelup");
        new SendUnlockMessageS2C(nbt).sendTo((ServerPlayer) player);
    }
    @ZenCodeType.Method
    public static void sendUnlock(Player player,String message,  String tittle, String subtitle, ResourceLocation id){
        CompoundTag nbt = new CompoundTag();
        nbt.putString("message", message);
        nbt.putString("title", tittle);
        nbt.putString("subtitle", subtitle);
        nbt.putString("audio", id.toString());
        new SendUnlockMessageS2C(nbt).sendTo((ServerPlayer) player);
    }

//    @ZenCodeType.Method
//    public static void setDifficultValue(Player player, float dif){
//        player.getCapability(DifficultySourceCapability.INSTANCE).ifPresent((source) -> {
//            source.setDifficulty(dif);
//        });
//    }

//    @ZenCodeType.Method
//    public static float getDifficultValue(Player player){
//        return SHDifficulty.source(player).getDifficulty();
//    }

//    @ZenCodeType.Method
//    public static void disconnectPlayer(Player player, Component component){
//        ServerPlayer serverPlayer = (ServerPlayer) player;
//        serverPlayer.connection.disconnect(component);
//    }

    @ZenCodeType.Method
    public static MinecraftServer getServer() {

        return ServerLifecycleHooks.getCurrentServer();
    }

    @ZenCodeType.Method
    public static UUID fromString(String message){
        return UUID.fromString(message);
    }
}
