package net.sdm.sdm_rpg_world.mixin.jade;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import snownee.jade.overlay.OverlayRenderer;
import snownee.jade.overlay.TooltipRenderer;

@Mixin(value = OverlayRenderer.class, remap = false)
public class OverlayRendererMixin {

    @Inject(method = "renderOverlay", at = @At("HEAD"), cancellable = true)
    private static void sdm$renderOverlay(TooltipRenderer tooltip, GuiGraphics guiGraphics, CallbackInfo ci){
        if(Minecraft.getInstance().player != null){

            if(!PlayerStateMachine.of(Minecraft.getInstance().player).progressManager.isJadeUnlocked) ci.cancel();
        }
        if(!SDMRpgWorld.SDMClient.unlockGUI.renders.isEmpty()) ci.cancel();
    }
}
