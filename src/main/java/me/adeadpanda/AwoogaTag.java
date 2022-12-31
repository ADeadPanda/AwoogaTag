package me.adeadpanda;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class AwoogaTag extends JavaPlugin {

    HitEvent hitEvent = new HitEvent(this);
    DropArrowEvent dropArrowEvent = new DropArrowEvent(this);
    StoreArrowEvent arrowStoreEvent = new StoreArrowEvent(this);

    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.ITALIC + "Cupid's Arrow");
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "You are it hit someone with");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "this item to 'Tag them'");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void onEnable() {
        createItem();
        getServer().getPluginManager().registerEvents(hitEvent, this);
        getServer().getPluginManager().registerEvents(dropArrowEvent, this);
        getServer().getPluginManager().registerEvents(arrowStoreEvent, this);
        this.getCommand("tag").setExecutor(new TagCommands(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}