package org.apiary.ravens_harvest.entity.client.raven;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class RavenRenderState extends LivingEntityRenderState {
    public boolean isFlying;

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();
}
