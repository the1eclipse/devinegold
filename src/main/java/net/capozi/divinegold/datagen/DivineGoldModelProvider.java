package net.capozi.divinegold.datagen;

import net.capozi.divinegold.foundation.BlockInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class DivineGoldModelProvider extends FabricModelProvider {
    public DivineGoldModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(BlockInit.METEORITE_ORE.block());
        generator.registerSimpleCubeAll(BlockInit.SEEPING_METEORITE_ORE.block());

        generator.registerAmethyst(BlockInit.GROWN_METEORITE_CRYSTAL.block());
        generator.registerAmethyst(BlockInit.METEORITE_CRYSTAL_4.block());
        generator.registerAmethyst(BlockInit.METEORITE_CRYSTAL_3.block());
        generator.registerAmethyst(BlockInit.METEORITE_CRYSTAL_2.block());
        generator.registerAmethyst(BlockInit.METEORITE_CRYSTAL_1.block());
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {

    }
}
