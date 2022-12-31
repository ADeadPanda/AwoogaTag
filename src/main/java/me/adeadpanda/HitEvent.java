package me.adeadpanda;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.UUID;

public class HitEvent implements Listener {

    private final AwoogaTag instance;

    public HitEvent(AwoogaTag instance) {
        this.instance = instance;
    }

    HashMap<UUID, Boolean> map = new HashMap<>();

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player attacker = ((Player) event.getDamager()).getPlayer();
            Player victim = (Player) event.getEntity();
            if (attacker.getInventory().getItemInMainHand().getType().equals(Material.ARROW) && attacker.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LUCK) && attacker.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.ITALIC + "Cupid's Arrow")) {
                if (map.containsKey(victim.getUniqueId())) {
                    event.setCancelled(true);
                    return;
                }
                attacker.getInventory().getItemInMainHand().setAmount(0);
                if (victim.getInventory().getItemInMainHand().getType().isAir()) {
                    victim.getInventory().setItemInMainHand(instance.createItem());
                    event.setDamage(0);
                    attacker.setCanPickupItems(false);
                    map.put(attacker.getUniqueId(), true);
                } else if (!victim.getInventory().getItemInMainHand().getType().isAir()) {
                    victim.getWorld().dropItem(victim.getLocation(), victim.getInventory().getItemInMainHand());
                    victim.getInventory().setItemInMainHand(instance.createItem());
                    event.setDamage(0);
                    attacker.setCanPickupItems(false);
                    map.put(attacker.getUniqueId(), true);
                }
                victim.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Anti-Tag back Time Started!");
                attacker.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Item pickup disabled temporarily!");
                Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + victim.getDisplayName() + ChatColor.RED + "" + ChatColor.BOLD + " has been tagged by: " + attacker.getPlayer().getDisplayName());

                Bukkit.getScheduler().runTaskLater(instance, () -> {
                    map.remove(attacker.getUniqueId());
                    attacker.setCanPickupItems(true);
                    attacker.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Anti-Tag back time is now up!");
                }, 20L * 10);
            }
        }
    }
}
