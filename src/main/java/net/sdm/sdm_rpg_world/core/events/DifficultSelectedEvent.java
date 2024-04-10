package net.sdm.sdm_rpg_world.core.events;

import com.blamejared.crafttweaker.api.data.MapData;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;

public class DifficultSelectedEvent extends PlayerEvent {

    public String difficult = "";
    public PlayerStateMachine stateMachine;
    public DifficultSelectedEvent(Player player, PlayerStateMachine stateMachine) {
        super(player);
        this.stateMachine = stateMachine;
    }


    public PlayerStateMachine getStateMachine() {
        return stateMachine;
    }

    public String getDifficult() {
        return difficult;
    }
}
