package notaro.chatcommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Clear implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player == false){
			sender.sendMessage(ChatColor.RED + "This command can only be used in the chat!");
			return true;
		}
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("clear")){
			if(player.hasPermission("notaro.clear") || player.hasPermission("notaro.*")){
				if(sender instanceof Player){
					player.getInventory().clear();
					player.sendMessage(ChatColor.DARK_AQUA + "Your inventory has been cleared");
				}
			} else{
				player.sendMessage(ChatColor.RED + "You need the permission: " + ChatColor.DARK_GREEN + "notaro.clear " + ChatColor.RED + "to perform this command.");
			}
		}
		return false;
	}
}
