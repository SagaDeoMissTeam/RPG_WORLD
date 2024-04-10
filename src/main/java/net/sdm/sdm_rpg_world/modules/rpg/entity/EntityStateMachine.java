package net.sdm.sdm_rpg_world.modules.rpg.entity;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.INBTSerializable;
import net.sdm.sdm_rpg_world.modules.api.ISyncData;
import net.sdm.sdm_rpg_world.modules.api.ITicking;
import net.sdm.sdm_rpg_world.modules.rpg.entity.stats.EntityStatValue;
import net.sdm.sdm_rpg_world.modules.rpg.entity.stats.EntityStatsBase;
import net.sdm.sdm_rpg_world.modules.rpg.entity.stats.EntityStatsList;
import net.sdm.sdm_rpg_world.modules.rpg.utils.EntityStatsHelper;
import org.openzen.zencode.java.ZenCodeType;

import java.util.*;


@ZenRegister
@ZenCodeType.Name("mods.rpgworld.entity.entity.EntityStateMachine")
public class EntityStateMachine implements INBTSerializable<SNBTCompoundTag>, ISyncData, ITicking {
    public Entity entity;

    public List<EntityStatsBase> statsList = new ArrayList<>();
    public Map<String, EntityStatsBase> statsListMap = new HashMap<>();
    public boolean needSync = false;


    protected EntityStateMachine(Entity entity) {
        this.entity = entity;
    }

    public static EntityStateMachine of(Entity entity) {
        EntityStateMachine stateMachine = new EntityStateMachine(entity);

        if(entity.getPersistentData().contains("sdmData")){
            if(entity.getPersistentData().getCompound("sdmData").contains("entityStateMachine")){
                SNBTCompoundTag nbt = SNBTCompoundTag.of(entity.getPersistentData().getCompound("sdmData").getCompound("entityStateMachine"));
                stateMachine.deserializeNBT(nbt);
                return stateMachine;
            }
        }

        stateMachine.generateBaseStats();
        stateMachine.syncData();
        return stateMachine;
    }


    @ZenCodeType.Method
    public void generateBaseStats(){
        statsList = new ArrayList<>();
        statsListMap = new HashMap<>();
        List<String> statsList = new ArrayList<>();
        String[] stats = {"void_damage", "divine_damage", "chaos_damage", "void_resist", "divine_resist", "chaos_resist", "damage_bonus", "damage_reduce_bonus"};
        statsList = Arrays.stream(stats).toList();
        double maxValuesStats = new Random().nextDouble(200);
        double sum = 0d;
        List<String> statsAdded = new ArrayList<String>();
        Map<String, Double> statsUpdated = new HashMap<String, Double>();

        while (sum < maxValuesStats){
            int stat = new Random().nextInt(statsList.size());
            String name = statsList.get(stat);
            double value = new Random().nextDouble();

            if(statsAdded.contains(name)){
                value = statsUpdated.get(name) + value;
                statsUpdated.put(name, value);
            } else {
                statsAdded.add(name);
                statsUpdated.put(name, value);
            }
            sum += value;
        }

        for (Map.Entry<String, Double> statData : statsUpdated.entrySet()) {
            if(EntityStatsList.statsMap.containsKey(statData.getKey())){
                this.statsList.add(EntityStatsList.statsMap.get(statData.getKey()));
                EntityStatsBase base = EntityStatsList.statsMap.get(statData.getKey()).copy();
                if(base instanceof EntityStatValue<?> value){
                    value.updateValue(statData.getValue());
                    this.statsListMap.put(statData.getKey(),value);
                }
            }
        }

        this.statsList.add(EntityStatsList.RED_RESIST.copy());
        this.statsList.add(EntityStatsList.GREEN_RESIST.copy());
        this.statsList.add(EntityStatsList.BLUE_RESIST.copy());
        this.statsList.add(EntityStatsList.ALPHA_RESIST.copy());


    }




    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = new SNBTCompoundTag();
        ListTag statsTags = new ListTag();
        for (EntityStatsBase entityStatsBase : statsList) {
            statsTags.add(entityStatsBase.serializeNBT());
        }
        nbt.put("statsList", statsTags);
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        if(nbt.contains("statsList")){
            ListTag list = (ListTag) nbt.get("statsList");
            for (Tag tag : list) {
                SNBTCompoundTag d1 = SNBTCompoundTag.of(tag);
                EntityStatsBase base = EntityStatsHelper.deserialize(d1);
                statsList.add(base);
                if(base instanceof EntityStatValue<?> value){
                    this.statsListMap.put(base.id,value);
                }
            }
        }
    }

    @Override
    @ZenCodeType.Method
    public void syncData() {
        SNBTCompoundTag nbt = serializeNBT();
        CompoundTag data = new CompoundTag();
        data.put("entityStateMachine", nbt);
        entity.getPersistentData().put("sdmData", data);
        this.needSync = false;
    }

    @Override
    @ZenCodeType.Method
    public void onTick() {
        if(needSync){
            syncData();
        }
    }

}
