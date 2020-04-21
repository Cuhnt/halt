package me.cats.halt.mixin.mixins.network;


import cookiedragon.eventsystem.EventDispatcher;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.GenericFutureListener;
import me.cats.halt.Halt;
import me.cats.halt.events.network.PacketExceptionEvent;
import me.cats.halt.events.network.packet.IncomingPacketEvent;
import me.cats.halt.events.network.packet.OutGoingPacketEvent;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cats
 */
@Mixin(ClientConnection.class)
public class MixinClientConnection {

    /**
     * shadow in the channel from ClientConnection
     */
    @Shadow
    private Channel channel;

    /**
     * Puts the packet event into incoming packets so they can be cancelled, altered, or just read
     * @param channelHandlerContext Channel handler parameter from the Minecraft code
     * @param packet the packet being read
     * @param ci callback info
     */
    @Inject(at = @At("HEAD"), method = "channelRead0", cancellable = true)
    private void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet, CallbackInfo ci) {
        if (this.channel.isOpen() && packet != null) {
            IncomingPacketEvent event = new IncomingPacketEvent(packet);
            EventDispatcher.Companion.dispatch(event);
            if (event.canceled) ci.cancel();
        }
    }

    /**
     * Gets the outgoing packets and lets you modify or cancel them
     * @param packet the packet
     * @param genericFutureListener A listener
     * @param ci the callback info
     */
    @Inject( at = @At("HEAD"), method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", cancellable = true)
    private void send(Packet packet, GenericFutureListener genericFutureListener, CallbackInfo ci) {
        OutGoingPacketEvent event = new OutGoingPacketEvent(packet);
        EventDispatcher.Companion.dispatch(event);
        if (event.canceled) ci.cancel();
    }

    /**
     * Sends an event when packet exceptions are thrown, allowing you to ignore it
     * @param channelHandlerContext channelHandlerContext
     * @param throwable the thrown exception
     * @param ci the callback info
     */
    @Inject(at = @At("HEAD"), method = "exceptionCaught(Lio/netty/channel/ChannelHandlerContext;Ljava/lang/Throwable;)V", cancellable = true)
    private void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable, CallbackInfo ci) {
        final PacketExceptionEvent event = new PacketExceptionEvent(throwable);
        EventDispatcher.Companion.dispatch(event);
        if (event.canceled) ci.cancel();
    }
}

