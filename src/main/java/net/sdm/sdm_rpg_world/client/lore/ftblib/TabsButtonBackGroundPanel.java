package net.sdm.sdm_rpg_world.client.lore.ftblib;

import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.TextField;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.client.lore.SDMScreen;

public class TabsButtonBackGroundPanel extends Panel {
    public TextField modPackTittle = new TextField(this);
    public SDMScreen loreScreen;
    public Component component;
    public TabsButtonBackGroundPanel(SDMScreen panel, Component component, int i, int i1) {
        super(panel);
        this.loreScreen = panel;
        setSize(i, i1);
        modPackTittle.setText(component);
        modPackTittle.setScale(2.0f);
        this.component = component;
    }



    @Override
    public void addWidgets() {
        add(modPackTittle);
    }

    @Override
    public void alignWidgets() {
        modPackTittle.setPos((int) ((this.width / 2) - Minecraft.getInstance().font.getSplitter().stringWidth(I18n.get(component.getString()))), getY() + 1);
    }

    @Override
    public void draw(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        super.draw(graphics, theme, x, y, w, h);
        //GuiHelper.drawText(graphics,x,y,1.5f, Component.literal("Xyita Adventure"), Color4I.WHITE.rgb());
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        NordColors.POLAR_NIGHT_4.draw(graphics,x,y,w,h);
    }
}
