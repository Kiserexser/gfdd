package com.example.speed.mixin;

import com.example.speed.EventManager;
import com.example.speed.events.EventOnMovePost;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Inject(method = "updateVelocity", at = @At("TAIL"))
    private void onUpdateVelocity(float speed, Vec3d movementInput, CallbackInfo ci) {
        Entity me = (Entity)(Object)this;
        if (MinecraftClient.getInstance().player != null && me.getId() == MinecraftClient.getInstance().player.getId()) {
            EventManager.onMovePost(new EventOnMovePost(speed, movementInput));
        }
    }
}
