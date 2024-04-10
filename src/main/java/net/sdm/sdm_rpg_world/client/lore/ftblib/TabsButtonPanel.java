package net.sdm.sdm_rpg_world.client.lore.ftblib;

import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import net.minecraft.client.gui.GuiGraphics;

public class TabsButtonPanel extends Panel implements NordColors {
    public TabsButtonBackGroundPanel tabsButtonBackGroundPanel;
    public TabsButtonPanel(TabsButtonBackGroundPanel panel) {
        super(panel);
        this.tabsButtonBackGroundPanel = panel;
    }

    @Override
    public void addWidgets() {

    }

    @Override
    public void alignWidgets() {

    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        
    }
}
