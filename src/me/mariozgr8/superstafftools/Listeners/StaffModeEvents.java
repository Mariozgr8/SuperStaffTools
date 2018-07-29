package me.mariozgr8.superstafftools.Listeners;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.mariozgr8.superstafftools.MessageManager;
import me.mariozgr8.superstafftools.SettingsManager;
import me.mariozgr8.superstafftools.SuperStaffTools;
import me.mariozgr8.superstafftools.Handlers.PlayerHandler;

public class StaffModeEvents implements Listener {
	private SettingsManager settings = SettingsManager.getSettings();
	private PlayerHandler utils = PlayerHandler.getSingleton();
	private MessageManager chat = SuperStaffTools.getMainInstance().getMessages();
	private SuperStaffTools ss = SuperStaffTools.getMainInstance();
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(!p.hasPlayedBefore()) {
			this.settings.getPlayerDataConfig().set(p.getName()+".inStaffMode", Boolean.valueOf(false));
			this.settings.getPlayerDataConfig().set(p.getName()+".isFrozen", Boolean.valueOf(false));
			this.settings.savePlayerDataConfig();
		}
		if (this.settings.getPlayerDataConfig().getBoolean(e.getPlayer().getName() + ".inStaffMode")) {
		      this.utils.addStaffModePlayer(p);
		}
		if (this.settings.getPlayerDataConfig().getBoolean(e.getPlayer().getName() + ".isFrozen")) {
		      this.utils.addFrozenPlayer(p);
		      this.chat.sendMessageToPlayer(this.chat.getPlayerFreezeMessage(), p);
		}
	}
	@EventHandler
    public void onQuitEvent(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(this.utils.isStaffModePlayer(p)) {
			this.utils.removeStaffModePlayer(p);
		}
		if(this.utils.isFrozenPlayer(p)) {
			this.utils.removeFrozenPlayer(p);
		}
    }
	@EventHandler
	public void onDamageEvent(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(this.utils.isStaffModePlayer(p)) {
				if(e.getCause() == DamageCause.FALL) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(this.utils.isStaffModePlayer(p)) {
			e.setCancelled(true);
			this.chat.sendMessageToPlayer(this.chat.getStaffModeNoBreak(), p);
		}
	}
	@EventHandler
	public void onBuild(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if(this.utils.isStaffModePlayer(p)) {
			e.setCancelled(true);
			this.chat.sendMessageToPlayer(this.chat.getStaffModeNoBuild(), p);
		}
	}
	@EventHandler
	public void onDropEvent(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if(this.utils.isStaffModePlayer(p)) {
			e.setCancelled(true);
			this.chat.sendMessageToPlayer(this.chat.getStaffModeNoDrop(), p);
		}
	}
	@EventHandler
	public void onPickupEvent(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		if(this.utils.isStaffModePlayer(p)) {
			e.setCancelled(true);
			
			
			if(!utils.isCooldown2(p)) {
				this.chat.sendMessageToPlayer(this.chat.getStaffModeNoPickup(), p);
				this.utils.addPlayerToCooldown2(p);
				
				new BukkitRunnable() {
					@Override
					public void run() {
						utils.removePlayerFromCooldown2(p);
					}
				}.runTaskLater(ss, 60);
			}		
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();	
		if(this.utils.isTestedPlayer(p) && (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK))
	    {
	    	  this.utils.incrementCount(p);
	    	  return;
	    }
		if(this.utils.isStaffModePlayer(p))
	    {
	        if (p.getInventory().getItemInMainHand().equals(this.utils.compassItem()))
	        {
	          Vector vector = p.getLocation().getDirection();
	          p.setVelocity(vector.multiply(5));
	        }
	        if (p.getInventory().getItemInMainHand().equals(this.utils.randomTeleportItem()))
	        {
	            if(Bukkit.getServer().getOnlinePlayers().size() <= 1)
	            {
	              this.chat.sendMessageToPlayer(this.chat.getCannotTeleportMessage(), p);
	              return;
	            }
	            ArrayList<Player> players = new ArrayList<Player>();
	            for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
	              if(!pl.getName().equals(p.getName())) {
	                players.add(pl);
	              }  
	            }
	            int random = new Random().nextInt(players.size());
	            p.teleport((Entity)players.get(random));
	            this.chat.sendMessageToPlayer("&6Teleporting to " + ((Player)players.get(random)).getName(), p);
	        }	
		    if(p.getInventory().getItemInMainHand().equals(this.utils.turnStaffModeOffItem()))
		    {
		  	  this.utils.toggleStaffMode(p);
		    }
	    }
	}
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(this.utils.isFrozenPlayer(p)) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onEntityInteract(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		if(this.utils.isStaffModePlayer(p) && !this.utils.isCooldown(p)) {
			if(e.getRightClicked() instanceof Player) {
				Player t = (Player) e.getRightClicked();
				if(p.getInventory().getItemInMainHand() == null) {
					return;
				}
				ItemStack i = p.getInventory().getItemInMainHand();
				if(i.equals(this.utils.inventoryItem())) {
					p.openInventory(t.getInventory());
				}
				if(i.equals(this.utils.freezeItem())) {
					if(this.utils.isFrozenPlayer(t)) {
						this.utils.removeFrozenPlayer(t);
						this.chat.sendMessageToPlayer(this.chat.getPlayerUnfreezeMessage(), t);
						this.chat.sendMessageToPlayer(this.chat.getStaffUnfreezePlayerMessage().replace("%player%", t.getName()), p);
						this.settings.getPlayerDataConfig().set(t.getName()+".isFrozen", false);
					}
					else {
						this.utils.addFrozenPlayer(t);
						this.chat.sendMessageToPlayer(this.chat.getPlayerFreezeMessage(), t);
						this.chat.sendMessageToPlayer(this.chat.getStaffFreezePlayerMessage().replace("%player%", t.getName()), p);
						this.settings.getPlayerDataConfig().set(t.getName()+".isFrozen", true);
					}
				}
				if(i.equals(this.utils.informationsItem())) {
			        this.chat.sendMessageToPlayer("&6&n" + t.getName() + "'s information:", p);
		            this.chat.sendMessageToPlayerWithoutPrefix("&6- Name: &c" + t.getName(), p);
		            this.chat.sendMessageToPlayerWithoutPrefix("&6- IP: &c" + t.getAddress().toString(), p);
		            this.chat.sendMessageToPlayerWithoutPrefix("&6- Location: &cX: " + t.getLocation().getBlockX() + 
		              ", Y: " + t.getLocation().getBlockY() + 
		              ", Z: " + t.getLocation().getBlockZ(), p);
		            this.chat.sendMessageToPlayerWithoutPrefix("&6- World: &c" + t.getLocation().getWorld().getName(), p); 
		            this.chat.sendMessageToPlayerWithoutPrefix("&6- Food Level: &c"+t.getFoodLevel()+"/20", p);
		            this.chat.sendMessageToPlayerWithoutPrefix("&6- Health: &c"+(int) t.getHealth()+"/"+(int) p.getMaxHealth(), p);
		            this.chat.sendMessageToPlayerWithoutPrefix("&6- Level: &c"+t.getLevel(), p);
		            this.chat.sendMessageToPlayerWithoutPrefix("&6- Fly Status: &c"+t.getAllowFlight(), p);
		            this.chat.sendMessageToPlayerWithoutPrefix("&6- God Status: &c"+t.isInvulnerable(), p);
		            this.chat.sendMessageToPlayerWithoutPrefix("&6- StaffMode: &c"+this.utils.isStaffModePlayer(t), p); 
				}
				if(i.equals(this.utils.cpsItem())) {
					this.utils.startCpsTest(p, t);
				}
				if(i.equals(this.utils.enderchestItem())) {
					p.openInventory(t.getEnderChest());
				}
				this.utils.addPlayerToCooldown(p);
			}
		}
	}
}