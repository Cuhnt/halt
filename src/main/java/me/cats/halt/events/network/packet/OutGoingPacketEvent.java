package me.cats.halt.events.network.packet;

import me.cats.halt.api.events.CancellableEvent;
import net.minecraft.network.Packet;

/**
 * @author cats
 * @since 22 mar 2020
 */
public class OutGoingPacketEvent extends CancellableEvent {

    /**
     * the outgoing packet
     */
    public Packet packet;

    public OutGoingPacketEvent(Packet packet) {
        this.packet = packet;
    }
}
