package net.capozi.divinegold.foundation;

import net.capozi.divinegold.DivineGold;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ItemInit {
    public static final Item DIVINE_GOLD_INGOT = registerItem("divine_gold_ingot", new Item(new FabricItemSettings()));
    public static final Item DIVINE_CERAMIC = registerItem("divine_ceramic", new Item(new FabricItemSettings()));
    public static final Item DIVINE_CERAMIC_TILE = registerItem("divine_ceramic_tile", new Item(new FabricItemSettings()));
    public static final Item DIVINE_GOLD_SHARD = registerItem("divine_gold_shard", new Item(new FabricItemSettings()));
    public static final Item DIVINE_GOLD_COIN = registerItem("divine_gold_coin", new Item(new FabricItemSettings()));

    public static void registerItems() {
        // static init
    }

    private static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, DivineGold.id(id), item);
    }
}
