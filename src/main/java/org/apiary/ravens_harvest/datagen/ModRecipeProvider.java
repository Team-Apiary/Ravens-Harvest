package org.apiary.ravens_harvest.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import org.apiary.ravens_harvest.block.ModBlocks;
import org.apiary.ravens_harvest.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_JACK_O_LANTERN.get())
                .pattern("C")
                .pattern("T")
                .define('C', ModBlocks.CARVED_PALE_PUMPKIN.get())
                .define('T', Blocks.TORCH.asItem())
                .unlockedBy(getHasName(ModBlocks.CARVED_PALE_PUMPKIN.get()), has(ModBlocks.CARVED_PALE_PUMPKIN.get()))
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.PALE_PUMPKIN_SEEDS.get(), 4)
                .requires(ModBlocks.PALE_PUMPKIN)
                .unlockedBy(getHasName(ModBlocks.PALE_PUMPKIN.get()), has(ModBlocks.PALE_PUMPKIN.get()))
                .save(output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "MCCourse Recipes";
        }
    }
}
