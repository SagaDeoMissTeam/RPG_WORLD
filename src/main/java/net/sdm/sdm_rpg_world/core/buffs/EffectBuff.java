package net.sdm.sdm_rpg_world.core.buffs;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.sdm.sdm_rpg_world.core.BuffsList;
import net.sdm.sdm_rpg_world.core.utils.CustomRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class EffectBuff extends BuffBase{
    public MobEffectInstance effect;
    public boolean onMobs = false;
    public EffectBuff(){}
    public EffectBuff(MobEffectInstance effect, boolean onMobs){
        this.effect = effect;
        this.onMobs = onMobs;
    }

    @Override
    public BuffsList getBuff() {
        return BuffsList.DAMAGE;
    }

    @Override
    public BuffSide getSide() {
        return BuffSide.EVENT;
    }

    @Override
    public BuffBase generated() {
        List<MobEffect> effectList = ForgeRegistries.MOB_EFFECTS.getEntries().stream().map(Map.Entry::getValue).toList();
        MobEffect mobEffect = effectList.get(new Random().nextInt(effectList.size()));
        MobEffectInstance instance = new MobEffectInstance(mobEffect, new Random().nextInt(100), CustomRandom.getRandom(0,5));
        return new EffectBuff(instance, Boolean.parseBoolean(String.valueOf(CustomRandom.getRandom(0,1))));
    }

    @Override
    public void accept(Object a) {
        if(a instanceof LivingEvent.LivingTickEvent event){
            if(event.getEntity() instanceof Player player && !onMobs){
                event.getEntity().addEffect(effect);
            } else {
                event.getEntity().addEffect(effect);
            }
        }
    }

    @Override
    public String toText() {
        return "Effect " + effect.getEffect().getDisplayName();
    }
}
