package org.apiary.ravens_harvest.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.apiary.ravens_harvest.RavensHarvest;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> PALE_PUMPKIN_PLACED_KEY = registerKey("pale_pumpkin_placed");
    public static final ResourceKey<PlacedFeature> PALE_PUMPKIN_PILE_PLACED_KEY = registerKey("pale_pumpkin_pile_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, PALE_PUMPKIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PALE_PUMPKIN_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

        register(context, PALE_PUMPKIN_PILE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PALE_PUMPKIN_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(RavensHarvest.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
