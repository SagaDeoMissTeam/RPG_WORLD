package net.sdm.sdm_rpg_world.craftTweaker.event;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.ForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.ForgeEventBusWire;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.sdm.sdm_rpg_world.core.events.StructureGenerationEvent;
import net.sdm.sdm_rpg_world.core.events.StructureSaveEvent;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenEvent
@NativeTypeRegistration(value = StructureGenerationEvent.class,zenCodeName = "mods.rpgworld.events.StructureGenerationEvent")
public class ExpandStructureGenerationEvent {

    @ZenEvent.Bus
    public static final IEventBus<StructureGenerationEvent> BUS = IEventBus.cancelable(
            StructureGenerationEvent.class,
            ForgeEventBusWire.of(),
            ForgeEventCancellationCarrier.of()
    );

    @ZenCodeType.Method
    @ZenCodeType.Getter("registry")
    public static String getRegistry(StructureGenerationEvent event){
        return event.getRegistry();
    }
    @ZenCodeType.Method
    @ZenCodeType.Getter("pos")
    public static BlockPos getPos(StructureGenerationEvent event){
        return event.getPos().getWorldPosition();
    }

    @ZenCodeType.Method
    @ZenCodeType.Getter("player")
    public static @Nullable Player getPlayer(StructureGenerationEvent event){
        return event.player;
    }
    @ZenCodeType.Method
    public static boolean isInside(StructureGenerationEvent event, BlockPos pos){
        return event.getBoundingBox().isInside(pos);
    }
}
