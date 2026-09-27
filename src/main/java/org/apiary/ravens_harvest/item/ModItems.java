package org.apiary.ravens_harvest.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apiary.ravens_harvest.RavensHarvest;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RavensHarvest.MODID);

    public static final DeferredItem<Item> RAVEN_FEATHER = ITEMS.registerSimpleItem("raven_feather");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
