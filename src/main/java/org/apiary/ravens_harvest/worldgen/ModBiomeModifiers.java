package org.apiary.ravens_harvest.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.entity.ModEntities;
import net.minecraft.util.random.Weighted;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_PALE_PUMPKIN = registerKey("add_pale_pumpkin");
    public static final ResourceKey<BiomeModifier> ADD_PALE_PUMPKIN_PILE = registerKey("add_pale_pumpkin_pile");

    public static final ResourceKey<BiomeModifier> SPAWN_RAVEN = registerKey("spawn_raven");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_PALE_PUMPKIN, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.PALE_GARDEN)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PALE_PUMPKIN_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_PALE_PUMPKIN_PILE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.PALE_GARDEN)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PALE_PUMPKIN_PILE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(SPAWN_RAVEN, BiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.DARK_FOREST),
                        biomes.getOrThrow(Biomes.BIRCH_FOREST),
                        biomes.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST),
                        biomes.getOrThrow(Biomes.WINDSWEPT_FOREST)
                ),
                new Weighted<>(new MobSpawnSettings.SpawnerData(ModEntities.RAVEN.get(), 2, 4), 15)));


    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Identifier.fromNamespaceAndPath(RavensHarvest.MODID, name));
    }
}
