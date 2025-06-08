package net.capozi.divinegold.foundation;

import net.capozi.divinegold.DivineGold;
import net.capozi.divinegold.content.blocks.meteorite.MeteoriteCrystalBlock;
import net.capozi.divinegold.content.blocks.meteorite.MeteoriteOreBlock;
import net.capozi.divinegold.content.blocks.meteorite.SeepingMeteoriteOreBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.BiFunction;

public class BlockInit {
    public static final BlockWithItem<MeteoriteCrystalBlock, BlockItem> GROWN_METEORITE_CRYSTAL =
            registerBlockWithItem(
                    "grown_meteorite_crystal",
                    new MeteoriteCrystalBlock(
                            7,
                            10,
                            AbstractBlock.Settings.create()
                                    .solid()
                                    .nonOpaque()
                                    .strength(1.5F)
                                    .emissiveLighting((state, world, pos) -> true)
                    )
            );

    public static final BlockWithItem<MeteoriteCrystalBlock, BlockItem> METEORITE_CRYSTAL_4 =
            registerBlockWithItem(
                    "meteorite_crystal_4",
                    new MeteoriteCrystalBlock(
                            6,
                            8,
                            AbstractBlock.Settings.copy(GROWN_METEORITE_CRYSTAL.block())
                    )
            );

    public static final BlockWithItem<MeteoriteCrystalBlock, BlockItem> METEORITE_CRYSTAL_3 =
            registerBlockWithItem(
                    "meteorite_crystal_3",
                    new MeteoriteCrystalBlock(
                            6,
                            6,
                            AbstractBlock.Settings.copy(GROWN_METEORITE_CRYSTAL.block())
                    )
            );

    public static final BlockWithItem<MeteoriteCrystalBlock, BlockItem> METEORITE_CRYSTAL_2 =
            registerBlockWithItem(
                    "meteorite_crystal_2",
                    new MeteoriteCrystalBlock(
                            4,
                            6,
                            AbstractBlock.Settings.copy(GROWN_METEORITE_CRYSTAL.block())
                    )
            );

    public static final BlockWithItem<MeteoriteCrystalBlock, BlockItem> METEORITE_CRYSTAL_1 =
            registerBlockWithItem(
                    "meteorite_crystal_1",
                    new MeteoriteCrystalBlock(
                            3,
                            4,
                            AbstractBlock.Settings.copy(GROWN_METEORITE_CRYSTAL.block())
                    )
            );

    public static final BlockWithItem<MeteoriteOreBlock, BlockItem> METEORITE_ORE =
            registerBlockWithItem(
                    "meteorite_ore",
                    new MeteoriteOreBlock(
                            AbstractBlock.Settings.create()
                                    .strength(1.5F)
                                    .requiresTool()
                    )
            );

    public static final BlockWithItem<SeepingMeteoriteOreBlock, BlockItem> SEEPING_METEORITE_ORE =
            registerBlockWithItem(
                    "seeping_meteorite_ore",
                    new SeepingMeteoriteOreBlock(
                            AbstractBlock.Settings.create()
                                    .strength(1.5F)
                                    .requiresTool()
                                    .ticksRandomly()
                    )
            );


    public static void registerBlocks() {
        // static init
    }

    private static <B extends Block> BlockWithItem<B, BlockItem> registerBlockWithItem(String id, B block) {
        return registerBlockWithItem(id, block, BlockItem::new, new Item.Settings());
    }

    private static <B extends Block, I extends BlockItem> BlockWithItem<B, I> registerBlockWithItem(
            String id, B block, BiFunction<B, Item.Settings, I> blockItemFactory, Item.Settings itemSettings
    ) {
        B registeredBlock = registerBlock(id, block);
        I blockItem = blockItemFactory.apply(registeredBlock, itemSettings);
        I registeredBlockItem = Registry.register(Registries.ITEM, DivineGold.id(id), blockItem);
        return new BlockWithItem<>(registeredBlock, registeredBlockItem);
    }

    private static <B extends Block> B registerBlock(String id, B block) {
        return Registry.register(Registries.BLOCK, DivineGold.id(id), block);
    }


    public record BlockWithItem<B extends Block, I extends BlockItem>(B block, I item) {}
}
