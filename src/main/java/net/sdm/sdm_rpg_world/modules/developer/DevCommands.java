package net.sdm.sdm_rpg_world.modules.developer;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.sdm.sdm_rpg_world.craftTweaker.Manager;
import net.sdm.sdm_rpg_world.modules.rpg.entity.player.PlayerStateMachine;

import java.util.HashMap;
import java.util.Map;

public class DevCommands {
    public static Map<String, DevCommand> COMMANDS = new HashMap<>();

    public static void init() {
        //register(new DevCommand("customData", 3));
        register(new DevCommand("openScreen 1", 3) {
            @Override
            public boolean run(Player player) {
                Manager.openScreen((ServerPlayer) player);
                return true;
            }
        });
        register(new DevCommand("openScreen 2", 3) {
            @Override
            public boolean run(Player player) {
                Manager.openLoreScreen((ServerPlayer) player);
                return true;
            }
        });
        register(new DevCommand("openScreen 3", 3) {
            @Override
            public boolean run(Player player) {
                Manager.openOldScreen((ServerPlayer) player);
                return true;
            }
        });
        register(new DevCommand("playerData", 2) {
            @Override
            public boolean run(Player player) {
                if(!player.getPersistentData().isEmpty()){
                    CraftTweakerAPI.getLogger("SDM").info(player.getName().getString() + " : " + player.getPersistentData().toString());
                    player.sendSystemMessage(Component.literal(player.getPersistentData().toString()));
                }
                return true;
            }
        });
        register(new DevCommand("playerDatas", 2) {
            @Override
            public boolean run(Player player) {
                for (ServerPlayer serverPlayer : player.getServer().getPlayerList().getPlayers()) {
                    CraftTweakerAPI.getLogger("SDM").info(serverPlayer.getName().getString() + " : " + serverPlayer.getPersistentData().toString());
                    player.sendSystemMessage(Component.literal(serverPlayer.getPersistentData().toString()));
                }
                return true;
            }
        });
        register(new DevCommand("sendInfo", 1) {
            @Override
            public boolean run(Player player) {
                Manager.sendUnlock(player, "Это Заголовок", "Жили-были два утёнка. Первый утёнок мечтал стать разработчиком сборок, а второй – Full Stack разработчиком. Однако, судьба распорядилась иначе. Первый утёнок стал оператором токарного станка, а второй – таксистом.");
                return true;
            }
        });
        register(new DevCommand("allContent", 1) {
            @Override
            public boolean run(Player player) {
                PlayerStateMachine machine = PlayerStateMachine.of(player);
                machine.progressManager.unlockMap();
                machine.progressManager.unlockMiniMap();
                machine.progressManager.unlockJade();
                machine.progressManager.unlockCurios();
                machine.progressManager.unlockSkillTree();
                return true;
            }
        });
        register(new DevCommand("removeContent", 1) {
            @Override
            public boolean run(Player player) {
                PlayerStateMachine machine = PlayerStateMachine.of(player);
                machine.progressManager.lockCurios();
                machine.progressManager.lockMiniMap();
                machine.progressManager.lockJade();
                machine.progressManager.lockCurios();
                machine.progressManager.lockSkillTree();
                return true;
            }
        });
        register(new DevCommand("loreStarted", 2) {
            @Override
            public boolean run(Player player) {
return true;
            }
        });
        register(new DevCommand("loreCompleted", 2) {
            @Override
            public boolean run(Player player) {
return true;

            }
        });
        register(new DevCommand("randomItem", 3) {
            @Override
            public boolean run(Player player) {
return true;
            }
        });
        register(new DevCommand("randomItems", 3) {
            @Override
            public boolean run(Player player) {
return true;
            }
        });
        register(new DevCommand("randomTeleport", 3) {
            @Override
            public boolean run(Player player) {
return true;
            }
        });
//        register(new DevCommand("addDifficult", 3) {
//            @Override
//            public boolean run(Player player) {
//                Manager.setDifficultValue(player, Manager.getDifficultValue(player) + 1.0f);
//                return true;
//            }
//        });
//        register(new DevCommand("removeDifficult", 3) {
//            @Override
//            public boolean run(Player player) {
//                Manager.setDifficultValue(player, Manager.getDifficultValue(player) - 1.0f);
//                return true;
//            }
//        });
//        register(new DevCommand("maxDifficult", 3) {
//            @Override
//            public boolean run(Player player) {
//                Manager.setDifficultValue(player, 1000);
//                return true;
//            }
//        });
//        register(new DevCommand("minDifficult", 3) {
//            @Override
//            public boolean run(Player player) {
//                Manager.setDifficultValue(player, 0);
//                return true;
//            }
//        });
        register(new DevCommand("help", 1) {
            @Override
            public boolean run(Player player) {
                for (Map.Entry<String, DevCommand> com : DevCommands.COMMANDS.entrySet()) {
                    if(com.getValue().level <= Developers.getLevel(player)){
                        player.sendSystemMessage(Component.literal(com.getKey()));
                    }
                }

                return true;
            }
        });
    }

    public static void register(DevCommand command){
        COMMANDS.put(command.command, command);
    }
}
