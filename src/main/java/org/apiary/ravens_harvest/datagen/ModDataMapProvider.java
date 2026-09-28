package org.apiary.ravens_harvest.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import org.apiary.ravens_harvest.block.ModBlocks;
import org.apiary.ravens_harvest.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    public ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {

        builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModItems.PALE_PUMPKIN_SEEDS.getId(), new Compostable(0.3f), false)
                .add(ModBlocks.PALE_PUMPKIN.getId(), new Compostable(0.65f), false)
                .add(ModBlocks.CARVED_PALE_PUMPKIN.getId(), new Compostable(0.65f), false);

    }
}
