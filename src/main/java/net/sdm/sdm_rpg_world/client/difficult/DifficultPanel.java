package net.sdm.sdm_rpg_world.client.difficult;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.*;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.client.SDMIcons;
import net.sdm.sdm_rpg_world.core.difficults.DifficultBase;
import org.w3c.dom.Text;

public class DifficultPanel extends Panel {
    public MainSelectDifficultScreen parentScreen;
    public Component tittle = Component.empty();
    public SelectDiffButton selectDiffButton;
    public TextField textField;
    public DifficultBase base;
    public DifficultPanel(MainPanelDif panel, DifficultBase base, int w, int h, Component tittle) {
        super(panel);
        this.base = base;
        this.parentScreen = panel.mainScreen;
        setSize(w,h);
        this.tittle = tittle;
        if(parentScreen.selectebles.containsKey(base.id)){
            base.setSelected(true);
        }
    }

    @Override
    public void addWidgets() {
        add(textField = new TextField(this));
        add(selectDiffButton = new SelectDiffButton(this, Component.literal("Select"), Icon.empty()));
    }

    @Override
    public void alignWidgets() {
        selectDiffButton.setPos(4, height - 18);
        selectDiffButton.setSize(width - 8, 16);
        textField.setScale(0.6f);
        textField.setMaxWidth((int) ((width - 4) + (width * 0.6f)));
        textField.setX(2);
        textField.setY(1+Theme.DEFAULT.getFontHeight() + 3);
        textField.setText(base.subtittle);
        textField.setHeight(height - selectDiffButton.height - 2);
        textField.resize(Theme.DEFAULT);
    }


    @Override
    public void drawOffsetBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        Font font = Minecraft.getInstance().font;
        theme.drawString(graphics, tittle, (int) (x + (w / 2) - (font.getSplitter().stringWidth(I18n.get(tittle.getString())) / 2)), y + 1);
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        NordColors.POLAR_NIGHT_0.draw(graphics, x, y, w, h);
        GuiHelper.drawHollowRect(graphics,x,y,w,h,base.color4I,false);
    }
}
