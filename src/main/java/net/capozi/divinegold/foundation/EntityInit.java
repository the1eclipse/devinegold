package net.capozi.divinegold.foundation;

import net.capozi.divinegold.DivineGold;
import net.capozi.divinegold.content.entity.object.FallingMeteorEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityInit {
    public static final EntityType<FallingMeteorEntity> FALLING_METEOR = Registry.register(Registries.ENTITY_TYPE, new Identifier(DivineGold.MOD_ID, "meteor"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, FallingMeteorEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build());
}
