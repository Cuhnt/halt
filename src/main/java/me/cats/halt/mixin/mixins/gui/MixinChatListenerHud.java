package me.cats.halt.mixin.mixins.gui;

import cookiedragon.eventsystem.EventDispatcher;
import me.cats.halt.events.hud.chat.ChatMessageEvent;
import net.minecraft.client.gui.hud.ChatListenerHud;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cats
 * @since 22 mar 2020
 */
@Mixin(ChatListenerHud.class)
public class MixinChatListenerHud {

    /**
     * this was originally supposed to be used for my commands, but it wasn't working as I wanted it to
     * as a result, it is not used, but I haven't removed it yet
     * @param messageType the type of message sent
     * @param message the text of the message
     * @param ci the callback info
     */
    @Inject(at = @At("HEAD"), method = "onChatMessage", cancellable = true)
    private void onChatMessage(MessageType messageType, Text message, CallbackInfo ci) {
        if (messageType != MessageType.CHAT) return;
        final ChatMessageEvent event = new ChatMessageEvent(message.asFormattedString());
        EventDispatcher.Companion.dispatch(event);
    }
}