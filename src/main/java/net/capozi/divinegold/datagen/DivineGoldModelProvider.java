package net.capozi.divinegold.datagen;

import net.capozi.divinegold.foundation.BlockInit;
import net.capozi.divinegold.foundation.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

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

    private static void registerDefault(ItemModelGenerator generator, Item item) {
        generator.register(item, Models.GENERATED);
    }
    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        registerDefault(generator, ItemInit.DIVINE_GOLD_INGOT);
        registerDefault(generator, ItemInit.DIVINE_CERAMIC);
        registerDefault(generator, ItemInit.DIVINE_CERAMIC_TILE);
        registerDefault(generator, ItemInit.DIVINE_GOLD_SHARD);
        registerDefault(generator, ItemInit.DIVINE_GOLD_COIN);
    }
}
