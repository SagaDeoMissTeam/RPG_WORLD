package net.sdm.sdm_rpg_world.multithread.ChunkGenerator;

import net.minecraft.CrashReport;
import net.minecraft.ReportedException;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class TestTR1 extends Thread{

    public ChunkGenerator mixin;
    public WorldGenLevel p_223087_;
    public ChunkAccess p_223088_;
    public StructureManager p_223089_;
    public Map<Integer, List<Structure>> map;
    public WorldgenRandom worldgenrandom;
    public Registry<Structure> registry;
    public SectionPos sectionpos;
    public BoundingBox boundingBox;
    public ChunkPos chunkpos;
    public int k;
    public long i;
    public TestTR1(ChunkGenerator mixin, WorldGenLevel p_223087_, ChunkAccess p_223088_, StructureManager p_223089_, Map<Integer, List<Structure>> map, WorldgenRandom worldgenrandom, Registry<Structure> registry, SectionPos sectionpos, BoundingBox boundingBox, ChunkPos chunkpos, long i, int k){
        this.mixin = mixin;
        this.p_223087_ = p_223087_;
        this.p_223088_ = p_223088_;
        this.p_223089_ = p_223089_;
        this.map = map;
        this.worldgenrandom =worldgenrandom;
        this.registry =registry;
        this.sectionpos = sectionpos;
        this.boundingBox = boundingBox;
        this.chunkpos = chunkpos;
        this.i = i;
        this.k = k;
    }

    @Override
    public void run() {
        if (p_223089_.shouldGenerateStructures()) {
            int l = 0;
            for(Structure structure : map.getOrDefault(k, Collections.emptyList())) {
                worldgenrandom.setFeatureSeed(i, l, k);
                Supplier<String> supplier = () -> {
                    return registry.getResourceKey(structure).map(Object::toString).orElseGet(structure::toString);
                };

                try {
                    p_223087_.setCurrentlyGenerating(supplier);
                    p_223089_.startsForStructure(sectionpos, structure).forEach((p_223086_) -> {
                        p_223086_.placeInChunk(p_223087_, p_223089_, mixin, worldgenrandom, boundingBox, chunkpos);
                    });
                } catch (Exception exception) {
                    CrashReport crashreport1 = CrashReport.forThrowable(exception, "Feature placement");
                    crashreport1.addCategory("Feature").setDetail("Description", supplier::get);
                    throw new ReportedException(crashreport1);
                }

                ++l;
            }
        }
    }
}
