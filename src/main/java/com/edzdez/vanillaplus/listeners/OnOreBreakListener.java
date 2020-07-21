package com.edzdez.vanillaplus.listeners;

import com.edzdez.vanillaplus.VanillaPlus;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class OnOreBreakListener implements Listener {
    List<Material> tools = Arrays.asList(Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE);
    VanillaPlus plugin;

    public OnOreBreakListener(VanillaPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void event(BlockBreakEvent event) {
        if (!(plugin.getConfig().getBoolean("autosmelt"))) return;

        Block block = event.getBlock();
        Player p = event.getPlayer();
        Material material = block.getType();
        if (material.equals(Material.IRON_ORE) && tools.contains(p.getInventory().getItemInMainHand().getType())) {
            event.getBlock().setType(Material.AIR);
            p.getWorld().playEffect(block.getLocation(), Effect.SMOKE, 0, 20);
            p.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
        }
        if (material.equals(Material.GOLD_ORE) && tools.contains(p.getInventory().getItemInMainHand().getType())) {
            event.getBlock().setType(Material.AIR);
            p.getWorld().playEffect(block.getLocation(), Effect.SMOKE, 0, 20);
            p.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
        }
    }
}
