package net.sdm.sdm_rpg_world.mixin;

import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.CrashReport;
import net.minecraft.ReportedException;
import net.minecraft.SharedConstants;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.FeatureSorter;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.RandomSupport;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.MinecraftForge;
import net.sdm.sdm_rpg_world.core.events.FeatureGenerationEvent;
import net.sdm.sdm_rpg_world.core.events.StructureGenerationEvent;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Mixin(ChunkGenerator.class)
public class ChunkGenerationMixin {
//    @Shadow @Final private Supplier<List<FeatureSorter.StepFeatureData>> featuresPerStep;
//    @Shadow @Final protected BiomeSource biomeSource;
//    @Shadow @Final private Function<Holder<Biome>, BiomeGenerationSettings> generationSettingsGetter;
//    private ChunkGenerator instance = (ChunkGenerator)(Object)this;
//
//    @Inject(method = "applyBiomeDecoration", at = @At("HEAD"), cancellable = true)
//    public void sdmrpg$applyBiomeDecoration(WorldGenLevel p_223087_, ChunkAccess p_223088_, StructureManager p_223089_, CallbackInfo ci){
//        ci.cancel();
//        ChunkPos chunkpos = p_223088_.getPos();
//        if (!SharedConstants.debugVoidTerrain(chunkpos)) {
//            SectionPos sectionpos = SectionPos.of(chunkpos, p_223087_.getMinSection());
//            BlockPos blockpos = sectionpos.origin();
//            Registry<Structure> registry = p_223087_.registryAccess().registryOrThrow(Registries.STRUCTURE);
//            Map<Integer, List<Structure>> map = registry.stream().collect(Collectors.groupingBy((p_223103_) -> {
//                return p_223103_.step().ordinal();
//            }));
//            List<FeatureSorter.StepFeatureData> list = this.featuresPerStep.get();
//            WorldgenRandom worldgenrandom = new WorldgenRandom(new XoroshiroRandomSource(RandomSupport.generateUniqueSeed()));
//            long i = worldgenrandom.setDecorationSeed(p_223087_.getSeed(), blockpos.getX(), blockpos.getZ());
//            Set<Holder<Biome>> set = new ObjectArraySet<>();
//            ChunkPos.rangeClosed(sectionpos.chunk(), 1).forEach((p_223093_) -> {
//                ChunkAccess chunkaccess = p_223087_.getChunk(p_223093_.x, p_223093_.z);
//
//                for(LevelChunkSection levelchunksection : chunkaccess.getSections()) {
//                    levelchunksection.getBiomes().getAll(set::add);
//                }
//
//            });
//            set.retainAll(this.biomeSource.possibleBiomes());
//            int j = list.size();
//
//            try {
//                Registry<PlacedFeature> registry1 = p_223087_.registryAccess().registryOrThrow(Registries.PLACED_FEATURE);
//                int i1 = Math.max(GenerationStep.Decoration.values().length, j);
//
//                for(int k = 0; k < i1; ++k) {
//                    int l = 0;
//                    if (p_223089_.shouldGenerateStructures()) {
//                        for(Structure structure : map.getOrDefault(k, Collections.emptyList())) {
//                            worldgenrandom.setFeatureSeed(i, l, k);
//                            Supplier<String> supplier = () -> {
//                                return registry.getKey(structure).toString();
//                            };
//
//                            try {
//                                BoundingBox writebleZone = getWritableArea(p_223088_);
//                                StructureGenerationEvent event = new StructureGenerationEvent(supplier.get(), chunkpos, writebleZone);
//                                MinecraftForge.EVENT_BUS.post(event);
//                                if(!event.isCanceled()) {
//                                    p_223087_.setCurrentlyGenerating(event::getRegistry);
//                                    p_223089_.startsForStructure(sectionpos, structure).forEach((p_223086_) -> {
//                                        p_223086_.placeInChunk(p_223087_, p_223089_, instance, worldgenrandom, event.boundingBox, event.pos);
//                                    });
//                                }
//                            } catch (Exception exception) {
//                                CrashReport crashreport1 = CrashReport.forThrowable(exception, "Feature placement");
//                                crashreport1.addCategory("Feature").setDetail("Description", supplier::get);
//                                throw new ReportedException(crashreport1);
//                            }
//
//                            ++l;
//                        }
//                    }
//
//                    if (k < j) {
//                        IntSet intset = new IntArraySet();
//
//                        for(Holder<Biome> holder : set) {
//                            List<HolderSet<PlacedFeature>> list1 = this.generationSettingsGetter.apply(holder).features();
//                            if (k < list1.size()) {
//                                HolderSet<PlacedFeature> holderset = list1.get(k);
//                                FeatureSorter.StepFeatureData featuresorter$stepfeaturedata1 = list.get(k);
//                                holderset.stream().map(Holder::value).forEach((p_223174_) -> {
//                                    intset.add(featuresorter$stepfeaturedata1.indexMapping().applyAsInt(p_223174_));
//                                });
//                            }
//                        }
//
//                        int j1 = intset.size();
//                        int[] aint = intset.toIntArray();
//                        Arrays.sort(aint);
//                        FeatureSorter.StepFeatureData featuresorter$stepfeaturedata = list.get(k);
//
//                        for(int k1 = 0; k1 < j1; ++k1) {
//                            int l1 = aint[k1];
//                            PlacedFeature placedfeature = featuresorter$stepfeaturedata.features().get(l1);
//                            Supplier<String> supplier1 = () -> {
//                                return registry1.getKey(placedfeature).toString();
//                            };
//                            worldgenrandom.setFeatureSeed(i, l1, k);
//
//                            try {
//                                FeatureGenerationEvent event = new FeatureGenerationEvent(supplier1.get(), blockpos);
//                                MinecraftForge.EVENT_BUS.post(event);
//                                if(!event.isCanceled()) {
//                                    p_223087_.setCurrentlyGenerating(event::getRegistry);
//                                    placedfeature.placeWithBiomeCheck(p_223087_, instance, worldgenrandom, event.position);
//                                }
//                            } catch (Exception exception1) {
//                                CrashReport crashreport2 = CrashReport.forThrowable(exception1, "Feature placement");
//                                crashreport2.addCategory("Feature").setDetail("Description", supplier1::get);
//                                throw new ReportedException(crashreport2);
//                            }
//                        }
//                    }
//                }
//
//                p_223087_.setCurrentlyGenerating((Supplier<String>)null);
//            } catch (Exception exception2) {
//                CrashReport crashreport = CrashReport.forThrowable(exception2, "Biome decoration");
//                crashreport.addCategory("Generation").setDetail("CenterX", chunkpos.x).setDetail("CenterZ", chunkpos.z).setDetail("Seed", i);
//                throw new ReportedException(crashreport);
//            }
//        }
//    }
//
//    private static BoundingBox getWritableArea(ChunkAccess p_187718_) {
//        ChunkPos chunkpos = p_187718_.getPos();
//        int i = chunkpos.getMinBlockX();
//        int j = chunkpos.getMinBlockZ();
//        LevelHeightAccessor levelheightaccessor = p_187718_.getHeightAccessorForGeneration();
//        int k = levelheightaccessor.getMinBuildHeight() + 1;
//        int l = levelheightaccessor.getMaxBuildHeight() - 1;
//        return new BoundingBox(i, k, j, i + 15, l, j + 15);
//    }

}
