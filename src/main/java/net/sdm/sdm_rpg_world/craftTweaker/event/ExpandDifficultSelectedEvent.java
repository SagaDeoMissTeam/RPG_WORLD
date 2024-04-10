package net.sdm.sdm_rpg_world.craftTweaker.event;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.ForgeEventBusWire;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.sdm.sdm_rpg_world.core.events.DifficultSelectedEvent;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenEvent
@NativeTypeRegistration(value = DifficultSelectedEvent.class,zenCodeName = "mods.rpgworld.events.DifficultSelectedEvent")
public class ExpandDifficultSelectedEvent {

    @ZenEvent.Bus
    public static final IEventBus<DifficultSelectedEvent> BUS = IEventBus.direct(
            DifficultSelectedEvent.class,
            ForgeEventBusWire.of()
    );

    @ZenCodeType.Method
    @ZenCodeType.Getter("stateMachine")
    public static PlayerStateMachine getStateMachine(DifficultSelectedEvent event){
        return event.getStateMachine();
    }
}
