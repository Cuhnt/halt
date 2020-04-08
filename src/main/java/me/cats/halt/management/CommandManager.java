package me.cats.halt.management;

import me.cats.halt.api.commands.Command;
import me.cats.halt.commands.ModulesCommand;

import java.util.ArrayList;

/**
 * @author cats
 * @since 22 mar 2020
 */
public class CommandManager {

    /**
     * a list of all commands
     */
    public ArrayList<Command> commands = new ArrayList<>();

    /**
     * a command prefix, gotten so you can change it more easily
     */
    public String prefix = ".";

    /**
     * A method to initialize the class
     */
    public void init() {
        commands.add(new ModulesCommand());
    }

    /**
     * dispatches a gotten command
     * @param message the possible command gotten
     */
    public void dispatchCommand(String message) {
        final String commandInput = message.replaceFirst(this.prefix, "");
        final String[] command = commandInput.split(" ");
        for (Command possibleCommand : this.commands) {
            if (command[0].equalsIgnoreCase(possibleCommand.name)) {
                final String[] commandArgs = commandInput.replace(command[0] + " ", "").split(" ");
                possibleCommand.run(commandArgs);
            }
        }
    }
}