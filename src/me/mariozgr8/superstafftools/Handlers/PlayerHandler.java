package me.mariozgr8.superstafftools.Handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.mariozgr8.superstafftools.MessageManager;
import me.mariozgr8.superstafftools.SettingsManager;
import me.mariozgr8.superstafftools.SuperStaffTools;

public class PlayerHandler {
	private static PlayerHandler handler = new PlayerHandler();
	private PlayerHandler() { }
	
	public static PlayerHandler getSingleton() {
		return handler;
	}
	
	private SuperStaffTools ss = SuperStaffTools.getMainInstance();
	private MessageManager chat = ss.getMessages();
	private SettingsManager settings = SettingsManager.getSettings();

	private ArrayList<Player> vanished = new ArrayList<Player>();
	private ArrayList<Player> staffModePlayers = new ArrayList<Player>();
	private ArrayList<Player> frozen = new ArrayList<Player>();
	private ArrayList<Player> cooldown2 = new ArrayList<Player>();
	
	private HashMap<Player, Integer> cooldown = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> cpsCount = new HashMap<Player, Integer>();
	
	private boolean chatMuted = false;
	
	//Methods that handle the PlayerPickupItem cooldown
	public boolean isCooldown2(Player p) {
		return cooldown2.contains(p);
	}
	public void addPlayerToCooldown2(Player p) {
		if(!isCooldown2(p))
			cooldown2.add(p);
	}
	public void removePlayerFromCooldown2(Player p) {
		if(isCooldown2(p))
			cooldown2.remove(p);
	}
	
	//Methods that handle the chat Mute
	public void toggleChat() {
		this.chatMuted = !chatMuted;
	}
	public boolean isChatMuted() { 
		return chatMuted;
	}
	
	//Methods relating the cooldown map
	public void addPlayerToCooldown(Player p) {
		cooldown.put(p, 1);
	}
	public void runnableRunner(Plugin ss) {
		new BukkitRunnable() 
		{
			@Override
			public void run() 
			{
				if(cooldown.keySet().isEmpty()) {
					return;
				}
				for(Player p: cooldown.keySet()) {
					int time = cooldown.get(p);
					if(time == 0) {
						cooldown.remove(p);
					}
					else {
						cooldown.put(p, time - 1);
					}
				}
			}
		}.runTaskTimer(ss, 0, 10);
	}
	public boolean isCooldown(Player p) {
		return cooldown.keySet().contains(p);
	}
	//Methods relating to the cpsCount map
	public boolean isTestedPlayer(Player p) {
		return cpsCount.keySet().contains(p);
	}
	public void startCpsTest(Player tester, Player tested) {
		cpsCount.put(tested, 0);
		this.chat.sendMessageToPlayer("&6Started cps test on "+tested.getName(), tester);
		new BukkitRunnable() {
			public void run()
			{
				stopCps(tester, tested);
			}
		}.runTaskLater(ss, this.chat.getCpsTestTime() * 20);
	}
	private void stopCps(Player tester, Player tested) {
		double cpsAverage = cpsCount.get(tested) / this.chat.getCpsTestTime();
		this.chat.sendMessageToPlayer("&6"+tested.getName()+" has a cps of: &c"+cpsAverage, tester);
		cpsCount.remove(tested);
	}
	public void incrementCount(Player p) {
		if(isTestedPlayer(p)) {
			int amount = cpsCount.get(p);
			cpsCount.put(p, amount +1);
		}
	}
	//Methods that handle the frozen players
	public boolean addFrozenPlayer(Player p) {
		if(!isFrozenPlayer(p)) {
			frozen.add(p);
			return true;
		}
		return false;
	}
	public boolean removeFrozenPlayer(Player p) {
		if(isFrozenPlayer(p)) {
			frozen.remove(p);
			return true;
		}
		return false;
	}
	public boolean isFrozenPlayer(Player p) {
		return frozen.contains(p);
	}
	public ArrayList<Player> getFrozenPlayers() {
		return frozen;
	}
	
	//Methods that handle the vanished players
	private boolean addVanishedPlayer(Player p) {
		if(!isVanishedPlayer(p)) {
			vanished.add(p);
			return true;
		}
		return false;
	}
	private boolean removeVanishedPlayer(Player p) {
		if(isVanishedPlayer(p)) {
			vanished.remove(p);
			return true;
		}
		return false;
	}
	public boolean isVanishedPlayer(Player p) {
		return vanished.contains(p);
	}
	public ArrayList<Player> getVanishedPlayers() {
		return vanished;
	}
	
	public void toggleVanish(Player p) {
		if(isVanishedPlayer(p)) {
			for(Player t: Bukkit.getServer().getOnlinePlayers()) {
				t.showPlayer(p);
			}
			removeVanishedPlayer(p);
		}
		else {
			for(Player t: Bukkit.getServer().getOnlinePlayers()) {
				t.hidePlayer(p);
			}
			addVanishedPlayer(p);
		}
	}
	
	//Methods that handle the Staff Mode players
	public boolean addStaffModePlayer(Player p) {
		if(!isStaffModePlayer(p)) {
			staffModePlayers.add(p);
			return true;
		}
		return false;
	}
	public boolean removeStaffModePlayer(Player p) {
		if(isStaffModePlayer(p)) {
			staffModePlayers.remove(p);
			return true;
		}
		return false;
	}
	public boolean isStaffModePlayer(Player p) {
		return staffModePlayers.contains(p);
	}
	public ArrayList<Player> getStaffModePlayers(Player p) {
		return staffModePlayers;
	}
	public void toggleStaffMode(Player p) {
		if(isStaffModePlayer(p)) {
			removeStaffModePlayer(p);
			this.loadInventoryFromConfig(p);
			this.chat.sendMessageToPlayer(this.chat.getStaffModeOff(), p);
			this.settings.getPlayerDataConfig().set(p.getName()+".inStaffMode", Boolean.valueOf(false));
			this.settings.savePlayerDataConfig();
		}
		else {
			addStaffModePlayer(p);
			this.writeInventoryToConfig(p);
			
			p.getInventory().clear();
			
			p.getInventory().setItem(0, this.compassItem());
			p.getInventory().setItem(1, this.randomTeleportItem());
			p.getInventory().setItem(2, this.freezeItem());
			p.getInventory().setItem(3, this.inventoryItem());
			p.getInventory().setItem(4, this.enderchestItem());
			p.getInventory().setItem(5, this.informationsItem());
			p.getInventory().setItem(6, this.cpsItem());
			
			p.getInventory().setItem(8, this.turnStaffModeOffItem());
			this.chat.sendMessageToPlayer(this.chat.getStaffModeOn(), p);
			this.settings.getPlayerDataConfig().set(p.getName()+".inStaffMode", Boolean.valueOf(true));
			this.settings.savePlayerDataConfig();
		}
	}
	//Staff Mode Items
	public ItemStack compassItem()
	{
		ItemStack i = new ItemStack(Material.FEATHER);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getCompassItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(this.chat.setColorForMessage(this.chat.getCompassItemLore()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack randomTeleportItem()
	{
		ItemStack i = new ItemStack(Material.STICK, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getTeleportItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(this.chat.setColorForMessage(this.chat.getTeleportItemLore()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack freezeItem()
	{
		ItemStack i = new ItemStack(Material.PACKED_ICE, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getFreezeItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(this.chat.setColorForMessage(this.chat.getFreezeItemLore()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack inventoryItem()
	{
		ItemStack i = new ItemStack(Material.CHEST, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getInventoryItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(this.chat.setColorForMessage(this.chat.getInventoryItemLore()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack enderchestItem()
	{
		ItemStack i = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getEnderChestItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(this.chat.setColorForMessage(this.chat.getEnderChestItemLore()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack informationsItem()
	{
		ItemStack i = new ItemStack(Material.NAME_TAG, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getInformationsItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(this.chat.setColorForMessage(this.chat.getInformationsItemLore()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack cpsItem()
	{
		ItemStack i = new ItemStack(Material.TRIPWIRE_HOOK, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getCpsItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(this.chat.setColorForMessage(this.chat.getCpsItemLore()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack turnStaffModeOffItem()
	{
		ItemStack i = new ItemStack(Material.BARRIER, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getDisableItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public void writeInventoryToConfig(Player p)
	{
		ItemStack[] storageContents = p.getInventory().getStorageContents();
		for(int i = 0; i<storageContents.length;i++)
		{
			if(storageContents[i] != null){
				settings.getPlayerDataConfig().set(p.getName()+".inventory."+i, storageContents[i]);
			}
		}
		ItemStack[] armorContents = p.getInventory().getArmorContents();
		for(int i = 0; i<armorContents.length;i++)
		{
			if(armorContents[i] != null) {
				settings.getPlayerDataConfig().set(p.getName()+".armor."+i, armorContents[i]);
			}
		}
		settings.savePlayerDataConfig();
	}
	
	public void loadInventoryFromConfig(Player p)
	{
		p.getInventory().clear();
		if(settings.getPlayerDataConfig().getConfigurationSection(p.getName()+".inventory") != null)
		{
			Set<String> sec1 = settings.getPlayerDataConfig().getConfigurationSection(p.getName()+".inventory").getKeys(false);
			ItemStack[] contents = new ItemStack[36];
			for(String s: sec1)
			{
				ItemStack i = settings.getPlayerDataConfig().getItemStack(p.getName()+".inventory."+s);
				Integer it = Integer.parseInt(s);
				contents[it] = i;
				settings.getPlayerDataConfig().set(p.getName()+".inventory."+s, null);
			}
			p.getInventory().setContents(contents);
		}
		
		if(settings.getPlayerDataConfig().getConfigurationSection(p.getName()+".armor") != null)
		{
			Set<String> sec2 = settings.getPlayerDataConfig().getConfigurationSection(p.getName()+".armor").getKeys(false);
			ItemStack[] armor = new ItemStack[4];
			for(String s: sec2)
			{
				ItemStack i = settings.getPlayerDataConfig().getItemStack(p.getName()+".armor."+s);
				Integer it = Integer.parseInt(s);
				armor[it] = i;
				settings.getPlayerDataConfig().set(p.getName()+".armor."+s, null);
			}
			p.getInventory().setArmorContents(armor);
		}
		this.settings.savePlayerDataConfig();
	}
	
	
	//Methods allow to add the permission lore to the GUI items
	private boolean hasPermission(Player p, String perm) {
		return p.hasPermission(perm);
	}
	public String permissionLore(Player p, String perm) {
		return (this.hasPermission(p, perm)) ? "" : this.chat.setColorForMessage(this.chat.getNoPermissionLore());
	}

	//Methods that handle the time feature of the plugin
	public String getTimeMessageFromItem(ItemStack i)
	{
		String time = i.getItemMeta().getDisplayName();
		if(time.contains("12 AM"))
		{
			return "12 AM";
		}
		else if(time.contains("11 AM"))
		{
			return "11 AM";
		}
		else if(time.contains("1 AM"))
		{
			return "1 AM";
		}
		else if(time.contains("2 AM"))
		{
			return "2 AM";
		}
		else if(time.contains("3 AM"))
		{
			return "3 AM";
		}
		else if(time.contains("4 AM"))
		{
			return "4 AM";
		}
		else if(time.contains("5 AM"))
		{
			return "5 AM";
		}
		else if(time.contains("6 AM"))
		{
			return "6 AM";
		}
		else if(time.contains("7 AM"))
		{
			return "7 AM";
		}
		else if(time.contains("8 AM"))
		{
			return "8 AM";
		}
		else if(time.contains("9 AM"))
		{
			return "9 AM";
		}
		else if(time.contains("10 AM"))
		{
			return "10 AM";
		}
		else if(time.contains("12 PM"))
		{
			return "12 PM";
		}
		else if(time.contains("11 PM"))
		{
			return "11 PM";
		}
		else if(time.contains("1 PM"))
		{
			return "1 PM";
		}
		else if(time.contains("2 PM"))
		{
			return "2 PM";
		}
		else if(time.contains("3 PM"))
		{
			return "3 PM";
		}
		else if(time.contains("4 PM"))
		{
			return "4 PM";
		}
		else if(time.contains("5 PM"))
		{
			return "5 PM";
		}
		else if(time.contains("6 PM"))
		{
			return "6 PM";
		}
		else if(time.contains("7 PM"))
		{
			return "7 PM";
		}
		else if(time.contains("8 PM"))
		{
			return "8 PM";
		}
		else if(time.contains("9 PM"))
		{
			return "9 PM";
		}
		else if(time.contains("10 PM"))
		{
			return "10 PM";
		}
		else {
			return "12 PM";
		}
	}
	public long getTimeFromItem(ItemStack i)
	{
		String time = i.getItemMeta().getDisplayName();
		if(time.contains("12 AM"))
		{
			return 18000L;
		}
		else if(time.contains("11 AM"))
		{
			return 5000L;
		}
		else if(time.contains("1 AM"))
		{
			return 19000L;
		}
		else if(time.contains("2 AM"))
		{
			return 20000L;
		}
		else if(time.contains("3 AM"))
		{
			return 21000L;
		}
		else if(time.contains("4 AM"))
		{
			return 22000L;
		}
		else if(time.contains("5 AM"))
		{
			return 23000L;
		}
		else if(time.contains("6 AM"))
		{
			return 24000L;
		}
		else if(time.contains("7 AM"))
		{
			return 1000L;
		}
		else if(time.contains("8 AM"))
		{
			return 2000L;
		}
		else if(time.contains("9 AM"))
		{
			return 3000L;
		}
		else if(time.contains("10 AM"))
		{
			return 4000L;
		}
		else if(time.contains("12 PM"))
		{
			return 6000L;
		}
		else if(time.contains("11 PM"))
		{
			return 17000L;
		}
		else if(time.contains("1 PM"))
		{
			return 7000L;
		}
		else if(time.contains("2 PM"))
		{
			return 8000L;
		}
		else if(time.contains("3 PM"))
		{
			return 9000L;
		}
		else if(time.contains("4 PM"))
		{
			return 10000L;
		}
		else if(time.contains("5 PM"))
		{
			return 11000L;
		}
		else if(time.contains("6 PM"))
		{
			return 12000L;
		}
		else if(time.contains("7 PM"))
		{
			return 13000L;
		}
		else if(time.contains("8 PM"))
		{
			return 14000L;
		}
		else if(time.contains("9 PM"))
		{
			return 15000L;
		}
		else if(time.contains("10 PM"))
		{
			return 16000L;
		}
		else {
			return 6000L;
		}
	}
	//Methods that handle the status effect feauture of this plugin
	public PotionEffectType getPotionFromItemStack(ItemStack i) 
	{
		if(i.getType() == Material.SUGAR)
		{
			return PotionEffectType.SPEED;
		}
		if(i.getType() == Material.RABBIT_FOOT)
		{
			return PotionEffectType.JUMP;
		}
		if(i.getType() == Material.GHAST_TEAR)
		{
			return PotionEffectType.REGENERATION;
		}
		if(i.getType() == Material.DIAMOND_CHESTPLATE)
		{
			return PotionEffectType.DAMAGE_RESISTANCE;
		}
		if(i.getType() == Material.BLAZE_POWDER)
		{
			return PotionEffectType.FIRE_RESISTANCE;
		}
		if(i.getType() == Material.DIAMOND_SWORD)
		{
			return PotionEffectType.INCREASE_DAMAGE;
		}
		if(i.getType() == Material.GOLD_PICKAXE)
		{
			return PotionEffectType.FAST_DIGGING;
		}
		if(i.getType() == Material.ELYTRA)
		{
			return PotionEffectType.LEVITATION;
		}
		else {
			return PotionEffectType.ABSORPTION;
		}
	}
	public String getNameOfPotionFromItemStack(ItemStack i)
	{
		if(i.getType() == Material.SUGAR)
		{
			return "Speed";
		}
		if(i.getType() == Material.RABBIT_FOOT)
		{
			return "Jump Boost";
		}
		if(i.getType() == Material.GHAST_TEAR)
		{
			return "Regeneration";
		}
		if(i.getType() == Material.DIAMOND_CHESTPLATE)
		{
			return "Resistance";
		}
		if(i.getType() == Material.BLAZE_POWDER)
		{
			return "Fire Resistance";
		}
		if(i.getType() == Material.DIAMOND_SWORD)
		{
			return "Strenght";
		}
		if(i.getType() == Material.GOLD_PICKAXE)
		{
			return "Haste";
		}
		if(i.getType() == Material.ELYTRA)
		{
			return "Levitation";
		}
		else {
			return "Absorbtion";
		}
	}
	public EntityType getEntityFromItemName(ItemStack i)
	{
		String name = i.getItemMeta().getDisplayName();
		if(name.contains("Zombie Pigman"))
		{
			return EntityType.PIG_ZOMBIE;
		}
		if(name.contains("Cave Spider"))
		{
			return EntityType.CAVE_SPIDER;
		}
		if(name.contains("Zombie"))
		{
			return EntityType.ZOMBIE;
		}
		if(name.contains("Creeper"))
		{
			return EntityType.CREEPER;
		}
		if(name.contains("Spider"))
		{
			return EntityType.SPIDER;
		}
		if(name.contains("Skeleton"))
		{
			return EntityType.SKELETON;
		}
		if(name.contains("Slime"))
		{
			return EntityType.SLIME;
		}
		if(name.contains("Ghast"))
		{
			return EntityType.GHAST;
		}
		if(name.contains("Blaze"))
		{
			return EntityType.BLAZE;
		}
		if(name.contains("Silverfish"))
		{
			return EntityType.SILVERFISH;
		}
		if(name.contains("Enderman"))
		{
			return EntityType.ENDERMAN;
		}
		if(name.contains("Witch"))
		{
			return EntityType.WITCH;
		}
		if(name.contains("Bat"))
		{
			return EntityType.BAT;
		}
		if(name.contains("Magma Cube"))
		{
			return EntityType.MAGMA_CUBE;
		}
		if(name.contains("Cow"))
		{
			return EntityType.COW;
		}
		if(name.contains("Pig"))
		{
			return EntityType.PIG;
		}
		if(name.contains("Sheep"))
		{
			return EntityType.SHEEP;
		}
		if(name.contains("Chicken"))
		{
			return EntityType.CHICKEN;
		}
		if(name.contains("Wolf"))
		{
			return EntityType.WOLF;
		}
		if(name.contains("Ocelot"))
		{
			return EntityType.OCELOT;
		}
		if(name.contains("Rabbit"))
		{
			return EntityType.RABBIT;
		}
		if(name.contains("Horse"))
		{
			return EntityType.HORSE;
		}
		else {
			return EntityType.MUSHROOM_COW;
		}
	}
}