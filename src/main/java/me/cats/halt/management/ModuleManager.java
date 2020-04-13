package me.cats.halt.management;

import cookiedragon.eventsystem.EventDispatcher;
import me.cats.halt.Halt;
import me.cats.halt.api.modules.Module;
import me.cats.halt.api.settings.Setting;
import me.cats.halt.modules.misc.NoCompressionKickModule;
import me.cats.halt.modules.movement.FlightModule;
import me.cats.halt.modules.render.ESPModule;
import me.cats.halt.modules.render.FreecamModule;
import me.cats.halt.modules.render.HUDModule;
import me.cats.halt.modules.movement.SprintModule;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author cats
 */
public class ModuleManager {

    /**
     * the modules
     */
    public ArrayList<Module> modules = new ArrayList<>();

    /**
     * the enabled modules, I'd rather do it this way then run a check every time I want to get them
     */
    public ArrayList<Module> enabledModules = new ArrayList<>();

    /**
     * initializes the module manager
     */
    public void init() {
        this.addModule(new NoCompressionKickModule());
        this.addModule(new SprintModule());
        this.addModule(new HUDModule());
        this.addModule(new FlightModule());
        this.addModule(new ESPModule());
        this.addModule(new FreecamModule());
        //TODO maybe a method to detect modules and init them automatically, but I'm not a huge fan of that idea tbh
    }

    /**
     * adds a module to the module list, and gets the settings for it
     * @param module the module to be added
     */
    private void addModule(Module module) {
        try {
            for (Field field : module.getClass().getDeclaredFields()) {
                if (Setting.class.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    Setting value = (Setting) field.get(module);
                    module.settings.add(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventDispatcher.Companion.register(module);

        this.modules.add(module);
    }

    /**
     * dispatches module commands to their run system
     * @param message the text for a possible module command
     */
    public void dispatchModule(String message) {
        final String moduleCommandInput = message.replaceFirst(Halt.INSTANCE.commandManager.prefix, "");
        final String[] moduleCommand = moduleCommandInput.split(" ");
        for (Module possibleModule : this.modules) {
            if (moduleCommand[0].equalsIgnoreCase(possibleModule.name)) {
                final String[] commandArgs = moduleCommandInput.replace(moduleCommand[0] + " ", "").split(" ");
                possibleModule.run(commandArgs);
            }
        }
    }
}
