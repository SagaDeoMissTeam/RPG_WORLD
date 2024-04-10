package net.sdm.sdm_rpg_world.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.sdm.sdm_rpg_world.core.events.BlockDropLootEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Block.class, remap = false)
public class BlockMixin {

//    @Inject(method = "dropResources(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;Z)V", at = @At("HEAD"), cancellable = true)
//    private static void sdm$getDrops(BlockState block, Level level, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, boolean dropXp, CallbackInfo ci){
//        ci.cancel();
//        if (level instanceof ServerLevel) {
//            BlockDropLootEvent event = new BlockDropLootEvent(level,pos,block,entity,Block.getDrops(block, (ServerLevel)level, pos, blockEntity, entity, stack));
//            MinecraftForge.EVENT_BUS.post(event);
//            if(event.isCanceled()) return;
//            event.itemStackList.forEach((p_49944_) -> {
//                Block.popResource(level, pos, p_49944_);
//
//            });
//            block.spawnAfterBreak((ServerLevel)level, pos, stack, dropXp);
//        }
//
//
//    }
}
