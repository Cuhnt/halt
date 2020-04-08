package me.cats.halt.mixin.mixins.render;

import cookiedragon.eventsystem.EventDispatcher;
import me.cats.halt.events.render.Render3DEvent;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cats
 * @since April 7, 2020
 */
@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    /**
     * fires
     * @param matrices
     * @param camera
     * @param tickDelta
     * @param info
     */
    @Inject(at = @At("HEAD"), method = "renderHand(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/Camera;F)V", cancellable = true)
    private void renderHand(MatrixStack matrices, Camera camera, float tickDelta, CallbackInfo info) {
        final Render3DEvent event = new Render3DEvent();
        EventDispatcher.Companion.dispatch(event);
        if (event.canceled) info.cancel();
    }
}
