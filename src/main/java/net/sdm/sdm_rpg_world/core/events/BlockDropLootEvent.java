package net.sdm.sdm_rpg_world.core.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


@Cancelable
public class BlockDropLootEvent extends Event {
    public @Nullable Level level;
    public @Nullable BlockPos pos;
    public @Nullable BlockState block;
    public @Nullable Entity entity;
    public List<ItemStack> itemStackList = new ArrayList<>();
    public BlockDropLootEvent(Level level, BlockPos pos, BlockState block, Entity entity, List<ItemStack> returnValue) {
        this.level = level;
        this.pos = pos;
        this.block = block;
        this.entity = entity;
        this.itemStackList = returnValue;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }

}
