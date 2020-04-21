package me.cats.halt.modules.render;

import cookiedragon.eventsystem.Subscriber;
import me.cats.halt.api.modules.Module;
import me.cats.halt.events.entity.player.MovementTickEvent;
import me.cats.halt.events.network.packet.IncomingPacketEvent;
import me.cats.halt.events.network.packet.OutGoingPacketEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.KeepAliveC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Vec3d;

/**
 * @author cats
 * Needs work
 * TODO add clipping to this, so it's actually a usable freecam
 */
public class FreecamModule extends Module {

    /**
     * the player's position, gotten when they activate the module
     */
    private Vec3d playerPos;

    public FreecamModule() {
        super("Freecam", "Allows for free movement independently");
    }

    /**
     * get the player's position onEnable
     */
    @Override
    public void onEnable() {
        super.onEnable();
        this.playerPos = mc.player.getPos();
    }

    /**
     * set the player's position onDisable
     */
    @Override
    public void onDisable() {
        super.onDisable();
        mc.player.setPos(this.playerPos.x, this.playerPos.y, this.playerPos.z);
    }

    /**
     * Allow flying
     * @param event movememnt tick event
     */
    @Subscriber
    public void moveTick(MovementTickEvent event) {
        if (mc.player == null) return;
        mc.player.abilities.flying = true;
    }

    /**
     * Ignore incoming packets
     * @param event The incoming packets
     */
    @Subscriber
    public void packetReceive(IncomingPacketEvent event) {
        final Packet packet = event.packet;

        if (packet instanceof PlayerPositionLookS2CPacket
                || packet instanceof PlayerAbilitiesS2CPacket) {
            event.canceled = true;
        }
    }

    /**
     * Don't send the server what you're doing
     * @param event the outgoing packets
     */
    @Subscriber
    public void packetSend(OutGoingPacketEvent event) {
        final Packet packet = event.packet;
        if (packet instanceof KeepAliveC2SPacket
                || packet instanceof PlayerMoveC2SPacket
                || packet instanceof PlayerInputC2SPacket) {
            event.canceled = true;
        }
    }
}
