package net.sdm.sdm_rpg_world.client.difficult;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.*;
import dev.ftb.mods.ftblibrary.ui.input.Key;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.client.BaseDeficultParamPanel;
import net.sdm.sdm_rpg_world.client.lore.SDMScreen;
import net.sdm.sdm_rpg_world.core.difficults.DifficultBase;
import net.sdm.sdm_rpg_world.core.difficults.DifficultProperty;
import net.sdm.sdm_rpg_world.core.difficults.ISelecteble;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainSelectDifficultScreen extends SDMScreen {
    @Override public boolean isDefaultScrollVertical() {return false;}
    public List<DifficultBase> difficultList = new ArrayList<>();
    public List<DifficultProperty> difficultProperties = new ArrayList<>();
    public Map<Integer, ISelecteble> selectebles = new HashMap<>();
    public Minecraft mc = Minecraft.getInstance();
    public MainPanelDif dif = new MainPanelDif(this);

    public int wightDifficult = 125;
    public int heightDifficult = 240;


    public MainSelectDifficultScreen(){

    }


    @Override
    public boolean onInit() {
        difficultList.clear();
        setWidth(getScreen().getGuiScaledWidth());
        setHeight(getScreen().getGuiScaledHeight());
        difficultList.add(new DifficultBase(Component.translatable("sdm.rpg.easy_mode"), Component.translatable("sdm.rpg.easy_mode.description"), 1).setColor(Color4I.rgb(84,252,252)).setNBT("easy_mode"));
        difficultList.add(new DifficultBase(Component.translatable("sdm.rpg.medium_mode"), Component.translatable("sdm.rpg.medium_mode.description"),2).setColor(Color4I.rgb(252,168,0)).setNBT("medium_mode"));
        difficultList.add(new DifficultBase(Component.translatable("sdm.rpg.hard_mode"), Component.translatable("sdm.rpg.hard_mode.description"), 3).setColor(Color4I.rgb(168,0,0)).setNBT("hard_mode"));
        difficultList.add(new DifficultBase(Component.translatable("sdm.rpg.madness_mode"), Component.translatable("sdm.rpg.madness_mode.description"), "madness_mode", 4).setNBT("madness_mode"));
        return true;
    }



    @Override
    public void addWidgets() {
        dif.setSize(getScreen().getGuiScaledWidth(), getScreen().getGuiScaledHeight());
        add(dif);
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {

    }

    @Override
    public void drawOffsetBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
    }

    public int getXCenter(){
        return getXCenter(0);
    }
    public int getXCenter(int w){
        int screenCenter = getScreen().getGuiScaledWidth() / 2;
        return screenCenter - (w / 2);
    }
    public int getXCenterPos(){
        if(difficultList.isEmpty()) return 0;
        int w = wightDifficult * getUnlockedDifficult() + (2 * getUnlockedDifficult());
        int wCenter = w / 2;
        int screenCenter = getScreen().getGuiScaledWidth() / 2;
        return screenCenter - wCenter;
    }

    public int getYCenterPos(){
        int screenCenter = getScreen().getGuiScaledHeight() / 2;
        int h = heightDifficult / 2;
        return screenCenter - h;
    }

    public int getUnlockedDifficult(){
        int i = 0;
        for (DifficultBase difficultBase : difficultList) {
            if(difficultBase.unlock.isEmpty() || ExpandEntity.getCustomData(mc.player).getInternal().contains(difficultBase.unlock)){
                i++;
            }
        }
        return i;
    }

    @Override
    public boolean onClosedByKey(Key key) {
        return false;
    }

    public static class PropertyScreen extends BaseScreen {
        @Override public boolean drawDefaultBackground(GuiGraphics graphics) {return false;}

        public int size = 80;
        public MainSelectDifficultScreen mainScreen;
        public List<Widget> propertyList = new ArrayList<>();

        public PropertyMainPanel propertyMainPanel;
        public PropertyScreen(MainSelectDifficultScreen screen) {
            this.mainScreen = screen;

        }

        @Override
        public boolean onInit() {
            setWidth(mainScreen.getScreen().getGuiScaledWidth());
            setHeight(mainScreen.getScreen().getGuiScaledHeight());
            mainScreen.difficultProperties.clear();
            propertyList.clear();

            propertyMainPanel = new PropertyMainPanel(this);
            propertyMainPanel.setSize(width,height);

            int id = mainScreen.difficultList.size() + 1;
            CompoundTag nbt = new CompoundTag();
            nbt.putBoolean("oredrop", true);
            mainScreen.difficultProperties.add(new DifficultProperty(id++, Component.translatable("sdm.rpg.advanced.oredrop"), Component.translatable("sdm.rpg.advanced.oredrop.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("randomspawndimension", true);
            mainScreen.difficultProperties.add(new DifficultProperty(id++, Component.translatable("sdm.rpg.advanced.randomspawndimension"), Component.translatable("sdm.rpg.advanced.randomspawndimension.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("randomdimension", true);
            mainScreen.difficultProperties.add(new DifficultProperty(id++, Component.translatable("sdm.rpg.advanced.randomdimension"), Component.translatable("sdm.rpg.advanced.randomdimension.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("missskill", true);
            mainScreen.difficultProperties.add(new DifficultProperty(id++, Component.translatable("sdm.rpg.advanced.monsters.missskill"), Component.translatable("sdm.rpg.advanced.monsters.missskill.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("random_start_kit", true);
            mainScreen.difficultProperties.add(new DifficultProperty(id++, Component.translatable("sdm.rpg.advanced.random_start_kit"), Component.translatable("sdm.rpg.advanced.random_start_kit.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("memory_of_monsters", true);
            mainScreen.difficultProperties.add(new DifficultProperty(id++, Component.translatable("sdm.rpg.advanced.memory_of_monsters"), Component.translatable("sdm.rpg.advanced.memory_of_monsters.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("thirsty", true);
            mainScreen.difficultProperties.add(new DifficultProperty(id++, Component.translatable("sdm.rpg.advanced.thirsty"), Component.translatable("sdm.rpg.advanced.thirsty.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("sanity", true);
            mainScreen.difficultProperties.add(new DifficultProperty(id++, Component.translatable("sdm.rpg.advanced.sanity"), Component.translatable("sdm.rpg.advanced.sanity.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("plot", true);
            mainScreen.difficultProperties.add(new DifficultProperty(id++, Component.translatable("sdm.rpg.advanced.plot"), Component.translatable("sdm.rpg.advanced.plot.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("progress", true);
            mainScreen.difficultProperties.add(new DifficultProperty(id++, Component.translatable("sdm.rpg.advanced.progress"), Component.translatable("sdm.rpg.advanced.progress.desription"), nbt));



            for (DifficultProperty difficultProperty : mainScreen.difficultProperties) {
                propertyList.add(new PropertyButtonWidget(propertyMainPanel, difficultProperty));
            }
            return true;
        }

        public static class PropertyMainPanel extends BlankPanel {
            public PropertyScreen mainScreen;
            public Button backButton;

            public PropertyMainPanel(PropertyScreen panel) {
                super(panel);
                this.mainScreen = panel;
            }

            @Override
            public void addWidgets() {
                add(backButton = new SimpleTextButton(this, Component.literal("Back"), Icon.empty()) {
                    @Override
                    public void onClicked(MouseButton mouseButton) {
                        if(mouseButton.isLeft()){
                            mainScreen.closeGui();
                        }
                    }

                    @Override
                    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
                        NordColors.POLAR_NIGHT_0.draw(graphics, x, y, w, h);
                    }
                });
                backButton.setSize(40, 16);

                boolean isfist = true;
                for (int i = 0; i < mainScreen.propertyList.size(); i++) {
                    Widget widget = mainScreen.propertyList.get(i);
                    widget.setSize(mainScreen.size, mainScreen.size);
                    if(isfist) {
                        widget.setPos(getStartX(), 2);
                        isfist = false;
                    } else{
                        if(mainScreen.propertyList.get(i - 1).getX() + mainScreen.size + 1 + mainScreen.size > width){
                            widget.setPos(getStartX(), mainScreen.propertyList.get(i - 1).getY() + mainScreen.size + 1);
                        } else {
                            widget.setPos(mainScreen.propertyList.get(i - 1).getX() + mainScreen.size + 1, mainScreen.propertyList.get(i - 1).getY());
                        }
                    }
                    add(widget);
                }
            }

            public int getStartX(){
                int count = (width / (mainScreen.size + 1));
                return (width / 2) - ((mainScreen.size + 1) * count) / 2;
            }


            @Override
            public void alignWidgets() {

            }
        }

        @Override
        public void addWidgets() {
            add(propertyMainPanel);
        }

        @Override
        public void alignWidgets() {

        }

        @Override
        public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
            NordColors.POLAR_NIGHT_0.withAlpha(180).draw(graphics,x,y,w,h);
        }
    }
}
