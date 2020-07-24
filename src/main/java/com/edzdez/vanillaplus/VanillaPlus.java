package com.edzdez.vanillaplus;

import com.edzdez.vanillaplus.commands.*;
import com.edzdez.vanillaplus.listeners.OnDeathListener;
import com.edzdez.vanillaplus.listeners.OnOreBreakListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

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
        loadDeathLocations();
    }

    @Override
    public void onDisable() {
        saveDeathLocations();
    }

    public HashMap<Player, Location> deathLocGetter() {
        return deathLoc;
    }

    public void deathLocSetter(Player p, Location loc) {
        deathLoc.put(p, loc);
        Bukkit.getLogger().info("Logged Death Location");
    }

    //https://bukkit.org/threads/saving-hashmap-to-file.129225/
    public void saveDeathLocations() {
        for (Player p : deathLoc.keySet()) {
            Location loc = deathLoc.get(p);
            String locToString = loc.getWorld().toString() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ();
            getConfig().set("deathlocations.players." + p.getName(), locToString);
        }
        saveConfig();
    }

    public void loadDeathLocations() {
        if (getConfig().getConfigurationSection("deaths.players") != null) {
            Set<String> set = getConfig().getConfigurationSection("deathlocations.players").getKeys(false);
            for (String player : set) {
                Location loc = getLocationString(getConfig().getString("deathlocations.players." + player));
                Bukkit.getLogger().info(getConfig().getString("deathlocations.players." + player));
                Player p = Bukkit.getPlayer(player);
                deathLoc.put(p, loc);
                Bukkit.getLogger().info(p.getName() + " has been loaded");
            }
        }
    }

    //https://www.spigotmc.org/threads/string-to-location.29924/?__cf_chl_jschl_tk__=a39be30b20ff41eaa45908a7c489d32a691d80b7-1595614689-0-AQZeDapsHF01vsD8nibFqGx55EMyb2KQELE9vqRwFoxh8kgCxCOKNtqCBi5hf72aDKbuxEbldFE_7dk_5bEfli-Vqfm_9u7ABu4iTgZytmBteQQMCQreZjTwr1RxiQO-WdviLITLVPufdi5Q1ta7OXgf2EFyHee3lUoaGCMYdgs9PafaFg0nLtKYq7irYdH0XF9N7y7TKNdfGeBsBjphyG39pJ6IBO9qYk3_4WmfgHH5XMZMscsKXTLVCxkWhaxpVnpE7ewLA9SRuHTtBJcmltTI3EvMJvd0LxiMeA6NaEZhsOmkp_1n0nC0T9nJMhIKyQ
    public Location getLocationString(String s) {
        if (s == null || s.trim() == "") {
            return null;
        }
        String[] parts = s.split(":");

        if (parts.length == 4) {
            World w = Bukkit.getServer().getWorld(parts[0]);
            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int z = Integer.parseInt(parts[3]);
            return new Location(w, x, y, z);
        }

        return null;
    }
}
