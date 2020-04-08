package me.cats.halt.events.network;

import me.cats.halt.api.events.CancellableEvent;

/**
 * @author cats
 */
public class PacketExceptionEvent extends CancellableEvent {

    /**
     * the thrown exception
     */
    public Throwable throwable;

    public PacketExceptionEvent(Throwable throwable) {
        this.throwable = throwable;
    }
}
