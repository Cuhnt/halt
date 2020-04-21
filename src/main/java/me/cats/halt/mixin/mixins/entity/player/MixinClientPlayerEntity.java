package me.cats.halt.mixin.mixins.entity.player;

import me.cats.halt.Halt;
import me.cats.halt.utils.Logger;
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
            // if it did not successfully go to a command, go to the module commands
            if (!Halt.INSTANCE.commandManager.dispatchCommandBoolean(commandInput)) {
                if (!Halt.INSTANCE.moduleManager.dispatchModuleBoolean(commandInput)) {
                    Logger.log("Command not found! Try again");
                }
            }
            ci.cancel();
        }
    }
}