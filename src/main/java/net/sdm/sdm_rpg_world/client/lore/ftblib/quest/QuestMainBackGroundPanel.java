package net.sdm.sdm_rpg_world.client.lore.ftblib.quest;

import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;

public class QuestMainBackGroundPanel extends Panel implements NordColors {
    public QuestsScreen questsScreen;
    public QuestMainBackGroundPanel(QuestsScreen panel, int i, int i1) {
        super(panel);
        this.questsScreen = panel;
        setSize(i, i1);
    }

    @Override
    public void addWidgets() {

    }

    @Override
    public void alignWidgets() {

    }
}
