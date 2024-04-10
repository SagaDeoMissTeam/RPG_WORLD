package net.sdm.sdm_rpg_world.client;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.sdm.engine_core.SDMEngineCore;
import net.sdm.sdm_rpg_world.core.buffs.BuffBase;
import net.sdm.sdm_rpg_world.core.buffs.IBuff;
import net.sdm.sdm_rpg_world.core.rarity.RarityType;
import net.sdm.sdm_rpg_world.core.structure.StructureBase;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class StructureGUI implements IGuiOverlay {
    public Component text = Component.empty();
    public int difficultCount = 0;
    public int difficultMax = RarityType.values().length - 1;
    public @Nullable StructureBase structureBase = null;
    public boolean isOn = false;
    public StructureGUI() {
    }

    @Override
    public void render(ForgeGui gui, GuiGraphics graphics, float partialTick, int screenWidth, int screenHeight) {
        if(!isOn) return;
        if(!Objects.equals(text, Component.empty()) && structureBase != null) {

            Minecraft mc = Minecraft.getInstance();
            int cy = mc.getWindow().getGuiScaledHeight() / 2;

            int f = (int) (2 + mc.font.getSplitter().stringWidth("Difficult:") + 4);

            int width = 0;
            width = (int) Math.max(f + (12 * difficultCount), mc.font.getSplitter().stringWidth(I18n.get(this.text.getString())));

            //width = Math.max(width, (int) mc.font.getSplitter().stringWidth(I18n.get(this.text.getString())));

            graphics.pose().pushPose();
            graphics.pose().translate(mc.getWindow().getGuiScaledWidth() - width * 1 - 8D, 0, 100);
            graphics.pose().scale(1, 1, 1F);

            int buffListSize = (structureBase.buffsEntity.isEmpty() ? 0 : (8 * structureBase.buffsEntity.size())) + (structureBase.buffsEvent.isEmpty() ? 0 : (8 * structureBase.buffsEvent.size()));

            Color4I.BLACK.withAlpha(100).draw(graphics, 0, 0, width + 8, (9 + 8 * 2));

            int gCol = 255;

            for (int i = 0; i < difficultMax; i++) {
                if(i <= difficultCount && difficultCount != 0) {
                    SDMIcons.STAR_ICON.withColor(Color4I.rgb(255, gCol, 0)).draw(graphics, (int) f, 3 + mc.font.lineHeight, 10, 10);
                    gCol -= 50;
                } else {
                    SDMIcons.STAR_ICON.withColor(Color4I.GRAY).draw(graphics, (int) f, 3 + mc.font.lineHeight, 10, 10);
                }
                f += 12;
            }


            graphics.drawString(mc.font, I18n.get(this.text.getString()), 4, 4, 0xFFFFFFFF);
            graphics.drawString(mc.font, "Difficult:", 4, 4 + (mc.font.lineHeight), 0xFFFFFFFF);

//            for (int i = 0; i < structureBase.buffsEntity.size(); i++) {
//                IBuff buff = structureBase.buffsEntity.get(i);
//                graphics.drawString(mc.font, buff.toString(), 4, 4 + (mc.font.lineHeight * (i + 2)), 0xFFFFFFFF);
//            }
//            for (int i = 0; i < structureBase.buffsEvent.size(); i++) {
//                IBuff buff = structureBase.buffsEvent.get(i);
//                graphics.drawString(mc.font, buff.toString(), 4, 4 + (mc.font.lineHeight * (i + 2)), 0xFFFFFFFF);
//            }

            graphics.pose().popPose();
        }
    }

}
