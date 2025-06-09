package net.capozi.divinegold.datagen;

import net.capozi.divinegold.foundation.BlockInit;
import net.capozi.divinegold.foundation.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.tag.ItemTags;

public class DivineGoldBlockLootTableProvider extends FabricBlockLootTableProvider {
    public DivineGoldBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        this.addDrop(
                BlockInit.GROWN_METEORITE_CRYSTAL.block(),
                block -> dropsWithSilkTouch(
                        block,
                        ItemEntry.builder(ItemInit.DIVINE_GOLD_SHARD)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F)))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
                                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                                .alternatively(
                                        this.applyExplosionDecay(
                                                block, ItemEntry.builder(ItemInit.DIVINE_GOLD_SHARD).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                        )
                                )
                )
        );
    }
}
