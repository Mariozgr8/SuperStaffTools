package me.mariozgr8.superstafftools.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.mariozgr8.superstafftools.Handlers.PlayerHandler;

public class VanishEvents implements Listener {
	private PlayerHandler handler = PlayerHandler.getSingleton();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for(Player t: handler.getVanishedPlayers()) {
			p.hidePlayer(t);
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		for(Player t: handler.getVanishedPlayers()) {
			p.showPlayer(t);
		}
	}

}
