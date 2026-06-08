package com.example.speed.mixin;

import com.example.speed.EventManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    private static KeyBinding speedKey = null;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (speedKey == null) {
            speedKey = new KeyBinding(
                "key.speed.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.speed"
            );
            // Регистрируем клавишу вручную (без Fabric API)
            // В ванильном Minecraft KeyBinding не нужно регистрировать, он работает сам, если добавить в список?
            // Но для надежности создаём и используем.
        }
        // Проверка нажатия
        if (speedKey != null && speedKey.wasPressed()) {
            EventManager.setEnabled(!EventManager.isEnabled());
        }
    }
}
