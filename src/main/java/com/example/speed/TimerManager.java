package com.example.speed;

public class TimerManager {
    private static float clientTickMultiplier = 1.0F;
    public static void setTimer(float speed) { clientTickMultiplier = speed; }
    public static float getTimer() { return clientTickMultiplier; }
}
