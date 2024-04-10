package net.sdm.sdm_rpg_world.modules.rpg.entity.player;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.sdm.sdm_rpg_world.craftTweaker.Manager;
import net.sdm.sdm_rpg_world.modules.rpg.entity.EntityStateMachine;
import net.sdm.sdm_rpg_world.modules.rpg.entity.stats.EntityStatValue;
import net.sdm.sdm_rpg_world.modules.rpg.entity.stats.EntityStatsBase;
import net.sdm.sdm_rpg_world.modules.rpg.entity.stats.EntityStatsList;
import net.sdm.sdm_rpg_world.modules.rpg.item.ItemStats;
import net.sdm.sdm_rpg_world.modules.rpg.utils.PlayerStatsUtils;
import net.sdm.sdm_rpg_world.net.SyncServerPlayerS2C;
import org.openzen.zencode.java.ZenCodeType;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.*;


@ZenRegister
@ZenCodeType.Name("mods.rpgworld.entity.player.PlayerStateMachine")
public class PlayerStateMachine extends EntityStateMachine {
    public Player player;
    @ZenCodeType.Field
    public PlayerProgressManager progressManager;
    @ZenCodeType.Field
    public PlayerLootMachine lootMachine;
    @ZenCodeType.Field
    public PlayerLoreMachine loreManager;

    public boolean isSelectedDifficulty = false;
    public PlayerStateMachine(Player entity) {
        super(entity);
        this.player = entity;
    }

    public static boolean isHaveStats(Player entity) {
        if(entity.getPersistentData().contains("sdmData")){
            if(entity.getPersistentData().getCompound("sdmData").contains("playerStateMachine")){
                return true;
            }
        }
        return false;
    }

    public static PlayerStateMachine of(Player entity) {
        PlayerStateMachine stateMachine = new PlayerStateMachine(entity);

        if(entity.getPersistentData().contains("sdmData")){
            if(entity.getPersistentData().getCompound("sdmData").contains("playerStateMachine")){
                SNBTCompoundTag nbt = SNBTCompoundTag.of(entity.getPersistentData().getCompound("sdmData").getCompound("playerStateMachine"));
                stateMachine.deserializeNBT(nbt);
                return stateMachine;
            }
        }


        stateMachine.loreManager = new PlayerLoreMachine(stateMachine);
        stateMachine.lootMachine = new PlayerLootMachine(stateMachine);
        stateMachine.progressManager = new PlayerProgressManager(stateMachine);
        stateMachine.generateBaseStats();
        return stateMachine;
    }




    @Override
    public void syncData() {
        SNBTCompoundTag nbt = serializeNBT();
        CompoundTag data = new CompoundTag();
        data.put("playerStateMachine", nbt);
        entity.getPersistentData().put("sdmData", data);
        syncDataClient();
        this.needSync = false;
    }

    @Override
    public void syncDataClient() {
        new SyncServerPlayerS2C(serializeNBT()).sendTo((ServerPlayer) player);
    }

    @Override
    public void generateBaseStats() {
        statsList = new ArrayList<>();
        statsListMap = new HashMap<>();
        EntityStatValue<Double> value1 = EntityStatsList.NULL;
        List<PlayerProperty> properties = PlayerStatsUtils.getPlayerDifficultProperty(player);
        PlayerDifficult difficult = PlayerStatsUtils.getPlayerDifficult(player);
        if(properties.contains(PlayerProperty.MISSKILL)){
            value1 = EntityStatsList.MISS_CHANCE;
            value1.updateValue(50.0);
            addStats(value1);
            value1 = EntityStatsList.MISS_REDUCE;
            value1.updateValue(0.0);
            addStats(value1);
        }

        value1 = EntityStatsList.VOID_DAMAGE;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.DIVINE_DAMAGE;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.CHAOS_DAMAGE;
        value1.updateValue(0.0);
        addStats(value1);

        value1 = EntityStatsList.VOID_DAMAGE_BUFF;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.CHAOS_DAMAGE_BUFF;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.DIVINE_DAMAGE_BUFF;
        value1.updateValue(0.0);
        addStats(value1);

        value1 = EntityStatsList.VOID_RESIST;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.DIVINE_RESIST;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.CHAOS_RESIST;
        value1.updateValue(0.0);
        addStats(value1);

        value1 = EntityStatsList.DAMAGE_BONUS_BUFF;
        value1.updateValue(0.0);
        addStats(value1);

        value1 = EntityStatsList.WATER_RESIST;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.LAVA_RESIST;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.LOOT_BONUS;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.LOOT_RARITY_BONUS;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.MIND_RESIST;
        value1.updateValue(0.0);
        addStats(value1);

        value1 = EntityStatsList.CRITICAL_HIT_CHANCE;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.CRITICAL_HIT_MULTIPLY;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.CRITICAL_HIT_RANK;
        value1.updateValue(0.0);
        addStats(value1);
        value1 = EntityStatsList.PROTECT_CLASS;
        value1.updateValue(0.0);
        addStats(value1);

        switch (difficult){
            case NONE -> {
                value1 = EntityStatsList.DAMAGE_BONUS;
                value1.updateValue(0.0);
                addStats(value1);
                value1 = EntityStatsList.DAMAGE_RESIST;
                value1.updateValue(0.0);
                addStats(value1);
                break;
            }
            case EASY -> {
                value1 = EntityStatsList.DAMAGE_BONUS;
                value1.updateValue(25.0);
                addStats(value1);
                value1 = EntityStatsList.DAMAGE_RESIST;
                value1.updateValue(25.0);
                addStats(value1);
                break;
            }
            case MEDIUM -> {
                value1 = EntityStatsList.DAMAGE_BONUS;
                value1.updateValue(0.0);
                addStats(value1);
                value1 = EntityStatsList.DAMAGE_RESIST;
                value1.updateValue(0.0);
                addStats(value1);
                break;
            }
            case HARD -> {
                value1 = EntityStatsList.DAMAGE_BONUS;
                value1.updateValue(-25.0);
                addStats(value1);
                value1 = EntityStatsList.DAMAGE_RESIST;
                value1.updateValue(-25.0);
                addStats(value1);
                break;
            }
        }
    }

    @Override
    public SNBTCompoundTag serializeNBT() {
        SNBTCompoundTag nbt = super.serializeNBT();
        nbt.putBoolean("isSelectedDifficulty", isSelectedDifficulty);
        nbt.put("progressManager", progressManager.serializeNBT());
        nbt.put("loreManager", loreManager.serializeNBT());
        nbt.put("lootMachine", lootMachine.serializeNBT());
        return nbt;
    }

    @Override
    public void deserializeNBT(SNBTCompoundTag nbt) {
        super.deserializeNBT(nbt);
        PlayerProgressManager manager = new PlayerProgressManager(this);
        PlayerLoreMachine machine = new PlayerLoreMachine(this);
        PlayerLootMachine lootMachine = new PlayerLootMachine(this);

        manager.deserializeNBT(nbt.getCompound("progressManager"));
        machine.deserializeNBT(nbt.getCompound("loreManager"));
        lootMachine.deserializeNBT(nbt.getCompound("lootMachine"));

        this.progressManager = manager;
        this.loreManager = machine;
        this.lootMachine = lootMachine;
        this.isSelectedDifficulty = nbt.getBoolean("isSelectedDifficulty");

        if(player.level().isClientSide){
            SNBTCompoundTag d1 = serializeNBT();
            CompoundTag data = new CompoundTag();
            data.put("playerStateMachine", d1);
            entity.getPersistentData().put("sdmData", data);
            Minecraft.getInstance().player.getPersistentData().merge(d1);
        } else {
            syncData();
        }
    }

    public void addStats(EntityStatsBase base){
        this.statsList.add(base);
        this.statsListMap.put(base.id, base);
    }

    @Override
    public void onTick() {
        super.onTick();
        progressManager.onTick();
    }

    @ZenCodeType.Method
    public List<String> getStatsKey(){
        List<String> list = new ArrayList<>();
        for (EntityStatsBase entityStatsBase : statsList) {
            list.add(entityStatsBase.id);
        }
        return list;
    }


    //TODO: Перенести механики из скриптов
    public void unlockByNBT(CompoundTag nbt){
        this.isSelectedDifficulty = true;
        if(nbt.contains("oredrop")){

        }
        if(nbt.contains("randomspawndimension")){

        }
        if(nbt.contains("randomdimension")){

        }
        if(nbt.contains("missskill")){

        }
        if(nbt.contains("random_start_kit")){

        }
        if(nbt.contains("memory_of_monsters")){

        }
        if(nbt.contains("thirsty")){
            progressManager.unlockThirst();
        }
        if(nbt.contains("sanity")){
            progressManager.unlockSanity();
        }
        if(nbt.contains("plot")){

        }
        if(nbt.contains("progress")){
            progressManager.unlockProgressMode();
        }

        if(!nbt.contains("progress") || !nbt.contains("plot")){
            progressManager.unlockCommands();
        }
        syncData();
    }

    @ZenCodeType.Method
    public void updateStats(){
//        generateBaseStats();
        updateStatsOnlyInventory(false);
        updateStatsOnlyCurio(false);
        needSync = true;
    }

    @ZenCodeType.Method
    public void updateStatsOnlyInventory(boolean clear){
        if(clear) {
            generateBaseStats();
            needSync = true;
        }
        List<String> statsKeyList = getStatsKey();
        Inventory inventory = player.getInventory();

        for (int i = 0; i < 4; i++) {
            ItemStack item = inventory.getArmor(i);
            if(item.hasTag()){
                CompoundTag itemData = item.getOrCreateTag();
                if(!itemData.isEmpty()){
                    if(itemData.contains("sdm_data")){
                        itemData = itemData.getCompound("sdm_data");
                        for (String s : ItemStats.getKeys()) {
                            if(itemData.contains(s) && statsKeyList.contains(s)){
                                for (EntityStatsBase stat : statsList) {
                                    if(Objects.equals(stat.id, s)){
                                        if(stat instanceof EntityStatValue<?> valueStat){
                                            valueStat.addValue(itemData.getDouble(s));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    @ZenCodeType.Method
    public void updateStatsOnlyCurio(boolean clear){
        if(clear) {
            generateBaseStats();
            needSync = true;
        }
        List<String> statsKeyList = getStatsKey();
        Map<String, ICurioStacksHandler> curios = CuriosApi.getCuriosInventory(player).resolve().get().getCurios();
        for (Map.Entry<String, ICurioStacksHandler> d1 : curios.entrySet()) {
            ItemStack item = d1.getValue().getStacks().getStackInSlot(0);
            if(item.hasTag()){
                CompoundTag itemData = item.getOrCreateTag();
                if(!itemData.isEmpty()){
                    if(itemData.contains("sdm_data")){
                        itemData = itemData.getCompound("sdm_data");
                        for (String s : ItemStats.getKeys()) {
                            if(itemData.contains(s) && statsKeyList.contains(s)){
                                for (EntityStatsBase stat : statsList) {
                                    if(Objects.equals(stat.id, s)){
                                        if(stat instanceof EntityStatValue<?> valueStat){
                                            valueStat.addValue(itemData.getDouble(s));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
