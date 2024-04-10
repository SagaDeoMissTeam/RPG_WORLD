package net.sdm.sdm_rpg_world;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

import static net.sdm.engine_core.utils.serializer.helper.NBTUtilsHelper.*;

public class TimeUtils {

    @ZenCodeType.Method
    public static void putBoundingBox(SNBTCompoundTag nbt, String key, BoundingBox pos){
        SNBTCompoundTag tag = new SNBTCompoundTag();
        putBlockPos(tag, "pos1", new BlockPos(pos.minX(), pos.minY(), pos.minZ()));
        putBlockPos(tag, "pos2", new BlockPos(pos.maxX(), pos.maxY(), pos.maxZ()));
        nbt.put(key,tag);
    }
    @ZenCodeType.Method
    @Nullable
    public static BoundingBox getBoundingBox(CompoundTag nbt, String key){
        try{
            if(nbt.contains(key)){
                BlockPos pos1 = getBlockPos(nbt.getCompound(key), "pos1");
                BlockPos pos2 = getBlockPos(nbt.getCompound(key), "pos2");
                return new BoundingBox(
                        pos1.getX(),
                        pos1.getY(),
                        pos1.getZ(),
                        pos2.getX(),
                        pos2.getY(),
                        pos2.getZ()
                );
            }
        } catch (ClassCastException classcastexception){}
        return null;
    }
}
