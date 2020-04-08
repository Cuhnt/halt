package me.cats.halt.modules.movement;

import me.cats.halt.api.modules.Module;
import me.cats.halt.api.settings.Setting;
import me.cats.halt.events.entity.player.MovementTickEvent;
import cookiedragon.eventsystem.Subscriber;

/**
 * @author cats
 */
public class FlightModule extends Module {

    /**
     * the speed at which you fly
     */
    private Setting<Float> flightSpeed = new Setting<>("Speed",1f);

    public FlightModule() {
        super("Flight", "Allows for vanilla style flight");
    }

    /**
     * makes you fly on movement tick
     * @param event movement tick
     */
    @Subscriber
    public void onMoveTick(MovementTickEvent event) {
        if (mc.player != null) {
            mc.player.abilities.allowFlying = true;
            mc.player.abilities.flying = true;
            mc.player.abilities.setFlySpeed(this.flightSpeed.value);
        }
    }
}
