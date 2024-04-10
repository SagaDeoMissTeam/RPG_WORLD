package net.sdm.sdm_rpg_world.client.structureInfo.infoPanel;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.*;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.sdm.engine_core.SDMEngineCore;
import net.sdm.engine_core.api.logger.SDMLogger;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.client.SDMIcons;
import net.sdm.sdm_rpg_world.core.buffs.IBuff;
import net.sdm.sdm_rpg_world.core.rarity.RarityType;

public class BaseStructurePropertyPanel extends Panel {
    public IBuff buff;
    public BaseStructurePropertyPanel(Panel panel,int w, int h, IBuff buff) {
        super(panel);
        setSize(w,h);
        this.buff = buff;
    }

    @Override
    public void addWidgets() {

    }

    @Override
    public void alignWidgets() {
    }

    @Override
    public void draw(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        super.draw(graphics, theme, x, y, w, h);
        theme.drawString(graphics, buff.toText(), x+ 4, y, theme.getContentColor(this.getWidgetType()), 2);

    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        Color4I.BLACK.withAlpha(180).draw(graphics, x, y, w, h);
        GuiHelper.drawHollowRect(graphics, x, y, w, h, Color4I.DARK_GRAY.withAlpha(180), false);
    }
}
