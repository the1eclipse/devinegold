package net.capozi.divinegold.content.blocks.meteorite;

import net.capozi.divinegold.foundation.BlockInit;
import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

import java.util.Map;

@SuppressWarnings("deprecation")
public class SeepingMeteoriteOreBlock extends MeteoriteOreBlock {
    public static final int GROW_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();
    private static final Map<MeteoriteCrystalBlock, MeteoriteCrystalBlock> GROW_CONVERSIONS =
            Map.ofEntries(
                    Map.entry(BlockInit.METEORITE_CRYSTAL_1.block(), BlockInit.METEORITE_CRYSTAL_2.block()),
                    Map.entry(BlockInit.METEORITE_CRYSTAL_2.block(), BlockInit.METEORITE_CRYSTAL_3.block()),
                    Map.entry(BlockInit.METEORITE_CRYSTAL_3.block(), BlockInit.METEORITE_CRYSTAL_4.block()),
                    Map.entry(BlockInit.METEORITE_CRYSTAL_4.block(), BlockInit.GROWN_METEORITE_CRYSTAL.block())
            );

    public SeepingMeteoriteOreBlock(Settings settings) {
        super(settings);
    }

    public static boolean canGrowIn(BlockState state) {
        if (state.isAir()) {
            return true;
        }
        return state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(GROW_CHANCE) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos budPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(budPos);
            Block block = null;
            if (canGrowIn(blockState)) {
                block = BlockInit.METEORITE_CRYSTAL_1.block();
            }
            else if (blockState.getBlock() instanceof MeteoriteCrystalBlock meteoriteCrystalBlock && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = GROW_CONVERSIONS.get(meteoriteCrystalBlock);
            }

            if (block != null) {
                BlockState budState = block.getDefaultState()
                        .with(AmethystClusterBlock.FACING, direction)
                        .with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(budPos, budState);
            }
        }
    }
}
