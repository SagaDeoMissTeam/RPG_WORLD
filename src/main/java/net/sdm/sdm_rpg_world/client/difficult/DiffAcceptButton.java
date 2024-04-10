package net.sdm.sdm_rpg_world.client.difficult;

import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.SimpleTextButton;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.core.difficults.ISelecteble;
import net.sdm.sdm_rpg_world.net.SendDataC2S;

import java.util.Map;

public class DiffAcceptButton extends SimpleTextButton {
    public MainPanelDif mainPanelDif;
    public DiffAcceptButton(MainPanelDif panel) {
        super(panel, Component.literal("Accept"), Icon.empty());
        this.mainPanelDif = panel;
    }

    @Override
    public boolean renderTitleInCenter() {
        return true;
    }

    @Override
    public void onClicked(MouseButton mouseButton) {
        if(mouseButton.isLeft()){
            boolean isFind = false;
            for (int i = 0; i < mainPanelDif.mainScreen.difficultList.size(); i++) {
                if(mainPanelDif.mainScreen.selectebles.containsKey(i)){
                    isFind =true;
                    break;
                }
            }
            if(isFind) {
                CompoundTag nbt = new CompoundTag();
                for (Map.Entry<Integer, ISelecteble> integerISelectebleEntry : mainPanelDif.mainScreen.selectebles.entrySet()) {
                    nbt.merge(integerISelectebleEntry.getValue().getNbt());
                }
                new SendDataC2S(nbt).sendToServer();
                parent.getGui().closeGui();
            }
        }
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        NordColors.POLAR_NIGHT_0.draw(graphics,x,y,w,h);
    }
}
