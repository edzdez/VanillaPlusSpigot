package com.edzdez.vanillaplus.commands;

import com.edzdez.vanillaplus.VanillaPlus;
import com.edzdez.vanillaplus.listeners.OnDeathListener;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class backCommand implements CommandExecutor {
    private VanillaPlus plugin;
    private OnDeathListener deathListener = new OnDeathListener();

    public backCommand(VanillaPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("back").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, console can't do this!");
        }

        Player p = (Player) sender;
        Location deathLoc = deathListener.getDeathLoc(p.getName());

        if (p.hasPermission("vanillaplus.back")) {
            p.teleport(deathLoc);
        } else {
            p.sendMessage("Sorry, you do not have permission to use this command. If you think this is a mistake, please contact your server operators.");
        }

        return false;
    }
}
