package com.github.allinkdev.ninety_seven_vision.mixin;

import com.github.allinkdev.ninety_seven_vision.Glasses;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(ClientPlayNetworkHandler.class)
final class ClientPlayNetworkHandlerMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "onGameJoin", at = @At(value = "TAIL"))
    private void onGameJoin(final GameJoinS2CPacket packet, final CallbackInfo ci) {
        Glasses.selfUuid = Objects.requireNonNull(client.player).getUuid();
    }
}
