package net.sdm.sdm_rpg_world.net;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import dev.ftb.mods.ftblibrary.ui.BaseScreen;
import dev.ftb.mods.ftblibrary.ui.ScreenWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.sdm.sdm_rpg_world.client.SelectDifficultScreen;
import net.sdm.sdm_rpg_world.client.difficult.MainSelectDifficultScreen;

import java.util.Objects;
import java.util.UUID;

public class OpenScreenS2C extends BaseS2CMessage {

    private final UUID playerId;

    public OpenScreenS2C(UUID playerId) {
        this.playerId = playerId;
    }
    public OpenScreenS2C(FriendlyByteBuf buf) {
        this.playerId = buf.readUUID();
    }

    @Override
    public MessageType getType() {
        return NetHandler.OPEN_SCREEN;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeUUID(playerId);
    }

    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if(packetContext.getEnv().isClient()) {
            if (!Objects.equals(Minecraft.getInstance().screen, new ScreenWrapper(new MainSelectDifficultScreen()))) {
                MainSelectDifficultScreen screen = new MainSelectDifficultScreen();
                screen.openGui();
            }
        }
    }
}
