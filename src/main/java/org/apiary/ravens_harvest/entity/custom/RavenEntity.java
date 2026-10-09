package org.apiary.ravens_harvest.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.entity.custom.goal.RavenEatCropGoal;
import org.jspecify.annotations.Nullable;

public class RavenEntity extends Animal implements FlyingAnimal {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int flyingAnimationTimeout = 0;
    public int moreCropTicks = 0;

    public RavenEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setPathfindingMalus(PathType.FIRE_IN_NEIGHBOR, -1.0F);
        this.setPathfindingMalus(PathType.FIRE, -1.0F);
        this.setPathfindingMalus(PathType.COCOA, -1.0F);
    }

    //GOALS AND ATTRIBUTES
    @Override
    protected void registerGoals() {
        //TODO Refine existing goals
        //TODO Add goal to avoid carved pumpkins
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new PanicGoal(this, 2d));
        goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.0, 1.0));
        goalSelector.addGoal(3, new RavenEatCropGoal(this));
        goalSelector.addGoal(4, new RavenFarmlandWanderGoal(this, 1.0));
        goalSelector.addGoal(5, new RavenTreeWanderGoal(this, 1.0));
        goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1d));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.FLYING_SPEED, 0.5F)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.FOLLOW_RANGE, 5.0);
    }

    /*RAVEN WANDER GOAL*/
    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, level);
        flyingPathNavigation.setCanOpenDoors(false);
        flyingPathNavigation.setCanFloat(true);
        return flyingPathNavigation;
    }

    private static class RavenFarmlandWanderGoal extends WaterAvoidingRandomFlyingGoal {
        public RavenFarmlandWanderGoal(PathfinderMob mob, double speedModifier) {
            super(mob, speedModifier);
        }

        @Override
        protected @Nullable Vec3 getPosition() {
            Vec3 pos = null;
            if (this.mob.isInWater()) {
                pos = LandRandomPos.getPos(this.mob, 15, 15);
            }

            if (this.mob.getRandom().nextFloat() >= this.probability) {
                pos = this.getFarmlandPos();
            }

            return pos == null ? super.getPosition() : pos;
        }

        private @Nullable Vec3 getFarmlandPos() {
            BlockPos mobPos = this.mob.blockPosition();
            BlockPos.MutableBlockPos abovePos = new BlockPos.MutableBlockPos();
            BlockPos.MutableBlockPos belowPos = new BlockPos.MutableBlockPos();

            for (BlockPos pos : BlockPos.betweenClosed(
                    Mth.floor(this.mob.getX() - 3.0),
                    Mth.floor(this.mob.getY() - 6.0),
                    Mth.floor(this.mob.getZ() - 3.0),
                    Mth.floor(this.mob.getX() + 3.0),
                    Mth.floor(this.mob.getY() + 6.0),
                    Mth.floor(this.mob.getZ() + 3.0)
            )) {
                if (!mobPos.equals(pos)) {
                    BlockState state = this.mob.level().getBlockState(belowPos.setWithOffset(pos, Direction.DOWN));
                    boolean canSitOn = state.getBlock() instanceof CropBlock || state.is(BlockTags.CROPS);
                    if (canSitOn && this.mob.level().isEmptyBlock(pos) && this.mob.level().isEmptyBlock(abovePos.setWithOffset(pos, Direction.UP))) {
                        return Vec3.atBottomCenterOf(pos.below());
                    }
                }
            }

            return null;
        }
    }

    private static class RavenTreeWanderGoal extends WaterAvoidingRandomFlyingGoal {
        public RavenTreeWanderGoal(PathfinderMob mob, double speedModifier) {
            super(mob, speedModifier);
        }

        @Override
        protected @Nullable Vec3 getPosition() {
            Vec3 pos = null;
            if (this.mob.isInWater()) {
                pos = LandRandomPos.getPos(this.mob, 15, 15);
            }

            if (this.mob.getRandom().nextFloat() >= this.probability) {
                pos = this.getTreePos();
            }

            return pos == null ? super.getPosition() : pos;
        }

        private @Nullable Vec3 getTreePos() {
            BlockPos mobPos = this.mob.blockPosition();
            BlockPos.MutableBlockPos abovePos = new BlockPos.MutableBlockPos();
            BlockPos.MutableBlockPos belowPos = new BlockPos.MutableBlockPos();

            for (BlockPos pos : BlockPos.betweenClosed(
                    Mth.floor(this.mob.getX() - 3.0),
                    Mth.floor(this.mob.getY() - 6.0),
                    Mth.floor(this.mob.getZ() - 3.0),
                    Mth.floor(this.mob.getX() + 3.0),
                    Mth.floor(this.mob.getY() + 6.0),
                    Mth.floor(this.mob.getZ() + 3.0)
            )) {
                if (!mobPos.equals(pos)) {
                    BlockState state = this.mob.level().getBlockState(belowPos.setWithOffset(pos, Direction.DOWN));
                    boolean canSitOn = state.getBlock() instanceof LeavesBlock || state.is(BlockTags.LOGS);
                    if (canSitOn && this.mob.level().isEmptyBlock(pos) && this.mob.level().isEmptyBlock(abovePos.setWithOffset(pos, Direction.UP))) {
                        return Vec3.atBottomCenterOf(pos);
                    }
                }
            }

            return null;
        }
    }

    /*SOUNDS*/
    @Override
    public @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.PARROT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.PARROT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PARROT_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockState) {
        this.playSound(SoundEvents.PARROT_STEP, 0.15F, 1.0F);
    }

    /* ANIMATION */
    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.flyingAnimationTimeout <= 0) {
            this.flyingAnimationTimeout = 40;
            this.flyingAnimationState.start(this.tickCount);
        } else {
            --this.flyingAnimationTimeout;
        }
    }

    /*MISC*/
    //Checks if the raven is still hungry
    public boolean wantsMoreFood() {
        return this.moreCropTicks <= 0;
    }
    //Check for if entity is flying
    @Override
    public boolean isFlying() {
        return !this.onGround();
    }
    //Leash offset
    @Override
    public Vec3 getLeashOffset() {
        return new Vec3(0.0, 0.5F * this.getEyeHeight(), this.getBbWidth() * 0.4F);
    }
    //Breeding methods
    @Override
    public boolean isBaby() {
        return false;
    }
    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }
    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }
    @Override
    public void ate() {
        super.ate();
        if (this.canAgeUp()) {
            this.ageUp(60);
        }
    }
    //Pushing
    @Override
    public boolean isPushable() {
        return true;
    }
    @Override
    protected void doPush(Entity entity) {
        if (!(entity instanceof Player)) {
            super.doPush(entity);
        }
    }
    //Tick
    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }
    //Fall Damage
    @Override
    protected void checkFallDamage(double ya, boolean onGround, BlockState onState, BlockPos pos) {
    }
}
