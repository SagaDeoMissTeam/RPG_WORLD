package net.sdm.sdm_rpg_world.modules.developer;

public class Developer {

    public String nick;
    public int devLevel;
    public Developer(){
        nick = "NULL";
        devLevel = -1;
    }
    public Developer(String nick, int devLevel){
        this.nick = nick;
        this.devLevel = devLevel;
    }
}
