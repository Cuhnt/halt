package me.cats.halt.modules.render;

import me.cats.halt.api.modules.Module;
import me.cats.halt.events.render.Render3DEvent;
import me.cats.halt.utils.render.RenderUtils;
import cookiedragon.eventsystem.Subscriber;
import net.minecraft.entity.Entity;

public class ESPModule extends Module {

    public ESPModule() {
        super("ESP", "lets you see stuff");
    }

    /**
     * draws a box around all the entities, except you
     * @param event the render event
     */
    @Subscriber
    public void on3DRender(Render3DEvent event) {
        for (Entity entity : mc.world.getEntities()) {
            if (entity != mc.player) {
                RenderUtils.drawFilledBox(entity.getBoundingBox(), 1.9F, 1.5F, 0.3F, 0.8F, 0.4f);
            }
        }
    }
}
