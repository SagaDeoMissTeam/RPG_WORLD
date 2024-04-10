package net.sdm.sdm_rpg_world.core.utils;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.sdm.engine_core.SDMEngineCore;
import net.sdm.engine_core.api.logger.SDMLogger;
import net.sdm.sdm_rpg_world.core.BuffsList;
import net.sdm.sdm_rpg_world.core.PropertyList;
import net.sdm.sdm_rpg_world.core.buffs.BuffBase;
import net.sdm.sdm_rpg_world.core.buffs.IBuff;
import net.sdm.sdm_rpg_world.core.property.ConditionBase;
import net.sdm.sdm_rpg_world.core.property.IConditionProperty;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PropertyHelper {

    public static @Nullable IBuff getBuff(SNBTCompoundTag nbt){
        String type = "";
        if(nbt.contains("type")) type = nbt.getString("type");
        if(type.isEmpty()) return null;
        BuffsList propertyList = BuffsList.valueOf(type.toUpperCase());
        try{
            Class<?> cls = propertyList.pr;
            Constructor<?> constructor = cls.getConstructor();
            BuffBase obj = (BuffBase) constructor.newInstance(new Object[]{});
            obj.deserializeNBT(nbt);
            return obj;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | StackOverflowError e) {
            SDMEngineCore.LOGGER.sendError(e.getMessage());
            SDMEngineCore.LOGGER.sendError(e.toString());
        }
        return null;
    }

    public static @Nullable IConditionProperty getConditionProperty(SNBTCompoundTag nbt)  {
        String type = "";
        if(nbt.contains("type")) type = nbt.getString("type");
        if(type.isEmpty()) return null;
        PropertyList propertyList = PropertyList.valueOf(type);
        try{
            Class<?> cls = propertyList.pr;
            Constructor<?> constructor = cls.getConstructor();
            ConditionBase obj = (ConditionBase) constructor.newInstance(new Object[]{});
            obj.deserializeNBT(nbt);
            return obj;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | StackOverflowError e) {}
        return null;
    }
}
