package net.sdm.sdm_rpg_world.mixin;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Commands.class)
public class CommandsMixin {


//    @Inject(method = "sendCommands", at = @At("HEAD"), cancellable = true)
//    public void sdm$sendCommands(ServerPlayer player, CallbackInfo ci){
//        CompoundTag nbt = ExpandEntity.getCustomData(player).getInternal();
//
//        if(!PlayerStateMachine.of(player).progressManager.isCanUseCommand) ci.cancel();
//    }
}
