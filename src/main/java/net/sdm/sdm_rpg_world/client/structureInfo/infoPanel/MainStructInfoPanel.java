package net.sdm.sdm_rpg_world.client.structureInfo.infoPanel;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.icon.Icons;
import dev.ftb.mods.ftblibrary.ui.*;
import dev.ftb.mods.ftblibrary.util.TooltipList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.sdm.engine_core.SDMEngineCore;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.client.SDMIcons;
import net.sdm.sdm_rpg_world.client.structureInfo.StructureButton;
import net.sdm.sdm_rpg_world.client.structureInfo.StructureScreen;
import net.sdm.sdm_rpg_world.core.buffs.IBuff;
import net.sdm.sdm_rpg_world.core.rarity.RarityType;
import net.sdm.sdm_rpg_world.core.structure.StructureBase;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MainStructInfoPanel extends Panel {
    public StructureScreen.StructureInfoPanel structureInfoPanel;
    public InfoTittlePanel infoTittlePanel;
    public PropertyListPanel propertyListPanel;
    public @Nullable StructureBase structureBase;
    public MainStructInfoPanel(StructureScreen.StructureInfoPanel panel, @Nullable StructureButton structureBase) {
        super(panel);
        this.structureInfoPanel = panel;
        if(structureBase != null) {
            this.structureBase = structureBase.structureBase;
        }
        setSize(panel.width, panel.height);
    }


    @Override
    public void addWidgets() {
        if (structureBase != null){
            Minecraft mc = Minecraft.getInstance();
            int f = (int) (2 + mc.font.getSplitter().stringWidth("Difficult:") + 4);
            int width = 0;
            width = (int) Math.max(f + (12 * SDMRpgWorld.SDMClient.structureGUI.difficultCount), mc.font.getSplitter().stringWidth(I18n.get(structureBase.structureString().getString())));

            add(infoTittlePanel = new InfoTittlePanel(this, width, 32, structureBase));
            infoTittlePanel.setPos(0,10);
            TextField field = new TextField(this);
            field.setText(Component.translatable("sdm.structure.propertylist"));
            add(field);
            field.setPos(1, (infoTittlePanel.posY + infoTittlePanel.height + 2) + 10);
            Button button = new SimpleButton(this, Component.empty(), Icons.INFO, ((simpleButton, mouseButton) -> {

            })){
                @Override
                public void addMouseOverText(TooltipList list) {
                    list.add(Component.translatable("sdm.structure.propertylist.description"));
                }
            };
            button.setSize(10,10);
            button.setPos(field.posX + 1 + (int) mc.font.getSplitter().stringWidth(I18n.get("sdm.structure.propertylist")), field.posY - 1);
            add(button);
            add(propertyListPanel = new PropertyListPanel(this,Math.min(240, this.width / 2), (int) ((this.height - infoTittlePanel.height - 2) / 2), structureBase));
            propertyListPanel.setPos(0, (infoTittlePanel.posY + infoTittlePanel.height + 2) + 20);
        }
    }

    @Override
    public void alignWidgets() {

    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {

    }

    public class InfoTittlePanel extends Panel{
        public StructureBase structureBase;
        public InfoTittlePanel(Panel panel, int w, int h, @Nullable StructureBase structureBase) {
            super(panel);
            setSize(w,h);
            this.structureBase = structureBase;
        }

        @Override
        public void addWidgets() {

        }

        @Override
        public void alignWidgets() {

        }

        @Override
        public boolean checkMouseOver(int mouseX, int mouseY) {
            return false;
        }

        @Override
        public void draw(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
            if (structureBase != null) {
                Minecraft mc = Minecraft.getInstance();
                int textX = x + 4;
                int mw = w - 6;
                theme.drawString(graphics, structureBase.structureString(), textX, y, theme.getContentColor(this.getWidgetType()), 2);
                theme.drawString(graphics, theme.trimStringToWidth((FormattedText) Component.literal("Difficult:"), mw), textX, y + 4 + (mc.font.lineHeight), theme.getContentColor(this.getWidgetType()), 2);

                int difficultMax = RarityType.values().length - 1;
                int f = (int)  (x + 2 + mc.font.getSplitter().stringWidth("Difficult:") + 4);
                int difficultCount = structureBase.rarityBase.type.ordinal();

                int gCol = 255;

                for (int i = 0; i < difficultMax; i++) {
                    if (i <= difficultCount && difficultCount != 0) {
                        SDMIcons.STAR_ICON.withColor(Color4I.rgb(255, gCol, 0)).draw(graphics, (int) f, 3 + 10 + mc.font.lineHeight, 10, 10);
                        gCol -= 50;
                    } else {
                        SDMIcons.STAR_ICON.withColor(Color4I.GRAY).draw(graphics, (int) f, 3 + 10 + mc.font.lineHeight, 10, 10);
                    }
                    f += 12;
                }
                theme.drawString(graphics, theme.trimStringToWidth((FormattedText) Component.literal(String.format("Position:%s ",
                        structureBase.structureZone.getCenter().getX() + ", " + structureBase.structureZone.getCenter().getY() + ", " + structureBase.structureZone.getCenter().getZ())
                ), mw), textX, y + 4 + (mc.font.lineHeight * 2) + 4, theme.getContentColor(this.getWidgetType()), 2);
            }
        }
    }

    public class PropertyListPanel extends Panel {
        public StructureBase structureBase;
        public BlankPanel listProperty;
        public PanelScrollBar scrollBar;
        public List<BaseStructurePropertyPanel> structurePropertyPanels = new ArrayList<>();
        public PropertyListPanel(Panel panel, int w, int h, StructureBase structureBase) {
            super(panel);
            setSize(w,h);
            this.structureBase = structureBase;



            this.listProperty = new BlankPanel(this){
                public void drawBackground(GuiGraphics matrixStack, Theme theme, int x, int y, int w, int h) {


                }
            };
            this.listProperty.setPosAndSize(1,1, w - 2, h - 2);
            this.scrollBar = new PanelScrollBar(this, this.listProperty){
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
            try {
                this.listProperty.getWidgets().clear();
                this.listProperty.addAll(items);
                this.scrollBar.setPosAndSize(this.listProperty.posX + this.listProperty.width - 4, this.listProperty.posY, 4, this.listProperty.height);
                this.scrollBar.setValue(0.0);
            } catch (Exception e){
                SDMEngineCore.LOGGER.sendError(e.getMessage());
            }
        }

        @Override
        public void addWidgets() {
            this.add(this.listProperty);
            this.add(this.scrollBar);

            CompletableFuture.supplyAsync(() -> {
                int i = 0;
                List<Widget> wi = new ArrayList<>();
                boolean isFist = true;
                for (IBuff buff : structureBase.buffsEvent) {
                    BaseStructurePropertyPanel bs;
                    bs = new BaseStructurePropertyPanel(this.listProperty, this.listProperty.width - 1 - scrollBar.width, 22, buff);

                    if (isFist) {
                        bs.setPos(1, 1);
                        isFist = false;
                    } else {
                        bs.setPos(1, wi.get(i - 1).posY + 26);
                    }
                    wi.add(bs);
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
            GuiHelper.drawHollowRect(graphics, x, y, w, h, Color4I.WHITE.withAlpha(255), false);

        }
    }
}
