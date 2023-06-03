package com.github.allinkdev.ninety_seven_vision.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.security.SecureRandom;

@Mixin(BeaconBlockEntityRenderer.class)
final class BeaconBlockEntityMixin {
    private static final SecureRandom JITTER_RANDOM = new SecureRandom();

    @Inject(method = "renderBeam(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/util/Identifier;FFJII[FFF)V", at = @At("HEAD"))
    private static void onRenderBeamHead(final MatrixStack matrices,
                                         final VertexConsumerProvider vertexConsumers,
                                         final Identifier textureId,
                                         final float tickDelta,
                                         final float heightScale,
                                         final long worldTime,
                                         final int yOffset,
                                         final int maxY,
                                         final float[] color,
                                         final float innerRadius, final float outerRadius,
                                         final CallbackInfo ci) {
        delay(.01f);
    }

    @Inject(method = "renderBeam(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/util/Identifier;FFJII[FFF)V", at = @At("TAIL"))
    private static void onRenderBeamTail(final MatrixStack matrices,
                                         final VertexConsumerProvider vertexConsumers,
                                         final Identifier textureId,
                                         final float tickDelta,
                                         final float heightScale,
                                         final long worldTime,
                                         final int yOffset,
                                         final int maxY,
                                         final float[] color,
                                         final float innerRadius, final float outerRadius,
                                         final CallbackInfo ci) {
        delay(.05f);
    }

    private static void delay(final float scale) {
        final float jitter = JITTER_RANDOM.nextFloat(scale * 10);
        final long delay = (long) (Math.floor(100 * scale)) + (long) Math.floor(jitter);

        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    @Inject(method = "renderBeamFace", at = @At(value = "HEAD"))
    private static void onRenderBeamFace(final Matrix4f positionMatrix, final Matrix3f normalMatrix, final VertexConsumer vertices,
                                         final float red, final float green, final float blue, final float alpha,
                                         final int yOffset, final int height,
                                         final float x1, final float z1, final float x2, final float z2,
                                         final float u1, final float u2, final float v1, final float v2,
                                         final CallbackInfo ci) {
        delay(.1f);
    }

    @Inject(method = "renderBeamVertex", at = @At(value = "HEAD"))
    private static void onRenderBeamVertex(final Matrix4f positionMatrix, final Matrix3f normalMatrix, final VertexConsumer vertices,
                                           final float red, final float green, final float blue, final float alpha,
                                           final int y, final float x, final float z,
                                           final float u, final float v,
                                           final CallbackInfo ci) {
        delay(.01f);
    }
}
