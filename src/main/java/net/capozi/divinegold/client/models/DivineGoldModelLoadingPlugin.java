package net.capozi.divinegold.client.models;

import net.capozi.divinegold.foundation.BlockInit;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class DivineGoldModelLoadingPlugin implements ModelLoadingPlugin {
    private static final ModelIdentifier SEEPING_METEORITE_ORE =
            new ModelIdentifier(Registries.BLOCK.getId(BlockInit.SEEPING_METEORITE_ORE.block()), "");

    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.modifyModelOnLoad().register((original, context) -> {
            Identifier id = context.id();
            if (SEEPING_METEORITE_ORE.equals(id)) {
                return new SeepingMeteoriteOreModel();
            }
            return original;
        });
    }
}
