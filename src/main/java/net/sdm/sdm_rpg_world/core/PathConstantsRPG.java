package net.sdm.sdm_rpg_world.core;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class PathConstantsRPG {

    public static final Path SDM_ENGINE;
    public static final Path RPG_STRUCTURES;
    public static final Path CONFIGS;
    public static final Path CONFIG_FILE;

    static {
        SDM_ENGINE = FMLPaths.CONFIGDIR.get().resolve("SDMEngine");
        RPG_STRUCTURES = SDM_ENGINE.resolve("Structure RPG");
        CONFIGS = RPG_STRUCTURES.resolve("config");
        CONFIG_FILE = CONFIGS.resolve("common.snbt");
    }
}
