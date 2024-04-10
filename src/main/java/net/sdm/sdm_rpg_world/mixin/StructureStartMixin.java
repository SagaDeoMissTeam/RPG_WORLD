package net.sdm.sdm_rpg_world.mixin;

import com.blamejared.crafttweaker.api.data.MapData;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.sdm.engine_core.SDMEngineCore;
import net.sdm.engine_core.api.logger.SDMLogger;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.ServerData;
import net.sdm.sdm_rpg_world.client.StructureGUI;
import net.sdm.sdm_rpg_world.core.ChunkHelper;
import net.sdm.sdm_rpg_world.core.config.ConfigProperty;
import net.sdm.sdm_rpg_world.core.events.StructureSaveEvent;
import net.sdm.sdm_rpg_world.core.structure.StructureBase;
import net.sdm.sdm_rpg_world.core.structure.StructureUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructureStart.class)
public abstract class StructureStartMixin {


//    @Shadow @Final private Structure structure;
//
//    @Shadow public abstract BoundingBox getBoundingBox();
//
//    @Inject(method = "createTag", at = @At("RETURN"))
//    public void sdm$createTag(StructurePieceSerializationContext p_192661_, ChunkPos p_192662_, CallbackInfoReturnable<CompoundTag> cir){
//        if(!SDMRpgWorld.configBase.propertyList.isEmpty()){
//            for (ConfigProperty property : SDMRpgWorld.configBase.propertyList) {
//                if(property.structure.equals(new ResourceLocation(cir.getReturnValue().getString("id"))) || property.structure.equals(new ResourceLocation("minecraft:structure_id"))){
//                    if(ServerData.STRUCTURE_DATA.containsKey(this.getBoundingBox())){
//                        cir.getReturnValue().put("sdmstructureRarity", ServerData.STRUCTURE_DATA.get(this.getBoundingBox()).serializeNBT());
//                    } else {
//                        StructureSaveEvent event = new StructureSaveEvent(ChunkHelper.unloadChunkPlayer, new ResourceLocation(cir.getReturnValue().getString("id")), new MapData());
//                        MinecraftForge.EVENT_BUS.post(event);
//                        StructureBase base = StructureUtils.generate(p_192661_.registryAccess().registryOrThrow(Registries.STRUCTURE).getKey(this.structure), property);
//                        base.structureZone = getBoundingBox();
//                        if(!event.isCanceled()) {
//                            event.structureData.merge(new MapData(base.serializeNBT()));
//                            cir.getReturnValue().put("sdmstructureRarity", event.structureData.getInternal());
//                        }
//                        ServerData.STRUCTURE_DATA.put(this.getBoundingBox(), base);
//                        if(ServerLifecycleHooks.getCurrentServer() != null)
//                            ServerData.sync(ServerLifecycleHooks.getCurrentServer());
//                    }
//                }
//            }
//        }
//    }
//
//    @Inject(method = "loadStaticStart", at = @At("RETURN"))
//    private static void sdm$unpuck(StructurePieceSerializationContext p_226858_, CompoundTag nbt, long p_226860_, CallbackInfoReturnable<StructureStart> cir){
//        if(nbt.contains("sdmstructureRarity")){
//            StructureBase base = new StructureBase(new ResourceLocation(""));
//            base.deserializeNBT(SNBTCompoundTag.of(nbt.getCompound("sdmstructureRarity")));
//            if(!ServerData.STRUCTURE_DATA.containsKey(cir.getReturnValue().getBoundingBox())){
//                ServerData.STRUCTURE_DATA.put(cir.getReturnValue().getBoundingBox(), base);
//                if(ServerLifecycleHooks.getCurrentServer() != null)
//                    ServerData.sync(ServerLifecycleHooks.getCurrentServer());
//            }
//        }
//    }
}
