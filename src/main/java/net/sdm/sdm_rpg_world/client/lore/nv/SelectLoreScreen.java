package net.sdm.sdm_rpg_world.client.lore.nv;

import dev.ftb.mods.ftblibrary.ui.BaseScreen;
import dev.ftb.mods.ftblibrary.ui.Theme;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class SelectLoreScreen extends BaseScreen {
    @Override public boolean drawDefaultBackground(GuiGraphics graphics) {return false;}
    public SelectLoreMainPanel mainPanel;
    public Minecraft mc = Minecraft.getInstance();
    public SelectLoreScreen(){

    }

    @Override
    public boolean onInit() {
        setWidth(getScreen().getGuiScaledWidth());
        setHeight(getScreen().getGuiScaledHeight());
        mainPanel = new SelectLoreMainPanel(this);
        return true;
    }

    @Override
    public void addWidgets() {
        add(mainPanel);
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {}

    public static class SelectTeamPlayerScreen extends BaseScreen{
        @Override public boolean drawDefaultBackground(GuiGraphics graphics) {return false;}
        public SelectLoreScreen mainScreen;
        public SelectTeamPlayerScreen(SelectLoreScreen mainScreen){
            this.mainScreen = mainScreen;
        }

        @Override
        public boolean onInit() {
            setWidth(mainScreen.getScreen().getGuiScaledWidth());
            setHeight(mainScreen.getScreen().getGuiScaledHeight());
            return true;
        }

        @Override
        public void addWidgets() {

        }
    }
}
