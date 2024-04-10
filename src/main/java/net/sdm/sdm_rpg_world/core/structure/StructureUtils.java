package net.sdm.sdm_rpg_world.core.structure;

import net.minecraft.resources.ResourceLocation;
import net.sdm.engine_core.SDMEngineCore;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.core.BuffsList;
import net.sdm.sdm_rpg_world.core.buffs.BuffBase;
import net.sdm.sdm_rpg_world.core.buffs.IBuff;
import net.sdm.sdm_rpg_world.core.buffs.ISummerBuff;
import net.sdm.sdm_rpg_world.core.config.ConfigProperty;
import net.sdm.sdm_rpg_world.core.rarity.RarityBase;
import net.sdm.sdm_rpg_world.core.rarity.RarityType;
import net.sdm.sdm_rpg_world.core.utils.CustomRandom;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StructureUtils {
    public static StructureBase generate(ResourceLocation structureID, ConfigProperty configProperty){
        StructureBase st = new StructureBase(structureID);
        generationBaseProperty(st, configProperty);
        generateConditions(st, configProperty);
        generateBuff(st, configProperty);

        return st;
    }

    public static void generationBaseProperty(StructureBase structureBase, ConfigProperty configProperty){
        structureBase.rarityBase = new RarityBase(CustomRandom.getRandomRarity(configProperty.minLevel, configProperty.maxLevel, configProperty.rarityChance));
        generationCountBuffs(structureBase, configProperty);
    }
    protected static void generationCountBuffs(StructureBase structureBase, ConfigProperty configProperty){
        switch (structureBase.rarityBase.type){
            case NONE -> {
                structureBase.rarityBase.countBuff = 0;
            }
            case COMMON -> {
                structureBase.rarityBase.countBuff = new Random().nextInt(1, 2);
            }
            case UNCOMMON -> {
                structureBase.rarityBase.countBuff = new Random().nextInt(1, 4);
            }
            case RARE -> {
                structureBase.rarityBase.countBuff = new Random().nextInt(2,6);
            }
            case EPIC -> {
                structureBase.rarityBase.countBuff = new Random().nextInt(3, 9);
            }
            case LEGENDARY -> {
                structureBase.rarityBase.countBuff = new Random().nextInt(4, 12);
            }
        }
    }

    public static void generateBuff(StructureBase structureBase, ConfigProperty configProperty){
        BuffsList buff = null;
        int maxGenerate = 100;
        int i1 = 0;
        while (buff == null){
            if(i1 >= maxGenerate) return;

            int id = new Random().nextInt(BuffsList.values().length);
            if(configProperty.property.contains(BuffsList.values()[id].name().toLowerCase()) || configProperty.property.isEmpty()){
                buff = BuffsList.values()[id];
            }
            i1++;
        }

        if(buff == null) {
            StringBuilder str = new StringBuilder("\n");
            for (String s : configProperty.property) {
                str.append(s).append("\n");
            }
            SDMRpgWorld.LOGGER.error("There are no existing parameters in the list. " + str + "\n" + BuffsList.toError());
            return;
        }

        Class<?> cls = buff.pr;
        Constructor<?> constructor = null;

        List<IBuff> bufList1 = new ArrayList<>();
        for (int i = 0; i < structureBase.rarityBase.countBuff; i++) {
            try {
                constructor = cls.getConstructor();
                BuffBase obj = (BuffBase) constructor.newInstance(new Object[]{});
                BuffBase buff1 = obj.generated();
                bufList1.add(buff1);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                SDMEngineCore.LOGGER.sendError(e.getMessage());
            }
        }

        if(bufList1.size() > 1) {
            start:
            for (int f = 0; f < bufList1.size(); f++) {
                IBuff bf = bufList1.get(f);
                if (bf instanceof ISummerBuff summerBuff) {
                    for (int d1 = 0; d1 < bufList1.size(); d1++) {
                        if(d1 != f && summerBuff.sum(bufList1.get(d1))){
                            bufList1.remove(d1);
                            continue start;
                        }
                    }
                }
            }
            start:
            for (int f = 0; f < bufList1.size(); f++) {
                IBuff bf = bufList1.get(f);
                if (bf instanceof ISummerBuff summerBuff) {
                    for (int d1 = 0; d1 < bufList1.size(); d1++) {
                        if(d1 != f && summerBuff.sum(bufList1.get(d1))){
                            bufList1.remove(d1);
                            continue start;
                        }
                    }
                }
            }
        }

        bufList1.forEach(structureBase::addBuff);
    }
    public static void generateConditions(StructureBase structureBase, ConfigProperty configProperty){

    }
}
