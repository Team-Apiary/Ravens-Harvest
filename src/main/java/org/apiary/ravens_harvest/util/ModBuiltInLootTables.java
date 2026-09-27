package org.apiary.ravens_harvest.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import org.apiary.ravens_harvest.RavensHarvest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ModBuiltInLootTables extends BuiltInLootTables {

    //Loot Table IDs
    public static ResourceKey<LootTable> CARVE_PALE_PUMPKIN = ResourceKey.create(Registries.LOOT_TABLE,Identifier.fromNamespaceAndPath(RavensHarvest.MODID, "carve/pale_pumpkin"));

    public static void registerLootTables() {
    }
}
