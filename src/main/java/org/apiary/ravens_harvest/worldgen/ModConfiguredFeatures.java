package org.apiary.ravens_harvest.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SimpleRandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.block.ModBlocks;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALE_PUMPKIN_KEY = registerKey("pale_pumpkin");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALE_PUMPKIN_PILE_KEY = registerKey("pale_pumpkin_pile");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        FeatureUtils.register(context, PALE_PUMPKIN_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PALE_PUMPKIN.get())));


        FeatureUtils.register(
                context,
                PALE_PUMPKIN_PILE_KEY,
                Feature.BLOCK_PILE,
                new BlockPileConfiguration(
                        new WeightedStateProvider(
                                WeightedList.<BlockState>builder().add(ModBlocks.PALE_PUMPKIN.get().defaultBlockState(), 19).add(ModBlocks.PALE_JACK_O_LANTERN.get().defaultBlockState(), 1)
                        )
                )
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(RavensHarvest.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
