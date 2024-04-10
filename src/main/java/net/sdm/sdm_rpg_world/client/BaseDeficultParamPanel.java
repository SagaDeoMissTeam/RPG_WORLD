package net.sdm.sdm_rpg_world.client;

import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.TextField;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.WidgetType;
import dev.ftb.mods.ftblibrary.util.TooltipList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.client.widget.CheckBox;

public class BaseDeficultParamPanel extends Panel {
    public Component name;
    public Component description;
    public CompoundTag nbt;
    public CheckBox checkBox;

    public TextField field;

    public SelectDifficultScreen.AdvancedPanel panel;
    public BaseDeficultParamPanel(SelectDifficultScreen.AdvancedPanel panel, int w, int h, Component name, CompoundTag nbt) {
       this(panel,w,h,name,Component.empty(), nbt);
    }
    public BaseDeficultParamPanel(SelectDifficultScreen.AdvancedPanel panel, int w, int h, Component name, Component description, CompoundTag nbt) {
        super(panel);
        setSize(w,h);
        this.name = name;
        this.description = description;
        this.nbt = nbt;
        this.panel = panel;
    }

    @Override
    public void addWidgets() {
        add(checkBox = new CheckBox(this));
        add(field = new TextField(this).setText(name).setMaxWidth(width).setScale(0.8f));

        if(panel.screenMain.selectedFunc.contains(this.nbt)){
            checkBox.isClick = true;
        }
    }

    @Override
    public void draw(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        super.draw(graphics, theme, x, y, w, h);
    }

    @Override
    public void addMouseOverText(TooltipList list) {
        list.add(description);
    }

    @Override
    public void alignWidgets() {
        checkBox.setSize(10,10);
        checkBox.setPos(this.width - checkBox.width - 8,0);
    }
}
