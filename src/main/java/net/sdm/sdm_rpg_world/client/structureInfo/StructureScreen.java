package net.sdm.sdm_rpg_world.client.structureInfo;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.icon.Icons;
import dev.ftb.mods.ftblibrary.ui.*;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.sdm.engine_core.SDMEngineCore;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.client.structureInfo.infoPanel.MainStructInfoPanel;
import net.sdm.sdm_rpg_world.core.structure.StructureBase;
import org.apache.commons.compress.archivers.sevenz.CLI;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class StructureScreen extends BaseScreen {
    @Override public boolean drawDefaultBackground(GuiGraphics graphics) {return false;}

    public StructureBase playerInStructure = SDMRpgWorld.SDMClient.structureGUI.structureBase;
    public StructuresPanel structuresPanel;
    public StructureInfoPanel structureInfoPanel;
    public StructureScreen(){
    }



    @Override
    public boolean onInit() {
        setWidth(getScreen().getGuiScaledWidth());
        setHeight(getScreen().getGuiScaledHeight());
        return true;
    }

    @Override
    public void addWidgets() {
        add(structuresPanel = new StructuresPanel(this, 160, this.height));
        structureInfoPanel = new StructureInfoPanel(this, this.width - structuresPanel.width - 1, this.height - 2);
        structureInfoPanel.setPos(this.posX + structuresPanel.width, this.posY + 1);
        add(structureInfoPanel);
    }

    @Override
    public void alignWidgets() {
        super.alignWidgets();
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        Color4I.BLACK.withAlpha(180).draw(graphics, x + 1, y + 1, w - 2, h - 2);
        GuiHelper.drawHollowRect(graphics, x, y, w, h, Color4I.BLACK.withAlpha(255), false);
    }

    public class StructureInfoPanel extends Panel{
        public StructureScreen screen;
        public MainStructInfoPanel infoPanel;
        public StructureInfoPanel(StructureScreen panel, int w, int h) {
            super(panel);
            this.screen = panel;
            setSize(w,h);
        }

        @Override
        public void addWidgets() {
            add(infoPanel = new MainStructInfoPanel(this, screen.structuresPanel.selected));
        }

        @Override
        public void alignWidgets() {

        }

        @Override
        public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
            GuiHelper.drawHollowRect(graphics, x - 1, y - 1, w + 2, h + 2, Color4I.GRAY.withAlpha(200), false);
        }
    }
    public class StructuresPanel extends Panel{
        public BlankPanel structuresList;
        public PanelScrollBar scrollBar;
        public StructureScreen screen;
        public StructureButton selected = null;
        public StructuresPanel(StructureScreen panel, int w, int h) {
            super(panel);
            this.screen = panel;
            setSize(w,h);

            this.structuresList = new BlankPanel(this){
                public void drawBackground(GuiGraphics matrixStack, Theme theme, int x, int y, int w, int h) {

                }
            };
            this.structuresList.setPosAndSize(1,1, this.width - 2, this.height - 2);
            this.scrollBar = new PanelScrollBar(this, this.structuresList){
                @Override
                public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
//                    GuiHelper.drawHollowRect(graphics, x, y, w, h, Color4I.GRAY.withAlpha(200), false);
                }

                @Override
                public void drawScrollBar(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
                    Color4I.GRAY.withAlpha(180).draw(graphics, x + 1, y + 1, w - 2, h - 2);
                    GuiHelper.drawHollowRect(graphics, x, y, w, h, Color4I.WHITE.withAlpha(255), false);
                }
            };
            this.scrollBar.setCanAlwaysScroll(true);
            this.scrollBar.setScrollStep(20.0);

            updateScroll(Collections.emptyList());
        }
        public void updateScroll(List<Widget> items){
            this.structuresList.getWidgets().clear();
            this.structuresList.addAll(items);
            this.scrollBar.setPosAndSize(this.structuresList.posX + this.structuresList.width - 4, this.structuresList.posY, 4, this.structuresList.height);
            this.scrollBar.setValue(0.0);
        }

        @Override
        public void addWidgets() {
            this.add(this.structuresList);
            this.add(this.scrollBar);


            CompletableFuture.supplyAsync(() -> {
                int i = 0;
                List<Widget> wi = new ArrayList<>();
                for (Map.Entry<BoundingBox, StructureBase> st : SDMRpgWorld.SDMClient.STRUCUTRES_DATA.entrySet()) {
                    StructureButton b = new StructureButton(structuresList, Component.empty(), st.getValue());
                    if(wi.isEmpty())
                        b.setPos(1,1);
                    else{
                        b.setPos(wi.get(i -1 ).posX, wi.get(i -1 ).posY + 26);
                    }
                    b.setSize(this.width - scrollBar.width - 2,24);
                    if(playerInStructure != null &&  st.getValue().serializeNBT().equals(playerInStructure.serializeNBT())){
                        this.selected = b;
                        b.isSelected = true;
                        structuresPanel.screen.structureInfoPanel.refreshWidgets();
                    } else {
                        this.selected = null;
                        b.isSelected = false;
                    }
                    wi.add(b);
                    i++;
                }
                return wi;
            }).thenAcceptAsync(this::updateScroll, Minecraft.getInstance());
        }

        @Override
        public void alignWidgets() {

        }

        @Override
        public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
            Color4I.BLACK.withAlpha(60).draw(graphics, x + 1, y + 1, w - 2, h - 2);
            GuiHelper.drawHollowRect(graphics, x, y, w, h, Color4I.GRAY.withAlpha(200), false);
        }
    }
}
