package org.apiary.ravens_harvest.block;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.block.custom.CarvedPalePumpkinBlock;
import org.apiary.ravens_harvest.block.custom.PalePumpkinBlock;
import org.apiary.ravens_harvest.item.ModItems;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(RavensHarvest.MODID);

    public static final DeferredBlock<AttachedStemBlock> ATTACHED_PALE_PUMPKIN_STEM = registerBlock("attached_pale_pumpkin_stem",
            properties -> new AttachedStemBlock(ModBlocks.PALE_PUMPKIN_STEM.getKey(), ModBlocks.PALE_PUMPKIN.getKey(), ModItems.PALE_PUMPKIN_SEEDS.getKey(), BlockTags.SUPPORTS_PUMPKIN_STEM,
                    properties
                            .mapColor(MapColor.PLANT)
                            .noCollision()
                            .instabreak()
                            .sound(SoundType.WOOD)
                            .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<StemBlock> PALE_PUMPKIN_STEM = registerBlock("pale_pumpkin_stem",
            properties -> new StemBlock(ModBlocks.PALE_PUMPKIN.getKey(), ModBlocks.ATTACHED_PALE_PUMPKIN_STEM.getKey(), ModItems.PALE_PUMPKIN_SEEDS.getKey(), BlockTags.SUPPORTS_PUMPKIN_STEM, BlockTags.SUPPORTS_PUMPKIN_STEM_FRUIT,
                    properties
                            .mapColor(MapColor.PLANT)
                            .noCollision()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.HARD_CROP)
                            .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<PalePumpkinBlock> PALE_PUMPKIN = registerBlock("pale_pumpkin",
            properties -> new PalePumpkinBlock(properties
                    .mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .instrument(NoteBlockInstrument.DIDGERIDOO)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<CarvedPalePumpkinBlock> CARVED_PALE_PUMPKIN = registerBlock("carved_pale_pumpkin",
            properties -> new CarvedPalePumpkinBlock(properties
                    .mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .isValidSpawn(Blocks::always)
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<CarvedPalePumpkinBlock> PALE_JACK_O_LANTERN = registerBlock("pale_jack_o_lantern",
            properties -> new CarvedPalePumpkinBlock(properties
                    .mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .lightLevel(statex -> 15)
                    .isValidSpawn(Blocks::always)
                    .pushReaction(PushReaction.DESTROY)
            ));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
