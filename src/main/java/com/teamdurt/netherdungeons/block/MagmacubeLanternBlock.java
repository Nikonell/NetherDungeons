package com.teamdurt.netherdungeons.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MagmacubeLanternBlock extends LanternBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public final boolean isEmpty;

    public MagmacubeLanternBlock(Properties properties, boolean isEmpty) {
        super(properties);
        this.isEmpty = isEmpty;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.SOUTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return Block.box(2, state.getValue(HANGING) ? 1 : 0, 2, 14, state.getValue(HANGING) ? 11 : 10, 14);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean b) {
        if (!this.isEmpty) {
            MagmaCube entity = new MagmaCube(EntityType.MAGMA_CUBE, level);
            entity.setPos(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
            entity.setSize(0, true);
            level.addFreshEntity(entity);
        }
        super.onRemove(state, level, pos, state1, b);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state != null) {
            return state.setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
        return null;
    }
}
