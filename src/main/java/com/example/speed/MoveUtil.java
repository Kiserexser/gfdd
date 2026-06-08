package com.example.speed;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;

public class MoveUtil {
    public static float getdir() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return 0;
        float yaw = mc.player.getYaw();
        float forward = mc.player.input.movementForward;
        float strafe = mc.player.input.movementSideways;
        if (forward == 0 && strafe == 0) return -1.0F;
        float yawRad = yaw * MathHelper.RADIANS_PER_DEGREE;
        float calc = (float) Math.atan2(strafe, forward);
        return yaw + (calc * MathHelper.DEGREES_PER_RADIAN);
    }
}
