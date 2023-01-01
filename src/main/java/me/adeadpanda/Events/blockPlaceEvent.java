package me.adeadpanda.Events;

import me.adeadpanda.AwoogaTag;
import me.adeadpanda.Utils.ColorUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class blockPlaceEvent implements Listener {

    private final AwoogaTag instance;

    public blockPlaceEvent(AwoogaTag instance) {
        this.instance = instance;
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if (event.getItemInHand().getItemMeta().getDisplayName().equals(ColorUtil.set(instance.getConfig().getString("Item.Name"))) && event.getItemInHand().hasItemMeta()) {
            event.getPlayer().sendMessage(ColorUtil.set(instance.getConfig().getString("Messages.no-place-block")));
            event.setCancelled(true);
        }
    }
}
