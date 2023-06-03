package com.github.allinkdev.ninety_seven_vision.mixin;

import com.github.allinkdev.ninety_seven_vision.Glasses;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.UUID;

@Mixin(ClientPlayerEntity.class)
final class ClientPlayerEntityMixin {

    @Inject(method = "init", at = @At("HEAD"))
    private void onInit(final CallbackInfo ci) {
        MinecraftClient.getInstance().getSkinProvider().loadSkin(new GameProfile(UUID.fromString("128aa125-6bd9-4c13-967b-76d2e17f94f7"), null), (type, id, texture) -> {
            switch (type) {
                case SKIN -> {
                    synchronized (Glasses.skinLock) {
                        Glasses.ninety_seven_skin_identifier = id;
                        synchronized (Glasses.modelLock) {
                            Glasses.model = Objects.requireNonNullElse(texture.getMetadata("model"), "default");
                        }
                    }
                }
                case CAPE -> {
                    synchronized (Glasses.capeLock) {
                        Glasses.ninety_seven_cape_identifier = id;
                    }
                }
                case ELYTRA -> {
                    synchronized (Glasses.elytraLock) {
                        Glasses.ninety_seven_elytra_identifier = id;
                    }
                }
            }
        }, true);
    }
}
