package net.sdm.sdm_rpg_world.client.lore.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.*;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import dev.ftb.mods.ftblibrary.util.TooltipList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.commands.arguments.coordinates.Vec2Argument;
import net.minecraft.network.chat.Component;
import net.sdm.sdm_rpg_world.client.SDMIcons;
import net.sdm.sdm_rpg_world.client.lore.ftblib.MainBackGroundPanel;
import org.lwjgl.opengl.GL11;

import static dev.ftb.mods.ftblibrary.math.MathUtils.mod;
import static net.minecraft.commands.arguments.coordinates.Vec2Argument.vec2;

public class CubeButtonWidget extends Panel implements NordColors {
    public MainBackGroundPanel mainPanel;
    public Icon buttonIcon;
    public Component buttonTittle;
    public Component subButtonTittle;


    @Override
    public boolean scrollPanel(double scroll) {
        return false;
    }

    public TextField tittleField = new TextField(this);
    public Button button;
    public CubeButtonWidget(MainBackGroundPanel panel, int w, int h, Icon buttonIcon, Component buttonTittle, SimpleButton.Callback c) {
        this(panel, w, h, buttonIcon, buttonTittle, Component.empty(), c);
    }
    public CubeButtonWidget(MainBackGroundPanel panel, int w, int h, Icon buttonIcon, Component buttonTittle, Component subButtonTittle) {
        this(panel, w, h, buttonIcon, buttonTittle, subButtonTittle, (simpleButton, mouseButton) ->{});
    }
    public CubeButtonWidget(MainBackGroundPanel panel, int w, int h, Icon buttonIcon, Component buttonTittle, Component subButtonTittle, SimpleButton.Callback c) {
        super(panel);
        setSize(w,h);
        this.mainPanel = panel;
        this.buttonIcon = buttonIcon;
        this.buttonTittle = buttonTittle;
        this.subButtonTittle = subButtonTittle;

        tittleField.setText(subButtonTittle);
        tittleField.setScale(0.5f);
        button = new SimpleButton(this, Component.empty(), Icon.empty(), c){
            @Override
            public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
            }

            @Override
            public void addMouseOverText(TooltipList list) {
                super.addMouseOverText(list);
            }
        };
    }

    public int getPosXRight(){
        return this.getPosX() + width;
    }

    public int getPosYDown(){
        return this.getPosY() + height;
    }

    @Override
    public void addWidgets() {
        add(button);
        add(tittleField);
    }

    @Override
    public void alignWidgets() {
        button.setSize(this.width,this.height);
        tittleField.setPos(getOffsetText(), this.height - 6);
    }

    public int getOffsetText(){
        if((this.width / 2) - (Minecraft.getInstance().font.width(subButtonTittle) / 3) < this.width){
            return 1;
        }
        return (this.width / 2) - (Minecraft.getInstance().font.width(subButtonTittle) / 3);
    }

    @Override
    public void drawBackground(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        int insX = x;
        int insY = y;
        NordColors.POLAR_NIGHT_3.draw(graphics,x,y,w,h);
        buttonIcon.withColor(Color4I.BLACK).draw(graphics,(x + ((w / 2) / 2)),y,w / 2 ,h / 2);
        NordColors.POLAR_NIGHT_1.draw(graphics, x + 4, y + 2 + (w / 2), w - 8, 1);
        insX += 4;
        insY += 2 + (w / 2) + 3;


        int buttonX = x + (w / 2) - (Minecraft.getInstance().font.width(buttonTittle) / 2);
        int buttonY = insY;
        theme.drawString(graphics,buttonTittle, buttonX,buttonY);

        NordColors.POLAR_NIGHT_1.draw(graphics, insX, insY + 1 + Minecraft.getInstance().font.lineHeight, w - 8, 1);

//        GuiHelper.drawHollowRect();


    }
}
