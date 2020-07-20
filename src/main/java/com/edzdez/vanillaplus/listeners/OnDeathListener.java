package com.edzdez.vanillaplus.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;

public class OnDeathListener implements Listener {
    private HashMap<String, Location> deathLocations;

    public Location getDeathLoc(String playerName) {
        Location deathLoc;
        Player p = Bukkit.getPlayer(playerName);

        if (!(deathLocations.containsKey(playerName))) {
            deathLoc = new Location(p.getWorld(), p.getWorld().getSpawnLocation().getX(), p.getWorld().getHighestBlockYAt(p.getWorld().getSpawnLocation()) + 2, p.getWorld().getSpawnLocation().getZ());
        } else {
            deathLoc = deathLocations.get(playerName);
        }

        return deathLoc;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = (Player) event.getEntity();
        Location deathLoc = p.getLocation();

        if (deathLocations.containsKey(p.getName())) {
            deathLocations.remove(p.getName());
            deathLocations.put(p.getName(), deathLoc);
        } else {
            deathLocations.put(p.getName(), deathLoc);
        }
    }
}
