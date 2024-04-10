package net.sdm.sdm_rpg_world.client.lore.nv;

import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class SelectLoreMainPanel extends Panel {
    public SelectLoreScreen mainScreen;
    public SelectLoreMainPanel(SelectLoreScreen panel) {
        super(panel);
        this.mainScreen = panel;
        setSize(mainScreen.width, mainScreen.height);
    }

    @Override
    public void addWidgets() {

    }

    @Override
    public void alignWidgets() {

    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        NordColors.POLAR_NIGHT_0.withAlpha(180).draw(graphics,x,y,w,h);
        NordColors.POLAR_NIGHT_0.draw(graphics, getXCenter(120), 12, 120, 18);
    }

    @Override
    public void drawOffsetBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        theme.drawString(graphics, Component.literal("СЮЖЕТНЫЙ РЕЖИМ"), (x + getXCenter((int) mainScreen.mc.font.getSplitter().stringWidth("ВЫБЕРИТЕ СЛОЖНОСТЬ"))), 16);
    }

    public int getXCenter(){
        return getXCenter(0);
    }
    public int getXCenter(int w){
        int screenCenter = getScreen().getGuiScaledWidth() / 2;
        return screenCenter - (w / 2);
    }
}
