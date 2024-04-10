package net.sdm.sdm_rpg_world.core.events;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ScreenCloseEvent extends PlayerEvent {
    public ScreenCloseEvent(Player player) {
        super(player);
    }
}
