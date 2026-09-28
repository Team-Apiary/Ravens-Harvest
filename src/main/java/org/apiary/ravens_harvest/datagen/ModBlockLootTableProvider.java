package org.apiary.ravens_harvest.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.apiary.ravens_harvest.block.ModBlocks;
import org.apiary.ravens_harvest.item.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        add(ModBlocks.ATTACHED_PALE_PUMPKIN_STEM.get(), createAttachedStemDrops(ModBlocks.ATTACHED_PALE_PUMPKIN_STEM.get(), ModItems.PALE_PUMPKIN_SEEDS.get()));
        add(ModBlocks.PALE_PUMPKIN_STEM.get(), createAttachedStemDrops(ModBlocks.PALE_PUMPKIN_STEM.get(), ModItems.PALE_PUMPKIN_SEEDS.get()));

        dropSelf(ModBlocks.PALE_PUMPKIN.get());
        dropSelf(ModBlocks.CARVED_PALE_PUMPKIN.get());
        dropSelf(ModBlocks.PALE_JACK_O_LANTERN.get());

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
