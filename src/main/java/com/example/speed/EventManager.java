package com.example.speed;

import com.example.speed.events.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Vec3d;

public class EventManager {
    private static MinecraftClient mc = MinecraftClient.getInstance();

    public static void onMovePost(EventOnMovePost e) {
        if (!SpeedModule.isEnabled()) return;   // если у тебя класс называется SpeedMod, замени на SpeedMod.isEnabled()
        int ticks = SpeedModule.ticks;
        TimerManager.setTimer(1.7F);
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
        SpeedModule.ticks++;
    }

    public static void onMoveInput(EventMoveInput e) {
        if (!SpeedModule.isEnabled()) return;
        if (mc.player == null) return;
        if (mc.player.verticalCollision) SpeedModule.groundTicks++;
        else SpeedModule.groundTicks = 0;
        if (SpeedModule.groundTicks >= 1) mc.player.jump();
    }

    public static void onPostMotion(EventPostMotion e) {
        if (!SpeedModule.isEnabled()) return;
        if ((SpeedModule.ticks % 2) == 0) {
            TimerManager.setTimer(0.3F);
            if (mc.player != null) {
                NetworkUtils.sendSilentPacket(new net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket(mc.player, net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode.START_FALL_FLYING));
            }
        }
    }

    public static void onPacket(EventPacket e) {
        if (!SpeedModule.isEnabled()) return;
        if (e.getPacket() instanceof PlayerPositionLookS2CPacket) {
            if ((SpeedModule.ticks % 2) == 1) {
                SpeedModule.ticks++;
            }
            TimerManager.setTimer(1.0F);
        }
    }
}
