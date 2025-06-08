package net.capozi.divinegold.client.models;

import net.capozi.divinegold.DivineGold;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.*;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class SeepingMeteoriteOreModel implements UnbakedModel, BakedModel, FabricBakedModel {
    private static final SpriteIdentifier BASE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, DivineGold.id("block/seeping_meteorite_ore_base"));
    private static final SpriteIdentifier EMISSIVE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, DivineGold.id("block/seeping_meteorite_ore_emissive"));
    private Mesh mesh;
    private Sprite particleSprite;

    @Override
    public Collection<Identifier> getModelDependencies() {
        return List.of();
    }

    @Override
    public void setParents(Function<Identifier, UnbakedModel> modelLoader) {

    }

    @Override
    public @Nullable BakedModel bake(Baker baker, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer, Identifier modelId) {
        this.particleSprite = textureGetter.apply(BASE);

        Renderer renderer = RendererAccess.INSTANCE.getRenderer();
        MeshBuilder builder = renderer.meshBuilder();
        QuadEmitter emitter = builder.getEmitter();

        for (Direction direction : Direction.values()) {
            emitter.square(direction, 0, 0, 1, 1, 0)
                    .spriteBake(textureGetter.apply(BASE), MutableQuadView.BAKE_LOCK_UV)
                    .color(-1, -1, -1, -1)
                    .emit();

            emitter.square(direction, 0, 0, 1, 1, 0)
                    .spriteBake(textureGetter.apply(EMISSIVE), MutableQuadView.BAKE_LOCK_UV)
                    .material(renderer.materialFinder().blendMode(BlendMode.CUTOUT).emissive(true).find())
                    .color(-1, -1, -1, -1)
                    .emit();
        }
        this.mesh = builder.build();

        return this;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, Random random) {
        return List.of();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean hasDepth() {
        return false;
    }

    @Override
    public boolean isSideLit() {
        return false;
    }

    @Override
    public boolean isBuiltin() {
        return false;
    }

    @Override
    public Sprite getParticleSprite() {
        return this.particleSprite;
    }

    @Override
    public ModelTransformation getTransformation() {
        return ModelTransformation.NONE;
    }

    @Override
    public ModelOverrideList getOverrides() {
        return ModelOverrideList.EMPTY;
    }

    @Override
    public boolean isVanillaAdapter() {
        return false;
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        mesh.outputTo(context.getEmitter());
    }
}
