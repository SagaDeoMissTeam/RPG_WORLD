package net.sdm.sdm_rpg_world.modules.rpg.utils;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.sdm.sdm_rpg_world.modules.rpg.entity.stats.EntityStatsBase;
import net.sdm.sdm_rpg_world.modules.rpg.entity.stats.EntityStatsList;

public class EntityStatsHelper {

    public static EntityStatsBase deserialize(SNBTCompoundTag nbt){
        if(nbt.contains("id")){
            String id = nbt.getString("id");
            for (EntityStatsBase entityStatsBase : EntityStatsList.statsList) {
                if(entityStatsBase.id.equals(id)){
                    EntityStatsBase base = entityStatsBase.copy();
                    base.deserializeNBT(nbt);
                    return base;
                }
            }
        }

        return EntityStatsList.NULL;
    }
}
