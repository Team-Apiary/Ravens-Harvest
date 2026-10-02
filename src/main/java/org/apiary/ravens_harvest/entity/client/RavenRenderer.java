package org.apiary.ravens_harvest.entity.client;

import net.minecraft.client.model.animal.parrot.ParrotModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ParrotRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.parrot.Parrot;
import org.apiary.ravens_harvest.RavensHarvest;
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

    public void extractRenderState(RavenEntity entity, RavenRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        float flap = Mth.lerp(partialTicks, entity.oFlap, entity.flap);
        float flapSpeed = Mth.lerp(partialTicks, entity.oFlapSpeed, entity.flapSpeed);
        state.flapAngle = (Mth.sin(flap) + 1.0F) * flapSpeed;
        state.pose = RavenModel.getPose(entity);
    }
}
