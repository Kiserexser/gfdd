package com.example.speed;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.Packet;

public class NetworkUtils {
    public static void sendSilentPacket(Packet<?> packet) {
        if (MinecraftClient.getInstance().getNetworkHandler() != null)
            MinecraftClient.getInstance().getNetworkHandler().sendPacket(packet);
    }
}
