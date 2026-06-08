package com.example.speed;

import com.example.speed.events.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Vec3d;

public class EventManager {
    private static MinecraftClient mc = MinecraftClient.getInstance();

    // Состояние модуля
    private static boolean enabled = false;
    private static int ticks = 0;
    private static int groundTicks = 0;

    public static void setEnabled(boolean e) {
        enabled = e;
        if (!enabled) {
            ticks = 0;
            groundTicks = 0;
            // TimerManager.setTimer(1.0F); // УДАЛЕНО
        }
    }

    public static boolean isEnabled() { return enabled; }
    public static int getTicks() { return ticks; }
    public static void incTicks() { ticks++; }
    public static int getGroundTicks() { return groundTicks; }
    public static void setGroundTicks(int v) { groundTicks = v; }
    public static void incGroundTicks() { groundTicks++; }

    // -----------------------------------------------------------------
    // Логика обходов (без таймера)
    // -----------------------------------------------------------------
    public static void onMovePost(EventOnMovePost e) {
        if (!enabled) return;
        // TimerManager.setTimer(1.7F); // УДАЛЕНО
        if (ticks > 3) {
            double bst = 0.03;
            if (ticks % 2 == 0) {
                if (mc.player != null) {
                    mc.player.addVelocityInternal(new Vec3d(0, 0.03F, 0));
                    if (mc.player.isOnGround()) bst = 0.085;
                    else bst = 0.03;
                }
            }
            double yaw = Math.toRadians(MoveUtil.getdir());
            double xt = -Math.sin(yaw);
            double zt = Math.cos(yaw);
            if (MoveUtil.getdir() == -1.0F) { xt = 0.0; zt = 0.0; }
            if (mc.player != null) {
                mc.player.addVelocityInternal(new Vec3d(xt * bst, 0, zt * bst));
            }
        }
        ticks++;
    }

    public static void onMoveInput(EventMoveInput e) {
        if (!enabled) return;
        if (mc.player == null) return;
        if (mc.player.verticalCollision) groundTicks++;
        else groundTicks = 0;
        if (groundTicks >= 1) mc.player.jump();
    }

    public static void onPostMotion(EventPostMotion e) {
        if (!enabled) return;
        if ((ticks % 2) == 0) {
            // TimerManager.setTimer(0.3F); // УДАЛЕНО
            if (mc.player != null) {
                NetworkUtils.sendSilentPacket(new net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket(mc.player, net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode.START_FALL_FLYING));
            }
        }
    }

    public static void onPacket(EventPacket e) {
        if (!enabled) return;
        if (e.getPacket() instanceof PlayerPositionLookS2CPacket) {
            if ((ticks % 2) == 1) {
                ticks++;
            }
            // TimerManager.setTimer(1.0F); // УДАЛЕНО
        }
    }
}
