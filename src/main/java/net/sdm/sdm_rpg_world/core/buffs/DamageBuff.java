package net.sdm.sdm_rpg_world.core.buffs;

import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.sdm.sdm_rpg_world.core.BuffsList;
import net.sdm.sdm_rpg_world.core.utils.CustomRandom;

import java.util.List;
import java.util.Random;

public class DamageBuff extends BuffBase implements ISummerBuff{

    public float damageP;
    public DamageBuff(){

    }
    public DamageBuff(float damageP, boolean isPosetive){
        this.damageP = damageP;
        this.positive = isPosetive;
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
        boolean b = new Random().nextBoolean();
        float d = (float) CustomRandom.getRandom(0.0, 0.7, 0.0);
        return new DamageBuff(d,b);
    }

    @Override
    public void accept(Object a) {
        if(a instanceof LivingDamageEvent event){
            if(event.getEntity() instanceof Player player && event.getSource().getEntity() instanceof Player) return;
            if(positive){
                event.setAmount(event.getAmount() + (event.getAmount() * damageP));
            } else {
                event.setAmount(event.getAmount() - (event.getAmount() * damageP));
            }

        }
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = super.serializeNBT();
        nbt.putFloat("damage", damageP);
        return nbt;
    }

    @Override
    public String toText() {
        return "Damage: " + (positive ? "+§a" : "-§c") + (damageP * 100) + "%";
    }

    @Override
    public List<Component> tooltips() {
        List<Component> s = super.tooltips();
        return s;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        super.deserializeNBT(nbt);
        damageP = nbt.getFloat("damage");
    }

    @Override
    public boolean sum(IBuff buff) {
        if(buff instanceof DamageBuff damageBuff){
            damageP += damageBuff.damageP;
            if(damageP < 0) positive = false;
            else positive = true;
            return true;
        }
        return false;
    }
}
