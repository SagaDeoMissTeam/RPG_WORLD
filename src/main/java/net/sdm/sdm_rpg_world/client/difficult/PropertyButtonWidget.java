package net.sdm.sdm_rpg_world.client.difficult;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.*;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import dev.ftb.mods.ftblibrary.util.TooltipList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.sdm.sdm_rpg_world.client.widget.SDMTextField;
import net.sdm.sdm_rpg_world.core.difficults.DifficultProperty;

public class PropertyButtonWidget extends Panel {
    public DifficultProperty property;
    public MainSelectDifficultScreen.PropertyScreen.PropertyMainPanel mainPanel;

    public Button selectButton;

    public TextField tittleTextField;
    public PropertyButtonWidget(MainSelectDifficultScreen.PropertyScreen.PropertyMainPanel panel, DifficultProperty property) {
        super(panel);
        this.property = property;
        this.mainPanel = panel;
    }

    @Override
    public void addMouseOverText(TooltipList list) {
        list.add(property.subtitle);
    }


    @Override
    public void addWidgets() {
        add(tittleTextField = new TextField(this));
        selectButton = new SimpleTextButton(this, Component.literal("Select"), Icon.empty()) {

            @Override
            public void onClicked(MouseButton mouseButton) {
                if(mouseButton.isLeft()){
                    if(mainPanel.mainScreen.mainScreen.selectebles.containsKey(property.id)){
                        mainPanel.mainScreen.mainScreen.selectebles.remove(property.id);
                        refreshWidgets();
                    } else {
                        for (int i = 0; i < mainPanel.mainScreen.mainScreen.difficultProperties.size(); i++) {
                            var d1 = mainPanel.mainScreen.mainScreen.difficultProperties.get(i);
                            if(d1.id == property.id){
                                property.setSelected(true);
                                mainPanel.mainScreen.mainScreen.selectebles.put(property.id, property);
                                refreshWidgets();
                                return;
                            }
                        }
                    }
                }
            }

            @Override
            public void draw(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
                this.drawBackground(graphics, theme, x, y, w, h);
                int s = h >= 16 ? 16 : 8;
                int off = (h - s) / 2;
                FormattedText title = this.getTitle();
                int textY = y + (h - theme.getFontHeight() + 1) / 2;
                int sw = theme.getStringWidth((FormattedText)title);
                int mw = w - (this.hasIcon() ? off + s : 0) - 6;
                if (sw > mw) {
                    sw = mw;
                    title = theme.trimStringToWidth((FormattedText)title, mw);
                }

                int textX;
                if (this.renderTitleInCenter()) {
                    textX = x + (mw - sw + 6) / 2;
                } else {
                    textX = x + 4;
                }

                if (this.hasIcon()) {
                    this.drawIcon(graphics, theme, x + off, y + off, s, s);
                    textX += off + s;
                }

                if(isMouseOver || property.isSelected() || mainPanel.mainScreen.mainScreen.selectebles.containsKey(property.id)){
                    theme.drawString(graphics, title, textX, textY, Color4I.BLACK, 2);
                } else {
                    theme.drawString(graphics, title, textX, textY, Color4I.WHITE, 2);
                }
            }

            @Override
            public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
                NordColors.POLAR_NIGHT_4.draw(graphics,x,y,w,h);
            }

            @Override
            public boolean renderTitleInCenter() {
                return true;
            }
        };
        if(mainPanel.mainScreen.mainScreen.selectebles.containsKey(property.id)){
            selectButton.setTitle(Component.literal("Selected"));
        } else {
            selectButton.setTitle(Component.literal("Select"));
        }
        add(selectButton);

    }

    @Override
    public void alignWidgets() {
        tittleTextField.setPos(0,2);
        tittleTextField.setMaxWidth(this.width);
        tittleTextField.setText(property.title);
        tittleTextField.resize(Theme.DEFAULT);

        selectButton.setPos(2, height - 18);
        selectButton.setSize(width - 4, 16);
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        NordColors.POLAR_NIGHT_0.draw(graphics,x,y,w,h);
        NordColors.POLAR_NIGHT_1.draw(graphics, x + 2, y + tittleTextField.height + 3, w - 4,1);
    }

    @Override
    public void drawOffsetBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {

    }
}
