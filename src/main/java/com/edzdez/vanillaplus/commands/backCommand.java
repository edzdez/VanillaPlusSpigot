package com.edzdez.vanillaplus.commands;

import com.edzdez.vanillaplus.VanillaPlus;
import com.edzdez.vanillaplus.listeners.OnDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;

public class backCommand implements CommandExecutor {
    private VanillaPlus plugin;

    public backCommand(VanillaPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("back").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, console can't use this command!");
            return true;
        }

        Player p = (Player) sender;
        OnDeathListener listener = new OnDeathListener(this.plugin);
        HashMap<Player, Location> deathLoc = listener.getHashMap();

        if (p.hasPermission("vanillaplus.back")) {
//            Bukkit.getLogger().info(deathLoc.get(p).getX() + " " + deathLoc.get(p).getY() + " " + deathLoc.get(p).getZ());
            return true;
        }

        p.sendMessage("Sorry, you do not have permission to use this command. If you think this is a mistake, please contact your server operators.");
        return false;
    }
}
