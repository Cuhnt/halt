package me.cats.halt.management;

import me.cats.halt.api.commands.Command;
import me.cats.halt.commands.ModulesCommand;
import me.cats.halt.utils.Logger;

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
        this.commands.add(new ModulesCommand());
    }

    /**
     * dispatches a gotten command and returns if it was successfully dispatched or not
     * @param message the possible command checked
     * @return if the command was dispatched properly or not
     */
    public boolean dispatchCommandBoolean(String message) {
        final String[] command = message.split(" ");
        for (Command possibleCommand : this.commands) {
            if (command[0].equalsIgnoreCase(possibleCommand.name)) {
                final String[] commandArgs = message.replace(command[0] + " ", "").split(" ");
                possibleCommand.run(commandArgs);
                return true;
            }
        }
        return false;
    }
}
