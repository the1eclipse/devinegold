package net.capozi.divinegold.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = FabricLanguageProvider.class, remap = false)
public class FabricLanguageProviderMixin {
	@ModifyExpressionValue(at = @At(value = "INVOKE", target = "Ljava/util/TreeMap;containsKey(Ljava/lang/Object;)Z"), method = "lambda$run$0")
	private static boolean containsKeyMoreLikeReturnsFalse(boolean original) {
		// we dont need that
		return false;
	}
}