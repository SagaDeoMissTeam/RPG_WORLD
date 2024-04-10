package net.sdm.sdm_rpg_world.client.difficult;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.SimpleTextButton;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.sdm.sdm_rpg_world.core.difficults.DifficultBase;
import net.sdm.sdm_rpg_world.core.difficults.ISelecteble;

public class SelectDiffButton extends SimpleTextButton {
    public DifficultPanel difficultPanel;

    public SelectDiffButton(DifficultPanel panel, Component txt, Icon icon) {
        super(panel, txt, icon);
        this.difficultPanel = panel;
    }

    @Override
    public void onClicked(MouseButton mouseButton) {
        if(mouseButton.isLeft()){
            for (DifficultBase difficultBase : difficultPanel.parentScreen.difficultList) {
                if(difficultPanel.parentScreen.selectebles.containsKey(difficultBase.id)){
                    difficultBase.isSelected = false;
                    difficultPanel.parentScreen.selectebles.remove(difficultBase.id);
                    difficultPanel.refreshWidgets();
                }
            }

            difficultPanel.base.setSelected(true);
            difficultPanel.parentScreen.selectebles.put(difficultPanel.base.id, difficultPanel.base);
            difficultPanel.refreshWidgets();

        }
    }

    @Override
    public boolean renderTitleInCenter() {
        return true;
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

        if(isMouseOver || difficultPanel.base.isSelected || difficultPanel.parentScreen.selectebles.containsKey(difficultPanel.base.id)){
            theme.drawString(graphics, Component.literal("Selected"), textX, textY, Color4I.BLACK, 2);
        } else {
            theme.drawString(graphics, Component.literal("Select"), textX, textY, Color4I.WHITE, 2);
        }
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        if(isMouseOver || difficultPanel.base.isSelected || difficultPanel.parentScreen.selectebles.containsKey(difficultPanel.base.id)){
            Color4I.WHITE.draw(graphics,x,y,w,h);
        } else {
            NordColors.POLAR_NIGHT_0.draw(graphics, x, y, w, h);
        }
    }
}
