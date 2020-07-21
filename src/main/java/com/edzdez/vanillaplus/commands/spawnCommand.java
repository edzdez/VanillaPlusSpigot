package com.edzdez.vanillaplus.commands;

import com.edzdez.vanillaplus.VanillaPlus;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawnCommand implements CommandExecutor {
    private VanillaPlus plugin;

    public spawnCommand(VanillaPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("spawn").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(plugin.getConfig().getBoolean("spawn"))) return false;

        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, Console can't do this!");
            return true;
        }
        Player p = (Player) sender;

        if (p.hasPermission("vanillaplus.spawn")) {
            try {
                p.teleport(p.getBedSpawnLocation());
            } catch (Exception e) {
                Location sp = new Location(p.getWorld(), p.getWorld().getSpawnLocation().getX(), p.getWorld().getHighestBlockYAt(p.getWorld().getSpawnLocation()) + 2, p.getWorld().getSpawnLocation().getZ());

                p.teleport(sp);
            }
        } else {
            p.sendMessage("Sorry, you do not have permission to use this command. If you think this is a mistake, please contact your server operators.");
        }

        return false;
    }
}
