package me.cats.halt.api.commands;

import me.cats.halt.utils.Logger;

/**
 * @author cats
 */
public class Command {

    /**
     * the command name
     */
    public String name;

    /**
     * the minimum arguments for the command
     */
    public int minArgs;

    /**
     * the maximum arguments for the command
     */
    public int maxArgs;

    /**
     * Create the command with all of the needed values
     * @param name the command name
     * @param minArgs the minimum arguments
     * @param maxArgs the maximum arguments
     */
    public Command(String name, int minArgs, int maxArgs) {
        this.name = name;
        this.minArgs = minArgs;
        this.maxArgs = maxArgs;
    }

    /**
     * runs the command
     * @param args the command arguments
     */
    public void run(String[] args) {
    }

    /**
     * a function to send an error for commands
     * @param input error message input
     */
    protected void sendCommandError(String input) {
        Logger.log("Command Error Occurred: " + input);
    }

    /**
     * sends an error if it is outside of the arguments
     */
    protected void sendArgsError() {
        Logger.log("Error Occurred: Arguments not within range");
    }
}
