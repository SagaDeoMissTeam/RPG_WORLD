package net.sdm.sdm_rpg_world.core.bestiary;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.sdm.sdm_rpg_world.core.bestiary.content.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class EntityBestiaryPage extends BestiaryPage{
    public EntityType<?> entity;

    public EntityBestiaryPage(){

    }
    public EntityBestiaryPage(String registryID, Component name) {
        super(registryID, name);
    }

    @Override
    public BestiaryPageType getType() {
        return BestiaryPageType.ENTITY;
    }

}
