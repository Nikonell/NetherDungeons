package com.teamdurt.netherdungeons.block;

import com.teamdurt.netherdungeons.init.NDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;

public class ArtichokeBlock extends BushBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);

    public ArtichokeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState) ((BlockState) this.stateDefinition.any()).setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AGE);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
        return new ItemStack(NDItems.ARTICHOKE_FRUIT.get());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        switch (state.getValue(AGE)) {
            case 0:
                return Block.box(5, 0, 5, 11, 10, 11);
            case 1:
                return Block.box(5, 0, 5, 11, 12, 11);
            case 2:
                return Block.box(5, 0, 5, 11, 14, 11);
            case 3:
                return Block.box(5, 0, 5, 11, 16, 11);
        }
        return Block.box(5, 0, 5, 11, 16, 11);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(Blocks.CRIMSON_NYLIUM) || state.is(Blocks.WARPED_NYLIUM);
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_57284_) {
        return (Integer) p_57284_.getValue(AGE) < MAX_AGE;
    }

    @Override
    public void randomTick(BlockState p_222563_, ServerLevel p_222564_, BlockPos p_222565_, RandomSource p_222566_) {
        int i = (Integer) p_222563_.getValue(AGE);
        if (i < MAX_AGE && ForgeHooks.onCropsGrowPre(p_222564_, p_222565_, p_222563_, p_222566_.nextInt(5) == 0)) {
            BlockState blockstate = (BlockState) p_222563_.setValue(AGE, i + 1);
            p_222564_.setBlock(p_222565_, blockstate, 2);
            p_222564_.gameEvent(GameEvent.BLOCK_CHANGE, p_222565_, GameEvent.Context.of(blockstate));
            ForgeHooks.onCropsGrowPost(p_222564_, p_222565_, p_222563_);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        int i = (Integer) state.getValue(AGE);
        boolean flag = i == MAX_AGE;
        if (flag) {
            ItemStack itemInHand = player.getItemInHand(hand);
            int breakChance = itemInHand.is(Items.SHEARS) ? 0 : 70;
            boolean willBreak = level.random.nextInt(1, 101) <= breakChance;
            popResource(level, pos, new ItemStack(NDItems.ARTICHOKE_FRUIT.get(), 1));
            if (itemInHand.getDamageValue() >= 237) {
                itemInHand.shrink(1);
                itemInHand.setDamageValue(0);
            } else player.getItemInHand(hand).setDamageValue(player.getItemInHand(hand).getDamageValue() + 1);

            if (willBreak) {
                level.destroyBlock(pos, false);
                level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(player, state));
            } else {
                level.playSound((Player) null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
                BlockState blockstate = (BlockState) state.setValue(AGE, 0);
                level.setBlock(pos, blockstate, 2);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockstate));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }

    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_51039_, BlockGetter p_51040_, BlockPos p_51041_) {
        return true;
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return PlantType.NETHER;
    }
}