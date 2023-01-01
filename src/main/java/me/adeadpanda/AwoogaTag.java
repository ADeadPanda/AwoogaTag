package me.adeadpanda;

import me.adeadpanda.Events.DropArrowEvent;
import me.adeadpanda.Events.HitEvent;
import me.adeadpanda.Events.StoreItemEvent;
import me.adeadpanda.Events.blockPlaceEvent;
import me.adeadpanda.Utils.ColorUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class AwoogaTag extends JavaPlugin {

    HitEvent hitEvent = new HitEvent(this);
    blockPlaceEvent blockPlaceEvent= new blockPlaceEvent(this);
    DropArrowEvent dropArrowEvent = new DropArrowEvent(this);
    StoreItemEvent storeItemEvent = new StoreItemEvent(this);

    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.getMaterial(this.getConfig().getString("Item.Material").toUpperCase(), true));
        if (item == null) {
            item = new ItemStack(Material.TNT);
        }
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.set(this.getConfig().getString("Item.Name")));
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> lore = new ArrayList<>();
        lore.addAll(this.getConfig().getStringList("Item.Lore"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        createItem();
        getServer().getPluginManager().registerEvents(hitEvent, this);
        getServer().getPluginManager().registerEvents(blockPlaceEvent, this);
        getServer().getPluginManager().registerEvents(dropArrowEvent, this);
        getServer().getPluginManager().registerEvents(storeItemEvent, this);
        this.getCommand("tag").setExecutor(new TagCommands(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
