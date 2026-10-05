package org.apiary.ravens_harvest.entity.client;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.animation.definitions.BatAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.animal.parrot.ParrotModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.BatRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.parrot.Parrot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.entity.custom.RavenEntity;

public class RavenModel extends EntityModel<RavenRenderState> {
    private final ModelPart base;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart legs;
    private final ModelPart left_leg;
    private final ModelPart right_leg;
    private final ModelPart left_wing;
    private final ModelPart right_wing;
    private final KeyframeAnimation flyingAnimation;

    public RavenModel(ModelPart root) {
        super(root);
        this.base = root.getChild("base");
        this.body = this.base.getChild("body");
        this.head = this.base.getChild("head");
        this.legs = this.base.getChild("legs");
        this.left_leg = this.legs.getChild("left_leg");
        this.right_leg = this.legs.getChild("right_leg");
        this.left_wing = this.base.getChild("left_wing");
        this.right_wing = this.base.getChild("right_wing");
        this.flyingAnimation = RavenAnimation.FLYING.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = base.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 7).addBox(-1.5F, -4.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = base.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(13, 12).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.5F, -0.5F));

        PartDefinition legs = base.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 0.0F));

        PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 14).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 0.0F));

        PartDefinition left_foot_r1 = left_leg.addOrReplaceChild("left_foot_r1", CubeListBuilder.create().texOffs(3, 14).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(6, 14).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

        PartDefinition right_foot_r1 = right_leg.addOrReplaceChild("right_foot_r1", CubeListBuilder.create().texOffs(9, 14).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition left_wing = base.addOrReplaceChild("left_wing", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_wing_r1 = left_wing.addOrReplaceChild("left_wing_r1", CubeListBuilder.create().texOffs(13, 0).addBox(0.0F, 0.0F, -1.5F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -3.5F, 0.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition right_wing = base.addOrReplaceChild("right_wing", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_wing_r1 = right_wing.addOrReplaceChild("right_wing_r1", CubeListBuilder.create().texOffs(13, 6).addBox(0.0F, 0.0F, -1.5F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -3.5F, 0.0F, 0.0F, 0.0F, 0.1745F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    public void setupAnim(RavenRenderState state) {
        super.setupAnim(state);
        this.prepare(state.pose);
        switch (state.pose) {
            case FLYING:
                RavensHarvest.LOGGER.info("Flying");
                this.flyingAnimation.apply(state.flyAnimationState, state.ageInTicks);
            case STANDING:
                this.left_leg.xRot = this.left_leg.xRot + Mth.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
                this.right_leg.xRot = this.right_leg.xRot + Mth.cos(state.walkAnimationPos * 0.6662F + (float) Math.PI) * 1.4F * state.walkAnimationSpeed;
            default:
                break;
        }

    }

    private void prepare(RavenModel.Pose pose) {
        switch (pose) {
            case FLYING:
                //this.left_leg.xRot += (float) (Math.PI * 2.0 / 9.0);
                //this.right_leg.xRot += (float) (Math.PI * 2.0 / 9.0);
                this.base.xRot += (float) (Math.PI / 2.25);
            case STANDING:
                this.base.xRot = 0;
            default:
                break;
        }
    }

    public static RavenModel.Pose getPose(RavenEntity entity) {
        return entity.isFlying() ? RavenModel.Pose.FLYING : RavenModel.Pose.STANDING;
    }

    @OnlyIn(Dist.CLIENT)
    public static enum Pose {
        FLYING,
        STANDING;
    }
}
