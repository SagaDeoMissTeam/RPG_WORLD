package net.sdm.sdm_rpg_world.core.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import org.jetbrains.annotations.Nullable;

@Cancelable
public class PlayerChestLootEvent extends PlayerEvent {

    private @Nullable LootParams ctx;
    private Container inventory;
    private BlockPos pos;
    public PlayerChestLootEvent(Player player, @Nullable LootParams ctx, Container inventory, BlockPos pos) {
        super(player);
        this.ctx = ctx;
        this.inventory = inventory;
        this.pos = pos;
    }


    public BlockPos getPos() {
        return pos;
    }

    public Container getInventory() {
        return inventory;
    }

    public LootParams getCtx() {
        return ctx;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
