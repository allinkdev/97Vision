package com.github.allinkdev.ninety_seven_vision;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;

import java.util.UUID;

public final class Glasses implements ClientModInitializer {
    public static final Object skinLock = new Object();
    public static final Object capeLock = new Object();
    public static final Object elytraLock = new Object();
    public static final Object modelLock = new Object();

    public static UUID selfUuid;
    public static Identifier ninety_seven_skin_identifier;
    public static Identifier ninety_seven_cape_identifier;
    public static Identifier ninety_seven_elytra_identifier;
    public static String model;

    @Override
    public void onInitializeClient() {
        //
    }
}
