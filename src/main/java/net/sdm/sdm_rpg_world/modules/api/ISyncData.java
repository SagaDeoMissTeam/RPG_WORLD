package net.sdm.sdm_rpg_world.modules.api;

public interface ISyncData {

    void syncData();

    default void syncDataClient(){

    }
}
