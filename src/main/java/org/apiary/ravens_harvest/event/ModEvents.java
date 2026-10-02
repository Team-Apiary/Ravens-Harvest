package org.apiary.ravens_harvest.event;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.entity.ModEntities;
import org.apiary.ravens_harvest.entity.custom.RavenEntity;
import org.apiary.ravens_harvest.item.ModItems;
import org.apiary.ravens_harvest.potions.ModPotions;

@EventBusSubscriber(modid = RavensHarvest.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.RAVEN.get(), RavenEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(Potions.AWKWARD, ModItems.RAVEN_FEATHER.asItem(), ModPotions.BLINDNESS);
        event.getBuilder().addMix(ModPotions.BLINDNESS, Items.REDSTONE.asItem(), ModPotions.LONG_BLINDNESS);

        event.getBuilder().addMix(ModPotions.BLINDNESS, Items.FERMENTED_SPIDER_EYE.asItem(), ModPotions.DARKNESS);
        event.getBuilder().addMix(ModPotions.LONG_BLINDNESS, Items.FERMENTED_SPIDER_EYE.asItem(), ModPotions.LONG_DARKNESS);
        event.getBuilder().addMix(ModPotions.DARKNESS, Items.REDSTONE.asItem(), ModPotions.LONG_DARKNESS);
    }
}
