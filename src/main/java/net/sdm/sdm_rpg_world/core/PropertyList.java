package net.sdm.sdm_rpg_world.core;

import net.sdm.sdm_rpg_world.core.property.ArmorNeededProperty;
import net.sdm.sdm_rpg_world.core.property.IConditionProperty;

public enum PropertyList {

    ArmorNeededProperty(ArmorNeededProperty.class);
    public Class<? extends IConditionProperty> pr;
    PropertyList(Class<? extends IConditionProperty> pr){
        this.pr = pr;
    }
}
