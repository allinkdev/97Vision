package com.github.allinkdev.ninety_seven_vision.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralTextContent;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(LiteralTextContent.class)
final class LiteralTextContentMixin {
    @Mutable
    @Shadow
    @Final
    private String string;

    @Redirect(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/text/LiteralTextContent;string:Ljava/lang/String;", opcode = Opcodes.PUTFIELD))
    private void onInit(final LiteralTextContent instance, final String value) {
        if (MinecraftClient.getInstance() == null) {
            this.string = value;

            return;
        }

        final String username = MinecraftClient.getInstance().getSession().getUsername();

        this.string = value.replace(username, "_97_");
    }
}
