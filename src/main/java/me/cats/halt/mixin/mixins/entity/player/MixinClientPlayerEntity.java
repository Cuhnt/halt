package me.cats.halt.mixin.mixins.entity.player;

import me.cats.halt.Halt;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cats
 * @since April 20, 2020
 * I use this to get the commands
 */
@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {

    /**
     * Dispatches commands and module commands
     * @param message the chat message
     * @param ci callback info!!
     */
    @Inject(at = @At("HEAD"), method = "sendChatMessage", cancellable = true)
    private void sendChatMessage(String message, CallbackInfo ci) {
        if (message.startsWith(Halt.INSTANCE.commandManager.prefix)) {
            final String commandInput = message.replaceFirst(Halt.INSTANCE.commandManager.prefix, "");
            Halt.INSTANCE.commandManager.dispatchCommand(commandInput);
            Halt.INSTANCE.moduleManager.dispatchModule(commandInput);
            ci.cancel();
        }
    }
}