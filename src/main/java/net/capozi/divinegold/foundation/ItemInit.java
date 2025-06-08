package net.capozi.divinegold.foundation;

import net.capozi.divinegold.DivineGold;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemInit {
    public static void itemsRegistry() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ItemInit::registerItemToToolsTabItemGroup);
    }
    private static Item registerItems(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(DivineGold.MOD_ID, name), item);
    }
    public static void registerItemToToolsTabItemGroup(FabricItemGroupEntries entries) {

    }
    public static final Item DIVINE_GOLD_INGOT = registerItems("divine_gold_ingot", new Item(new FabricItemSettings()));
    public static final Item DIVINE_CERAMIC = registerItems("divine_ceramic", new Item(new FabricItemSettings()));
    public static final Item DIVINE_CERAMIC_TILE = registerItems("divine_ceramic_tile", new Item(new FabricItemSettings()));
    public static final Item DIVINE_GOLD_SHARD = registerItems("divine_gold_shard", new Item(new FabricItemSettings()));
}
