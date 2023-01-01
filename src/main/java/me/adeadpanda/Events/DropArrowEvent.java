package me.adeadpanda.Events;

import me.adeadpanda.AwoogaTag;
import me.adeadpanda.Utils.ColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Objects;

public class DropArrowEvent implements Listener {

    private final AwoogaTag instance;

    public DropArrowEvent(AwoogaTag instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onDropEvent(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().hasItemMeta()) {
            if (Objects.requireNonNull(event.getItemDrop().getItemStack().getItemMeta()).getDisplayName().equalsIgnoreCase(ColorUtil.set(instance.getConfig().getString("Item.Name")))) {
                event.getPlayer().sendMessage(instance.getConfig().getString("Messages.no-drop"));
                event.getItemDrop().remove();
                event.getPlayer().getInventory().addItem(instance.createItem());
            }
        }
    }
}
