package net.sdm.sdm_rpg_world.craftTweaker;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

/**
 * @docParam this player
 */
@ZenRegister
@ZenCodeType.Expansion("crafttweaker.api.entity.type.player.Player")
public class ExpandPlayer {

    @ZenCodeType.Method
    public static PlayerStateMachine getStateMachine(Player player) {
        return PlayerStateMachine.of(player);
    }
}
