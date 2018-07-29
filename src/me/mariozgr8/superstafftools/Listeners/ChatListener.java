package me.mariozgr8.superstafftools.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.mariozgr8.superstafftools.MessageManager;
import me.mariozgr8.superstafftools.PermissionList;
import me.mariozgr8.superstafftools.SuperStaffTools;
import me.mariozgr8.superstafftools.Handlers.PlayerHandler;

public class ChatListener implements Listener {
	private PlayerHandler utils = PlayerHandler.getSingleton();
	private SuperStaffTools ss = SuperStaffTools.getMainInstance();
	private PermissionList perms = ss.getPerms();
	private MessageManager chat = ss.getMessages();
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(utils.isChatMuted()) {
			if(!p.hasPermission(perms.getMuteChatBypass())) {
				e.setCancelled(true);
				this.chat.sendMessageToPlayer(this.chat.getMuteChatMessageAction(), p);
			}
		}
	}
}
