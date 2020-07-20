package com.edzdez.vanillaplus;

import com.edzdez.vanillaplus.commands.healCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class VanillaPlus extends JavaPlugin {


    @Override
    public void onEnable() {

        new healCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
