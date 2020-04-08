package me.cats.halt.api.settings;

import me.cats.halt.utils.Logger;

/**
 * @author cats
 * @param <T> the type of setting to have
 */
public class Setting<T> {

    /**
     * the module name, public so you can get it and possibly change it if wanted
     */
    public final String name;

    /**
     * the value, made public for easy modification
     */
    public T value;

    /**
     * a way to create a new instance of the setting, easily adding the name and value and such
     * @param name the setting name
     * @param value the setting value
     */
    public Setting(String name, T value) {
        this.name = name;
        this.value = value;
    }

    /**
     * used to set the value and log the change
     * @param value what the value will be changed to
     */
    public void setValue(T value) {
        this.value = value;
        Logger.log(this.name + " was set to " + value);
    }
}
