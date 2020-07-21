package com.edzdez.vanillaplus;

import com.edzdez.vanillaplus.commands.*;
import com.edzdez.vanillaplus.config.Config;
import com.edzdez.vanillaplus.listeners.OnDeathListener;
import com.edzdez.vanillaplus.listeners.OnOreBreakListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class VanillaPlus extends JavaPlugin {

    public Config config;

    @Override
    public void onEnable() {

        this.config = new Config(this);

//        getServer().getPluginManager().registerEvents(new OnDeathListener(), this);
        getServer().getPluginManager().registerEvents(new OnOreBreakListener(this), this);

        new healCommand(this);
        new flyCommand(this);
        new craftCommand(this);
        new spawnCommand(this);
        new eChestCommand(this);
//        new backCommand(this);
    }

    @Override
    public void onDisable() {
    }
}
