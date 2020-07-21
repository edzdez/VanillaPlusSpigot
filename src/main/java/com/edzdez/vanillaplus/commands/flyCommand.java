package com.edzdez.vanillaplus.commands;

import com.edzdez.vanillaplus.VanillaPlus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class flyCommand implements CommandExecutor {
    private VanillaPlus plugin;

    public flyCommand(VanillaPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("fly").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, Console can't do this!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("vanillaplus.fly")) {
            if (p.getAllowFlight()) p.setAllowFlight(false);
            else p.setAllowFlight(true);
            p.sendMessage("Toggling Flight...");
            return true;
        } else {
            p.sendMessage("Sorry, you do not have permission to use this command. If you think this is a mistake, please contact your server operators.");
        }
        return false;
    }
}
