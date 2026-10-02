package org.apiary.ravens_harvest.item;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.waypoints.Waypoint;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.block.ModBlocks;
import org.apiary.ravens_harvest.entity.ModEntities;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RavensHarvest.MODID);

    public static final DeferredItem<Item> RAVEN_SPAWN_EGG = ITEMS.registerItem("raven_spawn_egg",
            properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.RAVEN.get())));

    public static final DeferredItem<Item> RAVEN_FEATHER = ITEMS.registerSimpleItem("raven_feather");

    public static final DeferredItem<Item> PALE_PUMPKIN_SEEDS = ITEMS.registerItem("pale_pumpkin_seeds",
            properties -> new BlockItem(ModBlocks.PALE_PUMPKIN_STEM.get(), properties.useItemDescriptionPrefix()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
