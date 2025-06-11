package net.capozi.divinegold.content.entity.object;

import net.capozi.divinegold.foundation.EntityInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class FallingMeteorEntity extends FireballEntity {
    private int explosionPower = 1;
    public FallingMeteorEntity(EntityType<? extends FireballEntity> entityType, World world) {
        super(entityType, world);
    }
    public FallingMeteorEntity(World world, LivingEntity owner, double velocityX, double velocityY, double velocityZ, int explosionPower) {
        super(EntityInit.FALLING_METEOR, world, velocityX, velocityY, velocityZ, owner);
        this.explosionPower = explosionPower;
    }
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            boolean bl = this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower, bl, World.ExplosionSourceType.MOB);
            this.discard();
        }

    }
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient) {
            Entity entity = entityHitResult.getEntity();
            Entity entity2 = this.getOwner();
            entity.damage(this.getDamageSources().fireball(this, entity2), 6.0F);
            if (entity2 instanceof LivingEntity) {
                this.applyDamageEffects((LivingEntity)entity2, entity);
            }

        }
    }
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putByte("ExplosionPower", (byte)this.explosionPower);
    }
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("ExplosionPower", 99)) {
            this.explosionPower = nbt.getByte("ExplosionPower");
        }

    }
}
