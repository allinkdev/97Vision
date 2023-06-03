package com.github.allinkdev.ninety_seven_vision.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {
    @Shadow
    public abstract String getModel();

    private AbstractClientPlayerEntity getThis() {
        return (AbstractClientPlayerEntity) (Object) this;
    }
}
