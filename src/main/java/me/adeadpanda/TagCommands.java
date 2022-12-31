package me.adeadpanda;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagCommands implements CommandExecutor {
    private final AwoogaTag instance;

    public TagCommands(AwoogaTag instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("tag.admin")) {
                if (args[0].equalsIgnoreCase("clear")) {
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        if (player.getInventory().contains(instance.createItem())) {
                            player.getInventory().remove(instance.createItem());
                        }
                    });
                }
                if (args[0].equalsIgnoreCase("set")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target.getInventory().contains(instance.createItem())) {
                        sender.sendMessage(target.getDisplayName() + ChatColor.GOLD + "" + ChatColor.BOLD + " is Already is it!");
                        return true;
                    }
                    if (target.getInventory().getItemInMainHand().getType().isAir()) {
                        target.getInventory().setItemInMainHand(instance.createItem());
                    } else if (!target.getInventory().getItemInMainHand().getType().isAir()) {
                        target.getWorld().dropItem(target.getLocation(), instance.createItem());
                    }
                }
            }
        }
        return true;
    }
}
