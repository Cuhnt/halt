package me.cats.halt;

import net.fabricmc.api.ModInitializer;

public class HaltMod implements ModInitializer {
    @Override
    public void onInitialize() {
        Halt.INSTANCE.init();
    }
}
