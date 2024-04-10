package net.sdm.sdm_rpg_world;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import dev.architectury.event.EventResult;
import dev.ftb.mods.ftblibrary.snbt.SNBT;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import dev.ftb.mods.ftblibrary.ui.CustomClickEvent;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.client.KeyMapping;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sdm.engine_core.SDMEngineCore;
import net.sdm.engine_core.api.Constants;
import net.sdm.sdm_rpg.core.events.minecraft.EntityEvents;
import net.sdm.sdm_rpg_world.client.StructureGUI;
import net.sdm.sdm_rpg_world.client.ToolTipsHandler;
import net.sdm.sdm_rpg_world.client.structureInfo.StructureScreen;
import net.sdm.sdm_rpg_world.client.unlock.UnlockGUI;
import net.sdm.sdm_rpg_world.core.PathConstantsRPG;
import net.sdm.sdm_rpg_world.core.aspect.AspectsRegister;
import net.sdm.sdm_rpg_world.core.bestiary.BestiaryManager;
import net.sdm.sdm_rpg_world.core.bestiary.BestiaryPage;
import net.sdm.sdm_rpg_world.core.config.ConfigBase;
import net.sdm.sdm_rpg_world.core.config.ConfigProperty;
import net.sdm.sdm_rpg_world.core.events.ModEvents;
import net.sdm.sdm_rpg_world.core.structure.StructureBase;
import net.sdm.sdm_rpg_world.modules.developer.DevCommands;
import net.sdm.sdm_rpg_world.modules.developer.Developers;
import net.sdm.sdm_rpg_world.modules.rpg.events.RPGEvents;
import net.sdm.sdm_rpg_world.net.NetHandler;
import net.sdm.sdm_rpg_world.net.SendStructureInfoS2C;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SDMRpgWorld.MODID)
public class SDMRpgWorld {
    public static ConfigBase configBase = new ConfigBase();
    public static final String MODID = "sdm_rpg_world";
    public static final Logger LOGGER = LogUtils.getLogger();
    public SDMRpgWorld() {
        if (!PathConstantsRPG.CONFIGS.toFile().exists()) {
            PathConstantsRPG.CONFIGS.toFile().mkdirs();
        }
        if(!PathConstantsRPG.CONFIG_FILE.toFile().exists()){
            try {
                PathConstantsRPG.CONFIG_FILE.toFile().createNewFile();
                ConfigBase base = new ConfigBase();
                ConfigProperty property = new ConfigProperty(new ResourceLocation("minecraft:structure_id"), 0, 5);
                property.property.add("FirstProperty");
                property.property.add("SecondProperty");
                base.propertyList.add(property);
                SNBT.write(PathConstantsRPG.CONFIG_FILE,base.serializeNBT());
            } catch (IOException e) {}
        }


        NetHandler.init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::preInitClient);
        modEventBus.addListener(this::onRegisterClientTooltipComponentFactories);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ModEvents.class);
        MinecraftForge.EVENT_BUS.register(RPGEvents.class);
        MinecraftForge.EVENT_BUS.addListener(this::onReload);
        AspectsRegister.init();
        DevCommands.init();
        Developers.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
    private void onReload(AddReloadListenerEvent event){
        event.addListener(BestiaryManager.INSTANCE);
    }

    private void preInitClient(final FMLClientSetupEvent event){
        ToolTipsHandler.init();
    }
    private void onRegisterClientTooltipComponentFactories(RegisterClientTooltipComponentFactoriesEvent event)
    {
        ToolTipsHandler.register(event);
    }

//    @SubscribeEvent
//    public void onServerStarted(ServerStartedEvent event){
//        SNBTCompoundTag nbt = SNBT.read(PathConstantsRPG.CONFIG_FILE);
//        if(nbt != null){
//            ConfigBase config = new ConfigBase();
//            config.deserializeNBT(nbt);
//            configBase = config;
//        }
//    }

//
//    @SubscribeEvent
//    public void onServerStopped(ServerStoppedEvent event){
//        ServerData.STRUCTURE_DATA.clear();
//    }

//    @SubscribeEvent
//    public void onPlayerTick(TickEvent.PlayerTickEvent event){
//
//        if(event.player.level().isClientSide) return;
//        ServerLevel level = (ServerLevel) event.player.level();
//        Map<Structure, LongSet> st = level.structureManager().getAllStructuresAt(event.player.blockPosition());
//        if(!st.isEmpty()) {
//            for (Map.Entry<Structure, LongSet> structureLongSetEntry : st.entrySet()) {
//                StructureStart start = level.structureManager().getStructureAt(event.player.blockPosition(), structureLongSetEntry.getKey());
//                if(start.isValid()) {
//                    if (ServerData.STRUCTURE_DATA.containsKey(start.getBoundingBox())) {
//                        new SendStructureInfoS2C(ServerData.STRUCTURE_DATA.get(start.getBoundingBox())).sendTo((ServerPlayer) event.player);
//                    }
//                }
//            }
//        } else {
//            new SendStructureInfoS2C(new CompoundTag()).sendTo((ServerPlayer) event.player);
//        }
//    }

    @SubscribeEvent
    public void onPlayerLoginEvent(PlayerEvent.PlayerLoggedInEvent event){
        if(!event.getEntity().level().isClientSide){
            ServerData.sync((ServerPlayer) event.getEntity());
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Mod.EventBusSubscriber(modid=SDMRpgWorld.MODID, bus=Mod.EventBusSubscriber.Bus.MOD, value= Dist.CLIENT)
    public static class SDMClient {

        public static List<BestiaryPage> bestiaryPages = new ArrayList<>();
        public static Map<String, BestiaryPage> bestiaryPageMap = new HashMap<>();
        public static final ResourceLocation OPEN_GUI = new ResourceLocation(SDMRpgWorld.MODID, "open_gui");

        public static final String SDMSHOP_CATEGORY = "key.sdm.rpg";
        public static final String KEY_NAME = "key.sdm.structurescreen";
        public static KeyMapping KEY_SHOP = new KeyMapping(KEY_NAME, KeyConflictContext.IN_GAME,
                InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, SDMSHOP_CATEGORY);

        public static StructureGUI structureGUI = new StructureGUI();
        public static UnlockGUI unlockGUI = new UnlockGUI();
        public static Map<BoundingBox, StructureBase> STRUCUTRES_DATA = new HashMap<>();
        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void onScreenRender(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("sdmstructuregui", structureGUI);
            event.registerAboveAll("sdmunlockgui", unlockGUI);
        }

        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KEY_SHOP);
        }
    }
    @Mod.EventBusSubscriber(modid = SDMRpgWorld.MODID, value = Dist.CLIENT)
    public static class ClientEvents{
//        @OnlyIn(Dist.CLIENT)
//        @SubscribeEvent
//        public static void keyInput(InputEvent.Key event) {
//            if (SDMClient.KEY_SHOP.consumeClick()) {
//                new StructureScreen().openGui();
//            }
//        }
    }
}
