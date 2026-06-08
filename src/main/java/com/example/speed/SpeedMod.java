package com.example.speed;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpeedMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("speedmod");

    @Override
    public void onInitialize() {
        LOGGER.info("Speed Mod with all bypasses loaded");

        // Регистрация клавиши R
        KeyBinding key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.speed.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.speed"
        ));

        // Обработчик нажатий (каждый тик проверяем, нажата ли клавиша)
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (key.wasPressed()) {
                // Переключаем состояние модуля
                EventManager.setEnabled(!EventManager.isEnabled());
            }
        });
    }
}
