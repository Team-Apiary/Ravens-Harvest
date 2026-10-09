package org.apiary.ravens_harvest.entity.custom.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.apiary.ravens_harvest.RavensHarvest;
import org.apiary.ravens_harvest.entity.custom.RavenEntity;

import java.util.EnumSet;
import java.util.function.Predicate;

public class RavenEatCropGoal extends MoveToBlockGoal{
private final RavenEntity raven;
    private boolean wantsToRaid;
    private boolean canRaid;

    public RavenEatCropGoal(RavenEntity raven) {
        super(raven, 1F, 32);
        this.raven = raven;
    }

    @Override
    public boolean canUse() {
        if (this.nextStartTick <= 0) {
            if (!net.neoforged.neoforge.event.EventHooks.canEntityGrief(getServerLevel(this.raven.level()), this.raven)) {
                return false;
            }

            this.canRaid = false;
            this.wantsToRaid = this.raven.wantsMoreFood();
        }

        return super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return this.canRaid && super.canContinueToUse();
    }

    @Override
    public void tick() {
        super.tick();
        this.raven
                .getLookControl()
                .setLookAt(this.blockPos.getX() + 0.5, this.blockPos.getY() + 1, this.blockPos.getZ() + 0.5, 10.0F, this.raven.getMaxHeadXRot());
        if (this.isReachedTarget()) {
            Level level = this.raven.level();
            BlockPos cropsPos = this.blockPos.above();
            BlockState blockState = level.getBlockState(cropsPos);
            Block block = blockState.getBlock();
            if (this.canRaid && block instanceof CropBlock) {
                int cropAge = blockState.getValue(CropBlock.AGE);
                if (cropAge == 0) {
                    level.setBlock(cropsPos, Blocks.AIR.defaultBlockState(), 2);
                    level.destroyBlock(cropsPos, true, this.raven);
                } else {
                    level.setBlock(cropsPos, blockState.setValue(CropBlock.AGE, cropAge - 1), 2);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, cropsPos, GameEvent.Context.of(this.raven));
                    level.levelEvent(2001, cropsPos, Block.getId(blockState));
                }

                this.raven.moreCropTicks = 10;
            }

            this.canRaid = false;
            this.nextStartTick = 10;
        }
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.is(BlockTags.SUPPORTS_CROPS) && this.wantsToRaid && !this.canRaid) {
            state = level.getBlockState(pos.above());
            if (state.getBlock() instanceof CropBlock cropBlock && cropBlock.isMaxAge(state)) {
                this.canRaid = true;
                return true;
            }else{
                this.canRaid = true;
                return true;
            }
        }

        return false;
    }
}