package net.sdm.sdm_rpg_world.client.lore.ftblib;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.*;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.client.SDMIcons;
import net.sdm.sdm_rpg_world.client.lore.SDMScreen;
import net.sdm.sdm_rpg_world.client.lore.ftblib.quest.QuestsScreen;
import net.sdm.sdm_rpg_world.client.lore.widget.CubeButtonWidget;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class MainBackGroundPanel extends Panel implements NordColors {
    public SDMScreen loreScreen;
    public List<CubeButtonWidget> cubeButtonWidgetList = new ArrayList<>();
    public MainBackGroundPanel(LoreScreen panel, int i, int i1) {
        super(panel);
        setSize(i, i1);
        this.loreScreen = panel;

        init();
    }

    public void init(){
        cubeButtonWidgetList.add(new CubeButtonWidget(this, 48,48, SDMIcons.ID_CARD, Component.literal("You Stats"), Component.literal("Ваша информация")));
        cubeButtonWidgetList.add(new CubeButtonWidget(this, 48,48, SDMIcons.SOCIAL, Component.literal("Wiki"),Component.literal("Основная информация"),
        (simpleButton, mouseButton) -> {
            if(mouseButton.isLeft()){
                handleClick("https://www.wikipedia.org/");
            }
        }
        ));
        cubeButtonWidgetList.add(new CubeButtonWidget(this, 48,48, SDMIcons.DISCORD, Component.literal("Discord"), Component.literal("Это мой дискордик"),
            (simpleButton, mouseButton) -> {
                if(mouseButton.isLeft()){
                    handleClick("https://discord.gg/4CemepRxmN");
                }
            }
        ));
        cubeButtonWidgetList.add(new CubeButtonWidget(this, 48,48, SDMIcons.INFO, Component.literal("DEVBLOG"), Component.literal("Список Изменений")));
        cubeButtonWidgetList.add(new CubeButtonWidget(this, 48,48, SDMIcons.BOOK_COVER, Component.literal("Quests"), Component.literal("Задания от Сидоровича"),
            (simpleButton, mouseButton) -> {
                if(mouseButton.isLeft()){
                    simpleButton.getGui().closeGui();
                    new QuestsScreen().openGui();
                }
            }
        ));
    }

    public int getButtonsSize(){
        int size = cubeButtonWidgetList.size();
        int space = 2;
        int lastX = (space * (size - 1)) + (cubeButtonWidgetList.get(0).width * size);
        return lastX;
    }

    public int getXButtons() {
        return (this.width / 2) - (getButtonsSize() / 2);
    }

    @Override
    public void addWidgets() {
        boolean isFists = true;
        int sum = getXButtons();
        for (int i = 0; i < cubeButtonWidgetList.size(); i++) {
            CubeButtonWidget button = cubeButtonWidgetList.get(i);
            if(isFists){
                button.setPos(sum,4);
                isFists = false;
            } else {
                sum+= 2 + 48;
                button.setPos(sum,4);
            }

            if(button.getPosXRight() > this.getPosX() + this.width) {
                break;
            }

            add(button);
        }
    }

    @Override
    public void alignWidgets() {
//        int offset = 10;
//        boolean isFists = true;
//        for (int i = 0; i < widgets.size(); i++) {
//            if(widgets.get(i) instanceof CubeButtonWidget buttonWidget){
//                if(isFists) {
//                    buttonWidget.setPos(0,4);
//                    isFists = false;
//                } else {
//                    buttonWidget.setPos(widgets.get(i - 1).getX() + 2 + widgets.get(i - 1).width, widgets.get(i - 1).getY());
//                }
//            }
//        }
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        NordColors.FROST_0.draw(graphics,x,y,w,h);
        NordColors.RED.draw(graphics, x + getXButtons() - 2, y + 2, getButtonsSize() + 4, 52);
    }
}
