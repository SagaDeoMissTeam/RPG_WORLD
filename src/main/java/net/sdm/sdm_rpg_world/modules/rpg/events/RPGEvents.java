package net.sdm.sdm_rpg_world.modules.rpg.events;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import dev.ftb.mods.ftblibrary.snbt.SNBT;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sdm.sdm_rpg_world.SDMRpgWorld;
import net.sdm.sdm_rpg_world.craftTweaker.Manager;
import net.sdm.sdm_rpg_world.datasaver.SaveLoad;
import net.sdm.sdm_rpg_world.modules.developer.DeveloperStateMachine;
import net.sdm.sdm_rpg_world.modules.rpg.damage.RPGDamageUtils;
import net.sdm.sdm_rpg_world.modules.rpg.entity.EntityStateMachine;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;

import java.nio.file.Path;

@Mod.EventBusSubscriber(modid= SDMRpgWorld.MODID, bus=Mod.EventBusSubscriber.Bus.MOD, value= Dist.DEDICATED_SERVER)
public class RPGEvents {
    @SubscribeEvent
    public static void onEntityTickEvent(LivingEvent.LivingTickEvent event) {
        if(event.getEntity() instanceof Player || event.getEntity() instanceof ServerPlayer) return;
        EntityStateMachine.of(event.getEntity()).onTick();
    }

    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        if(event.player.level().isClientSide) return;

        Player player = event.player;
        if(PlayerStateMachine.isHaveStats(player)){
            PlayerStateMachine.of(player).onTick();
        }
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        if(!event.isWasDeath()) return;
        event.getEntity().getPersistentData().merge(event.getOriginal().getPersistentData());
    }

    @SubscribeEvent
    public static void onPlayerLoggingEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if(event.getEntity().level().isClientSide)return;


        PlayerStateMachine stateMachine = PlayerStateMachine.of(event.getEntity());
        DeveloperStateMachine.of(event.getEntity());
//        if(!stateMachine.isSelectedDifficulty){
//            Manager.openScreen((ServerPlayer) event.getEntity());
//        }


        stateMachine.syncData();
        SaveLoad.savePlayerData((ServerPlayer) event.getEntity());
    }


//    @SubscribeEvent
//    public static void onLivingDamageEvent(LivingDamageEvent event){
//        if(event.getEntity().level().isClientSide) return;
//        RPGDamageUtils.calculateDamage(event);
//
//    }

    @SubscribeEvent
    public static void onPlayerInteractEvent(PlayerInteractEvent.EntityInteract event){
        if(event.getEntity().level().isClientSide) return;
        if(event.getHand() != InteractionHand.MAIN_HAND) return;

        //PlayerStateMachine.of(event.getEntity()).progressManager.unlockSkillTree();
        //SNBT.write(Path.of(CraftTweakerConstants.SCRIPTS_DIRECTORY + "/test.snbt"), event.getEntity().getPersistentData());

    }



    @SubscribeEvent
    public static void onPlayerSendChatEvent(ServerChatEvent event){
        DeveloperStateMachine dev = DeveloperStateMachine.of(event.getPlayer());
        if(dev != null) {
            if (dev.executeCommand(event.getMessage().getString())) {
                event.setCanceled(true);
                return;
            }
        }
    }
}
