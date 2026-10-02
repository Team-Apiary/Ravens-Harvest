package org.apiary.ravens_harvest.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.entity.custom.RavenEntity;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.createEntities(RavensHarvest.MODID);

    public static final ResourceKey<EntityType<?>> RAVEN_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(RavensHarvest.MODID, "raven"));


    public static final Supplier<EntityType<RavenEntity>> RAVEN = ENTITY_TYPES.register("raven",
            () -> EntityType.Builder.of(RavenEntity::new, MobCategory.CREATURE).sized(0.5F, 0.9F).build(RAVEN_KEY));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
