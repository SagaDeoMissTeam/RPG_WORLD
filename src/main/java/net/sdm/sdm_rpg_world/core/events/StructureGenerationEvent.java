package net.sdm.sdm_rpg_world.core.events;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import net.sdm.sdm_rpg_world.core.ChunkHelper;
import org.jetbrains.annotations.Nullable;

@Cancelable
public class StructureGenerationEvent extends Event {
    public String registry;
    public ChunkPos pos;
    public BoundingBox boundingBox;
    public @Nullable Player player = ChunkHelper.loadChunkPlayer;
    public StructureGenerationEvent(String registry, ChunkPos pos, BoundingBox boundingBox){
        this.registry = registry;
        this.pos = pos;
        this.boundingBox = boundingBox;
    }


    public String getRegistry() {
        return registry;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public ChunkPos getPos() {
        return pos;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
