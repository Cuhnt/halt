package me.cats.halt.modules.render;

import me.cats.halt.Halt;
import me.cats.halt.api.modules.Module;
import me.cats.halt.api.settings.Setting;
import me.cats.halt.events.hud.chat.RenderInGameHudEvent;
import cookiedragon.eventsystem.Subscriber;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.Window;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.dimension.DimensionType;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class HUDModule extends Module {

    private Setting<Boolean> arrayList = new Setting<>("ArrayList", true);

    private Setting<Boolean> coords = new Setting<>("Coordinates", true);

    public HUDModule() {
        super("HUD", "A heads up display");
    }

    /**
     * draws the ArrayList and Coordinates on the hud render event
     * @param event the hud render event
     */
    @Subscriber
    public void onRender(RenderInGameHudEvent event) {
        if (this.arrayList.value) this.drawArrayList();
        if (this.coords.value) this.drawCoordinates();
    }

    /**
     * draws the arraylist
     */
    private void drawArrayList() {
        int height = 2;
        final int width = 2;

        mc.textRenderer.drawWithShadow("Halt", width, height, Color.DARK_GRAY.getRGB());
        height += mc.textRenderer.fontHeight;

        final ArrayList<Module> enabledModules = new ArrayList<>(Halt.INSTANCE.moduleManager.enabledModules);
        Collections.reverse(enabledModules);
        //enabledModules.sort(Comparator.comparingInt(module -> -mc.textRenderer.getStringWidth(module.name)));
        for (Module module : enabledModules) {
            mc.textRenderer.drawWithShadow("-" + module.name, width, height, Color.WHITE.getRGB());
            height += mc.textRenderer.fontHeight;
        }
    }

    private void drawCoordinates() {
        final PlayerEntity player = mc.player;
        final Window scaledResolution = mc.getWindow();

        final Vec3d normalCoords = mc.player.getPos();
        final Vec3d otherCoords =(mc.player.dimension == DimensionType.THE_NETHER) ? player.getPos().multiply(8) : mc.player.getPos().multiply(1d/8d);

        mc.textRenderer.drawWithShadow(String.format("%s, %s, %s", (int) normalCoords.x, (int) normalCoords.y, (int) normalCoords.z),
                2f, scaledResolution.getScaledHeight() - ((mc.inGameHud.getChatHud().isChatFocused()) ? 24f : 12f), Color.WHITE.getRGB());

        mc.textRenderer.drawWithShadow(String.format("%s, %s, %s", (int) otherCoords.x, (int) otherCoords.y, (int) otherCoords.z),
                2f, scaledResolution.getScaledHeight() - (((mc.inGameHud.getChatHud().isChatFocused()) ? 24f : 12f) + mc.textRenderer.fontHeight), Color.RED.getRGB());

    }
}