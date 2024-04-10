package net.sdm.sdm_rpg_world.craftTweaker.event;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.ForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.ForgeEventBusWire;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.sdm.sdm_rpg_world.core.events.FeatureGenerationEvent;
import net.sdm.sdm_rpg_world.core.events.StructureGenerationEvent;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenEvent
@NativeTypeRegistration(value = FeatureGenerationEvent.class,zenCodeName = "mods.rpgworld.events.FeatureGenerationEvent")
public class ExpandFeatureGenerationEvent {

    @ZenEvent.Bus
    public static final IEventBus<FeatureGenerationEvent> BUS = IEventBus.cancelable(
            FeatureGenerationEvent.class,
            ForgeEventBusWire.of(),
            ForgeEventCancellationCarrier.of()
    );

    @ZenCodeType.Method
    @ZenCodeType.Getter("registry")
    public static String getRegistry(FeatureGenerationEvent event){
        return event.getRegistry();
    }

    @ZenCodeType.Method
    @ZenCodeType.Getter("player")
    public static @Nullable Player getPlayer(FeatureGenerationEvent event){
        return event.player;
    }
    @ZenCodeType.Method
    @ZenCodeType.Getter("pos")
    public static BlockPos getPos(FeatureGenerationEvent event){
        return event.getPosition();
    }
}
