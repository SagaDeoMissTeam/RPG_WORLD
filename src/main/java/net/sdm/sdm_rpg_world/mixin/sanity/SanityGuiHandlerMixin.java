package net.sdm.sdm_rpg_world.mixin.sanity;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import croissantnova.sanitydim.client.GuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiHandler.class, remap = false)
public class SanityGuiHandlerMixin {


    @Inject(method = "renderSanityIndicator", at = @At("HEAD"), remap = false, cancellable = true)
    private void sdm$renderSanityIndicator(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int scw, int sch, CallbackInfo ci){
        if(Minecraft.getInstance().player != null) {
            if(!PlayerStateMachine.of(Minecraft.getInstance().player).progressManager.isSanity) ci.cancel();
        }
    }
}
