package org.apiary.ravens_harvest.entity.client.raven;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class RavenModel extends EntityModel<RavenRenderState> {
    private final ModelPart raven;
    private final ModelPart body;
    private final ModelPart wing_left;
    private final ModelPart wing_right;
    private final ModelPart head;
    private final ModelPart tail;
    private final ModelPart leg_left;
    private final ModelPart leg_right;
    private final KeyframeAnimation idleAnimation;
    private final KeyframeAnimation flyingAnimation;

    public RavenModel(ModelPart root) {
        super(root);
        this.raven = root.getChild("raven");
        this.body = this.raven.getChild("body");
        this.wing_left = this.body.getChild("wing_left");
        this.wing_right = this.body.getChild("wing_right");
        this.head = this.raven.getChild("head");
        this.tail = this.raven.getChild("tail");
        this.leg_left = this.raven.getChild("leg_left");
        this.leg_right = this.raven.getChild("leg_right");

        this.idleAnimation = RavenAnimation.IDLE.bake(root);
        this.flyingAnimation = RavenAnimation.FLYING.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition raven = partdefinition.addOrReplaceChild("raven", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = raven.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.307F, -2.1583F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.6642F, -1.5757F, 0.8727F, 0.0F, 0.0F));

        PartDefinition wing_left = body.addOrReplaceChild("wing_left", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, -0.1963F, -2.265F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.8465F, 0.3291F, 0.1745F, 0.0F, 0.0F));

        PartDefinition wing_right = body.addOrReplaceChild("wing_right", CubeListBuilder.create().texOffs(9, 17).addBox(-1.0F, -0.1963F, -2.265F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -0.8465F, 0.3291F, 0.1745F, 0.0F, 0.0F));

        PartDefinition head = raven.addOrReplaceChild("head", CubeListBuilder.create().texOffs(9, 10).addBox(-1.0F, -2.8F, -1.8F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(20, 13).addBox(-1.0F, -2.8F, -2.8F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 17).addBox(-1.0F, -3.8F, -2.8F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(13, 0).addBox(0.0F, -4.8F, -1.8F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(20, 9).addBox(-0.5F, -3.06F, -4.64F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.5F, -2.0F));

        PartDefinition tail = raven.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 19).addBox(-1.4F, 0.0243F, -0.6231F, 2.8F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 1.5F, 1.309F, 0.0F, 0.0F));

        PartDefinition leg_left = raven.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(22, 0).addBox(-0.4F, 0.0F, -0.5F, 0.9F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.9F, -2.0F, 0.0F));

        PartDefinition leg_right = raven.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(22, 4).addBox(-0.5F, 0.0F, -0.5F, 0.9F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.9F, -2.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }



    @Override
    public void setupAnim(RavenRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(state.yRot, state.xRot);

        if (state.isFlying) {
            this.flyingAnimation.apply(state.flyingAnimationState, state.ageInTicks, 1f);
        }

        this.idleAnimation.apply(state.idleAnimationState, state.ageInTicks, 1f);
    }


    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch *  ((float)Math.PI / 180f);
    }
}
