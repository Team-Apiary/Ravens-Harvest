package org.apiary.ravens_harvest.entity.client.raven;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.entity.client.ModModelLayerLocations;
import org.apiary.ravens_harvest.entity.custom.RavenEntity;

public class RavenRenderer extends MobRenderer<RavenEntity, RavenRenderState, RavenModel> {
    public RavenRenderer(EntityRendererProvider.Context context) {
        super(context, new RavenModel(context.bakeLayer(ModModelLayerLocations.RAVEN)), 0.65f);
    }

    @Override
    public Identifier getTextureLocation(RavenRenderState state) {
        return Identifier.fromNamespaceAndPath(RavensHarvest.MODID, "textures/entity/raven/raven.png");
    }

    @Override
    public RavenRenderState createRenderState() {
        return new RavenRenderState();
    }

    @Override
    public void extractRenderState(RavenEntity entity, RavenRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isFlying = entity.isFlying();

        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.flyingAnimationState.copyFrom(entity.flyingAnimationState);
    }
}
