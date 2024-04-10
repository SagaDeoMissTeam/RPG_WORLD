package net.sdm.sdm_rpg_world.core.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import net.sdm.sdm_rpg_world.core.ChunkHelper;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

@Cancelable
public class FeatureGenerationEvent extends Event {

    public String registry;
    public BlockPos position;
    public @Nullable Player player = ChunkHelper.loadChunkPlayer;
    public FeatureGenerationEvent(String id, BlockPos position){
        this.registry = id;
        this.position = position;
    }
    @Override
    public boolean isCancelable() {
        return true;
    }

    public String getRegistry() {
        return registry;
    }

    public BlockPos getPosition() {
        return position;
    }
}
