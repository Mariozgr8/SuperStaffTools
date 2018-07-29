package me.mariozgr8.superstafftools.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.superstafftools.MessageManager;
import me.mariozgr8.superstafftools.PermissionList;
import me.mariozgr8.superstafftools.SuperStaffTools;

public class StaffChatCommand implements CommandExecutor {
	private MessageManager chat = SuperStaffTools.getMainInstance().getMessages();
	private PermissionList perms = SuperStaffTools.getMainInstance().getPerms();
	
	public StaffChatCommand(SuperStaffTools ss) {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))
		{
			this.chat.playerOnly(sender);
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("staffchat"))
		{
			if(!p.hasPermission(this.perms.getStaffChatCommand())) {
				this.chat.noPermission(p);
				return true;
			}
			if(args.length == 0)
			{
				this.chat.wrongArgs(p);
				return true;
			}
			StringBuilder str = new StringBuilder();
			for(int i = 0; i<args.length; i++)
			{
				str.append(args[i]+" ");
			}
			String messages = str.toString();
			Bukkit.getServer().broadcast(this.chat.setColorForMessage(this.chat.getStaffChatPrefix()+messages).replace("%sender%", p.getName()), this.perms.getStaffChatCommand());
			return true;
		}
		return false;
	}
}
