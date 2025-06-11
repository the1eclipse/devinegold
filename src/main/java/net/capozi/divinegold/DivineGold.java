package net.capozi.divinegold;

import net.capozi.divinegold.foundation.BlockInit;
import net.capozi.divinegold.foundation.ItemInit;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DivineGold implements ModInitializer {
	public static final String MOD_ID = "divine_gold";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		LOGGER.info("Divine secrets");
		ItemInit.registerItems();
		BlockInit.registerBlocks();
	}

	public static @NotNull Identifier id(String path) {
		return new Identifier(DivineGold.MOD_ID, path);
	}
}