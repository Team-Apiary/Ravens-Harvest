package org.apiary.ravens_harvest.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apiary.ravens_harvest.RavensHarvest;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RavensHarvest.MODID);

    public static final Supplier<CreativeModeTab> RAVENS_HARVEST_TAB = CREATIVE_MODE_TABS.register("ravens_harvest_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAVEN_FEATHER.get()))
                    .title(Component.translatable("itemGroup.ravens_harvest"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.RAVEN_FEATHER);

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
