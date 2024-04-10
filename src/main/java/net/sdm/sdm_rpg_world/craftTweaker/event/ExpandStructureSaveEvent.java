package net.sdm.sdm_rpg_world.craftTweaker.event;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.event.ForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.ForgeEventBusWire;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.sdm.sdm_rpg_world.core.events.StructureSaveEvent;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenEvent
@NativeTypeRegistration(value = StructureSaveEvent.class,zenCodeName = "mods.rpgworld.events.StructureSaveEvent")
public class ExpandStructureSaveEvent {


    @ZenEvent.Bus
    public static final IEventBus<StructureSaveEvent> BUS = IEventBus.cancelable(
            StructureSaveEvent.class,
            ForgeEventBusWire.of(),
            ForgeEventCancellationCarrier.of()
    );

    @ZenCodeType.Method
    @ZenCodeType.Getter("data")
    public static MapData getData(StructureSaveEvent event){
        return event.getStructureData();
    }
    @ZenCodeType.Method
    @ZenCodeType.Getter("player")
    public static Player getPlayer(StructureSaveEvent event){
        return event.getPlayer();
    }
    @ZenCodeType.Method
    @ZenCodeType.Getter("id")
    public static ResourceLocation getStructureId(StructureSaveEvent event){
        return event.getId();
    }
}
