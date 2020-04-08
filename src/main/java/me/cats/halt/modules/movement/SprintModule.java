package me.cats.halt.modules.movement;

import me.cats.halt.api.modules.Module;
import me.cats.halt.events.entity.player.MovementTickEvent;
import cookiedragon.eventsystem.Subscriber;

public class SprintModule extends Module {

    public SprintModule() {
        super("Sprint", "Forces sprinting");
    }

    /**
     * on movement tick, it makes you sprint
     * @param event the movement tick event
     */
    @Subscriber
    public void onMoveTick(MovementTickEvent event) {
        if (mc.player != null) {
            if ((mc.options.keyForward.isPressed()
                    || mc.options.keyBack.isPressed()
                    || mc.options.keyRight.isPressed()
                    || mc.options.keyLeft.isPressed()) && !(mc.player.isSneaking()) && !(mc.player.horizontalCollision)) {
                mc.player.setSprinting(true);
            }
        }
    }
}
