package me.cats.halt.events.network.packet;

import me.cats.halt.api.events.CancellableEvent;
import net.minecraft.network.Packet;

/**
 * @author cats
 * @since April 7, 2020
 */
public class IncomingPacketEvent extends CancellableEvent {

    /**
     * the incoming packet
     */
    public Packet packet;

    public IncomingPacketEvent(Packet packet) {
        this.packet = packet;
    }
}
