package me.adeadpanda.Events;

import me.adeadpanda.AwoogaTag;
import me.adeadpanda.Utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class StoreItemEvent implements Listener {
    private final AwoogaTag instance;

    public StoreItemEvent(AwoogaTag instance) {
        this.instance = instance;
    }

    @EventHandler
    public void ItemStoreEvent(InventoryClickEvent event) {
        if (event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) && event.getCurrentItem().equals(instance.createItem())) {
            event.getCurrentItem().setAmount(0);
            event.getWhoClicked().sendMessage(ColorUtil.set(instance.getConfig().getString("Messages.no-store")));
            Bukkit.getScheduler().runTaskLater(instance, () -> {
                event.getWhoClicked().getInventory().addItem(instance.createItem());
            }, 20L);
        }

    }
}
