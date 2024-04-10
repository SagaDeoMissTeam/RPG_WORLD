package net.sdm.sdm_rpg_world.modules.developer;

import net.minecraft.world.entity.player.Player;

public abstract class DevCommand {

    public String command;
    public int level;

    public DevCommand(String command, int level){
        this.command = command;
        this.level = level;
    }

    public abstract boolean run(Player player);
}
