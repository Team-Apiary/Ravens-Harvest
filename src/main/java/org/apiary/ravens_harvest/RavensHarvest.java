package org.apiary.ravens_harvest;

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
    }
}
