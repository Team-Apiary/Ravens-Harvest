package org.apiary.ravens_harvest.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.block.ModBlocks;
import org.apiary.ravens_harvest.item.ModItems;

import java.util.function.Consumer;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, RavensHarvest.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        itemModels.generateFlatItem(ModItems.RAVEN_FEATHER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAVEN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PALE_PUMPKIN_SEEDS.get(), ModelTemplates.FLAT_ITEM);

        this.createPumpkins(blockModels, ModBlocks.PALE_PUMPKIN.get(), ModBlocks.CARVED_PALE_PUMPKIN.get(), ModBlocks.PALE_JACK_O_LANTERN.get());

        blockModels.createStems(ModBlocks.PALE_PUMPKIN_STEM.get(), ModBlocks.ATTACHED_PALE_PUMPKIN_STEM.get());

    }

    public void createPumpkins(BlockModelGenerators blockModels, Block pumpkin, Block carved, Block jack_o_lantern) {
        TextureMapping pumpkinTextures = TextureMapping.column(pumpkin);
        blockModels.blockStateOutput.accept(createSimpleBlock(pumpkin, plainVariant(ModelLocationUtils.getModelLocation(pumpkin))));
        this.createPumpkinVariant(blockModels, carved, pumpkinTextures);
        this.createPumpkinVariant(blockModels, jack_o_lantern, pumpkinTextures);
    }

    public void createPumpkinVariant(BlockModelGenerators blockModels, Block block, TextureMapping textures) {
        MultiVariant model = plainVariant(
                ModelTemplates.CUBE_ORIENTABLE.create(block, textures.copyAndUpdate(TextureSlot.FRONT, TextureMapping.getBlockTexture(block)), blockModels.modelOutput)
        );
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, model).with(ROTATION_HORIZONTAL_FACING));
    }
}
