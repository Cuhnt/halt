package me.cats.halt.api.modules;

import cookiedragon.eventsystem.EventDispatcher;
import me.cats.halt.Halt;
import me.cats.halt.api.commands.Command;
import me.cats.halt.api.settings.Setting;
import me.cats.halt.utils.Logger;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;

/**
 * @author cats
 * @since 22 mar 2020
 */
public class Module extends Command {

    /**
     * define mc as MinecraftClient.getInstance(), because I'm going to be using it like that anyway
     */
    protected MinecraftClient mc = MinecraftClient.getInstance();

    /**
     * The module name
     */
    public final String name;

    /**
     * a boolean to determine if a module is hidden in the HUD
     */
    public boolean hidden;

    /**
     * The module's description
     */
    public final String description;

    /**
     * determines if a module is enabled
     */
    public boolean enabled;

    /**
     * the list of settings for the module
     */
    public ArrayList<Setting> settings = new ArrayList<>();

    /**
     * declare a module with a name and description
     * @param name module name
     * @param description module description
     */
    public Module(String name, String description) {
        super(name, 2, 2);
        this.name = name;
        this.description = description;
    }

    /**
     * lets you set the module's state to a boolean
     * @param state the state to set the module to
     */
    public void setState(boolean state) {
        if (state) {
            if (!this.enabled) EventDispatcher.Companion.subscribe(this);
            this.enabled = true;
            Logger.log(this.name + " enabled");
            onEnable();
        } else {
            if (this.enabled) EventDispatcher.Companion.unsubscribe(this);
            this.enabled = false;
            Logger.log(this.name + " disabled");
            onDisable();
        }
    }

    /**
     * lets you toggle the module
     */
    public void toggle() {
        if(!enabled) {
            EventDispatcher.Companion.subscribe(this);
            this.enabled = true;
            Logger.log(this.name + " enabled");
            this.onEnable();
        } else {
            EventDispatcher.Companion.unsubscribe(this);
            this.enabled = false;
            Logger.log(this.name + " disabled");
            this.onDisable();
        }
    }

    /**
     * runs the onEnable task
     */
    public void onEnable() {
        if (!Halt.INSTANCE.moduleManager.enabledModules.contains(this)) {
            Halt.INSTANCE.moduleManager.enabledModules.add(this);
        }
    }

    /**
     * runs the onDisable task
     */
    public void onDisable() {
        Halt.INSTANCE.moduleManager.enabledModules.remove(this);
    }

    /**
     * runs the module given the arguments for it
     * @param args the module arguments for the command
     */
    public void run(String[] args) {

        if (args[0].equalsIgnoreCase("enabled")) {
            if (args.length == 2) {
                this.setState(Boolean.parseBoolean(args[1]));
            } else {
                this.toggle();
            }
            return;
        }

        if (args[0].equalsIgnoreCase("hidden")) {

            if (args.length == 2) {
                this.hidden = Boolean.parseBoolean(args[1]);
            }
            return;
        }

        for (Setting setting : settings) {
            if (args[0].equalsIgnoreCase(setting.name)) {
                if (setting.value instanceof Boolean) {
                    setting.value = Boolean.parseBoolean(args[1]);
                } else
                if (setting.value instanceof Number) {
                    if (setting.value instanceof Double) {
                        setting.value = Double.parseDouble(args[1]);
                    } else
                    if (setting.value instanceof Float) {
                        setting.value = Float.parseFloat(args[1]);
                    } else
                    if (setting.value instanceof Integer) {
                        setting.value = Integer.parseInt(args[0]);
                    } else
                    if (setting.value instanceof Long) {
                        setting.value = Long.parseLong(args[0]);
                    }
                }

                //I would use args[1] instead of setting.value, but this way it tells me if I made a mistake with the setting and it is not an instance of those
                Logger.log(setting.name + " in " + this.name + " was set to " + setting.value);
            }
        }
    }

    /**
     * sends a module error
     * @param input the error message
     */
    private void sendModuleError(String input) {
        Logger.log("Module Error Occurred: " + input);
    }
}
