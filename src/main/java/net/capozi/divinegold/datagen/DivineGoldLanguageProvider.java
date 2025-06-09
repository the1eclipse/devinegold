package net.capozi.divinegold.datagen;

import net.capozi.divinegold.DivineGold;
import net.capozi.divinegold.foundation.BlockInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.text.WordUtils;

public class DivineGoldLanguageProvider extends FabricLanguageProvider {
    public DivineGoldLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    private static <T> void addFromId(TranslationBuilder builder, Registry<T> registry, T object) {
        String translation = getFromId(registry, object);
        Class<?> clazz = object.getClass();
        try {
            String translationKey = (String) clazz.getMethod("getTranslationKey").invoke(object);
            builder.add(translationKey, translation);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unable to add translation to %s".formatted(object), e);
        }
    }

    private static <T> String getFromId(Registry<T> registry, T object) {
        Identifier id = registry.getId(object);
        if (id == null) {
            throw new IllegalArgumentException("Object not registered: %s".formatted(object));
        }
        String stringId = id.getPath();

        return WordUtils.capitalizeFully(stringId.replace("_", " "));
    }

    private static <T> void addRegistryIfMatchesNamespace(TranslationBuilder builder, Registry<T> registry) {
        registry.stream()
                .filter(object -> matchesNamespace(registry, object))
                .forEach(object -> addFromId(builder, registry, object));
    }

    private static <T> boolean matchesNamespace(Registry<T> registry, T object) {
        return registry.getId(object).getNamespace().equals(DivineGold.MOD_ID);
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        addRegistryIfMatchesNamespace(builder, Registries.ITEM);
        addRegistryIfMatchesNamespace(builder, Registries.BLOCK);

        builder.add(BlockInit.METEORITE_CRYSTAL_1.block(), "Meteorite Crystal");
        builder.add(BlockInit.METEORITE_CRYSTAL_2.block(), "Meteorite Crystal");
        builder.add(BlockInit.METEORITE_CRYSTAL_3.block(), "Meteorite Crystal");
        builder.add(BlockInit.METEORITE_CRYSTAL_4.block(), "Meteorite Crystal");
    }
}
