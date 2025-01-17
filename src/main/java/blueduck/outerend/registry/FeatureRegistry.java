package blueduck.outerend.registry;

import blueduck.outerend.OuterEndMod;
import blueduck.outerend.features.*;
import blueduck.outerend.features.crystalcragsurface.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureRegistry {
    public static final ConfiguredFeature<NoFeatureConfig, AzureTreeFeature> AZURE_TREE =
            (ConfiguredFeature<NoFeatureConfig, AzureTreeFeature>) newConfiguredFeature(
                    "azure_tree",
                    newFeature(
                            "azure_tree",
                            new AzureTreeFeature(NoFeatureConfig.field_236558_a_)
                    ).withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG)
                            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(2, 1, 2)))
            );
    public static final ConfiguredFeature<NoFeatureConfig, EndGrassDecorator> END_GRASS_DECORATOR =
            (ConfiguredFeature<NoFeatureConfig, EndGrassDecorator>) newConfiguredFeature(
                    "end_grass_decorator",
                    newFeature(
                            "end_grass_decorator",
                            new EndGrassDecorator(NoFeatureConfig.field_236558_a_)
                    ).withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG)
                            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(2, 1, 8)))
            );
    public static final ConfiguredFeature<NoFeatureConfig, CrystalSpikeFeature> CRYSTAL_SPIKE_FEATURE =
            (ConfiguredFeature<NoFeatureConfig, CrystalSpikeFeature>) newConfiguredFeature(
                    "crystal_spike_feature",
                    newFeature(
                            "crystal_spike_feature",
                            new CrystalSpikeFeature(NoFeatureConfig.field_236558_a_)
                    ).withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG)
                            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.2F, 2)))
            );

    public static final ConfiguredFeature<NoFeatureConfig, LargeCrystalSpikeFeature> LARGE_CRYSTAL_SPIKE_FEATURE =
            (ConfiguredFeature<NoFeatureConfig, LargeCrystalSpikeFeature>) newConfiguredFeature(
                    "large_crystal_spike_feature",
                    newFeature(
                            "large_crystal_spike_feature",
                            new LargeCrystalSpikeFeature(NoFeatureConfig.field_236558_a_)
                    ).withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG)
                            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.2F, 2)))
            );

    public static final ConfiguredFeature<NoFeatureConfig, VioliteBumpFeature> VIOLITE_BUMP_FEATURE =
            (ConfiguredFeature<NoFeatureConfig, VioliteBumpFeature>) newConfiguredFeature(
                    "violite_bump_feature",
                    newFeature(
                            "violite_bump_feature",
                            new VioliteBumpFeature(NoFeatureConfig.field_236558_a_)
                    ).withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG)
                            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 1, 10)))
            );

    public static final ConfiguredFeature<NoFeatureConfig, VioliteRockBumpFlowerFeature> VIOLITE_ROCK_BUMP_FLOWER_FEATURE =
            (ConfiguredFeature<NoFeatureConfig, VioliteRockBumpFlowerFeature>) newConfiguredFeature(
                    "violite_rock_bump_flower_feature",
                    newFeature(
                            "violite_rock_bump_flower_feature",
                            new VioliteRockBumpFlowerFeature(NoFeatureConfig.field_236558_a_)
                    ).withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG)
                            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.1F, 1)))
            );

    public static final ConfiguredFeature<NoFeatureConfig, VioliteDeadRainbowFeature> VIOLITE_DEAD_RAINBOW_FEATURE =
            (ConfiguredFeature<NoFeatureConfig, VioliteDeadRainbowFeature>) newConfiguredFeature(
                    "violite_dead_rainbow_feature",
                    newFeature(
                            "violite_dead_rainbow_feature",
                            new VioliteDeadRainbowFeature(NoFeatureConfig.field_236558_a_)
                    ).withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG)
                            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1)))
            );
    
    public static final ConfiguredFeature<NoFeatureConfig, CragMoonFeature> CRAG_MOON_FEATURE =
            (ConfiguredFeature<NoFeatureConfig, CragMoonFeature>) newConfiguredFeature(
                    "crag_moon_feature",
                    newFeature(
                            "crag_moon_feature",
                            new CragMoonFeature(NoFeatureConfig.field_236558_a_)
                    ).withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG)
                            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.03F, 1)))
            );
    
    private static <FC extends IFeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> newConfiguredFeature(String registryName, ConfiguredFeature<FC, F> configuredFeature) {
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(OuterEndMod.MODID, registryName), configuredFeature);
        return configuredFeature;
    }

    private static <FC extends IFeatureConfig> Feature<FC> newFeature(String registryName, Feature<FC> configuredFeature) {
        //thank you noeppi_noeppi
        configuredFeature.setRegistryName(new ResourceLocation(OuterEndMod.MODID, registryName));
        ForgeRegistries.FEATURES.register(configuredFeature);
        return configuredFeature;
    }
}
