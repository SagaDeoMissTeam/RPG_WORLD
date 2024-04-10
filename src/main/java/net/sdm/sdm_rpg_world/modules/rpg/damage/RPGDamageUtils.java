package net.sdm.sdm_rpg_world.modules.rpg.damage;

import com.blamejared.crafttweaker.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.sdm.sdm_rpg_world.core.utils.CustomRandom;
import net.sdm.sdm_rpg_world.modules.rpg.entity.EntityStateMachine;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import net.sdm.sdm_rpg_world.modules.rpg.entity.stats.EntityStatValue;
import net.sdm.sdm_rpg_world.modules.rpg.item.genItems.LootItemStack;

import java.util.Map;

public class RPGDamageUtils {


    public static void calculateDamage(LivingDamageEvent event){
        if(event.getSource().getEntity() instanceof LivingEntity entity) {

            if (entity instanceof Player player) {
                PlayerStateMachine playerStateMachine = PlayerStateMachine.of(player);

                double damageAmount = event.getAmount();

                double damage_bonus = 0.0;
                double void_damage = 0.0;
                double divine_damage = 0.0;
                double chaos_damage = 0.0;

                double critical_hit_chance = 0.0;
                double critical_hit_multiply = 0.0;

                double damage_bonus_buff = 0.0;
                double void_damage_buff = 0.0;
                double divine_damage_buff = 0.0;
                double chaos_damage_buff = 0.0;

                if (playerStateMachine.statsListMap.containsKey("damage_bonus"))
                    damage_bonus = ((EntityStatValue<Double>) playerStateMachine.statsListMap.get("damage_bonus")).value.doubleValue() / 100.0;
                if (playerStateMachine.statsListMap.containsKey("void_damage"))
                    void_damage = ((EntityStatValue<Double>) playerStateMachine.statsListMap.get("void_damage")).value.doubleValue();
                if (playerStateMachine.statsListMap.containsKey("divine_damage"))
                    divine_damage = ((EntityStatValue<Double>) playerStateMachine.statsListMap.get("divine_damage")).value.doubleValue();
                if (playerStateMachine.statsListMap.containsKey("chaos_damage"))
                    chaos_damage = ((EntityStatValue<Double>) playerStateMachine.statsListMap.get("chaos_damage")).value.doubleValue();

                if (playerStateMachine.statsListMap.containsKey("critical_hit_chance"))
                    critical_hit_chance = ((EntityStatValue<Double>) playerStateMachine.statsListMap.get("critical_hit_chance")).value.doubleValue();
                if (playerStateMachine.statsListMap.containsKey("critical_hit_multiply"))
                    critical_hit_multiply = ((EntityStatValue<Double>) playerStateMachine.statsListMap.get("critical_hit_multiply")).value.doubleValue();

                if (playerStateMachine.statsListMap.containsKey("damage_bonus_buff"))
                    damage_bonus_buff = ((EntityStatValue<Double>) playerStateMachine.statsListMap.get("damage_bonus_buff")).value.doubleValue() / 100.0;
                if (playerStateMachine.statsListMap.containsKey("void_damage_buff"))
                    void_damage_buff = ((EntityStatValue<Double>) playerStateMachine.statsListMap.get("void_damage_buff")).value.doubleValue() / 100.0;
                if (playerStateMachine.statsListMap.containsKey("divine_damage_buff"))
                    divine_damage_buff = ((EntityStatValue<Double>) playerStateMachine.statsListMap.get("divine_damage_buff")).value.doubleValue() / 100.0;
                if (playerStateMachine.statsListMap.containsKey("chaos_damage_buff"))
                    chaos_damage_buff = ((EntityStatValue<Double>) playerStateMachine.statsListMap.get("chaos_damage_buff")).value.doubleValue() / 100.0;

                LootItemStack itemStat = LootItemStack.of(entity.getMainHandItem());

                if (itemStat.statBaseList.containsKey("damage_bonus")) {
                    damage_bonus += itemStat.statBaseList.get("damage_bonus").value;
                }
                if (itemStat.statBaseList.containsKey("void_damage")) {
                    void_damage += itemStat.statBaseList.get("void_damage").value;
                }
                if (itemStat.statBaseList.containsKey("divine_damage")) {
                    divine_damage += itemStat.statBaseList.get("divine_damage").value;
                }
                if (itemStat.statBaseList.containsKey("chaos_damage")) {
                    chaos_damage += itemStat.statBaseList.get("chaos_damage").value;
                }
                if (itemStat.statBaseList.containsKey("void_damage_buff")) {
                    void_damage_buff += itemStat.statBaseList.get("void_damage_buff").value / 100;
                }
                if (itemStat.statBaseList.containsKey("divine_damage_buff")) {
                    divine_damage_buff += itemStat.statBaseList.get("divine_damage_buff").value / 100;
                }
                if (itemStat.statBaseList.containsKey("chaos_damage_buff")) {
                    chaos_damage_buff += itemStat.statBaseList.get("chaos_damage_buff").value / 100;
                }
                if (itemStat.statBaseList.containsKey("damage_bonus_buff")) {
                    damage_bonus_buff += itemStat.statBaseList.get("damage_bonus_buff").value / 100;
                }
                if (itemStat.statBaseList.containsKey("critical_hit_chance")) {
                    critical_hit_chance += itemStat.statBaseList.get("critical_hit_chance").value / 100;
                }
                if (itemStat.statBaseList.containsKey("critical_hit_multiply")) {
                    critical_hit_multiply += itemStat.statBaseList.get("critical_hit_multiply").value / 100;
                }

                boolean isCrit = false;
                double d1 = CustomRandom.getRandom(0.0, 100.0, 0.0);
                if (d1 <= critical_hit_chance && !(critical_hit_chance <= 0.0)) {
                    isCrit = true;
                }


                EntityStateMachine targetEntityMachine = EntityStateMachine.of(event.getEntity());

                damage_bonus += damage_bonus * damage_bonus_buff;
                void_damage += void_damage * void_damage_buff;
                divine_damage += divine_damage * divine_damage_buff;
                chaos_damage += chaos_damage * chaos_damage_buff;

                if (!targetEntityMachine.statsListMap.isEmpty()) {
                    if (targetEntityMachine.statsListMap.containsKey("damage_reduce_bonus"))
                        damage_bonus = damage_bonus - ((EntityStatValue<Double>) targetEntityMachine.statsListMap.get("damage_reduce_bonus")).value.doubleValue();
                    if (targetEntityMachine.statsListMap.containsKey("void_resist"))
                        void_damage = void_damage - ((EntityStatValue<Double>) targetEntityMachine.statsListMap.get("void_resist")).value.doubleValue();
                    if (targetEntityMachine.statsListMap.containsKey("divine_resist"))
                        divine_damage = divine_damage - ((EntityStatValue<Double>) targetEntityMachine.statsListMap.get("divine_resist")).value.doubleValue();
                    if (targetEntityMachine.statsListMap.containsKey("chaos_resist"))
                        chaos_damage = chaos_damage - ((EntityStatValue<Double>) targetEntityMachine.statsListMap.get("chaos_resist")).value.doubleValue();
                }

                void_damage = Math.max(void_damage, 0.0);
                damage_bonus = Math.max(damage_bonus, 0.0);
                divine_damage = Math.max(divine_damage, 0.0);
                chaos_damage = Math.max(chaos_damage, 0.0);

                double totalDamage = damageAmount + damage_bonus + void_damage + divine_damage + chaos_damage;

                if (isCrit && !(critical_hit_multiply <= 0.0)) {
                    event.setAmount((float) (totalDamage + (totalDamage * critical_hit_multiply)));
                } else {
                    event.setAmount((float) totalDamage);
                }

            }
        }
    }



}
