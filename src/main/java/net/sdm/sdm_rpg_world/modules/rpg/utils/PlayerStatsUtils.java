package net.sdm.sdm_rpg_world.modules.rpg.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerDifficult;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerProperty;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatsUtils {

    public static PlayerDifficult getPlayerDifficult(Player player) {
        CompoundTag nbt = player.getPersistentData();
        if(nbt.contains("gameSetting")){
            nbt = nbt.getCompound("gameSetting");
            if(nbt.contains("easy_mode")){
                return PlayerDifficult.EASY;
            } else if(nbt.contains("medium_mode")){
                return PlayerDifficult.MEDIUM;
            } else if(nbt.contains("hard_mode")){
                return PlayerDifficult.HARD;
            }
        }

        return PlayerDifficult.NONE;
    }

    public static List<PlayerProperty> getPlayerDifficultProperty(Player player){
        final List<PlayerProperty> properties = new ArrayList<PlayerProperty>();
        CompoundTag nbt = player.getPersistentData();
        if(nbt.contains("gameSetting")){
            nbt = nbt.getCompound("gameSetting");
            if(nbt.contains("random_start_kit")){
                properties.add(PlayerProperty.RANDOM_START_KIT);
            }
            if(nbt.contains("randomdimension")){
                properties.add(PlayerProperty.RANDOM_DIMENSION);
            }
            if(nbt.contains("missskill")){
                properties.add(PlayerProperty.MISSKILL);
            }
            if(nbt.contains("oredrop")){
                properties.add(PlayerProperty.OREDROP);
            }
            if(nbt.contains("randomspawndimension")){
                properties.add(PlayerProperty.RANDOM_SPAWN_DIMENSION);
            }
        }
        return properties;
    }
}
