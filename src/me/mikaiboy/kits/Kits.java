package me.mikaiboy.kits;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class Kits extends JavaPlugin{
	      
   
    ArrayList<Player> cooldown = new ArrayList<Player>();
   
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
            if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "Ãlleen players kunnen kits!");
                    Player p = (Player) sender;
                    if(p.hasPermission("Server.kit"))
                    return true;
            }
           
            final Player p = (Player) sender;
            PlayerInventory pi = p.getInventory();
           
        	if (cmd.getName().equalsIgnoreCase("kit")) {
                    if (cooldown.contains(p)) {
                		if(p.hasPermission("Server.kit")) {
                			p.sendMessage(ChatColor.RED + "You dont have permission!");
                            return true;
                    } else
                    	p.sendMessage(ChatColor.RED + "Je kunt nog geen kit!");
                            pi.addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
                            pi.addItem(new ItemStack(Material.DIAMOND_HELMET, 1));
                            pi.addItem(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                            pi.addItem(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                            pi.addItem(new ItemStack(Material.DIAMOND_BOOTS, 1));
                            pi.addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
                            p.sendMessage(ChatColor.GREEN + "Je hebt je kit!");
                            cooldown.add(p);
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                    public void run() {
                                            cooldown.remove(p);
                                    }
                            }, 20000);
                            return true;
                    }
                    else {
                    	    Player p1 = (Player) sender;
                            p1.sendMessage(ChatColor.RED + "Je kunt op dit moment nog geen kit");
                            return true;
                    }
}
			return false;
    }
}



