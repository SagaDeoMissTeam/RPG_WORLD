package net.sdm.sdm_rpg_world.core.events;

import com.blamejared.crafttweaker.api.data.MapData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import org.jetbrains.annotations.Nullable;

@Cancelable
public class StructureSaveEvent extends Event {
    public @Nullable Player player;
    public @Nullable ResourceLocation id;
    public @Nullable MapData structureData;

    public StructureSaveEvent(Player player, ResourceLocation id, MapData structureData){
        this.player = player;
        this.id = id;
        this.structureData = structureData;
    }

    public Player getPlayer() {
        return player;
    }

    public MapData getStructureData() {
        return structureData;
    }

    public ResourceLocation getId() {
        return id;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
