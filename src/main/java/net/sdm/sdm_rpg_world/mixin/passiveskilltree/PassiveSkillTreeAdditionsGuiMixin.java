package net.sdm.sdm_rpg_world.mixin.passiveskilltree;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.client.event.ScreenEvent;
import net.pixeldreamstudios.passiveskilltreeadditions.client.PassiveSkillTreeAdditionsClient;
import net.pixeldreamstudios.passiveskilltreeadditions.client.PassiveSkillTreeAdditionsGui;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PassiveSkillTreeAdditionsClient.class,remap = false)
public class PassiveSkillTreeAdditionsGuiMixin {

    @Inject(method = "addAttribComponent", at = @At("HEAD"), cancellable = true)
    public void sdm$addAttribComponent(ScreenEvent.Init.Post e, CallbackInfo ci){
        if(Minecraft.getInstance().player != null){
            Screen var3 = e.getScreen();
            if (var3 instanceof InventoryScreen scn) {
                if(!PlayerStateMachine.of(Minecraft.getInstance().player).progressManager.isSkillTreeUnlocked) ci.cancel();
            }
        }
    }

//    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
//    public void sdm$render(GuiGraphics gfx, int mouseX, int mouseY, float partialTicks, CallbackInfo ci){
//        if(Minecraft.getInstance().player != null){
//            CompoundTag nbt = ExpandEntity.getCustomData(Minecraft.getInstance().player).getInternal();
//            if(!nbt.contains("playerSateMachine")) ci.cancel();
//            if(!nbt.getCompound("playerSateMachine").contains("unlockSkillTree")) ci.cancel();
//            if(!nbt.getCompound("playerSateMachine").getBoolean("unlockSkillTree")) ci.cancel();
//        }
//    }
}
