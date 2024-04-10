package net.sdm.sdm_rpg_world.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public interface ISDMRender {

    default void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight){

    }
    void render( GuiGraphics guiGraphics, int screenWidth, int screenHeight);
    boolean isEnd();

}
