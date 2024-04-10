package net.sdm.sdm_rpg_world.core.events;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.data.StringData;
import com.blamejared.crafttweaker.natives.entity.ExpandEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.ftb.mods.ftblibrary.snbt.SNBT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.ServerData;
import net.sdm.sdm_rpg_world.core.buffs.BuffBase;
import net.sdm.sdm_rpg_world.core.buffs.IBuff;
import net.sdm.sdm_rpg_world.core.structure.StructureBase;
import net.sdm.sdm_rpg_world.modules.rpg.entity.EntityStateMachine;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;
import net.sdm.sdm_rpg_world.modules.rpg.entity.stats.EntityStatValue;
import net.sdm.sdm_rpg_world.modules.rpg.entity.stats.EntityStatsBase;
import net.sdm.sdm_rpg_world.modules.rpg.item.genItems.GenLootUtils;
import org.lwjgl.opengl.GL11;

import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Mod.EventBusSubscriber(modid= SDMRpgWorld.MODID, bus=Mod.EventBusSubscriber.Bus.MOD, value= Dist.DEDICATED_SERVER)
public class ModEvents {
//    @SubscribeEvent
//    public static void onEntityDamage(LivingDamageEvent event){
//        if(event.getEntity().level().isClientSide || ServerData.STRUCTURE_DATA.isEmpty()) return;
//        for (Map.Entry<BoundingBox, StructureBase> boundingBoxStructureBaseEntry : ServerData.STRUCTURE_DATA.entrySet()) {
//            if(boundingBoxStructureBaseEntry.getKey().isInside(event.getEntity().blockPosition())){
//                boundingBoxStructureBaseEntry.getValue().buffsEvent.forEach(s -> s.accept(event));
//                break;
//            }
//        }
//    }

    @SubscribeEvent
    public static void onToolTips(ItemTooltipEvent event){
        ItemStack item = event.getItemStack();
        List<Component> tooltips = event.getToolTip();

        if(!item.hasTag()) return;
        if(!item.getTag().contains("sdm_data")) return;
        CompoundTag sdmData = item.getTag().getCompound("sdm_data");
        Set<String> keys = sdmData.getAllKeys();
        for (String key : keys) {
            tooltips.add(Component.translatable("sdm.itemproperty." + key, new DecimalFormat("#0.00").format(sdmData.getDouble(key))));
//            tooltips.add(Component.literal(key + " " + (new DecimalFormat("#0.00").format(sdmData.getDouble(key))) + "%"));
        }


    }


//    @SubscribeEvent
//    public static void onPlayerLootChestGen(PlayerChestLootEvent event) {
//        Player player = event.getEntity();
//        if(player.level().isClientSide) return;
//        PlayerStateMachine machine = PlayerStateMachine.of(player);
//
//        if(machine.progressManager.isProgressMode){
//            GenLootUtils.deleteModsLootByModId(event.getInventory(), machine.lootMachine.UNLOCKED_MODS);
//        }
//
//
//    }

//    @SubscribeEvent
//    public static void onEntityRenderersEventPre(RenderLivingEvent.Pre event){
//        LivingEntity entity = event.getEntity();
//
//
//        if(entity instanceof AbstractClientPlayer) return;
//
//        CompoundTag data = entity.getPersistentData();
//        if(data.contains("sdmData")) {
//            CompoundTag sdmData = data.getCompound("sdmData");
//            if(!sdmData.contains("entityStateMachine")) return;
//            EntityStateMachine stateMachine = EntityStateMachine.of(entity);
//            float redF = -99.0f;
//            float greenF = -99.0f;
//            float blueF = -99.0f;
//            float alpha = -99f;
//
//            for (EntityStatsBase entityStatsBase : stateMachine.statsList) {
//                if(entityStatsBase instanceof EntityStatValue<?> value) {
//                    if (entityStatsBase.id.equals("redF")) {
//                        redF = (float) value.value.floatValue();
//                    }
//                    if (entityStatsBase.id.equals("greenF")) {
//                        greenF = (float) value.value.floatValue();
//                    }
//                    if (entityStatsBase.id.equals("blueF")) {
//                        blueF = (float) value.value.floatValue();
//                    }
//                    if (entityStatsBase.id.equals("alpha")) {
//                        alpha = (float) value.value.floatValue();
//                    }
//                }
//            }
//
//
//            if(redF == -99.0f || greenF == -99.0f || blueF == -99 || alpha == -99.0f) return;
//            GuiGraphics graphics = new GuiGraphics(Minecraft.getInstance(), Minecraft.getInstance().renderBuffers().bufferSource());
//            graphics.pose().pushPose();
//            RenderSystem.enableBlend();
//            RenderSystem.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
//            RenderSystem.setShaderColor(redF, greenF, blueF, alpha);
//        }
//
//    }
//
//    @SubscribeEvent
//    public static void onEntityRenderersEventPost(RenderLivingEvent.Post event){
//        LivingEntity entity = event.getEntity();
//
//
//        CompoundTag data = entity.getPersistentData();
//        if(data.contains("sdmData")) {
//            CompoundTag sdmData = data.getCompound("sdmData");
//            if (!sdmData.contains("entityStateMachine")) return;
//            if (!entity.isAlive()) {
//                GuiGraphics graphics = new GuiGraphics(Minecraft.getInstance(), Minecraft.getInstance().renderBuffers().bufferSource());
//                graphics.pose().popPose();
//                RenderSystem.disableBlend();
//            }
//        }
//
//
//    }
}
