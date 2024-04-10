package net.sdm.sdm_rpg_world.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.MinecraftForge;
import net.sdm.sdm_rpg_world.core.events.PlayerChestLootEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LootTable.class)
public class LootTableMixin {

    @Inject(
            method = {"fill"},
            at = {@At("TAIL")},
            cancellable = true)
    public void sdm$$onLootGenMixin(Container inventory, LootParams context, long l, CallbackInfo ci) {
        try {
            try {

                if(context.hasParam(LootContextParams.THIS_ENTITY) && context.hasParam(LootContextParams.ORIGIN)
                        && context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player){
                    BlockEntity chest = null;
                    BlockPos pos = new BlockPos((int) context.getOptionalParameter(LootContextParams.ORIGIN).x, (int) context.getOptionalParameter(LootContextParams.ORIGIN).y, (int) context.getOptionalParameter(LootContextParams.ORIGIN).z);
                    Player player = (Player) context.getParamOrNull(LootContextParams.THIS_ENTITY);
                    Level world = player.level();
                    if(inventory instanceof BlockEntity){
                        chest = (BlockEntity) inventory;
                    }

                    if(world == null){
                        return;
                    }

                    if(chest instanceof RandomizableContainerBlockEntity){
                        PlayerChestLootEvent event = new PlayerChestLootEvent(player, context, inventory, pos);
                        MinecraftForge.EVENT_BUS.post(event);
                        if(event.isCancelable()) ci.cancel();
                    }
                }

            } catch (Exception ignored){
            }
        } catch (Exception ignored) {
        }

    }
}