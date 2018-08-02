package me.mariozgr8.superstafftools.Handlers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.mariozgr8.superstafftools.MessageManager;
import me.mariozgr8.superstafftools.PermissionList;
import me.mariozgr8.superstafftools.SuperStaffTools;

public class InventoryHandler {
	private static InventoryHandler instance = new InventoryHandler();
	private InventoryHandler() { }
	
	public static InventoryHandler getSingleton() {
		return instance;
	}
	
	
	private SuperStaffTools ss = SuperStaffTools.getMainInstance();
	private MessageManager chat = ss.getMessages();
	private PermissionList perms = ss.getPerms();
	private PlayerHandler utils = PlayerHandler.getSingleton();
	//Inventory
	public Inventory getStaffGUI(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(null, 45, chat.getMainInvName());
		inv.setItem(0, gp());
		inv.setItem(1, gp());
		inv.setItem(2, gp());
		inv.setItem(3, gp());
		inv.setItem(4, gp());
		inv.setItem(5, gp());
		inv.setItem(6, gp());
		inv.setItem(7, gp());
		inv.setItem(8, gp());
		
		inv.setItem(9, gp());
		inv.setItem(10, this.flyItem(p));
		inv.setItem(11, this.godItem(p));
		inv.setItem(12, this.vanishItem(p));
		inv.setItem(13, this.staffModeItem(p));
		inv.setItem(14, this.healItem(p));
		inv.setItem(15, this.feedItem(p));
		inv.setItem(16, this.clearChatItem(p));
		inv.setItem(17, gp());
		
		inv.setItem(18, gp());
		inv.setItem(19, this.muteChatItem(p));
		inv.setItem(20, this.gamemodeItem(p));
		inv.setItem(21, this.difficultyItem(p));
		inv.setItem(22, this.weatherItem(p));
		inv.setItem(23, this.timeItem(p));	
		inv.setItem(24, this.whitelistItem(p));
		inv.setItem(25, this.effectItem(p));
		inv.setItem(26, gp());
	
		inv.setItem(27, gp());
		inv.setItem(28, this.spawnItem(p));
		inv.setItem(34, this.closeInvItem());
		inv.setItem(35, gp());
		
		inv.setItem(36, gp());
		inv.setItem(37, gp());
		inv.setItem(38, gp());
		inv.setItem(39, gp());
		inv.setItem(40, gp());
		inv.setItem(41, gp());
		inv.setItem(42, gp());
		inv.setItem(43, gp());
		inv.setItem(44, gp());
	
		return inv;
	}
	public Inventory gamemodeInv()
	{
		Inventory inv = Bukkit.getServer().createInventory(null, 9*1, this.chat.getGamemodeInvName());
		inv.setItem(0, this.survivalItem());
		inv.setItem(1, this.creativeItem());
		inv.setItem(2, this.adventureItem());
		inv.setItem(3, this.spectatorItem());
		inv.setItem(8, this.backItem());
		return inv;
	}
	public Inventory difficultyInv()
	{
		Inventory inv = Bukkit.getServer().createInventory(null, 9*1, this.chat.getDifficultyInvName());
		inv.setItem(0, this.peacefullItem());
		inv.setItem(1, this.easyItem());
		inv.setItem(2, this.normalItem());
		inv.setItem(3, this.hardItem());
		inv.setItem(8, this.backItem());
		return inv;
	}
	public Inventory weatherInv()
	{
		Inventory inv = Bukkit.getServer().createInventory(null, 9*1, this.chat.getWeatherInvName());
		inv.setItem(0, this.rainItem());
		inv.setItem(1, this.dryItem());
		inv.setItem(8, this.backItem());
		return inv;
	}
	public Inventory timeInv()
	{
		Inventory inv = Bukkit.getServer().createInventory(null, 9*3, this.chat.getTimeInvName());
		inv.setItem(0, this.setTimeItem("12 AM"));
		inv.setItem(1, this.setTimeItem("1 AM"));
		inv.setItem(2, this.setTimeItem("2 AM"));
		inv.setItem(3, this.setTimeItem("3 AM"));
		inv.setItem(4, this.setTimeItem("4 AM"));
		inv.setItem(5, this.setTimeItem("5 AM"));
		inv.setItem(6, this.setTimeItem("6 AM"));
		inv.setItem(7, this.setTimeItem("7 AM"));
		inv.setItem(8, this.setTimeItem("8 AM"));
		inv.setItem(9, this.setTimeItem("9 AM"));
		inv.setItem(10, this.setTimeItem("10 AM"));
		inv.setItem(11, this.setTimeItem("11 AM"));
		inv.setItem(12, this.setTimeItem("12 PM"));
		inv.setItem(13, this.setTimeItem("1 PM"));
		inv.setItem(14, this.setTimeItem("2 PM"));
		inv.setItem(15, this.setTimeItem("3 PM"));
		inv.setItem(16, this.setTimeItem("4 PM"));
		inv.setItem(17, this.setTimeItem("5 PM"));
		inv.setItem(18, this.setTimeItem("6 PM"));
		inv.setItem(19, this.setTimeItem("7 PM"));
		inv.setItem(20, this.setTimeItem("8 PM"));
		inv.setItem(21, this.setTimeItem("9 PM"));
		inv.setItem(22, this.setTimeItem("10 PM"));
		inv.setItem(23, this.setTimeItem("11 PM"));
		inv.setItem(26, this.backItem());
		return inv;
	}
	public Inventory effectInv()
	{
		Inventory inv = Bukkit.getServer().createInventory(null, 9*2, this.chat.getEffectInvName());
		inv.setItem(0, this.speedItem());
		inv.setItem(1, this.jumpBoostItem());
		inv.setItem(2, this.resistanceItem());
		inv.setItem(3, this.fireResistanceItem());
		inv.setItem(4, this.regenerationItem());
		inv.setItem(5, this.strenghItem());
		inv.setItem(6, this.hasteItem());
		inv.setItem(7, this.nightVisionItem());
		inv.setItem(8, this.levitationItem());
		inv.setItem(9, this.waterBreathingItem());
		inv.setItem(10, this.invisibilityItem());
		inv.setItem(11, this.glowingItem());
		inv.setItem(12, this.absorbtionItem());
		inv.setItem(13, this.healItem());
		inv.setItem(16, this.clearEffectsItem());
		inv.setItem(17, this.backItem());
		return inv;
	}
	public Inventory levelsEffectInv(String potion, Material m, String name, short md)
	{
		Inventory inv = Bukkit.getServer().createInventory(null, 9*5, this.chat.getLevelEffectInvName().replace("%potion%", potion));
		inv.setItem(0, this.gp());
		inv.setItem(1, this.gp());
		inv.setItem(2, this.gp());
		inv.setItem(3, this.gp());
		inv.setItem(4, this.gp());
		inv.setItem(5, this.gp());
		inv.setItem(6, this.gp());
		inv.setItem(7, this.gp());
		inv.setItem(8, this.gp());
		
		inv.setItem(9, this.gp());
		inv.setItem(13, this.potionItemBuilder(m, name, md));
		inv.setItem(17, this.gp());
		
		inv.setItem(18, this.gp());
	    inv.setItem(19, this.ButtonResetToZero());
	    inv.setItem(20, this.ButtonMinus10());
	    inv.setItem(21, this.ButtonMinus1());
	    inv.setItem(22, this.levelSelector(1));
	    inv.setItem(23, this.ButtonPlus1());
	    inv.setItem(24, this.ButtonPlus10());
	    inv.setItem(25, this.ButtonPlus100());
		inv.setItem(26, this.gp());
		
		inv.setItem(27, this.gp());
		inv.setItem(28, this.backItem());
		inv.setItem(34, this.applyEffectItem());
		inv.setItem(35, this.gp());
		
		inv.setItem(36, this.gp());
	    inv.setItem(37, this.gp());
	    inv.setItem(38, this.gp());
	    inv.setItem(39, this.gp());
	    inv.setItem(40, this.gp());
	    inv.setItem(41, this.gp());
	    inv.setItem(42, this.gp());
	    inv.setItem(43, this.gp());
	    inv.setItem(44, this.gp());
	    return inv;
	}
	public Inventory spawnInv()
	{
		Inventory spawnInv = Bukkit.createInventory(null, 9*4, this.chat.getSpawnInvName());
		spawnInv.setItem(0, this.mobSpawnItem("Zombie"));
		spawnInv.setItem(1, this.mobSpawnItem("Creeper"));
		spawnInv.setItem(2, this.mobSpawnItem("Spider"));
		spawnInv.setItem(3, this.mobSpawnItem("Skeleton"));
		spawnInv.setItem(4, this.mobSpawnItem("Slime"));
		spawnInv.setItem(5, this.mobSpawnItem("Ghast"));
		spawnInv.setItem(6, this.mobSpawnItem("Zombie Pigman"));
		spawnInv.setItem(7, this.mobSpawnItem("Cave Spider"));
		spawnInv.setItem(8, this.mobSpawnItem("Blaze"));
		spawnInv.setItem(9, this.mobSpawnItem("Silverfish"));
		spawnInv.setItem(10, this.mobSpawnItem("Enderman"));
		spawnInv.setItem(11, this.mobSpawnItem("Witch"));
		spawnInv.setItem(12, this.mobSpawnItem("Bat"));
		spawnInv.setItem(13, this.mobSpawnItem("Magma Cube"));
		spawnInv.setItem(14, this.mobSpawnItem("Cow"));
		spawnInv.setItem(15, this.mobSpawnItem("Pig"));
		spawnInv.setItem(16, this.mobSpawnItem("Sheep"));
		spawnInv.setItem(17, this.mobSpawnItem("Chicken"));
		spawnInv.setItem(18, this.mobSpawnItem("Wolf"));
		spawnInv.setItem(19, this.mobSpawnItem("Ocelot"));
		spawnInv.setItem(20, this.mobSpawnItem("Rabbit"));
		spawnInv.setItem(21, this.mobSpawnItem("Horse"));
		spawnInv.setItem(22, this.mobSpawnItem("Mushroom"));
		spawnInv.setItem(23, this.mobSpawnItem("Iron Golem"));
		spawnInv.setItem(24, this.mobSpawnItem("Snow Golem"));
		spawnInv.setItem(25, this.mobSpawnItem("Guardian"));
		spawnInv.setItem(26, this.mobSpawnItem("Giant Zombie"));
		spawnInv.setItem(27, this.mobSpawnItem("Wither"));
		spawnInv.setItem(35, this.backItem());
		return spawnInv;
		
		//Need to add Guardian, Elder Guardian, Giant Zombie, Wither, Ender Dragon, Iron Golem, Snow Golem, Wither Skeleton
	}
	//Items
	private ItemStack gp() {
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(" ");
		
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack backItem() {
		ItemStack i = new ItemStack(Material.PAPER, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getBackItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	
	//Main Inventory Items
	public ItemStack flyItem(Player p) {
		ItemStack i = new ItemStack(Material.FEATHER, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getFlyItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add((p.getAllowFlight()) ? this.chat.setColorForMessage(this.chat.getFlyItemLoreEnabled()) :this.chat.setColorForMessage(this.chat.getFlyItemLoreDisabled()));
		lore.add(this.utils.permissionLore(p, this.perms.getFlyItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		
		return i;
	}
	public ItemStack godItem(Player p) {
		ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getGodItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add((p.isInvulnerable()) ? this.chat.setColorForMessage(this.chat.getGodItemLoreEnabled()) : this.chat.setColorForMessage(this.chat.getGodItemLoreDisabled()));
		lore.add(this.utils.permissionLore(p, this.perms.getGodItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack vanishItem(Player p) {
		ItemStack i = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getVanishItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add((utils.isVanishedPlayer(p))?  this.chat.setColorForMessage(this.chat.getVanishItemLoreEnabled()) : this.chat.setColorForMessage(this.chat.getVanishItemLoreDisabled()));
		lore.add(this.utils.permissionLore(p, this.perms.getVanishItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack staffModeItem(Player p) {
		ItemStack i = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getStaffModeItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add((utils.isStaffModePlayer(p)) ? this.chat.setColorForMessage(this.chat.getStaffModeItemLoreEnabled()) : this.chat.setColorForMessage(this.chat.getStaffModeItemLoreDisabled()));
		lore.add(utils.permissionLore(p, this.perms.getStaffModeItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
		
	}
	public ItemStack healItem(Player p) {
		ItemStack i = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getHealItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(utils.permissionLore(p, this.perms.getHealItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack feedItem(Player p) {
		ItemStack i = new ItemStack(Material.COOKED_BEEF, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getFeedItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(utils.permissionLore(p, this.perms.getFeedItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack clearChatItem(Player p) {
		ItemStack i = new ItemStack(Material.PAPER, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getClearChatItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(utils.permissionLore(p, this.perms.getClearChatItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;	
	}
	public ItemStack muteChatItem(Player p) {
		ItemStack i = new ItemStack(Material.MAP, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getMuteChatItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(utils.permissionLore(p,  this.perms.getMuteChatItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;	
	}
	
	public ItemStack gamemodeItem(Player p)
	{
		ItemStack i = new ItemStack(Material.DIAMOND_BLOCK, 1);
		ItemMeta imeta  = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getGamemodeItemName()));
		
		ArrayList<String> lore = new ArrayList<String>(); 
		lore.add(utils.permissionLore(p, this.perms.getGamemodeItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack difficultyItem(Player p)
	{
		ItemStack i = new ItemStack(Material.SKULL_ITEM, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getDifficultyItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(utils.permissionLore(p, this.perms.getDifficultyItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack weatherItem(Player p)
	{
		ItemStack i = new ItemStack(Material.SPONGE, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getWeatherItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(utils.permissionLore(p, this.perms.getWeatherItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack timeItem(Player p)
	{
		ItemStack i = new ItemStack(Material.WATCH, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getTimeItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(utils.permissionLore(p, this.perms.getTimeItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack whitelistItem(Player p)
	{
		boolean check = Bukkit.getServer().hasWhitelist();
		
		ItemStack i = new ItemStack(Material.NAME_TAG, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getWhitelistItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(this.chat.setColorForMessage((check == true) ? this.chat.getWhitelistItemLoreEnabled(): this.chat.getWhitelistItemLoreDisabled()));
		lore.add(utils.permissionLore(p, this.perms.getWhitelistItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack effectItem(Player p)
	{
		ItemStack i = new ItemStack(Material.BEACON, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getEffectItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(utils.permissionLore(p, this.perms.getEffectItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack spawnItem(Player p)
	{
		ItemStack i = new ItemStack(Material.MOB_SPAWNER, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getSpawnItemName()));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(utils.permissionLore(p, this.perms.getSpawnItem()));
		
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack closeInvItem()
	{
		ItemStack i = new ItemStack(Material.REDSTONE_LAMP_OFF, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getCloseInvItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	//Gamemode Items
	public ItemStack survivalItem()
	{
		ItemStack i = new ItemStack(Material.STAINED_CLAY, 1, (short) 0);
		ItemMeta imeta  = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getGamemodeItemSurvivalName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack creativeItem()
	{
		ItemStack i = new ItemStack(Material.STAINED_CLAY, 1, (short) 8);
		ItemMeta imeta  = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getGamemodeItemCreativeName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack adventureItem()
	{
		ItemStack i = new ItemStack(Material.STAINED_CLAY, 1, (short) 7);
		ItemMeta imeta  = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getGamemodeItemAdventureName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack spectatorItem()
	{
		ItemStack i = new ItemStack(Material.STAINED_CLAY, 1, (short) 15);
		ItemMeta imeta  = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getGamemodeItemSpectatorName()));
		i.setItemMeta(imeta);
		return i;
	}
	
	//Difficulty Items
	public ItemStack peacefullItem()
	{
		ItemStack i = new ItemStack(Material.WOOL, 1, (short) 0);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getDifficultyItemPeacefullName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack easyItem()
	{
		ItemStack i = new ItemStack(Material.WOOL, 1, (short) 8);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getDifficultyItemEasyName()));
		i.setItemMeta(imeta);
		return i;
	}
	
	public ItemStack normalItem()
	{
		ItemStack i = new ItemStack(Material.WOOL, 1, (short) 7);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getDifficultyItemNormalName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack hardItem()
	{
		ItemStack i = new ItemStack(Material.WOOL, 1, (short) 15);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getDifficultyItemHardName()));
		i.setItemMeta(imeta);
		return i;
	}
	
	//Weather Items
	public ItemStack rainItem()
	{
		ItemStack i = new ItemStack(Material.WATER_LILY, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getWeatherRainItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack dryItem()
	{
		ItemStack i = new ItemStack(Material.DEAD_BUSH, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getWeatherDryItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	//Time Items
	public ItemStack setTimeItem(String time)
	{
		ItemStack i = new ItemStack(Material.DOUBLE_PLANT, 1, (short) 0);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getSetTimeItemName().replace("%time%", time)));
		i.setItemMeta(imeta);
		return i;
	}
	//Effects Items
	public ItemStack speedItem()
	{
		ItemStack i = new ItemStack(Material.SUGAR, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getSpeedItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack jumpBoostItem()
	{
		ItemStack i = new ItemStack(Material.RABBIT_FOOT, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getJumpBoostItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack hasteItem()
	{
		ItemStack i = new ItemStack(Material.GOLD_PICKAXE, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getHasteItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack strenghItem()
	{
		ItemStack i = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getStrenghtItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack fireResistanceItem()
	{
		ItemStack i = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getSpeedItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack resistanceItem()
	{
		ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getResistanceItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack regenerationItem()
	{
		ItemStack i = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getRegenerationItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack nightVisionItem()
	{
		ItemStack i = new ItemStack(Material.GOLDEN_CARROT, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getNightVisionItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack invisibilityItem()
	{
		ItemStack i = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getInvisibilityItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack waterBreathingItem()
	{
		ItemStack i = new ItemStack(Material.RAW_FISH, 1, (short) 3);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getWaterBreathingItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack levitationItem()
	{
		ItemStack i = new ItemStack(Material.ELYTRA, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getLevitationItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack glowingItem()
	{
		ItemStack i = new ItemStack(Material.GLOWSTONE_DUST, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getGlowingItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack absorbtionItem()
	{
		ItemStack i = new ItemStack(Material.SHIELD, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getAbsorbtionItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack healItem()
	{
		ItemStack i = new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getInstantHeaItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack clearEffectsItem()
	{
		ItemStack i = new ItemStack(Material.MILK_BUCKET, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage(this.chat.getClearEffectsItemName()));
		i.setItemMeta(imeta);
		return i;
	}
	
	public ItemStack potionItemBuilder(Material m, String displayName, short materialData) {
		ItemStack i = new ItemStack(m, 1, materialData);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(displayName);
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack ButtonPlus1()
	{
	    ItemStack i = new ItemStack(Material.STONE_BUTTON);
	    ItemMeta imeta = i.getItemMeta();
	    imeta.setDisplayName("Level +1");
	    i.setItemMeta(imeta);
	    return i;
	}  
    public ItemStack ButtonPlus10()
    {
      ItemStack i = new ItemStack(Material.STONE_BUTTON);
      ItemMeta imeta = i.getItemMeta();
      imeta.setDisplayName("Level +10");
      i.setItemMeta(imeta);
      return i;
    }
    public ItemStack ButtonPlus100()
    {
      ItemStack i = new ItemStack(Material.STONE_BUTTON);
      ItemMeta imeta = i.getItemMeta();
      imeta.setDisplayName("Level +100");
      i.setItemMeta(imeta);
      return i;
    } 
    public ItemStack ButtonMinus1()
    {
      ItemStack i = new ItemStack(Material.WOOD_BUTTON);
      ItemMeta imeta = i.getItemMeta();
      imeta.setDisplayName("Level -1");
      i.setItemMeta(imeta);
      return i;
    }
    public ItemStack ButtonMinus10()
    {
      ItemStack i = new ItemStack(Material.WOOD_BUTTON);
      ItemMeta imeta = i.getItemMeta();
      imeta.setDisplayName("Level -10");
      i.setItemMeta(imeta);
      return i;
    }
  
    public ItemStack ButtonResetToZero()
    {
      ItemStack i = new ItemStack(Material.WOOD_BUTTON);
      ItemMeta imeta = i.getItemMeta();
      imeta.setDisplayName("Reset level to 1");
      i.setItemMeta(imeta);
      return i;
    }
    public ItemStack levelSelector(int level)
    {
      ItemStack i = new ItemStack(Material.NAME_TAG);
      ItemMeta imeta = i.getItemMeta();
      imeta.setDisplayName("Level: " + level);
      i.setItemMeta(imeta);
      return i;
    }
    public ItemStack applyEffectItem()
    {
      ItemStack i = new ItemStack(Material.WOOL, 1, (short)5);
      ItemMeta imeta = i.getItemMeta();
      imeta.setDisplayName("Confirm");
      i.setItemMeta(imeta);
      return i;
    }
    public ItemStack mobSpawnItem(String mob)
	{
		ItemStack i = new ItemStack(Material.MOB_SPAWNER, 1);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(this.chat.setColorForMessage("&6Spawn &9%mob%").replace("%mob%", mob));
		i.setItemMeta(imeta);
		return i;
	}

}
