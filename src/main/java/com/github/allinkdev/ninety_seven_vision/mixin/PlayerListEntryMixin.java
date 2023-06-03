package com.github.allinkdev.ninety_seven_vision.mixin;

import com.github.allinkdev.ninety_seven_vision.Glasses;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PlayerListEntry.class)
abstract class PlayerListEntryMixin {
    @Shadow
    public abstract GameProfile getProfile();

    private boolean isNotPlayer() {
        return !Objects.requireNonNullElse(Glasses.selfUuid, Util.NIL_UUID).equals(this.getProfile().getId());
    }

    @Inject(method = "getSkinTexture", at = @At(value = "HEAD"), cancellable = true)
    private void onGetSkinTexture(final CallbackInfoReturnable<Identifier> cir) {
        if (isNotPlayer()) {
            return;
        }

        cir.cancel();

        synchronized (Glasses.skinLock) {
            cir.setReturnValue(Objects.requireNonNullElse(Glasses.ninety_seven_skin_identifier, DefaultSkinHelper.getTexture(this.getProfile().getId())));
        }
    }

    @Inject(method = "getCapeTexture", at = @At(value = "HEAD"), cancellable = true)
    private void onGetCapeTexture(final CallbackInfoReturnable<Identifier> cir) {
        if (isNotPlayer()) {
            return;
        }

        cir.cancel();

        synchronized (Glasses.capeLock) {
            cir.setReturnValue(Glasses.ninety_seven_cape_identifier);
        }
    }

    @Inject(method = "getElytraTexture", at = @At(value = "HEAD"), cancellable = true)
    private void onGetElytraTexture(final CallbackInfoReturnable<Identifier> cir) {
        if (isNotPlayer()) {
            return;
        }

        cir.cancel();

        synchronized (Glasses.elytraLock) {
            cir.setReturnValue(Glasses.ninety_seven_elytra_identifier);
        }
    }

    @Inject(method = "getModel", at = @At(value = "HEAD"), cancellable = true)
    private void onGetModel(final CallbackInfoReturnable<String> cir) {
        if (isNotPlayer()) {
            return;
        }

        cir.cancel();

        synchronized (Glasses.modelLock) {
            cir.setReturnValue(Glasses.model);
        }
    }
}
