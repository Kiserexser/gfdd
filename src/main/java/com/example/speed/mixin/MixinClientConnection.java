package com.example.speed.mixin;

import com.example.speed.EventManager;
import com.example.speed.events.EventPacket;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Inject(method = "handlePacket", at = @At("HEAD"))
    private static void onHandlePacket(Packet<?> packet, CallbackInfo ci) {
        EventManager.onPacket(new EventPacket(packet));
    }
}
