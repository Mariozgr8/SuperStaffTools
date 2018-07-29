package me.mariozgr8.superstafftools.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.mariozgr8.superstafftools.MessageManager;
import me.mariozgr8.superstafftools.PermissionList;
import me.mariozgr8.superstafftools.SuperStaffTools;
import me.mariozgr8.superstafftools.Handlers.InventoryHandler;
import me.mariozgr8.superstafftools.Handlers.PlayerHandler;

public class ClickListener implements Listener {
	private SuperStaffTools ss = SuperStaffTools.getMainInstance();
	private MessageManager chat = ss.getMessages();
	private PlayerHandler utils = PlayerHandler.getSingleton();
	private InventoryHandler invHandler = InventoryHandler.getSingleton();
	private PermissionList perms = ss.getPerms();
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getInventory() == null) {
			return;
		}
		if(e.getCurrentItem() == null) {
			return;
		}
		Inventory inv = e.getInventory();
		ItemStack i = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		
		//Cancel the event if the inventory is an inv from this plugin
		if(inv.getName().equals(this.chat.getMainInvName())|| inv.getName().equals(this.chat.getGamemodeInvName()) || inv.getName().equals(this.chat.getDifficultyInvName()) || inv.getName().equals(this.chat.getWeatherInvName()) || inv.getName().equals(this.chat.getEffectInvName()) || inv.getName().equals(this.chat.getTimeInvName()) || inv.getName().equals(this.chat.getSpawnInvName())) {
			e.setCancelled(true);
			if(i.equals(this.invHandler.backItem())) {
				p.openInventory(this.invHandler.getStaffGUI(p));
			}
		}
		//Spawn Inv Actions
		if(inv.getName().equals(this.chat.getSpawnInvName())) {
			if(i.getType() == Material.MOB_SPAWNER)
			{
				EntityType type = this.utils.getEntityFromItemName(i);
				String mobName = i.getItemMeta().getDisplayName().replace("Spawn ", "");
				Location loc = p.getLocation();
				loc.getWorld().spawnEntity(loc, type);
				this.chat.sendMessageToPlayer(this.chat.getSpawnMobMessage().replace("%mob%", mobName), p);
			}
		}
		//Time Inv Actions
		if(inv.getName().equals(this.chat.getTimeInvName())) {
			if(i.getType() == Material.DOUBLE_PLANT) {
				World w = p.getLocation().getWorld();
				w.setTime(utils.getTimeFromItem(i));
				this.chat.sendMessageToPlayer(this.chat.getTimeChangeMessage().replace("%time%", this.utils.getTimeMessageFromItem(i)), p);
			}
		}
		
		//Effects Inv Actions
		if(inv.getName().equals(this.chat.getEffectInvName())) {
			if(i.equals(this.invHandler.nightVisionItem()))
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 100000, 0));
				this.chat.sendMessageToPlayer(this.chat.getApplyEffect("Night Vision", 1), p);
			}
			if(i.equals(this.invHandler.waterBreathingItem()))
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 100000, 0));
				this.chat.sendMessageToPlayer(this.chat.getApplyEffect("Water Breathing", 1), p);
			}
			if(i.equals(this.invHandler.invisibilityItem()))
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 0));
				this.chat.sendMessageToPlayer(this.chat.getApplyEffect("Invisibility", 1), p);
			}
			if(i.equals(this.invHandler.glowingItem()))
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100000, 0));
				this.chat.sendMessageToPlayer(this.chat.getApplyEffect("Glowing", 1), p);
			}
			if(i.equals(this.invHandler.healItem()))
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 100000, 0));
				this.chat.sendMessageToPlayer(this.chat.getApplyEffect("Heal", 1), p);
			}
			if(i.equals(this.invHandler.clearEffectsItem()))
			{
				p.getActivePotionEffects().clear();
				for (PotionEffect pe : p.getActivePotionEffects()) {
			          p.removePotionEffect(pe.getType());
				}
				this.chat.sendMessageToPlayer(this.chat.getClearEffects(), p);
			} 
			if(i.equals(this.invHandler.speedItem()) || i.equals(this.invHandler.jumpBoostItem()) || i.equals(this.invHandler.resistanceItem()) || i.equals(this.invHandler.fireResistanceItem()) || i.equals(this.invHandler.strenghItem()) || i.equals(this.invHandler.regenerationItem()) || i.equals(this.invHandler.hasteItem()) || i.equals(this.invHandler.levitationItem()) || i.equals(this.invHandler.absorbtionItem()))		
			{
				p.openInventory(this.invHandler.levelsEffectInv(this.utils.getNameOfPotionFromItemStack(i), i.getType(), i.getItemMeta().getDisplayName(), i.getDurability()));
			}
		}
		//Levels Inventory Actions
		if(inv.getName().equals(this.chat.getLevelEffectInvName().replace("%potion%", this.utils.getNameOfPotionFromItemStack(p.getOpenInventory().getItem(13))))) {
			e.setCancelled(true);
			if(i.equals(this.invHandler.backItem())) {
				p.openInventory(this.invHandler.effectInv());
			}
			if(i.equals(this.invHandler.ButtonPlus1()))
			{
				int int1 = Integer.parseInt(inv.getItem(22).getItemMeta().getDisplayName().replace("Level: ", ""));
				p.getOpenInventory().setItem(22, this.invHandler.levelSelector(int1 +1));
			}
			if(i.equals(this.invHandler.ButtonPlus10()))
			{
				int int1 = Integer.parseInt(inv.getItem(22).getItemMeta().getDisplayName().replace("Level: ", ""));
				p.getOpenInventory().setItem(22, this.invHandler.levelSelector(int1 +10));
			}
			if(i.equals(this.invHandler.ButtonPlus100()))
			{
				int int1 = Integer.parseInt(inv.getItem(22).getItemMeta().getDisplayName().replace("Level: ", ""));
				p.getOpenInventory().setItem(22, this.invHandler.levelSelector(int1 +100));
			}
			if(i.equals(this.invHandler.ButtonMinus1()))
			{
				int int1 = Integer.parseInt(inv.getItem(22).getItemMeta().getDisplayName().replace("Level: ", ""));
				p.getOpenInventory().setItem(22, this.invHandler.levelSelector(int1 - 1));
			}
			if(i.equals(this.invHandler.ButtonMinus10()))
			{
				int int1 = Integer.parseInt(inv.getItem(22).getItemMeta().getDisplayName().replace("Level: ", ""));
				p.getOpenInventory().setItem(22, this.invHandler.levelSelector(int1 - 10));
			}
			if(i.equals(this.invHandler.ButtonResetToZero()))
			{
				p.getOpenInventory().setItem(22, this.invHandler.levelSelector(1));
			}
			if(i.equals(this.invHandler.applyEffectItem()))
			{
				ItemStack it = p.getOpenInventory().getItem(13);
				int int1 = Integer.parseInt(inv.getItem(22).getItemMeta().getDisplayName().replace("Level: ", ""));
				p.addPotionEffect(new PotionEffect(this.utils.getPotionFromItemStack(it), 100000000, int1 -1));
				p.openInventory(this.invHandler.effectInv());
				this.chat.sendMessageToPlayer(this.chat.getApplyEffect(this.utils.getNameOfPotionFromItemStack(it), int1), p);
			}
		}
		
		//Gamemode Inv Actions
		if(inv.getName().equals(this.chat.getGamemodeInvName())) {
			if(i.equals(this.invHandler.survivalItem())) {
				p.setGameMode(GameMode.SURVIVAL);
				this.chat.sendMessageToPlayer(this.chat.getGamemodeToSurvival(), p);
			}
			if(i.equals(this.invHandler.creativeItem())) {
				p.setGameMode(GameMode.CREATIVE);
				this.chat.sendMessageToPlayer(this.chat.getGamemodeToCreative(), p);
			}
			if(i.equals(this.invHandler.adventureItem())) {
				p.setGameMode(GameMode.ADVENTURE);
				this.chat.sendMessageToPlayer(this.chat.getGamemodeToAdventure(), p);
			}
			if(i.equals(this.invHandler.spectatorItem())) {
				p.setGameMode(GameMode.SPECTATOR);
				this.chat.sendMessageToPlayer(this.chat.getGamemodeToSpectator(), p);
			}
		}
		//Difficulty Inv Actions
		if(inv.getName().equals(this.chat.getDifficultyInvName())) {
			ConsoleCommandSender sender = Bukkit.getServer().getConsoleSender();
			if(i.equals(this.invHandler.peacefullItem())) {
				Bukkit.getServer().dispatchCommand(sender, "difficulty 0");
			}
			if(i.equals(this.invHandler.easyItem())) {
				Bukkit.getServer().dispatchCommand(sender, "difficulty 1");
			}
			if(i.equals(this.invHandler.normalItem())) {
				Bukkit.getServer().dispatchCommand(sender, "difficulty 2");
			}
			if(i.equals(this.invHandler.hardItem())) {
				Bukkit.getServer().dispatchCommand(sender, "difficulty 3");
			}
		}
		//Weather Inv Actions 
		if(inv.getName().equals(this.chat.getWeatherInvName())) {
			if(i.equals(this.invHandler.rainItem())) {
				World w = p.getLocation().getWorld();
				w.setStorm(true);
				this.chat.sendMessageToPlayer(this.chat.getWeatherToRain(), p);
			}
			if(i.equals(this.invHandler.dryItem())) {
				World w = p.getLocation().getWorld();
				w.setStorm(false);
				this.chat.sendMessageToPlayer(this.chat.getWeatherToDry(), p);
			}
		}
		//Main Inv Actions
		if(inv.getName().equals(this.chat.getMainInvName())) {
			if(i.equals(this.invHandler.flyItem(p))) {
				if(p.hasPermission(this.perms.getFlyItem()))
				{
					if(p.getAllowFlight()) {
						p.setAllowFlight(false);
						p.setFlying(false);
						this.chat.sendMessageToPlayer(this.chat.getFlyModeOff(), p);
					} 
					else {
						p.setAllowFlight(true);
						this.chat.sendMessageToPlayer(this.chat.getFlyModeOn(), p);
						
					}
					p.openInventory(this.invHandler.getStaffGUI(p));
					return;
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.godItem(p))) {
				if(p.hasPermission(this.perms.getGodItem()))
				{
					p.setInvulnerable(!p.isInvulnerable());	
					this.chat.sendMessageToPlayer((p.isInvulnerable()? this.chat.getGodModeOn(): this.chat.getGodModeOff()), p);
					p.openInventory(this.invHandler.getStaffGUI(p));
					return;
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.vanishItem(p))) {
				if(p.hasPermission(this.perms.getVanishItem())) {
					utils.toggleVanish(p);
					this.chat.sendMessageToPlayer((utils.isVanishedPlayer(p)) ? this.chat.getVanishModeOn() : this.chat.getVanishModeOff(), p);
					p.openInventory(this.invHandler.getStaffGUI(p));
					return;
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.staffModeItem(p))) {
				if(p.hasPermission(this.perms.getStaffModeItem())) {
					this.utils.toggleStaffMode(p);
					p.openInventory(this.invHandler.getStaffGUI(p));
					return;
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.healItem(p))) {
				
				if(p.hasPermission(this.perms.getHealItem())) {
					this.chat.sendMessageToPlayer(this.chat.getHealMessage(), p);
					p.setFoodLevel(20);
					p.setHealth(20.0);
				return;
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.feedItem(p))) {
				if(p.hasPermission(this.perms.getFeedItem())) {
					p.setFoodLevel(20);
					this.chat.sendMessageToPlayer(this.chat.getFeedMessage(), p);
					return;
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.clearChatItem(p))) {
				if(p.hasPermission(this.perms.getClearChatItem())) {
					for(int it = 0; it<100; it++)
					{
						Bukkit.broadcastMessage("");
					}
					Bukkit.broadcastMessage(this.chat.setColorForMessage(this.chat.getPrefix() + this.chat.getClearChatMessagePlayers()));
					this.chat.sendMessageToPlayer(this.chat.getClearChatMessageSender(), p);
					return;
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.muteChatItem(p))) {
				if(p.hasPermission(this.perms.getMuteChatItem())) {
					if(this.utils.isChatMuted()) {
						this.utils.toggleChat();
						this.chat.sendMessageToPlayer(this.chat.getMuteChatMessageSenderDisabled(), p);
						for(Player target: Bukkit.getOnlinePlayers())
						{
							this.chat.sendMessageToPlayer(this.chat.getMuteChatMessagePlayersDisabled(), target);
						}
					}
					else {
						this.utils.toggleChat();
						this.chat.sendMessageToPlayer(this.chat.getMuteChatMessageSenderEnabled(), p);
						for(Player target: Bukkit.getOnlinePlayers())
						{
							this.chat.sendMessageToPlayer(this.chat.getMuteChatMessagePlayersEnabled(), target);
						}
					}
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.gamemodeItem(p))) {
				if(p.hasPermission(this.perms.getGamemodeItem())) {
					p.openInventory(this.invHandler.gamemodeInv());
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.difficultyItem(p))) {
				if(p.hasPermission(this.perms.getDifficultyItem())) {
					p.openInventory(this.invHandler.difficultyInv());
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.weatherItem(p))) {
				if(p.hasPermission(this.perms.getWeatherItem())) {
					p.openInventory(this.invHandler.weatherInv());
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.timeItem(p))) {
				if(p.hasPermission(this.perms.getTimeItem())) {
					p.openInventory(this.invHandler.timeInv());
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.whitelistItem(p))) {
				if(p.hasPermission(this.perms.getWhitelistItem())) {
					boolean check = Bukkit.getServer().hasWhitelist();
					if(check) {
						Bukkit.getServer().setWhitelist(false);
						this.chat.sendMessageToPlayer(this.chat.getWhitelistToOff(), p);
						p.openInventory(this.invHandler.getStaffGUI(p));
					}
					else {
						Bukkit.getServer().setWhitelist(true);
						this.chat.sendMessageToPlayer(this.chat.getWhitelistToOn(), p);
						p.openInventory(this.invHandler.getStaffGUI(p));
					}
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.effectItem(p))) {
				if(p.hasPermission(this.perms.getEffectItem())) {
					p.openInventory(this.invHandler.effectInv());
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.spawnItem(p))) {
				if(p.hasPermission(this.perms.getSpawnItem())) {
					p.openInventory(this.invHandler.spawnInv());
				}
				else {
					return;
				}
			}
			if(i.equals(this.invHandler.closeInvItem())) {
				p.closeInventory();
				return;
			}	
 		}
	}
}