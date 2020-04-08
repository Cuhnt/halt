package me.cats.halt.modules.misc;

import me.cats.halt.api.modules.Module;
import me.cats.halt.events.network.PacketExceptionEvent;
import cookiedragon.eventsystem.Subscriber;

/**
 * @author cats
 * this might work
 */
public class NoCompressionKickModule extends Module {

    public NoCompressionKickModule() {
        super("NoCompressionKick", "Prevents kicks due to compression");
    }

    /**
     * Cancels bad packet exceptions
     * hopefully should stop chunkbans
     * @param event the exception event
     */
    @Subscriber
    public void onBadPacket(PacketExceptionEvent event) {
        event.canceled = true;
    }
}
