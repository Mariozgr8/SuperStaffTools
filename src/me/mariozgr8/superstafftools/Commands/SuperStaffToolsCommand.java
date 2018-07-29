package me.mariozgr8.superstafftools.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.superstafftools.MessageManager;
import me.mariozgr8.superstafftools.PermissionList;
import me.mariozgr8.superstafftools.SuperStaffTools;
import me.mariozgr8.superstafftools.Handlers.InventoryHandler;

public class SuperStaffToolsCommand implements CommandExecutor {
	public SuperStaffToolsCommand(SuperStaffTools ss) {
		
	}
	
	private SuperStaffTools ss = SuperStaffTools.getMainInstance();
	private MessageManager chat = ss.getMessages();
	private PermissionList perms = ss.getPerms();
	private InventoryHandler invHandler = InventoryHandler.getSingleton();
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))
		{
			this.chat.playerOnly(sender);
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("sstafftools"))
		{
			if(!p.hasPermission(this.perms.getStaffCommand()))
			{
				this.chat.noPermission(p);
				return true;
			}
			if(args.length != 0)
			{
				this.chat.wrongArgs(p);
				return true;
			}
			p.openInventory(this.invHandler.getStaffGUI(p));
			return true;
		}
		return false;
	}

}
