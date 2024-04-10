package net.sdm.sdm_rpg_world.core.bestiary;

import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.INBTSerializable;
import net.sdm.sdm_rpg_world.net.ResetBestiaryDataS2C;
import net.sdm.sdm_rpg_world.net.SyncBestiaryDataS2C;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestiaryManager extends SimplePreparableReloadListener<Void> {
    public static BestiaryManager INSTANCE = new BestiaryManager();
    public static List<BestiaryPage> bestiaryPages = new ArrayList<>();
    public static Map<String, BestiaryPage> bestiaryPageMap = new HashMap<>();


    public static void register(BestiaryPage bestiaryPage){
        bestiaryPage.numID = bestiaryPages.size();
        bestiaryPages.add(bestiaryPage);
        bestiaryPageMap.put(bestiaryPage.registryID, bestiaryPage);
    }


    public static void syncData(MinecraftServer server){
        new ResetBestiaryDataS2C().sendToAll(server);
        for (BestiaryPage bestiaryPage : bestiaryPages) {
            new SyncBestiaryDataS2C(bestiaryPage.serializeNBT()).sendToAll(server);
        }
    }

    @Override
    protected Void prepare(ResourceManager p_10796_, ProfilerFiller p_10797_) {
        return null;
    }

    @Override
    protected void apply(Void p_10793_, ResourceManager p_10794_, ProfilerFiller p_10795_) {
        bestiaryPages.clear();
        bestiaryPageMap.clear();
    }

    public static CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        ListTag listTag = new ListTag();
        for (BestiaryPage bestiaryPage : bestiaryPages) {
            listTag.add(bestiaryPage.serializeNBT());
        }
        nbt.put("pages", listTag);
        return nbt;
    }


    public static void deserializeNBT(CompoundTag nbt) {
        bestiaryPages.clear();
        bestiaryPageMap.clear();
        ListTag listTag = (ListTag) nbt.get("pages");
        for (Tag tag : listTag) {
            SNBTCompoundTag data = SNBTCompoundTag.of(tag);
            switch (BestiaryPageType.valueOf(data.getString("type"))){
                case BASIC -> {
                    BestiaryPage page = new BestiaryPage();
                    page.deserializeNBT(data);
                    bestiaryPages.add(page);
                    bestiaryPageMap.put(page.registryID,page);
                }
                case ENTITY -> {
                    EntityBestiaryPage page = new EntityBestiaryPage();
                    page.deserializeNBT(data);
                    bestiaryPages.add(page);
                    bestiaryPageMap.put(page.registryID,page);
                }
            }
        }
    }
}
