package net.sdm.sdm_rpg_world.client;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.*;
import dev.ftb.mods.ftblibrary.ui.input.Key;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.SoundType;
import net.sdm.sdm_rpg_world.net.SendDataC2S;

public class BaseDeficultPanel extends Panel {
    public Button selectButton;
    public TextField textBox;
    public CompoundTag nbt;
    public Component Name;
    public Component description;

    public SelectDifficultScreen.SPanel mPanel;
    public BaseDeficultPanel(SelectDifficultScreen.SPanel panel, int w, int h, CompoundTag nbt, Component Name, Component description) {
        super(panel);
        this.setSize(w, h);
        this.nbt = nbt;
        this.Name = Name;
        this.description = description;
        mPanel = panel;
    }

    @Override
    public void addWidgets() {
        selectButton = new SimpleTextButton(this, Name, Icon.empty()) {
            @Override
            public void onClicked(MouseButton mouseButton) {
                if(mouseButton.isLeft()){
                    CompoundTag mainN = new CompoundTag();
                    if(!mPanel.mainScreen.selectedFunc.isEmpty()) {
                        mPanel.mainScreen.selectedFunc.forEach(s -> {
                            nbt.merge(s);

                        });
                    }
                    mainN.put("gameSetting", nbt);
                    new SendDataC2S(mainN).sendToServer();
                    parent.getGui().closeGui();
                }
            }

            @Override
            public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
                Color4I.fromString("#3E1317").draw(graphics, x + 1, y + 1, w - 2, h - 2);
                GuiHelper.drawHollowRect(graphics, x, y, w, h, Color4I.fromString("#947657"), false);
            }
        };
        textBox = new TextField(this){
        };

        add(selectButton);
        add(textBox);
    }



    @Override
    public void alignWidgets() {
        selectButton.setPos( 2, 2);
        selectButton.setSize(this.width - 4,14);
        textBox.setPos(4, 20);
        switch ((int) Minecraft.getInstance().options.guiScale().get()){
            case 0:
                textBox.setScale(0.64f);
                break;
            case 1:
                textBox.setScale(1.02f);
                break;
            case 2:
                textBox.setScale(1.02f);
                break;
            case 3:
                textBox.setScale(0.66f);
                break;
            case 4:
                textBox.setScale(0.52f);
                break;
            default:
                textBox.setScale(0.72f);
        }
        textBox.setWidth((int) ((this.width - 4)));
        textBox.setHeight(this.height);
        textBox.setMaxWidth((int) ((this.width - 4)));
        textBox.setText(description);

    }

    @Override
    public void refreshWidgets() {
        widgets.clear();
        addWidgets();
        alignWidgets();
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        GuiHelper.drawHollowRect(graphics, x, y, w, h, Color4I.fromString("#947657"), false);
    }
}
