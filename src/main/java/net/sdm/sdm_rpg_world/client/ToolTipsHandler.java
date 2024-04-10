package net.sdm.sdm_rpg_world.client;


import com.mojang.datafixers.util.Either;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.ui.Theme;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.sdm.engine_core.utils.client.GuiHelper;
import net.sdm.sdm_rpg_world.core.aspect.AspectsRegister;
import net.sdm.sdm_rpg_world.core.aspect.ItemAspect;
import net.sdm.sdm_rpg_world.core.utils.SDMGuiHelper;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ToolTipsHandler {

    public static void init(){
        MinecraftForge.EVENT_BUS.register(new ToolTipsHandler());
    }

    public static void register(RegisterClientTooltipComponentFactoriesEvent event){
        event.register(TestTooltip.class, TestTooltipRender::new);
    }

    static class TestTooltipRender implements ClientTooltipComponent{
        private List<ItemAspect> aspectList = new ArrayList<>();
        private TestTooltip testTooltip;
        TestTooltipRender(TestTooltip testTooltip){
            this.testTooltip = testTooltip;
            if(testTooltip.itemStack.hasTag()) {
                aspectList = AspectsRegister.getByData(testTooltip.itemStack.getOrCreateTag());
            }
        }

        public int getSize() {
            return 10;
        }

        @Override
        public int getHeight() {
            return aspectList.isEmpty() ? 0 : getSize();
        }

        @Override
        public int getWidth(Font p_169952_) {
            if(aspectList.isEmpty()) return 0;
            return aspectList.size() + 1 + (getSize() * aspectList.size());
        }

        @Override
        public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
            if(aspectList.isEmpty()) return;
            ItemStack itemStack = testTooltip.itemStack;
            boolean isFirst = true;
            for (int i = 0; i < aspectList.size(); i++) {
                if(isFirst) {
                    SDMIcons.BACK.draw(guiGraphics, x, y, getSize(), getSize());
                    aspectList.get(i).aspect.icon.withColor(aspectList.get(i).aspect.color).draw(guiGraphics, x, y, getSize(), getSize());

                    SDMGuiHelper.drawText(guiGraphics,
                            (int) ((x + getSize() - 3 + i) + (getSize() * i)),
                            (int) (y + getSize() - 4),
                            0.5f, Component.literal(String.valueOf(aspectList.get(i).value)), Color4I.WHITE.rgb());
                    isFirst = false;
                }
                else {

                    SDMIcons.BACK.draw(guiGraphics, x + 1 + i + (getSize() * i), y, getSize(), getSize());
                    aspectList.get(i).aspect.icon.withColor(aspectList.get(i).aspect.color).draw(guiGraphics, x + 1 + i + (getSize() * i), y, getSize(), getSize());
                    SDMGuiHelper.drawText(guiGraphics,
                            (int) ((x + getSize() - 3 + i) + (getSize() * i)),
                            (int) (y + getSize() - 4),
                            0.5f, Component.literal(String.valueOf(aspectList.get(i).value)), Color4I.WHITE.rgb());
                }
            }
        }
    }

    static class TestTooltip implements TooltipComponent{
        private ItemStack itemStack;

        TestTooltip(ItemStack itemStack){
            this.itemStack = itemStack;
        }
    }
    @SubscribeEvent
    public void gatherTooltips(RenderTooltipEvent.GatherComponents event){
        TestTooltip testTooltip = new TestTooltip(event.getItemStack());

        event.getTooltipElements().add(Either.right(testTooltip));
    }
}
