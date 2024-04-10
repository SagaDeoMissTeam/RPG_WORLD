package net.sdm.sdm_rpg_world.net;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import dev.ftb.mods.ftblibrary.ui.ScreenWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.sdm.sdm_rpg_world.client.lore.ftblib.LoreScreen;
import net.sdm.sdm_rpg_world.client.lore.nv.SelectLoreScreen;

import java.util.Objects;
import java.util.UUID;

public class OpenSelectLoreScreenS2C extends BaseS2CMessage {
    private final UUID playerId;

    public OpenSelectLoreScreenS2C(UUID playerId) {
        this.playerId = playerId;
    }
    public OpenSelectLoreScreenS2C(FriendlyByteBuf buf) {
        this.playerId = buf.readUUID();
    }

    @Override
    public MessageType getType() {
        return NetHandler.OPEN_SELECT_LORE_SCREEN;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeUUID(playerId);
    }

    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if(packetContext.getEnv().isClient()) {
//            MuiForgeApi.openScreen(new LoreScreenMUI());
            if (!Objects.equals(Minecraft.getInstance().screen, new ScreenWrapper(new SelectLoreScreen()))) {
                SelectLoreScreen screen = new SelectLoreScreen();
                screen.openGui();
            }
        }
    }
}
