package net.sdm.sdm_rpg_world.core.aspect;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.client.SDMIcons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AspectsRegister {

    public static Map<String,Aspect> aspects = new HashMap<>();
    public static void init(){
        aspects.put("aer",new Aspect("aer", SDMIcons.AER, Color4I.rgb(255,255,130)));
        aspects.put("alienis",new Aspect("alienis", SDMIcons.ALIENIS, Color4I.rgb(128,89,129)));
        aspects.put("alkimia",new Aspect("alkimia", SDMIcons.ALKIMIA, Color4I.rgb(38,172,157)));
        aspects.put("aqua",new Aspect("aqua", SDMIcons.AQUA, Color4I.rgb(75,214,252)));
        aspects.put("auram",new Aspect("auram", SDMIcons.AURAM, Color4I.rgb(233,183,234)));
        aspects.put("aversio",new Aspect("aversio", SDMIcons.AVERSIO, Color4I.rgb(189,78,79)));
        aspects.put("bestia",new Aspect("bestia", SDMIcons.BESTIA, Color4I.rgb(160,99,7)));
        aspects.put("cognitio",new Aspect("cognitio", SDMIcons.COGNITIO, Color4I.rgb(255,196,184)));
        aspects.put("desiderium",new Aspect("desiderium", SDMIcons.DESIDERIUM, Color4I.rgb(230,188,68)));
        aspects.put("exanimis",new Aspect("exanimis", SDMIcons.EXANIMIS, Color4I.rgb(56,63,0)));
        aspects.put("fabrico",new Aspect("fabrico", SDMIcons.FABRICO, Color4I.rgb(112,139,112)));
        aspects.put("gelum",new Aspect("gelum", SDMIcons.GELUM, Color4I.rgb(223,254,254)));
        aspects.put("herba",new Aspect("herba", SDMIcons.HERBA, Color4I.rgb(6,164,8)));
        aspects.put("humanus",new Aspect("humanus", SDMIcons.HUMANUS, Color4I.rgb(255,214,191)));
        aspects.put("ignis",new Aspect("ignis", SDMIcons.IGNIS, Color4I.rgb(253,91,0)));
        aspects.put("instrumentum",new Aspect("instrumentum", SDMIcons.INSTRUMENTUM, Color4I.rgb(65,67,239)));
        aspects.put("lux",new Aspect("lux", SDMIcons.LUX, Color4I.rgb(207,199,81)));
        aspects.put("machina",new Aspect("machina", SDMIcons.MACHINA, Color4I.rgb(130,131,161)));
        aspects.put("metallum",new Aspect("metallum", SDMIcons.METALLUM, Color4I.rgb(193,194,213)));
        aspects.put("mortuus",new Aspect("mortuus", SDMIcons.MORTUUS, Color4I.rgb(136,118,138)));
        aspects.put("motus",new Aspect("motus", SDMIcons.MOTUS, Color4I.rgb(165,164,198)));
        aspects.put("ordo",new Aspect("ordo", SDMIcons.ORDO, Color4I.rgb(224,222,244)));
        aspects.put("perditio",new Aspect("perditio", SDMIcons.PERDITIO, Color4I.rgb(105,105,105)));
        aspects.put("permutatio",new Aspect("permutatio", SDMIcons.PERMUTATION, Color4I.rgb(88,129,87)));
        aspects.put("potentia",new Aspect("potentia", SDMIcons.POTENTIA, Color4I.rgb(193,255,252)));
        aspects.put("praecantatio",new Aspect("praecantatio", SDMIcons.PRAECANTATIO, Color4I.rgb(151,0,195)));
        aspects.put("praemunio",new Aspect("praemunio", SDMIcons.PRAEMUNIO, Color4I.rgb(6,190,180)));
        aspects.put("sensus",new Aspect("sensus", SDMIcons.SENSUS, Color4I.rgb(24,216,247)));
        aspects.put("spiritus",new Aspect("spiritus", SDMIcons.SPIRITUS, Color4I.rgb(62,61,65)));
        aspects.put("tenebrae",new Aspect("tenebrae", SDMIcons.TENEBRAE, Color4I.rgb(105,105,105)));
        aspects.put("terra",new Aspect("terra", SDMIcons.TERRA, Color4I.rgb(2,193,86)));
        aspects.put("vacuos",new Aspect("vacuos", SDMIcons.VACUOS, Color4I.rgb(186,186,186)));
        aspects.put("victus",new Aspect("victus", SDMIcons.VICTUS, Color4I.rgb(179,25,27)));
        aspects.put("vinculum",new Aspect("vinculum", SDMIcons.VINCULUM, Color4I.rgb(152,134,133)));
        aspects.put("vitium",new Aspect("vitium", SDMIcons.VITIUM, Color4I.rgb(129,1,131)));
        aspects.put("vitreus",new Aspect("vitreus", SDMIcons.VITREUS, Color4I.rgb(130,254,255)));
        aspects.put("volatus",new Aspect("volatus", SDMIcons.VOLATUS, Color4I.rgb(231,232,215)));
    }


    public static List<ItemAspect> getDev(){
        List<ItemAspect> list = new ArrayList<>();
        list.add(new ItemAspect(new Aspect("vacuos", SDMIcons.VACUOS, Color4I.rgb(89,89,89)), 64));
        list.add(new ItemAspect(new Aspect("victus", SDMIcons.VICTUS, Color4I.rgb(179,25,27)), 1));
        list.add(new ItemAspect(new Aspect("ordo", SDMIcons.ORDO, Color4I.rgb(224,222,244)), 35));
        return list;
    }
    public static List<ItemAspect> getByData(CompoundTag nbt){
        List<ItemAspect> list = new ArrayList<>();
        if(nbt.contains("aspects")){
            ListTag aspects = (ListTag) nbt.get("aspects");

            for (int i = 0; i < aspects.size(); i++) {
                CompoundTag data = aspects.getCompound(i);
                if(AspectsRegister.aspects.containsKey(data.getString("id"))){
                    list.add(new ItemAspect(AspectsRegister.aspects.get(data.getString("id")), data.getInt("value")));
                }
            }
        }
        return list;
    }
}
