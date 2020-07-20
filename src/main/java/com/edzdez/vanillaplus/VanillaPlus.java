package com.edzdez.vanillaplus;

import com.edzdez.vanillaplus.commands.craftCommand;
import com.edzdez.vanillaplus.commands.flyCommand;
import com.edzdez.vanillaplus.commands.healCommand;
import com.edzdez.vanillaplus.commands.spawnCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class VanillaPlus extends JavaPlugin {


    @Override
    public void onEnable() {

        new healCommand(this);
        new flyCommand(this);
        new craftCommand(this);
        new spawnCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
