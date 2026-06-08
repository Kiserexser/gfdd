package com.example.speed.events;

import net.minecraft.util.math.Vec3d;

public class EventOnMovePost {
    private final float speed;
    private final Vec3d movementInput;
    public EventOnMovePost(float speed, Vec3d movementInput) {
        this.speed = speed;
        this.movementInput = movementInput;
    }
    public float getSpeed() { return speed; }
    public Vec3d getMovementInput() { return movementInput; }
}
