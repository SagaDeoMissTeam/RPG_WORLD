package net.sdm.sdm_rpg_world.mixin.thirsty;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import dev.ghen.thirst.foundation.gui.ThirstBarRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ThirstBarRenderer.class, remap = false)
public class ThirstBarRendererMixin {


    @OnlyIn(Dist.CLIENT)
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private static void sdm$render(ForgeGui gui, int width, int height, GuiGraphics guiGraphics, CallbackInfo ci){
        if(Minecraft.getInstance().player != null) {
            if(!PlayerStateMachine.of(Minecraft.getInstance().player).progressManager.isThirst) ci.cancel();
        }
    }
}
