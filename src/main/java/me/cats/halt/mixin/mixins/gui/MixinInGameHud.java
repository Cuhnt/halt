package me.cats.halt.mixin.mixins.gui;

import cookiedragon.eventsystem.EventDispatcher;
import me.cats.halt.events.hud.chat.RenderInGameHudEvent;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cats
 */
@Mixin(InGameHud.class)
public class MixinInGameHud {

    /**
     * launches an event for rendering
     * @param ci callback info
     */
    @Inject(at = @At(value = "RETURN"), method = "render(F)V", cancellable = true)
    private void render(CallbackInfo ci) {
        final RenderInGameHudEvent event = new RenderInGameHudEvent();
        EventDispatcher.Companion.dispatch(event);
        if (event.canceled) ci.cancel();
    }
}
