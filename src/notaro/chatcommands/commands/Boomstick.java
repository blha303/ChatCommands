package notaro.chatcommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Boomstick implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player == false){
			sender.sendMessage(ChatColor.RED + "This command can only be used in the chat!");
			return true;
		}
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("boomstick")){
			if(player.hasPermission("notaro.boomstick") || player.hasPermission("notaro.*")){
				player.sendMessage(ChatColor.GOLD + "You have now been given a " + ChatColor.AQUA + "boomstick");
				PlayerInventory inventory = player.getInventory();
				ItemStack boomstick = new ItemStack(Material.BLAZE_ROD, 1);
				boomstick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
				boomstick.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
				ItemMeta meta = (ItemMeta) boomstick.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_AQUA + "Boomstick");
				inventory.addItem(boomstick);	
			}else{
				player.sendMessage(ChatColor.RED + "You need the permission: " + ChatColor.DARK_GREEN + "notaro.boomstick " + ChatColor.RED + "to perform this command."); 
			}
		}		
		return false;
	}
}
