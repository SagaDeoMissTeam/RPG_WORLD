package net.sdm.sdm_rpg_world.modules.developer;

import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Developers {
    public static List<Developer> developers = new ArrayList<>();

    public static String register(String name, int level){
        developers.add(new Developer(name, level));
        return name;
    }

    public static void init(){
        register("Dev", 3);
        register("Sixik", 3);
        register("kitoragas", 3);
        register("vanilovv", 3);
        register("yShe4Ka", 3);
        register("ySh", 3);
    }

    public static boolean isDeveloper(Player player){
        for (Developer developer : developers) {
            if(developer.nick.equals(player.getName().getString())) return true;
        }
        return false;
    }

    public static int getLevel(Player player){
        for (Developer developer : developers) {
            if(developer.nick.equals(player.getName().getString())) return developer.devLevel;
        }

        return 0;
    }
}
