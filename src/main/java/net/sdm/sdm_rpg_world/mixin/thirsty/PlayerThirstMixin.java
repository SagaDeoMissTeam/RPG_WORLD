package net.sdm.sdm_rpg_world.mixin.thirsty;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import dev.ghen.thirst.content.thirst.PlayerThirst;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PlayerThirst.class, remap = false)
public class PlayerThirstMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void sdm$tick(Player player, CallbackInfo ci){
        if(!PlayerStateMachine.of(player).progressManager.isThirst) ci.cancel();
    }
}
