package me.cats.halt;

import me.cats.halt.management.*;
import me.cats.halt.utils.Logger;

public class Halt {

    public static Halt INSTANCE = new Halt();

    public String modName = "Halt";

    /**
     * Here we make a var for my Module Manager
     */
    public ModuleManager moduleManager = new ModuleManager();

    /**
     * Here we make a var for my command manager
     */
    public CommandManager commandManager = new CommandManager();

    /**
     * initializes the client
     */
    void init() {
        Logger.log("Init reached");

        commandManager.init();
        Logger.log("Commands init");

        moduleManager.init();
        Logger.log("Modules init");
    }
}
