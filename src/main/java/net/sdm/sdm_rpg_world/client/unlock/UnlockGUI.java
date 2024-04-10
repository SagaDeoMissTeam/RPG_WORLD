package net.sdm.sdm_rpg_world.client.unlock;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.sdm.sdm_rpg_world.client.ISDMRender;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class UnlockGUI implements IGuiOverlay {
    public List<ISDMRender> renders = new ArrayList<>();
    public UnlockGUI() {

    }

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate((Minecraft.getInstance().getWindow().getGuiScaledWidth() / 2) - (72 / 2), 4, 100);
        List<Integer> idRemove = new ArrayList<>();
        for (int i = 0; i < renders.size(); i++){
            if(!renders.get(i).isEnd()) {
                renders.get(i).render(gui, guiGraphics, partialTick,  92, 72);
                break;
            }
            else idRemove.add(i);
        }
        guiGraphics.pose().popPose();
        if(!idRemove.isEmpty()){
            for (Integer integer : idRemove) {
                renders.remove(integer.intValue());
            }
        }

    }
}
