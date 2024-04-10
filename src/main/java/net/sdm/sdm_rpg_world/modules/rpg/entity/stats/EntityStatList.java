package net.sdm.sdm_rpg_world.modules.rpg.entity.stats;

import java.util.List;

public class EntityStatList<T extends List<E>, E extends Number> extends EntityStatsBase{
    public T list;
    public EntityStatList(String id, T list) {
        super(id);
    }
}
