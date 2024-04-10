package net.sdm.sdm_rpg_world.net;

import dev.architectury.networking.simple.MessageType;
import dev.architectury.networking.simple.SimpleNetworkManager;
import net.sdm.sdm_rpg_world.SDMRpgWorld;

public interface NetHandler {

    SimpleNetworkManager NET = SimpleNetworkManager.create(SDMRpgWorld.MODID);

    MessageType OPEN_SCREEN = NET.registerS2C("open_screen", OpenScreenS2C::new);
    MessageType OPEN_OLD_SCREEN = NET.registerS2C("open_old_screen", OpenOldScreenS2C::new);
    MessageType OPEN_LORE_SCREEN = NET.registerS2C("open_lore_screen", OpenLoreScreenS2C::new);
    MessageType OPEN_SELECT_LORE_SCREEN = NET.registerS2C("open_select_lore_screen", OpenSelectLoreScreenS2C::new);
    MessageType SEND_DATA = NET.registerC2S("send_data", SendDataC2S::new);
    MessageType SEND_DATA_CLIENT = NET.registerS2C("send_data_client", SendDataS2C::new);
    MessageType STRUCTURE_INFO = NET.registerS2C("structure_info", SendStructureInfoS2C::new);
    MessageType SYNC_DATA_STRUCT = NET.registerS2C("sync_data_struct", SyncStructDataS2C::new);
    MessageType SYNC_ENTITY_DATA = NET.registerS2C("sync_data_struct", SyncEntityDataS2C::new);
    MessageType SEND_SCREEN_CLOSE = NET.registerC2S("send_screen_close", SendScreenCloseC2S::new);
    MessageType SYNC_SERVERPLAYER = NET.registerS2C("sync_server_player", SyncServerPlayerS2C::new);
    MessageType SYNC_BESTIARY_DATA = NET.registerS2C("sync_bestiary_data", SyncBestiaryDataS2C::new);
    MessageType RESET_BESTIARY_DATA = NET.registerS2C("reset_bestiary_data", ResetBestiaryDataS2C::new);
    MessageType SEND_UNLOCK_MESSAGE = NET.registerS2C("send_unlock_message", SendUnlockMessageS2C::new);
    static void init() {
    }
}
