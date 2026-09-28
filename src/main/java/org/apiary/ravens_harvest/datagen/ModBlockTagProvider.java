package org.apiary.ravens_harvest.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.internal.NeoForgeBlockTagsProvider;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, RavensHarvest.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.PALE_PUMPKIN.get())
                .add(ModBlocks.CARVED_PALE_PUMPKIN.get())
                .add(ModBlocks.PALE_JACK_O_LANTERN.get());

        tag(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.PALE_PUMPKIN.get())
                .add(ModBlocks.CARVED_PALE_PUMPKIN.get())
                .add(ModBlocks.PALE_JACK_O_LANTERN.get());

        tag(BlockTags.ENDERMAN_HOLDABLE)
                .add(ModBlocks.PALE_PUMPKIN.get())
                .add(ModBlocks.CARVED_PALE_PUMPKIN.get());

        tag(Tags.Blocks.PUMPKINS_NORMAL)
                .add(ModBlocks.PALE_PUMPKIN.get());
        tag(Tags.Blocks.PUMPKINS_CARVED)
                .add(ModBlocks.CARVED_PALE_PUMPKIN.get());
        tag(Tags.Blocks.PUMPKINS_JACK_O_LANTERNS)
                .add(ModBlocks.PALE_JACK_O_LANTERN.get());

        tag(BlockTags.CROPS)
                .add(ModBlocks.PALE_PUMPKIN_STEM.get());
        tag(BlockTags.MAINTAINS_FARMLAND)
                .add(ModBlocks.PALE_PUMPKIN_STEM.get())
                .add(ModBlocks.ATTACHED_PALE_PUMPKIN_STEM.get());

    }
}
