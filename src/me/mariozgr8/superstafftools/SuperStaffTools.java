package me.mariozgr8.superstafftools;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.mariozgr8.superstafftools.Commands.StaffChatCommand;
import me.mariozgr8.superstafftools.Commands.SuperStaffToolsCommand;
import me.mariozgr8.superstafftools.Handlers.PlayerHandler;
import me.mariozgr8.superstafftools.Listeners.ChatListener;
import me.mariozgr8.superstafftools.Listeners.ClickListener;
import me.mariozgr8.superstafftools.Listeners.StaffModeEvents;
import me.mariozgr8.superstafftools.Listeners.VanishEvents;

public class SuperStaffTools extends JavaPlugin{
	private String version = "2.2.1";
	
	private MessageManager messages;
	private PermissionList perms;
	private SettingsManager settings;
	private PlayerHandler utils;
	
	private static SuperStaffTools main;
	public static SuperStaffTools getMainInstance()
	{
		return main;
	}
	
	@Override
	public void onEnable() {
		main = this;
		messages = new MessageManager();
		perms = new PermissionList();
		settings = SettingsManager.getSettings();
		utils = PlayerHandler.getSingleton();
		
		//Load the files and create the missing one
		settings.setup(this);
		
		//Load the messages 
		loadConfig();
		messages.setup(this);
		
		registerCommands();
		registerEvents();
	
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage("Super StaffTools Enabled !");
		console.sendMessage("Version: " +this.getVersion());
		
		if(!Bukkit.getServer().getOnlinePlayers().isEmpty()) {
			for(Player p: Bukkit.getServer().getOnlinePlayers()) {
				if (this.settings.getPlayerDataConfig().getBoolean(p.getName() + ".inStaffMode")) {
				      this.utils.addStaffModePlayer(p);
				}
				if(this.settings.getPlayerDataConfig().getBoolean(p.getName()+".isFrozen")) {
					this.utils.addFrozenPlayer(p);
				}
			}
		}
		
		//Enable the cooldown runnable
		this.utils.runnableRunner(this);
		
	}
	@Override
	public void onDisable() {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage("Super StaffTools Disabled !");
		console.sendMessage("Version: " +this.getVersion());
	}
	
	public String getVersion() {
		return version;
	}
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	public void registerCommands()
	{
		this.getCommand("sstafftools").setExecutor(new SuperStaffToolsCommand(this));
		this.getCommand("staffchat").setExecutor(new StaffChatCommand(this));
	}
	public void registerEvents()
	{
		Bukkit.getServer().getPluginManager().registerEvents(new ClickListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new VanishEvents(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new StaffModeEvents(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(), this);
	}
	public PermissionList getPerms() {
		return perms;
	}
	public MessageManager getMessages() {
		return messages;
	}
	
}
