package me.cats.halt.mixin.mixins.entity.player;

import cookiedragon.eventsystem.EventDispatcher;
import me.cats.halt.events.entity.player.MovementTickEvent;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cats
 */
@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {

    /**
     * Ticks ran during possible movement
     * @param ci callback info
     */
    @Inject(at = @At("HEAD"), method = "tickMovement", cancellable = true)
    private void tickMovement(CallbackInfo ci) {
        final MovementTickEvent event = new MovementTickEvent();
        EventDispatcher.Companion.dispatch(event);
    }

}
