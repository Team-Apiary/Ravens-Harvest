package org.apiary.ravens_harvest;

import org.apiary.ravens_harvest.item.ModCreativeModeTabs;
import org.apiary.ravens_harvest.item.ModItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(RavensHarvest.MODID)
public class RavensHarvest {
    public static final String MODID = "ravens_harvest";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RavensHarvest(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing the unkindness.");

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
    }
}
