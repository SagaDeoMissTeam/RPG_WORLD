package net.sdm.sdm_rpg_world.mixin.journeymap;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import journeymap.client.ui.minimap.MiniMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MiniMap.class, remap = false)
public class MiniMapMixin {

    @Inject(method = "drawMap(Lnet/minecraft/client/gui/GuiGraphics;Z)V", at = @At("HEAD"), cancellable = true)
    public void sdm$drawMap(GuiGraphics graphics, boolean preview, CallbackInfo ci){
        if(Minecraft.getInstance().player != null){

            if(!PlayerStateMachine.of(Minecraft.getInstance().player).progressManager.isMiniMapUnlocked) ci.cancel();
        }
    }
}
