package org.apiary.ravens_harvest.entity.client;

import net.minecraft.client.model.animal.parrot.ParrotModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.animal.parrot.Parrot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RavenRenderState extends LivingEntityRenderState {
    public float flapAngle;
    public RavenModel.Pose pose = RavenModel.Pose.FLYING;
}
