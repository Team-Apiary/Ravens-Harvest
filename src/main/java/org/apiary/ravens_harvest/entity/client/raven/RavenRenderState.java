package org.apiary.ravens_harvest.entity.client.raven;

import net.minecraft.client.model.animal.parrot.ParrotModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RavenRenderState extends LivingEntityRenderState {
    public ParrotModel.Pose pose = ParrotModel.Pose.FLYING;

    public final AnimationState flyingAnimationState = new AnimationState();
}
