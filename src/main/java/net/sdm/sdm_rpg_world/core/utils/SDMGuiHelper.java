package net.sdm.sdm_rpg_world.core.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.SDMRpgWorld;

public class SDMGuiHelper {

    public static void drawText(GuiGraphics poseStack, int x, int y, float size, Component text, int textColor){
        drawText(poseStack, Minecraft.getInstance().font,x,y,size,text,textColor);
    }
    public static void drawText(GuiGraphics poseStack, Font font, int x, int y, float size, Component text, int textColor){
        poseStack.pose().pushPose();
        poseStack.pose().scale(size, size, 1.0f);
        poseStack.pose().translate(x, y, 0);
        poseStack.drawString(font,text, (int) x, (int) y, textColor);
        poseStack.pose().popPose();
    }
}
