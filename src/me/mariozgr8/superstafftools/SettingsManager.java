package me.mariozgr8.superstafftools;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SettingsManager {	
	private static SettingsManager settings = new SettingsManager();
	private SettingsManager() { }
	
	public static SettingsManager getSettings() {
		return settings;
	}
	
	private FileConfiguration playerDataConfig;
	private File playerDataFile;
	
	public void setup(Plugin p) {
		if(!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		
		playerDataFile = new File(p.getDataFolder(), "playerdata.yml");
		if(!this.playerDataFile.exists()) {
			try {
				playerDataFile.createNewFile();
			}
			catch(IOException e) {
				Bukkit.getServer().getLogger().severe("Could not create playerData.yml file !");
			}
		}
		playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);
	}
	
	public FileConfiguration getPlayerDataConfig() {
		return playerDataConfig;
	}
	public void reloadPlayerDataConfig() {
		playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);
	}
	public void savePlayerDataConfig() {
		try {
			playerDataConfig.save(playerDataFile);
		}
		catch(IOException e) {
			Bukkit.getServer().getLogger().severe("Could not save playerData.yml file !");
		}
	}
}
