package org.apiary.ravens_harvest.potions;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apiary.ravens_harvest.RavensHarvest;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, RavensHarvest.MODID);

    public static final Holder<Potion> BLINDNESS = POTIONS.register("blindness",
            () -> new Potion("blindness", new MobEffectInstance(MobEffects.BLINDNESS, 300, 0)));
    public static final Holder<Potion> LONG_BLINDNESS = POTIONS.register("long_blindness",
            () -> new Potion("blindness", new MobEffectInstance(MobEffects.BLINDNESS, 600, 0)));

    public static final Holder<Potion> DARKNESS = POTIONS.register("darkness",
            () -> new Potion("darkness", new MobEffectInstance(MobEffects.DARKNESS, 300, 0)));
    public static final Holder<Potion> LONG_DARKNESS = POTIONS.register("long_darkness",
            () -> new Potion("darkness", new MobEffectInstance(MobEffects.DARKNESS, 600, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
