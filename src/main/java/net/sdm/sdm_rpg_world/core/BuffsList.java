package net.sdm.sdm_rpg_world.core;

import net.sdm.sdm_rpg_world.core.buffs.BuffBase;
import net.sdm.sdm_rpg_world.core.buffs.DamageBuff;
import net.sdm.sdm_rpg_world.core.buffs.EffectBuff;
import net.sdm.sdm_rpg_world.core.buffs.IBuff;
import net.sdm.sdm_rpg_world.core.property.IConditionProperty;

import java.util.ArrayList;
import java.util.List;

public enum BuffsList {
    EFFECT("effect", EffectBuff.class),
    DAMAGE("damage", DamageBuff.class);
    public Class<? extends IBuff> pr;
    public String name;
    BuffsList(String name, Class<? extends IBuff> pr){
        this.pr = pr;
        this.name = name;
    }

    public static List<String> toList(){
        List<String> id = new ArrayList<>();
        for (BuffsList value : BuffsList.values()) {
            id.add(value.name);
        }
        return id;
    }

    public static String toError(){
        StringBuilder stringBuilder = new StringBuilder("All existing ones\n");
        for (BuffsList value : BuffsList.values()) {
            stringBuilder.append("    " + value.name().toLowerCase() + "\n");
        }
        return stringBuilder.toString();
    }
}
