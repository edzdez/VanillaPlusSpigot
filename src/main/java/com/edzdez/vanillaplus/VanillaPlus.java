package com.edzdez.vanillaplus;

import com.edzdez.vanillaplus.commands.*;
import com.edzdez.vanillaplus.listeners.OnDeathListener;
import com.edzdez.vanillaplus.listeners.OnOreBreakListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class VanillaPlus extends JavaPlugin {
    private HashMap<Player, Location> deathLoc = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new OnDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new OnOreBreakListener(this), this);

        new healCommand(this);
        new flyCommand(this);
        new craftCommand(this);
        new spawnCommand(this);
        new eChestCommand(this);
        new backCommand(this);
    }

    @Override
    public void onDisable() {
    }

    public HashMap<Player, Location> deathLocGetter() {
        return deathLoc;
    }

    public void deathLocSetter(Player p, Location loc) {
        deathLoc.put(p, loc);
        Bukkit.getLogger().info("Logged Death Location");
    }
}
