package org.apiary.ravens_harvest.entity.custom.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;
import java.util.function.Predicate;

public class EatCropGoal extends Goal {
    private static final int EAT_ANIMATION_TICKS = 40;
    private static final Predicate<BlockState> IS_EDIBLE = state -> state.is(BlockTags.CROPS);
    private final Mob mob;
    private final Level level;
    private int eatAnimationTick;

    public EatCropGoal(Mob mob) {
        this.mob = mob;
        this.level = mob.level();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (this.mob.getRandom().nextInt(this.adjustedTickDelay(this.mob.isBaby() ? 50 : 1000)) != 0) {
            return false;
        } else {
            BlockPos pos = this.mob.blockPosition();
            return IS_EDIBLE.test(this.level.getBlockState(pos)) || this.level.getBlockState(pos.below(-1)).is(BlockTags.CROPS);
        }
    }

    @Override
    public void start() {
        this.eatAnimationTick = this.adjustedTickDelay(40);
        this.level.broadcastEntityEvent(this.mob, (byte)10);
        this.mob.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.eatAnimationTick = 0;
    }

    @Override
    public boolean canContinueToUse() {
        return this.eatAnimationTick > 0;
    }

    public int getEatAnimationTick() {
        return this.eatAnimationTick;
    }

    @Override
    public void tick() {
        this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        if (this.eatAnimationTick == this.adjustedTickDelay(4)) {
            BlockPos pos = this.mob.blockPosition();
            if (IS_EDIBLE.test(this.level.getBlockState(pos))) {
                if (net.neoforged.neoforge.event.EventHooks.canEntityGrief(getServerLevel(this.level), this.mob)) {
                    this.level.destroyBlock(pos, false);
                }

                this.mob.ate();
            } else {
                BlockPos inside = pos.below(1);
                if (this.level.getBlockState(inside).is(BlockTags.CROPS)) {
                    if (net.neoforged.neoforge.event.EventHooks.canEntityGrief(getServerLevel(this.level), this.mob)) {
                        this.level.levelEvent(2001, inside, Block.getId(this.level.getBlockState(pos)));
                        this.level.setBlock(inside, Blocks.AIR.defaultBlockState(), 2);
                    }

                    this.mob.ate();
                }
            }
        }
    }
}
