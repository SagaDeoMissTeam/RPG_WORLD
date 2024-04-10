package net.sdm.sdm_rpg_world.modules.rpg.entity.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityStatsList {

    public static final List<EntityStatsBase> statsList = new ArrayList<>();
    public static final Map<String, EntityStatsBase> statsMap = new HashMap<>();

    public static final EntityStatValue<Double> NULL = register(new EntityStatValue<Double>("null", 0.0));
    public static final EntityStatValue<Double> DAMAGE_BONUS = register(new EntityStatValue<Double>("damage_bonus", 0.0));
    public static final EntityStatValue<Double> DAMAGE_BONUS_BUFF = register(new EntityStatValue<Double>("damage_bonus_buff", 0.0));
    public static final EntityStatValue<Double> DAMAGE_RESIST = register(new EntityStatValue<Double>("damage_resist", 0.0));
    public static final EntityStatValue<Double> VOID_DAMAGE = register(new EntityStatValue<Double>("void_damage", 0.0));
    public static final EntityStatValue<Double> VOID_DAMAGE_BUFF = register(new EntityStatValue<Double>("void_damage_buff", 0.0));
    public static final EntityStatValue<Double> DIVINE_DAMAGE = register(new EntityStatValue<Double>("divine_damage", 0.0));
    public static final EntityStatValue<Double> DIVINE_DAMAGE_BUFF = register(new EntityStatValue<Double>("divine_damage_buff", 0.0));
    public static final EntityStatValue<Double> CHAOS_DAMAGE = register(new EntityStatValue<Double>("chaos_damage", 0.0));
    public static final EntityStatValue<Double> CHAOS_DAMAGE_BUFF = register(new EntityStatValue<Double>("chaos_damage_buff", 0.0));
    public static final EntityStatValue<Double> VOID_RESIST = register(new EntityStatValue<Double>("void_resist", 0.0));
    public static final EntityStatValue<Double> DIVINE_RESIST = register(new EntityStatValue<Double>("divine_resist", 0.0));
    public static final EntityStatValue<Double> CHAOS_RESIST = register(new EntityStatValue<Double>("chaos_resist", 0.0));
    public static final EntityStatValue<Double> WATER_RESIST = register(new EntityStatValue<Double>("water_resist", 0.0));
    public static final EntityStatValue<Double> LAVA_RESIST = register(new EntityStatValue<Double>("lava_resist", 0.0));
    public static final EntityStatValue<Double> LOOT_BONUS = register(new EntityStatValue<Double>("loot_bonus", 0.0));
    public static final EntityStatValue<Double> LOOT_RARITY_BONUS = register(new EntityStatValue<Double>("loot_rarity_bonus", 0.0));
    public static final EntityStatValue<Double> MIND_RESIST = register(new EntityStatValue<Double>("mind_resist", 0.0));
    public static final EntityStatValue<Double> MISS_CHANCE = register(new EntityStatValue<Double>("miss_chance", 0.0));
    public static final EntityStatValue<Double> MISS_REDUCE = register(new EntityStatValue<Double>("miss_reduce", 0.0));
    public static final EntityStatValue<Double> CRITICAL_HIT_CHANCE = register(new EntityStatValue<Double>("critical_hit_chance", 0.0));
    public static final EntityStatValue<Double> CRITICAL_HIT_MULTIPLY = register(new EntityStatValue<Double>("critical_hit_multiply", 0.0));
    public static final EntityStatValue<Double> CRITICAL_HIT_RANK = register(new EntityStatValue<Double>("critical_hit_rank", 0.0));
    public static final EntityStatValue<Double> PROTECT_CLASS = register(new EntityStatValue<Double>("protect_class", 0.0));
    public static final EntityStatValue<Double> RED_RESIST = register(new EntityStatValue<Double>("redF", 50.0));
    public static final EntityStatValue<Double> GREEN_RESIST = register(new EntityStatValue<Double>("greenF", 50.0));
    public static final EntityStatValue<Double> BLUE_RESIST = register(new EntityStatValue<Double>("blueF", 50.0));
    public static final EntityStatValue<Double> ALPHA_RESIST = register(new EntityStatValue<Double>("alpha", 255.0));

    public static <T extends EntityStatsBase> T register(T stat){
        statsList.add(stat);
        statsMap.put(stat.id, stat);
        return stat;
    }
}
