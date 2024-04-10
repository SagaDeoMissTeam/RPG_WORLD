package net.sdm.sdm_rpg_world.client.widget;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.icon.Icons;
import dev.ftb.mods.ftblibrary.ui.Button;
import dev.ftb.mods.ftblibrary.ui.GuiHelper;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.client.BaseDeficultParamPanel;

public class CheckBox extends Button {
    public boolean isClick = false;
    public BaseDeficultParamPanel panel;
    public CheckBox(BaseDeficultParamPanel panel) {
        super(panel, Component.empty(), Icons.CANCEL);
        this.panel = panel;
    }

    @Override
    public void onClicked(MouseButton mouseButton) {
        if(mouseButton.isLeft()){
            isClick = !isClick;
        }
    }

    @Override
    public void drawIcon(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        if(isClick){
            Icons.ACCEPT.draw(graphics,x,y,w,h);
            panel.panel.screenMain.selectedFunc.add(panel.nbt);
        } else{
            Icons.CANCEL.draw(graphics,x,y,w,h);
            if(panel.panel.screenMain.selectedFunc.contains(panel.nbt)){
                for (int i = 0; i < panel.panel.screenMain.selectedFunc.size(); i++) {
                    if(panel.panel.screenMain.selectedFunc.get(i).equals(panel.nbt)) panel.panel.screenMain.selectedFunc.remove(i);
                }
            }
        }
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        Color4I.fromString("#3E1317").draw(graphics, x + 1, y + 1, w - 2, h - 2);
    }
}
