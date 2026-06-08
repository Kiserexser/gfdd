package com.example.speed.mixin;

import com.example.speed.TimerManager;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderTickCounter.class)
public class MixinTimer {
    @Unique
    private float tickTime;

    @Inject(method = "beginRenderTick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/RenderTickCounter;prevTimeMillis:J", opcode = 0xb4), cancellable = true)
    private void onBeginRenderTick(long nanos, CallbackInfoReturnable<Integer> cir) {
        float multiplier = TimerManager.getTimer();
        if (multiplier != 1.0F) {
            this.tickTime = 50.0F / multiplier;
        }
    }
}
