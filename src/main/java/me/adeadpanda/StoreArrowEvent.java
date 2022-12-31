package me.adeadpanda;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class StoreArrowEvent implements Listener {
    private final AwoogaTag instance;

    public StoreArrowEvent(AwoogaTag instance) {
        this.instance = instance;
    }

    @EventHandler
    public void ArrowStoreEvent(InventoryClickEvent event) {
        if (event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) && event.getCurrentItem().equals(instance.createItem())) {
            event.getCurrentItem().setAmount(0);
            Bukkit.getScheduler().runTaskLater(instance, () -> {
                event.getWhoClicked().getInventory().addItem(instance.createItem());
            }, 20L * 5);
        }

    }
}
