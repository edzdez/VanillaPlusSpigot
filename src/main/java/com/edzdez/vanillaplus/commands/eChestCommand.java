package com.edzdez.vanillaplus.commands;

import com.edzdez.vanillaplus.VanillaPlus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class eChestCommand implements CommandExecutor {
    private VanillaPlus plugin;

    public eChestCommand(VanillaPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("echest").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, Console can't do this!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("vanillaplus.echest")) {
            p.openInventory(p.getEnderChest());
            return true;
        } else {
            p.sendMessage("Sorry, you do not have permission to use this command. If you think this is a mistake, please contact your server operators.");
        }
        return false;
    }
}
