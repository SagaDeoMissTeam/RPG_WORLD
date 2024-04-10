package net.sdm.sdm_rpg_world.client.unlock;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.ui.GuiHelper;
import dev.ftb.mods.ftblibrary.ui.TextField;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import icyllis.arc3d.core.Paint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.common.util.INBTSerializable;
import net.sdm.sdm_rpg_world.client.ISDMRender;
import net.sdm.sdm_rpg_world.client.SDMIcons;
import net.sdm.sdm_rpg_world.client.widget.SDMTextField;
import net.sdm.sdm_rpg_world.core.aspect.AspectsRegister;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestUnlock implements ISDMRender, INBTSerializable<CompoundTag>, NordColors {

    private static final RandomSource RANDOM = RandomSource.create();
    private boolean isEnd = false;
    private long timer = 0;
    private Color4I rgb = NordColors.POLAR_NIGHT_0;
    private Theme theme = new Theme();

    public List<String> contentList = new ArrayList<>();
    private String message;
    private String tittle = "";
    private String maineText = "";
    private String screenText = "";
    private int charId = 0;
    public boolean isPrinted = false;
    public int line = 0;
    public float scale = 0.7f;
    public long textTime = 0;
    public boolean isFulView = false;
    public boolean isOff = false;
    public long timeView = 0;
    public long maxTimeView = 255;
    public int alpha = 0;
    public boolean soundSended = false;

    public @Nullable ResourceLocation audioID = null;
    public TestUnlock(String message, String title, String subtitle){
        this.message = message;
        this.tittle = I18n.get(title);
        this.maineText = I18n.get(subtitle);
        this.audioID = null;
    }
    public TestUnlock(String message, String title, String subtitle, ResourceLocation audioID){
        this.message = message;
        this.tittle = I18n.get(title);
        this.maineText = I18n.get(subtitle);
        this.audioID = audioID;
    }

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        if(!soundSended){
            soundSended = true;
            if(audioID != null) {
                SimpleSoundInstance instance = new SimpleSoundInstance(audioID,
                        SoundSource.VOICE, 0.7F, 1, RANDOM, false, 0, SoundInstance.Attenuation.NONE, 0, 0, 0, true);
                Minecraft.getInstance().getSoundManager().play(instance);
            }
        }

        if(!isFulView) {
            if(alpha > maxTimeView){
                if (timer >= 320) {
                    isEnd = true;
                    return;
                }
                rgb.draw(guiGraphics, 0, 0, screenWidth, screenHeight);
                GuiHelper.drawHollowRect(guiGraphics, 0, 0, screenWidth, screenHeight, NordColors.POLAR_NIGHT_1, false);
                int posY = 1;
                int xCenter = (int) ((screenWidth / 2) - (Minecraft.getInstance().font.getSplitter().stringWidth(I18n.get(I18n.get(message)))) / 2);
                theme.drawString(guiGraphics, Component.translatable(message), xCenter, posY);

                NordColors.POLAR_NIGHT_4.draw(guiGraphics, 4, Minecraft.getInstance().font.lineHeight + 1, screenWidth - 8, 1);
                posY += Minecraft.getInstance().font.lineHeight + 1;

                xCenter = (int) ((screenWidth / 2) - (Minecraft.getInstance().font.getSplitter().stringWidth(I18n.get(tittle))) / 2);
                theme.drawString(guiGraphics, Component.translatable(tittle), xCenter, posY);
                posY += (Minecraft.getInstance().font.lineHeight / 2) + 1;

                SDMIcons.STAR_ICON.withColor(Color4I.rgb(255, 255, 0)).draw(guiGraphics, screenWidth - 4, -4, 8, 8);

                new SDMTextField(Component.literal(screenText), 0.6f, 0, posY).render(gui, guiGraphics, partialTick, screenWidth, screenHeight);

                if (isTextPrinted()) {
                    timer++;
                } else {
                    printText();
                    textTime++;
                }
            } else {
                rgb.withAlpha((int) alpha++).draw(guiGraphics, 0, 0, screenWidth, screenHeight);
                GuiHelper.drawHollowRect(guiGraphics, 0, 0, screenWidth, screenHeight, NordColors.POLAR_NIGHT_1.withAlpha((int) alpha++), false);
            }
        } else {

        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {

    }

    public void printText(){
//        if(!isPrinted){
//            if(!(charId > maineText.length() - 1)) {
//                screenText += maineText.charAt(charId);
////                if(Minecraft.getInstance().font.getSplitter().stringWidth(screenText) > 72){
////                    contentList.add(screenText);
////                    screenText = "";
////                }
//            } else isPrinted = true;
//            charId++;
//        }
        if(textTime % 5 == 0){
            if(!(charId > maineText.length() - 1)) {
                screenText += maineText.charAt(charId);
            } else isPrinted = true;
            charId++;
        }
    }

    public boolean isTextPrinted(){
        return isPrinted;
    }

    @Override
    public boolean isEnd() {
        return isEnd;
    }

    @Override
    public CompoundTag serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }
}
