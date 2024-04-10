package net.sdm.sdm_rpg_world.craftTweaker.event;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.ForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.ForgeEventBusWire;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.sdm.sdm_rpg_world.core.events.BlockDropLootEvent;
import net.sdm.sdm_rpg_world.core.events.DifficultSelectedEvent;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenEvent
@NativeTypeRegistration(value = BlockDropLootEvent.class,zenCodeName = "mods.rpgworld.events.BlockDropLootEvent")
public class ExpandBlockDropLootEvent {

    @ZenEvent.Bus
    public static final IEventBus<BlockDropLootEvent> BUS = IEventBus.cancelable(
            BlockDropLootEvent.class,
            ForgeEventBusWire.of(),
            ForgeEventCancellationCarrier.of()
    );

    @ZenCodeType.Method
    @ZenCodeType.Getter("entity")
    public static @ZenCodeType.Nullable Entity getEntity(BlockDropLootEvent event){
        return event.entity;
    }


    @ZenCodeType.Method
    @ZenCodeType.Getter("loot")
    public static List<IItemStack> getLoot(BlockDropLootEvent event){
        List<IItemStack> it = new ArrayList<>();
        for (ItemStack itemStack : event.itemStackList) {
            it.add(IItemStack.of(itemStack));
        }
        return it;
    }
    @ZenCodeType.Method
    @ZenCodeType.Setter("loot")
    public static void setLoot(BlockDropLootEvent event, List<IItemStack> itemStackList){
        List<ItemStack> it = new ArrayList<>();
        for (IItemStack itemStack : itemStackList) {
            it.add(itemStack.getInternal());
        }
        event.itemStackList = it;
    }
}
