package com.edzdez.vanillaplus.commands;

import com.edzdez.vanillaplus.VanillaPlus;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class healCommand implements CommandExecutor {

    private VanillaPlus plugin;

    public healCommand(VanillaPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("heal").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(plugin.getConfig().getBoolean("heal"))) return false;

        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, Console can't do this!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("vanillaplus.heal")) {
            if (args.length < 1) {
                p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
                p.setFoodLevel(20);
                p.sendMessage(p.getName() + " has been healed to full health!");
                return true;
            } else {
                for (String str : args) {
                    Player t = Bukkit.getPlayer(str);
                    t.setHealth(t.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
                    t.setFoodLevel(20);
                    p.sendMessage(t.getName() + " has been healed to full health!");
                }
                return true;
            }
        } else {
            p.sendMessage("Sorry, you do not have permission to use this command. If you think this is a mistake, please contact your server operators.");
        }

        return false;
    }
}
