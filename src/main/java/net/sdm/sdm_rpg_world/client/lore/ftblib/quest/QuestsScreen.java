package net.sdm.sdm_rpg_world.client.lore.ftblib.quest;

import dev.ftb.mods.ftblibrary.ui.BaseScreen;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.client.lore.SDMScreen;
import net.sdm.sdm_rpg_world.client.lore.ftblib.TabsButtonBackGroundPanel;

public class QuestsScreen extends SDMScreen {
    public TabsButtonBackGroundPanel tabsPanel;
    public QuestsScreen(){

    }

    @Override
    public boolean onInit() {
        setWidth(getScreen().getGuiScaledWidth());
        setHeight(getScreen().getGuiScaledHeight());
        tabsPanel = new TabsButtonBackGroundPanel(this, Component.literal("Quests"), this.width - 80,20);

        return true;
    }

    @Override
    public void addWidgets() {
        add(tabsPanel);

    }

    @Override
    public void alignWidgets() {
        tabsPanel.setPos(40, 4);
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        NordColors.POLAR_NIGHT_0.draw(graphics,x,y,w,h);

    }
}
