package net.sdm.sdm_rpg_world.client;

import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.*;
import dev.ftb.mods.ftblibrary.ui.input.Key;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import dev.ftb.mods.ftblibrary.util.TooltipList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class SelectDifficultScreen extends BaseScreen {
    public List<CompoundTag> selectedFunc = new ArrayList<>();
    public SPanel panel;
    public TextField field;
    public AdvancedPanel advancedPanel;
    public AddSettingsButton settingsButton;
    public boolean openedAdvanced = false;
    @Override
    public boolean onInit() {
        setWidth(getScreen().getGuiScaledWidth() * 4/5);
        setHeight(getScreen().getGuiScaledHeight() * 4/5);
        return true;
    }

    @Override
    public void addWidgets() {
        if(openedAdvanced) {
            add(settingsButton = new AddSettingsButton(this,75,14));
            add(advancedPanel = new AdvancedPanel(this, width, height));
        } else {
            add(settingsButton = new AddSettingsButton(this,75,14));
            add(panel = new SPanel(this, width, height));
        }
        field = new TextField(this).setText(Component.translatable("sdm.rpg.tittle"));
        add(field);
    }

    @Override
    public void alignWidgets() {
        settingsButton.setPos((((this.width / 2) - (settingsButton.width / 2))), this.height);
        field.setPos((width / Minecraft.getInstance().font.width(I18n.get("sdm.rpg.tittle"))), -Minecraft.getInstance().font.lineHeight);
    }
    @Override
    public boolean keyPressed(Key key) {
        if(key.keyCode != GLFW.GLFW_KEY_ESCAPE) return true;
        return super.keyPressed(key);
    }


    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        Color4I.fromString("#3E1317").draw(graphics, x + 1, y + 1, w - 2, h - 2);
        GuiHelper.drawHollowRect(graphics, x, y, w, h, Color4I.fromString("#947657"), false);
    }

    //--------------------------------------------------------------------------------------------------
    public class SPanel extends Panel{
        public List<BaseDeficultPanel> difficulty = new ArrayList<>();
        public SelectDifficultScreen mainScreen;
        public SPanel(SelectDifficultScreen panel, int w, int h) {
            super(panel);
            setSize(w,h);
            this.mainScreen = panel;
        }

        @Override
        public void addWidgets() {
            CompoundTag nt = new CompoundTag();
            nt.putBoolean("easy_mode", true);
            BaseDeficultPanel b1 = new BaseDeficultPanel(this, width,height,nt, Component.translatable("sdm.rpg.easy_mode"), Component.translatable("sdm.rpg.easy_mode.description"));
            difficulty.add(b1);
            add(b1);

            nt = new CompoundTag();
            nt.putBoolean("medium_mode", true);
            b1 = new BaseDeficultPanel(this, width,height,nt, Component.translatable("sdm.rpg.medium_mode"), Component.translatable("sdm.rpg.medium_mode.description"));
            difficulty.add(b1);
            add(b1);

            nt = new CompoundTag();
            nt.putBoolean("hard_mode", true);
            b1 = new BaseDeficultPanel(this, width,height,nt, Component.translatable("sdm.rpg.hard_mode"), Component.translatable("sdm.rpg.hard_mode.description"));
            difficulty.add(b1);
            add(b1);

            CompoundTag nbt = ExpandEntity.getCustomData(Minecraft.getInstance().player).getInternal();
            if(nbt.contains("madness_mode")) {
                nt = new CompoundTag();
                nt.putBoolean("madness_mode", true);
                b1 = new BaseDeficultPanel(this, width, height, nt, Component.translatable("sdm.rpg.madness_mode"), Component.translatable("sdm.rpg.madness_mode.description"));
                difficulty.add(b1);
                add(b1);
            }
        }

        @Override
        public void alignWidgets() {
            boolean isFirst = true;
            for (int i = 0; i < widgets.size(); i++) {
                Widget widget = widgets.get(i);
                if(widget instanceof BaseDeficultPanel panel){
                    panel.setWidth((this.width / difficulty.size()) + 1);
                    panel.setHeight(this.height);
                    if(isFirst){
                        panel.setPos(0,0);
                        isFirst = false;
                    } else {
                        panel.setPos(widgets.get(i - 1).getPosX() + widgets.get(i - 1).width - 1,0);
                    }
                }
            }
        }
    }

    //--------------------------------------------------------------------------------------------------
    public class AdvancedPanel extends Panel{
        public SelectDifficultScreen screenMain;
        public AdvancedPanel(SelectDifficultScreen panel, int w, int h) {
            super(panel);
            setSize(w,h);
            this.screenMain = panel;
        }

        @Override
        public void addWidgets() {
            CompoundTag nbt = new CompoundTag();
            nbt.putBoolean("oredrop", true);
            add(new BaseDeficultParamPanel(this, width,14, Component.translatable("sdm.rpg.advanced.oredrop"), Component.translatable("sdm.rpg.advanced.oredrop.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("randomspawndimension", true);
            add(new BaseDeficultParamPanel(this, width,14, Component.translatable("sdm.rpg.advanced.randomspawndimension"), Component.translatable("sdm.rpg.advanced.randomspawndimension.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("randomdimension", true);
            add(new BaseDeficultParamPanel(this, width,14, Component.translatable("sdm.rpg.advanced.randomdimension"), Component.translatable("sdm.rpg.advanced.randomdimension.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("missskill", true);
            add(new BaseDeficultParamPanel(this, width,14, Component.translatable("sdm.rpg.advanced.monsters.missskill"), Component.translatable("sdm.rpg.advanced.monsters.missskill.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("random_start_kit", true);
            add(new BaseDeficultParamPanel(this, width,14, Component.translatable("sdm.rpg.advanced.random_start_kit"), Component.translatable("sdm.rpg.advanced.random_start_kit.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("memory_of_monsters", true);
            add(new BaseDeficultParamPanel(this, width,14, Component.translatable("sdm.rpg.advanced.memory_of_monsters"), Component.translatable("sdm.rpg.advanced.memory_of_monsters.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("thirsty", true);
            add(new BaseDeficultParamPanel(this, width,14, Component.translatable("sdm.rpg.advanced.thirsty"), Component.translatable("sdm.rpg.advanced.thirsty.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("sanity", true);
            add(new BaseDeficultParamPanel(this, width,14, Component.translatable("sdm.rpg.advanced.sanity"), Component.translatable("sdm.rpg.advanced.sanity.desription"), nbt));
            nbt = new CompoundTag();
            nbt.putBoolean("plot", true);
            add(new BaseDeficultParamPanel(this, width,14, Component.translatable("sdm.rpg.advanced.plot"), Component.translatable("sdm.rpg.advanced.plot.desription"), nbt));
//            nbt = new CompoundTag();
//            nbt.putBoolean("random_start_kit", true);
//            add(new BaseDeficultParamPanel(this, width,14, Component.translatable("sdm.rpg.advanced.random_start_kit"), Component.translatable("sdm.rpg.advanced.random_start_kit.desription"), nbt));

        }

        @Override
        public void alignWidgets() {

            boolean isFirst = true;
            for (int i = 0; i < widgets.size(); i++) {
                Widget widget = widgets.get(i);
                if(isFirst){
                    widget.setPos(2,2);
                    isFirst =false;
                } else {
                    widget.setPos(widgets.get(i - 1).posX,widgets.get(i - 1).posY + widget.height);
                }
            }
        }
    }

    //--------------------------------------------------------------------------------------------------
    public class AddSettingsButton extends Panel {
        public Button button;
        public SelectDifficultScreen panelS;
        public AddSettingsButton(SelectDifficultScreen panel, int w, int h) {
            super(panel);
            setSize(w,h);
            panelS = panel;
        }

        @Override
        public void addWidgets() {
            add(button = new SimpleTextButton(this, Component.translatable("sdm.rpg.additions"), Icon.empty()) {
                @Override
                public void onClicked(MouseButton mouseButton) {
                    if(mouseButton.isLeft()){
                        if(SelectDifficultScreen.this.openedAdvanced){
                            SelectDifficultScreen.this.openedAdvanced = !SelectDifficultScreen.this.openedAdvanced;
                            SelectDifficultScreen.this.refreshWidgets();
                        } else {
                            SelectDifficultScreen.this.openedAdvanced = !SelectDifficultScreen.this.openedAdvanced;
                            SelectDifficultScreen.this.refreshWidgets();
                        }
                    }
                }

                @Override
                public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
                    Color4I.fromString("#3E1317").draw(graphics, x + 1, y + 1, w - 2, h - 2);
                    GuiHelper.drawHollowRect(graphics, x, y, w, h, Color4I.fromString("#947657"), false);
                }

                @Override
                public void addMouseOverText(TooltipList list) {
                    list.add(Component.translatable("sdm.rpg.additions.description"));
                }

            });
        }

        @Override
        public void onClosed() {
            super.onClosed();
        }

        @Override
        public void alignWidgets() {
            button.setSize(this.width, this.height);
        }
    }
}
