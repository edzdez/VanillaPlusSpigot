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
    private HashMap<Player, Location> deathLoc = new HashMap<Player, Location>();

    public OnDeathListener(VanillaPlus plugin) {
        this.plugin = plugin;
    }

    public HashMap<Player, Location> getHashMap() {
        return deathLoc;
    }

    @EventHandler
    public void event(PlayerDeathEvent event) {
        Bukkit.getLogger().info("proc'd");
        Player p = event.getEntity();
        Location loc = p.getLocation();

        deathLoc.put(p, loc);
        Bukkit.getLogger().info("Added " + p.getName() + "'s death location at " + loc.getX() + " " + loc.getY() + " " + loc.getZ());
//        Bukkit.getLogger().info(p.getName() + ": " + deathLoc.get(p.getName()).getX() + " " + deathLoc.get(p.getName()).getY() + " " + deathLoc.get(p.getName()).getZ());

        p.spigot().respawn();
        p.teleport(deathLoc.get(p));
    }
}
