package me.adeadpanda.Events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class blockPlaceEvent implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "Cheese Touch") && event.getItemInHand().hasItemMeta()) {
            event.getPlayer().sendMessage(ChatColor.RED + "You can not place the Cheese!");
            event.setCancelled(true);
        }
    }
}
