package net.sdm.sdm_rpg_world.mixin;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import daripher.skilltree.client.screen.SkillTreeScreen;
import journeymap.client.ui.fullscreen.Fullscreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.client.gui.CuriosScreen;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    public void sdm$setScreen(Screen screen, CallbackInfo ci){
        if(screen instanceof SkillTreeScreen skillTreeScreen){
            if(Minecraft.getInstance().player != null){

                if(!PlayerStateMachine.of(Minecraft.getInstance().player).progressManager.isSkillTreeUnlocked) ci.cancel();
            }
        } else if(screen instanceof CuriosScreen screen1){
            if(Minecraft.getInstance().player != null){
                if(!PlayerStateMachine.of(Minecraft.getInstance().player).progressManager.isCurioUnlocked) ci.cancel();
            }
        } else if(screen instanceof Fullscreen fullscreen){
            if(Minecraft.getInstance().player != null){
                if(!PlayerStateMachine.of(Minecraft.getInstance().player).progressManager.isMapUnlocked) ci.cancel();
            }
        }
    }
}
