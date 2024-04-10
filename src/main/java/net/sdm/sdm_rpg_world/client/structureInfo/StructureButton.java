package net.sdm.sdm_rpg_world.client.structureInfo;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.*;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import dev.ftb.mods.ftblibrary.util.TooltipList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.client.SDMIcons;
import net.sdm.sdm_rpg_world.core.rarity.RarityType;
import net.sdm.sdm_rpg_world.core.structure.StructureBase;

public class StructureButton extends SimpleTextButton {
    public boolean isSelected = false;
    public StructureBase structureBase;
    public StructureScreen.StructuresPanel structuresPanel;
    public StructureButton(BlankPanel panel, Component txt, StructureBase structureBase) {
        super(panel, txt, Icon.empty());
        this.structureBase = structureBase;
        this.structuresPanel = (StructureScreen.StructuresPanel) panel.getParent();
        setTitle(structureBase.structureString());
    }

    @Override
    public void onClicked(MouseButton mouseButton) {
        if(mouseButton.isLeft()){
            isSelected = !isSelected;
            if(parent.getParent() instanceof StructureScreen.StructuresPanel panel){
                if(panel.selected == null){
                    panel.selected = this;
                } else if(panel.selected != this){
                    panel.selected.isSelected = false;
                    panel.selected = this;
                } else if(!isSelected){
                    panel.selected = null;
                }
                structuresPanel.screen.structureInfoPanel.refreshWidgets();
            }
        }
    }

    @Override
    public void addMouseOverText(TooltipList list) {

    }

    @Override
    public void draw(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        this.drawBackground(graphics, theme, x, y, w, h);
        Minecraft mc = Minecraft.getInstance();
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
        theme.drawString(graphics, title, textX, y, theme.getContentColor(this.getWidgetType()), 2);
        theme.drawString(graphics, theme.trimStringToWidth((FormattedText)Component.literal("Difficult:"), mw), textX, y + 4 + (mc.font.lineHeight), theme.getContentColor(this.getWidgetType()), 2);

        int difficultMax = RarityType.values().length - 1;
        int f = (int) (2 + mc.font.getSplitter().stringWidth("Difficult:") + 4);
        int difficultCount = 0;
        for (int i = 0; i < RarityType.values().length; i++) {
            if (RarityType.values()[i].equals(structureBase.rarityBase.type)) {
                difficultCount = i;
                break;
            }
        }
        int gCol = 255;

        for (int i = 0; i < difficultMax; i++) {
            if(i <= difficultCount && difficultCount != 0) {
                SDMIcons.STAR_ICON.withColor(Color4I.rgb(255, gCol, 0)).draw(graphics, (int) f, y + 3 + mc.font.lineHeight, 10, 10);
                gCol -= 50;
            } else {
                SDMIcons.STAR_ICON.withColor(Color4I.GRAY).draw(graphics, (int) f, y + 3 + mc.font.lineHeight, 10, 10);
            }
            f += 12;
        }

    }



    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        Color4I.BLACK.withAlpha(180).draw(graphics, x + 1, y + 1, w - 2, h - 2);
        if(isSelected || isMouseOver()) {
            GuiHelper.drawHollowRect(graphics, x, y, w, h, Color4I.WHITE.withAlpha(255), false);
        }
    }
}
