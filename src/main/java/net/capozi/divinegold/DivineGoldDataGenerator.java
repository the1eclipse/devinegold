package net.capozi.divinegold;

import net.capozi.divinegold.datagen.DivineGoldBlockLootTableProvider;
import net.capozi.divinegold.datagen.DivineGoldLanguageProvider;
import net.capozi.divinegold.datagen.DivineGoldModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DivineGoldDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(DivineGoldModelProvider::new);
		pack.addProvider(DivineGoldBlockLootTableProvider::new);
		pack.addProvider(DivineGoldLanguageProvider::new);
	}
}
