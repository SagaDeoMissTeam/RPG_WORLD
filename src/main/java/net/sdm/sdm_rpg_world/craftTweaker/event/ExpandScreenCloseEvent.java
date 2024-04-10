package net.sdm.sdm_rpg_world.craftTweaker.event;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.ForgeEventBusWire;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.sdm.sdm_rpg_world.core.events.DifficultSelectedEvent;
import net.sdm.sdm_rpg_world.core.events.ScreenCloseEvent;

@ZenRegister
@ZenEvent
@NativeTypeRegistration(value = ScreenCloseEvent.class,zenCodeName = "mods.rpgworld.events.ScreenCloseEvent")
public class ExpandScreenCloseEvent {

    @ZenEvent.Bus
    public static final IEventBus<ScreenCloseEvent> BUS = IEventBus.direct(
            ScreenCloseEvent.class,
            ForgeEventBusWire.of()
    );
}
