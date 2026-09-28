package org.apiary.ravens_harvest.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.block.ModBlocks;
import org.apiary.ravens_harvest.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, RavensHarvest.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {

        tag(Tags.Items.FEATHERS)
                .add(ModItems.RAVEN_FEATHER.get());


        tag(Tags.Items.CROPS_PUMPKIN)
                .add(ModBlocks.PALE_PUMPKIN.asItem());
        tag(Tags.Items.PUMPKINS_NORMAL)
                .add(ModBlocks.PALE_PUMPKIN.asItem());
        tag(Tags.Items.PUMPKINS_CARVED)
                .add(ModBlocks.CARVED_PALE_PUMPKIN.asItem());
        tag(Tags.Items.PUMPKINS_JACK_O_LANTERNS)
                .add(ModBlocks.PALE_JACK_O_LANTERN.asItem());


        tag(ItemTags.EQUIPPABLE_ENCHANTABLE)
                .add(ModBlocks.CARVED_PALE_PUMPKIN.asItem());
        tag(ItemTags.GAZE_DISGUISE_EQUIPMENT)
                .add(ModBlocks.CARVED_PALE_PUMPKIN.asItem());
        tag(ItemTags.MAP_INVISIBILITY_EQUIPMENT)
                .add(ModBlocks.CARVED_PALE_PUMPKIN.asItem());
        tag(ItemTags.VANISHING_ENCHANTABLE)
                .add(ModBlocks.CARVED_PALE_PUMPKIN.asItem());


        tag(Tags.Items.SEEDS_PUMPKIN)
                .add(ModItems.PALE_PUMPKIN_SEEDS.get());
        tag(ItemTags.PARROT_FOOD)
                .add(ModItems.PALE_PUMPKIN_SEEDS.get());
        tag(ItemTags.CHICKEN_FOOD)
                .add(ModItems.PALE_PUMPKIN_SEEDS.get());

    }
}
