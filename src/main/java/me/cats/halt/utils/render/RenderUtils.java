package me.cats.halt.utils.render;

import com.mojang.blaze3d.platform.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/**
 * @author cats
 * I used BleachHack renderutils as a refrence while making this, thank you Bleach!
 */
public class RenderUtils {

    /**
     * I used bleach's render utils for reference while writing this
     *
     * Draws a filled box at the specified box
     * @param box the box
     * @param r the red (out of 1)
     * @param g the green (out of 1)
     * @param b the blue (out of 1)
     * @param outLineAlpha the alpha (out of 1)
     */
	public static void drawFilledBox(Box box, float r, float g, float b, float outLineAlpha, float fillAlpha) {
        GL11.glPushMatrix();
	    glBegin();
        GlStateManager.lineWidth(2.5F);

        //Fills the box
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(5, VertexFormats.POSITION_COLOR);
        WorldRenderer.drawBox(buffer,
        		box.x1, box.y1, box.z1,
        		box.x2, box.y2, box.z2, r, g, b, outLineAlpha/2f);
        tessellator.draw();
        
        //Draws the outline
        buffer.begin(3, VertexFormats.POSITION_COLOR);
        buffer.vertex(box.x1, box.y1, box.z1).color(r, b, b, fillAlpha).next();
        buffer.vertex(box.x1, box.y1, box.z2).color(r, b, b, fillAlpha).next();
        buffer.vertex(box.x2, box.y1, box.z2).color(r, b, b, fillAlpha).next();
        buffer.vertex(box.x2, box.y1, box.z1).color(r, b, b, fillAlpha).next();
        buffer.vertex(box.x1, box.y1, box.z1).color(r, b, b, fillAlpha).next();
        buffer.vertex(box.x1, box.y2, box.z1).color(r, b, b, fillAlpha).next();
        buffer.vertex(box.x2, box.y2, box.z1).color(r, b, b, fillAlpha).next();
        buffer.vertex(box.x2, box.y2, box.z2).color(r, b, b, fillAlpha).next();
        buffer.vertex(box.x1, box.y2, box.z2).color(r, b, b, fillAlpha).next();
        buffer.vertex(box.x1, box.y2, box.z1).color(r, b, b, fillAlpha).next();
        buffer.vertex(box.x1, box.y1, box.z2).color(r, b, b, 0f).next();
        buffer.vertex(box.x1, box.y2, box.z2).color(r, b, b, fillAlpha).next();
        buffer.vertex(box.x2, box.y1, box.z2).color(r, b, b, 0f).next();
        buffer.vertex(box.x2, box.y2, box.z2).color(r, b, b, fillAlpha).next();
        buffer.vertex(box.x2, box.y1, box.z1).color(r, b, b, 0f).next();
        buffer.vertex(box.x2, box.y2, box.z1).color(r, b, b, fillAlpha).next();
        tessellator.draw();
        
        glEnd();
        GL11.glPopMatrix();
    }

    /**
     * offsets the render position
     * fixing the render position stuff
     */
	public static void offsetRender() {
		final Camera camera = BlockEntityRenderDispatcher.INSTANCE.camera;
		final Vec3d camPos = camera.getPos();
		GL11.glRotated(MathHelper.wrapDegrees(camera.getPitch()), 1, 0, 0);
		GL11.glRotated(MathHelper.wrapDegrees(camera.getYaw() + 180.0), 0, 1, 0);
		GL11.glTranslated(-camPos.x, -camPos.y, -camPos.z);
	}

    /**
     * begins gl rendering
     */
	public static void glBegin() {
        GlStateManager.enableBlend();
	    GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        GlStateManager.disableTexture();
        GlStateManager.disableDepthTest();
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		offsetRender();
	}

    /**
     * shuts down the gl start
     */
	public static void glEnd() {
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GlStateManager.enableDepthTest();
		GlStateManager.enableTexture();
		GlStateManager.disableBlend();
    }
}
