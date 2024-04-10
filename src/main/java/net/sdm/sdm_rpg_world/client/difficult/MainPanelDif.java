package net.sdm.sdm_rpg_world.client.difficult;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.SimpleButton;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.client.SDMIcons;
import net.sdm.sdm_rpg_world.core.difficults.DifficultBase;

public class MainPanelDif extends Panel {

    public MainSelectDifficultScreen mainScreen;
    public DiffAcceptButton acceptButton;
    public SimpleButton settingButton;
    public MainPanelDif(MainSelectDifficultScreen panel) {
        super(panel);
        this.mainScreen = panel;
    }

    @Override
    public void addWidgets() {
        boolean isFirst = true;
        int d1 = 0;
        for (int i = 0; i < mainScreen.difficultList.size(); i++) {
            DifficultPanel difficultPanel = new DifficultPanel(this, mainScreen.difficultList.get(i), mainScreen.wightDifficult, mainScreen.heightDifficult, mainScreen.difficultList.get(i).tittle);
            if(isFirst) {
                difficultPanel.setPos(getXCenterPos(), getYCenterPos());
                isFirst = false;
            } else {
                difficultPanel.setPos(mainScreen.getXCenterPos() + (mainScreen.wightDifficult * d1) + (d1), getYCenterPos());
            }
            if(mainScreen.difficultList.get(i).unlock.isEmpty() || ExpandEntity.getCustomData(mainScreen.mc.player).getInternal().contains(mainScreen.difficultList.get(i).unlock)) {
                d1++;
                add(difficultPanel);
            }
        }
        add(acceptButton = new DiffAcceptButton(this));
        add(settingButton = new SimpleButton(this, Component.empty(), SDMIcons.SETTINGS, ((simpleButton, mouseButton) ->
            {
                if(mouseButton.isLeft()){
                    new MainSelectDifficultScreen.PropertyScreen(mainScreen).openGui();
                }
            }
        )){
            @Override
            public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
                NordColors.POLAR_NIGHT_0.draw(graphics, x, y, w, h);
            }
        });
    }

    @Override
    public void alignWidgets() {
        acceptButton.setSize(120, 18);
        acceptButton.setPos(getXCenter(120), getYPos(mainScreen.heightDifficult + 20));
        settingButton.setPos(getXCenter(0) - 1 + 120 / 2, 12);
        settingButton.setSize(18, 18);
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        NordColors.POLAR_NIGHT_0.draw(graphics, getXCenter(120) - 2, 12, 120, 18);
//        Color4I.RED.draw(graphics,getXCenter(0) - 1 + 120 / 2, 12, 18, 18);
//        SDMIcons.SETTINGS.draw(graphics,getXCenter(0) - 1 + 120 / 2 + 1, 13, 16,16);

    }

    @Override
    public void drawOffsetBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        theme.drawString(graphics, Component.literal("ВЫБЕРИТЕ СЛОЖНОСТЬ"), (int) (x + (w / 2) - (mainScreen.mc.font.getSplitter().stringWidth("ВЫБЕРИТЕ СЛОЖНОСТЬ") / 2)), 16);
    }

    public int getXCenter(){
        return getXCenter(0);
    }
    public int getXCenter(int w){
        int screenCenter = getScreen().getGuiScaledWidth() / 2;
        return screenCenter - (w / 2);
    }
    public int getXCenterPos(){
        if(mainScreen.difficultList.isEmpty()) return 0;
        int w = mainScreen.wightDifficult * getUnlockedDifficult() + (2 * getUnlockedDifficult());
        int wCenter = w / 2;
        int screenCenter = getScreen().getGuiScaledWidth() / 2;
        return screenCenter - wCenter;
    }

    public int getYCenterPos(){
        int screenCenter = getScreen().getGuiScaledHeight() / 2;
        int h = mainScreen.heightDifficult / 2;
        return screenCenter - h;
    }

    public int getYPos(int y){
        int result = getYCenterPos() + y;
        if(result > height) return height;
        return result;
    }

    public int getUnlockedDifficult(){
        int i = 0;
        for (DifficultBase difficultBase : mainScreen.difficultList) {
            if(difficultBase.unlock.isEmpty() || ExpandEntity.getCustomData(mainScreen.mc.player).getInternal().contains(difficultBase.unlock)){
                i++;
            }
        }
        return i;
    }
}
