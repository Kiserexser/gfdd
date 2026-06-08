package com.example.speed.events;

import net.minecraft.network.packet.Packet;

public class EventPacket {
    private final Packet<?> packet;
    public EventPacket(Packet<?> packet) { this.packet = packet; }
    public Packet<?> getPacket() { return packet; }
}
