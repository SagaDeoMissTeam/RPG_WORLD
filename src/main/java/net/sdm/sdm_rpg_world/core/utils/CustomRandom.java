package net.sdm.sdm_rpg_world.core.utils;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import net.minecraft.world.level.block.Block;
import net.sdm.sdm_rpg_world.core.buffs.DamageBuff;
import net.sdm.sdm_rpg_world.core.buffs.IBuff;
import net.sdm.sdm_rpg_world.core.rarity.RarityType;
import net.sdm.sdm_rpg_world.core.structure.StructureBase;
import org.joml.Math;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@ZenRegister
@ZenCodeType.Name("mods.rpgworld.utils.CustomRandom")
public class CustomRandom {
    @ZenCodeType.Method
    public static double lerp(double a, double b){
        return Math.lerp(a, b, Math.random());
    }

    public static RarityType getRandomRarity(){
        return getRandomRarity(0);
    }


    public static RarityType getRandomRarity(double addedChance){
        double chance = Math.lerp(0, RarityType.values().length, Math.random());
        double current = 0 + addedChance;
        for (int i = 0; i < RarityType.values().length; i++) {
            if(current <= chance && chance < current + RarityType.values()[i].getChance()){
                return RarityType.values()[i];
            }
            current += RarityType.values()[i].getChance();
        }

        return RarityType.NONE;
    }

    public static RarityType getRandomRarity(double num1, double num2, double addedChance){

        int chanceF = RarityType.values().length < num2 ? RarityType.values().length : (int) num2;
        double chance = Math.lerp(num1, num2, Math.random());
        addedChance = addedChance / 100;
        double current = 0 + addedChance;
        for (int i = chanceF; i > 0; i--) {
            if(current <= chance && chance < current + RarityType.values()[i].getChance()){
                return RarityType.values()[i];
            }
            current += RarityType.values()[i].getChance();
        }

        return RarityType.NONE;
    }
    @ZenCodeType.Method
    public static double getRandom(double num1, double num2, double addedChance){
        double chance = Math.lerp((double) num1, (double) num2, Math.random());
        double current = 0 + addedChance;
        for (int i = 0; i < num2; i++) {
            if(current <= chance && chance < current + (double)num2){
                return num2;
            }
            current += num2;
        }
        return 0.0;
    }

    @ZenCodeType.Method
    public static int getRandom(int num1, int num2){
        return getRandom(num1,num2, 0.0);
    }
    @ZenCodeType.Method
    public static int getRandom(int num1, int num2, double addedChance){
        double chance = Math.lerp((double) num1, (double) num2, Math.random());
        double current = 0 + addedChance;
        for (int i = 0; i < num2; i++) {
            if(current <= chance && chance < current + num2){
                return (int) num2;
            }
            current += num2;
        }
        return num1;
    }

    public static double getRandomDamage(StructureBase base, DamageBuff damageBuff){


        return 0.0D;
    }

    public static boolean getRandomBool(){
        Random random = new Random();



        return true;
    }
}
