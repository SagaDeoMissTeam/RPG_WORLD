package net.sdm.sdm_rpg_world.mixin.curio;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.client.event.ScreenEvent;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.client.gui.GuiEventHandler;

@Mixin(value = GuiEventHandler.class, remap = false)
public class GuiEventHandlerMixin {

    @Inject(method = "onInventoryGuiInit", at = @At("HEAD"), cancellable = true)
    public void sdm$onInventoryGuiInit(ScreenEvent.Init.Post evt, CallbackInfo ci){
        Screen screen = evt.getScreen();
        if (screen instanceof InventoryScreen || screen instanceof CreativeModeInventoryScreen) {
            if(Minecraft.getInstance().player != null){

                if(!PlayerStateMachine.of(Minecraft.getInstance().player).progressManager.isCurioUnlocked) ci.cancel();
            }
        }
    }
}
