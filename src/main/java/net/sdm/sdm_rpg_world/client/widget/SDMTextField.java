package net.sdm.sdm_rpg_world.client.widget;

import dev.ftb.mods.ftblibrary.ui.Theme;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.sdm.sdm_rpg_world.client.ISDMRender;

import java.util.ArrayList;
import java.util.List;

public class SDMTextField implements ISDMRender {

    private FormattedText[] texts = {};
    private Component component;
    private float scale;
    private int x;
    private int y;

    public List<Component> componentList = new ArrayList<>();

    public SDMTextField(Component component, float size, int x, int y){

        this.component = component;
        this.scale = size;
        this.x = x;
        this.y = y;

    }

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        render(guiGraphics,screenWidth,screenHeight);
    }

    public void render(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {
        resize(screenWidth);
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate((double)x, (double)y, 0.0);
        guiGraphics.pose().scale(this.scale, this.scale, 1.0F);
        Theme theme = new Theme();

        int d1 = y;
        for (int i = 0; i < texts.length; i++) {
            theme.drawString(guiGraphics,texts[i], 2, (int) (d1 + (i * theme.getFontHeight() - (theme.getFontHeight() * 0.6f))));
        }

        int xCenter = (int) (((screenWidth + (screenHeight * 0.6f)) / 2) - (Minecraft.getInstance().font.getSplitter().stringWidth(I18n.get("sdm.unlock.gui.wiki.info")) + (Minecraft.getInstance().font.getSplitter().stringWidth(I18n.get("sdm.unlock.gui.wiki.info")) * 0.6f)) / 2);

//        theme.drawString(guiGraphics,Component.translatable("sdm.unlock.gui.wiki.info"), 1, (int) (screenHeight + theme.getFontHeight() + (theme.getFontHeight() * 0.6f)) - 4);
//        for (FormattedText component1 : texts) {
//            theme.drawString(guiGraphics,component1, 0, d1);
//        }

        guiGraphics.pose().popPose();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public void resize(int screenWidth){
        Theme theme = new Theme();
        texts = (FormattedText[])theme.listFormattedStringToWidth(component, (int) (screenWidth + (screenWidth * 0.6))).toArray(new FormattedText[0]);
    }
}
