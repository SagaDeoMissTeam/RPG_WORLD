package net.sdm.sdm_rpg_world.multithread.ChunkGenerator;

import net.minecraft.CrashReport;
import net.minecraft.ReportedException;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.FeatureSorter;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Supplier;

public class TestTR2 extends Thread{
    public ChunkGenerator mixin;
    public WorldGenLevel p_223087_;
    public ChunkAccess p_223088_;
    public StructureManager p_223089_;
    public int[] aint;
    public int k1;
    public Registry<PlacedFeature> registry1;
    public WorldgenRandom worldgenrandom;
    public FeatureSorter.StepFeatureData featuresorter$stepfeaturedata;
    public BlockPos blockpos;
    public long i;
    public int k;
    public TestTR2(ChunkGenerator mixin, WorldGenLevel p_223087_, ChunkAccess p_223088_, StructureManager p_223089_, int[] aint, int k1, Registry<PlacedFeature> registry1, WorldgenRandom worldgenrandom, FeatureSorter.StepFeatureData featuresorter$stepfeaturedata, BlockPos blockpos, long i, int k){
        this.mixin = mixin;
        this.p_223087_ = p_223087_;
        this.p_223088_ = p_223088_;
        this.p_223089_ = p_223089_;
        this.aint = aint;
        this.k1 = k1;
        this.registry1 = registry1;
        this.worldgenrandom = worldgenrandom;
        this.featuresorter$stepfeaturedata = featuresorter$stepfeaturedata;
        this.blockpos = blockpos;
        this.i = i;
        this.k = k;

    }

    @Override
    public void run() {
        int l1 = aint[k1];
        PlacedFeature placedfeature = featuresorter$stepfeaturedata.features().get(l1);
        Supplier<String> supplier1 = () -> {
            return registry1.getResourceKey(placedfeature).map(Object::toString).orElseGet(placedfeature::toString);
        };
        worldgenrandom.setFeatureSeed(i, l1, k);

        try {
            p_223087_.setCurrentlyGenerating(supplier1);
            placedfeature.placeWithBiomeCheck(p_223087_, mixin, worldgenrandom, blockpos);
        } catch (Exception exception1) {
            CrashReport crashreport2 = CrashReport.forThrowable(exception1, "Feature placement");
            crashreport2.addCategory("Feature").setDetail("Description", supplier1::get);
            throw new ReportedException(crashreport2);
        }
    }
}
