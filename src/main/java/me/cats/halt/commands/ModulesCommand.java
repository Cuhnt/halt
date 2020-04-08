package me.cats.halt.commands;

import me.cats.halt.Halt;
import me.cats.halt.api.commands.Command;
import me.cats.halt.api.modules.Module;
import me.cats.halt.api.settings.Setting;
import me.cats.halt.utils.Logger;

/**
 * @author cats
 */
public class ModulesCommand extends Command {

    public ModulesCommand() {
        super("Modules", 0, 0);
    }

    public void run(String[] args) {
        for (Module module : Halt.INSTANCE.moduleManager.modules) {
            if (module.enabled) {
                Logger.log("§7" + module.name);
            } else {
                Logger.log(module.name);
            }

            if (!module.settings.isEmpty()) {
                for (Setting setting : module.settings) {
                    Logger.logRawText(setting.name + Logger.divider + setting.value);
                }
            }
        }
    }
}