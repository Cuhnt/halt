package me.cats.halt.events.hud.chat;

import me.cats.halt.api.events.CancellableEvent;

/**
 * @author cats
 * @since 22 mar 2020
 * this didn't do what I wanted it to!
 * Update: might have actually done what I wanted it to do, I may have just made a mistake
 * NOPE: Didn't work
 */
public class ChatMessageEvent extends CancellableEvent {

    /**
     * the chat message
     */
    public String chatMessage;

    public ChatMessageEvent(String message) {
        this.chatMessage = message;
    }
}
