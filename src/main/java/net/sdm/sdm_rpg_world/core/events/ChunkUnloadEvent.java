package net.sdm.sdm_rpg_world.core.events;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ChunkUnloadEvent extends PlayerEvent {

    public ChunkUnloadEvent(Player player) {
        super(player);
    }
}
