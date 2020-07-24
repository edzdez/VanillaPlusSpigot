package com.edzdez.vanillaplus.listeners;

import com.edzdez.vanillaplus.VanillaPlus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.logging.Logger;

public class OnDeathListener implements Listener {
    private VanillaPlus plugin;

    public OnDeathListener(VanillaPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void event(PlayerDeathEvent event) {
        Bukkit.getLogger().info("proc'd");
        Player p = event.getEntity();
        Location loc = p.getLocation();

        HashMap<Player, Location> deathLoc = plugin.deathLocGetter();
        plugin.deathLocSetter(p, loc);

        Bukkit.getLogger().info("Added " + p.getName() + "'s death location at " + loc.getX() + " " + loc.getY() + " " + loc.getZ());
    }
}
