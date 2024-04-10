package net.sdm.sdm_rpg_world.mixin.sanity;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import croissantnova.sanitydim.SanityProcessor;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(value = SanityProcessor.class,remap = false)
public class SanityProcessorMixin {

    @Inject(method = "tickPlayer", at =  @At("HEAD"), cancellable = true)
    private static void sdm$tickPlayer(ServerPlayer player, CallbackInfo ci) {
        if(!PlayerStateMachine.of(player).progressManager.isSanity) ci.cancel();
    }
}
