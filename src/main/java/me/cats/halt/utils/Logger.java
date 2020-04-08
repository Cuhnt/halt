package me.cats.halt.utils;

import me.cats.halt.Halt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

/**
 * @author cats
 * @since 20 Mar 2020
 */
public class Logger {

    /**
     * the thing used to divide the prefix from the message
     */
    public static final String divider = " -> ";

    /**
     * the prefix for logged messages
     */
    private static final String prefix = Halt.INSTANCE.modName + divider;


    /**
     * Logs a message
     * @param message the message to log
     */
    public static void log(String message) {
        try {
            MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(prefix + message));
        } catch (Exception e) {
            System.out.println(prefix + message);
        }
    }

    /**
     * logs a message without the fancy prefix
     * @param message the message to log
     */
    public static void logRawText(String message) {
        try {
            MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(message));
        } catch (Exception e) {
            System.out.println(message);
        }
    }
}
