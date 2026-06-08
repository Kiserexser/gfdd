package com.example.speed;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpeedMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("speedmod");
    public static boolean enabled = false;
    public static int ticks = 0;
    public static int groundTicks = 0;

    @Override
    public void onInitialize() {
        LOGGER.info("Speed Mod with all bypasses loaded");
        // Регистрация клавиши R и ивентов будет позже, пока просто заглушка
    }

    public static boolean isEnabled() { return enabled; }
}
