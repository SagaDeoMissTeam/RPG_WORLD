package net.sdm.sdm_rpg_world.client.lore.ftblib;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.ui.BaseScreen;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import icyllis.modernui.ModernUI;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.client.lore.SDMScreen;

public class LoreScreen extends SDMScreen implements NordColors {
    @Override public boolean drawDefaultBackground(GuiGraphics graphics) {return false;}

    public TabsButtonBackGroundPanel tabsPanel;
    public MainBackGroundPanel mainPanel;
    public LoreScreen(){

    }

    @Override
    public boolean onInit() {
        setWidth(getScreen().getGuiScaledWidth());
        setHeight(getScreen().getGuiScaledHeight());

        mainPanel = new MainBackGroundPanel(this, this.width - 20, this.height - 36);
        tabsPanel = new TabsButtonBackGroundPanel(this, Component.literal("Xyita Adventure"), this.width - 80,20);
        return true;
    }

    @Override
    public void addWidgets() {
        add(mainPanel);
        add(tabsPanel);
    }

    @Override
    public void alignWidgets() {
        mainPanel.setPos(10, 26);
        tabsPanel.setPos(40, 4);
        super.alignWidgets();
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        NordColors.POLAR_NIGHT_0.draw(graphics,x,y,w,h);

    }
}
