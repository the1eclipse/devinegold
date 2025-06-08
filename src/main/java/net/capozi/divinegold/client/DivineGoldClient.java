package net.capozi.divinegold.client;

import net.capozi.divinegold.client.models.DivineGoldModelLoadingPlugin;
import net.capozi.divinegold.foundation.BlockInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.render.RenderLayer;

public class DivineGoldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLoadingPlugin.register(new DivineGoldModelLoadingPlugin());
        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
                BlockInit.GROWN_METEORITE_CRYSTAL.block(),
                BlockInit.METEORITE_CRYSTAL_4.block(),
                BlockInit.METEORITE_CRYSTAL_3.block(),
                BlockInit.METEORITE_CRYSTAL_2.block(),
                BlockInit.METEORITE_CRYSTAL_1.block()
        );
    }
}
